package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.HomeFragmentPresenter;
import com.chunsun.redenvelope.ui.adapter.RedListAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IHomeFragmentView;
import com.chunsun.redenvelope.utils.DensityUtils;
import com.chunsun.redenvelope.utils.helper.RedEvenlopeListHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.AdImageAdapter;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class TaskListActivity extends BaseActivity implements IHomeFragmentView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private GuideGallery mViewPager;
    private HomeFragmentPresenter mPresenter;
    private RedListAdapter mAdapter;
    private AdImageAdapter imageAdapter;

    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedListDetailEntity.ResultEntity.PoolEntity> mList = new ArrayList<RedListDetailEntity.ResultEntity.PoolEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;
    //选中的Item的详情
    private RedListDetailEntity.ResultEntity.PoolEntity mEntity;
    /**
     * 滚动广告类型
     */
    private String mScrollAdType;
    /**
     * 列表显示广告类型
     */
    private String mShowAdType;
    /**
     * 帮助类
     */
    RedEvenlopeListHelper mRedEvenlopeListHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(this);
        mPresenter = new HomeFragmentPresenter(this);
        mRedEvenlopeListHelper = new RedEvenlopeListHelper(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("任务", "", "", Constants.TITLE_TYPE_SAMPLE);
        /**
         * 轮播图
         */
        int density = (int) (120 * DensityUtils.getDensity(this));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, density);
        mViewPager = new GuideGallery(this);
        mViewPager.setLayoutParams(params);

        /**
         * List列表
         */
        mListView.addHeaderView(mViewPager);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mAdapter = new RedListAdapter(this, mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TextUtils.isEmpty(new Preferences(TaskListActivity.this).getToken()) && !Constants.SCROLL_AD_TYPE.equals(mScrollAdType)) {
                    toLogin();
                    return;
                }
                showLoading();
                mEntity = (RedListDetailEntity.ResultEntity.PoolEntity) parent.getAdapter().getItem(position);
                //如果是转发
                if (Constants.RED_DETAIL_TYPE_REPEAT == mEntity.getType()) {
                    toRepeatRedDetail(mEntity.getId());
                } else {
                    mPresenter.grabRedEnvelope(new Preferences(TaskListActivity.this).getToken(), mEntity.getId());
                }
            }
        });

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mScrollAdType = intent.getStringExtra(Constants.EXTRA_KEY);
        if (Constants.RED_AD_TYPE.equals(mScrollAdType)) {
            mShowAdType = Constants.RED_DETAIL_TYLE_SAMPLE;
        } else if (Constants.TASK_AD_TYPE.equals(mScrollAdType)) {
            mShowAdType = Constants.RED_DETAIL_TYPE_REPEAT + "";
        }
    }

    public void getData() {
        if (mCurrentPage * Constants.PAGE_NUM > mTotal + Constants.PAGE_NUM) {
            mPtr.refreshComplete();
            return;
        }
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
            mPresenter.getAdData(mScrollAdType);
        }
        mPresenter.loadData(new Preferences(this).getToken(), mShowAdType, mCurrentPage);
    }

    @Override
    public void setData(RedListDetailEntity.ResultEntity entity) {
        List<RedListDetailEntity.ResultEntity.PoolEntity> list = entity.getPool();

        mTotal = Integer.parseInt(entity.getTotal_count());

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }

        mCurrentPage++;

        mList.addAll(list);

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }

    @Override
    public void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert) {
        imageAdapter = new AdImageAdapter(advert, this);
        mViewPager.setSize(advert.size());
        mViewPager.setAdapter(imageAdapter);
        mViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedAutoAdEntity.ResultEntity.AdvertEntity entity = (RedAutoAdEntity.ResultEntity.AdvertEntity) parent.getAdapter().getItem(position);
                toAdWebView(entity.getTitle(), entity.getTarget_url());
            }
        });
        mViewPager.startAutoScroll();
    }

    @Override
    public void toRedDetail(String id) {
        mRedEvenlopeListHelper.toRedDetail(id);
    }

    @Override
    public void toWebRedDetail(String id) {
        mRedEvenlopeListHelper.toWebRedDetail(id, mEntity.getType());
    }

    @Override
    public void toRepeatRedDetail(String id) {
        hideLoading();
        mRedEvenlopeListHelper.toRepeatRedDetail(id);
    }

    @Override
    public void toAdWebView(String title, String url) {
        mRedEvenlopeListHelper.toAdWebView(title, url);
    }

    @Override
    public void toForwardRedDetail(String id) {
        mRedEvenlopeListHelper.toForwardRedDetail(id);
    }

    @Override
    public void gradRedEnvelopeSuccess(String id) {
        hideLoading();
        if (mEntity != null) {
            switch (mEntity.getType()) {
                case 1:
                case 2:
                case 3:
                    toRedDetail(id);
                    break;
                case 4:
                    toWebRedDetail(id);
                    break;
                case 5:
                    toRepeatRedDetail(mEntity.getId());
                    break;
                case 6:
                    toForwardRedDetail(id);
                    break;
            }

        }
    }

    @Override
    public void toLogin() {
        mRedEvenlopeListHelper.toLogin();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
        }
    }
}
