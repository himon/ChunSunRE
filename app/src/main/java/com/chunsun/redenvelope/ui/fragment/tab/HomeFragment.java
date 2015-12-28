package com.chunsun.redenvelope.ui.fragment.tab;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.HomeFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.adapter.RedListAdapter;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.base.view.LoadingView;
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

/**
 * 广告列表Fragment
 */
public class HomeFragment extends BaseFragment implements IHomeFragmentView, LoadingView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.iv_to_top)
    ImageView mIvTop;

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
    private int mShowAdType;
    /**
     * 排序方式
     */
    private int mOrderType = Constants.CIRCLE_ORDER_TYPE_FLASHBACK;
    /**
     * 帮助类
     */
    RedEvenlopeListHelper mRedEvenlopeListHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new HomeFragmentPresenter(this);
        mRedEvenlopeListHelper = new RedEvenlopeListHelper(getActivity());
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        Bundle bundle = getArguments();
        mScrollAdType = bundle.getString(Constants.EXTRA_KEY);
        if (Constants.RED_AD_TYPE.equals(mScrollAdType)) {
            mShowAdType = Constants.RED_DETAIL_TYLE_SAMPLE;
        } else if (Constants.TASK_AD_TYPE.equals(mScrollAdType)) {
            mShowAdType = Constants.RED_DETAIL_TYPE_REPEAT;
        } else if (Constants.SCROLL_AD_TYPE.equals(mScrollAdType)) {
            mShowAdType = Constants.RED_DETAIL_TYPE_CIRCLE;
        }

        /**
         * 轮播图
         */
        int density = (int) (120 * DensityUtils.getDensity(getActivity()));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, density);
        mViewPager = new GuideGallery(getActivity());
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

        mAdapter = new RedListAdapter(getActivity(), mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TextUtils.isEmpty(new Preferences(getActivity()).getToken()) && !Constants.SCROLL_AD_TYPE.equals(mScrollAdType)) {
                    toLogin();
                    return;
                }
                showLoading();
                mEntity = (RedListDetailEntity.ResultEntity.PoolEntity) parent.getAdapter().getItem(position);
                if (Constants.RED_DETAIL_TYPE_REPEAT == mEntity.getType()) {//转发
                    toRepeatRedDetail(mEntity.getId());
                } else if (Constants.RED_DETAIL_TYPE_CIRCLE == mEntity.getType()) {//圈子
                    toRedDetail(mEntity.getId());
                } else if (Constants.RED_DETAIL_TYPE_CIRCLE_LINK == mEntity.getType()) {//链接圈子
                    toWebRedDetail(mEntity.getId());
                } else {
                    mPresenter.grabRedEnvelope(new Preferences(getActivity()).getToken(), mEntity.getId());
                }
            }
        });

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {

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

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mListView.doOnScrollStateChanged(view, scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mListView.doOnScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                if ((firstVisibleItem + visibleItemCount) - visibleItemCount >= 3) {
                    mIvTop.setVisibility(View.VISIBLE);
                } else {
                    mIvTop.setVisibility(View.GONE);
                }
            }
        });

        initEvent();
    }

    private void initEvent() {
        mIvTop.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    public void getData() {

        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
            mPresenter.getAdData(mScrollAdType);
        }

        mPresenter.loadData(new Preferences(getActivity()).getToken(), mShowAdType, mCurrentPage, mOrderType, "");
    }

    @Override
    public void setData(RedListDetailEntity.ResultEntity entity) {
        List<RedListDetailEntity.ResultEntity.PoolEntity> list = entity.getPool();

        mTotal = Integer.parseInt(entity.getTotal_count());

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        } else {
            mListView.setHasMore();
        }

        mCurrentPage++;

        mList.addAll(list);

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }

    @Override
    public void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert) {
        imageAdapter = new AdImageAdapter(advert, getActivity());
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
        hideLoading();
        mRedEvenlopeListHelper.toRedDetail(id, mShowAdType);
    }

    @Override
    public void toWebRedDetail(String id) {
        hideLoading();
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
        ((MainActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideLoading();
    }

    /**
     * 按排序刷新
     *
     * @param order
     */
    public void orderRefresh(int order) {
        showLoading();
        switch (order) {
            case 0:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_FLASHBACK;
                break;
            case 1:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_HOT;
                break;
            case 2:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_ESSENCE;
                break;
            case 3:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_RANDOM;
                break;
            case 4:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_LOCAL;
                break;
            case 5:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_MY_CIRCLE;
                break;
            case 6:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_COLLECTION;
                break;
            case 7:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_RANGE;
                break;
        }
        mList.clear();
        mCurrentPage = 1;
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_to_top:
                mListView.setSelection(0);
                break;
        }
    }
}
