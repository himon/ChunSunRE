package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.AtMessageEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.AtMePresenter;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.adapter.AtMeAdapter;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IAtMeView;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class AtMeActivity extends MBaseActivity<IAtMeView, AtMePresenter> implements IAtMeView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private AtMePresenter mPresenter;
    private AtMeAdapter mAdapter;
    //是否是下拉刷新
    private boolean isRefresh;
    //当前显示页数
    private int mCurrentPage = 1;
    private String mToken;
    private List<AtMessageEntity.ResultEntity> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_me);
        ButterKnife.bind(this);
        mPresenter = (AtMePresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new AtMePresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("与我相关", "", "", Constants.TITLE_TYPE_SAMPLE);

        /**
         * List列表
         */
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mAdapter = new AtMeAdapter(this, mList, R.layout.adapter_at_me);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AtMessageEntity.ResultEntity entity = (AtMessageEntity.ResultEntity) parent.getAdapter().getItem(position);
                toJump(entity.getHbType(), entity.getHbId(), entity.getCity());
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
    }

    /**
     * 跳转详情
     */
    private void toJump(int type, String id, String city) {
        Intent intent = null;
        if (0 == type) {//圈子
            if ("全国".equals(city)) {
                intent = new Intent(this, InteractivePlatformActivity.class);
            } else {
                intent = new Intent(this, InteractivePlatformActivity.class);
                intent.putExtra(Constants.EXTRA_KEY, "city");
            }
        } else {
            if (type == Constants.RED_DETAIL_TYPE_LINK || type == Constants.RED_DETAIL_TYPE_CIRCLE_LINK || type == Constants.RED_DETAIL_TYPE_lUCK_LINK) {//链接
                intent = new Intent(this, WebRedDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY, id);
                intent.putExtra(Constants.EXTRA_KEY2, type);
            } else if (type == Constants.RED_DETAIL_TYPE_REPEAT) {//转发
                intent = new Intent(this, RepeatRedDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY, id);
            } else{
                intent = new Intent(this, RedDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY, id);
                intent.putExtra(Constants.EXTRA_KEY2, type);
            }
        }
        startActivity(intent);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
    }

    public void getData() {
        if (isRefresh) {
            mCurrentPage = 1;
        }
        mPresenter.getUserNoReadMessage(mToken, mCurrentPage);
    }

    @Override
    protected void click(View v) {

    }

    @Override
    public void setData(List<AtMessageEntity.ResultEntity> list) {

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }

        mCurrentPage++;

        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(list);

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }
}
