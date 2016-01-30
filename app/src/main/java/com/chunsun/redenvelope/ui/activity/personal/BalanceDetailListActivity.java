package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.BalanceListEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.BalanceDetailListPresenter;
import com.chunsun.redenvelope.ui.adapter.BalanceDetailListAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IBalanceDetailListView;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 账户明细列表
 */
public class BalanceDetailListActivity extends BaseActivity implements IBalanceDetailListView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private BalanceDetailListPresenter mPresenter;
    private BalanceDetailListAdapter mAdapter;
    //当前显示页数
    private int mCurrentPage = 1;
    private List<BalanceListEntity.ResultEntity.LogsEntity> mList = new ArrayList<BalanceListEntity.ResultEntity.LogsEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;
    private String mToken;

    private String mClassify;
    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_detail);
        ButterKnife.bind(this);
        mPresenter = new BalanceDetailListPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mAdapter = new BalanceDetailListAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //RedDetailSendRecordListEntity.ResultEntity.LogsEntity entity = (RedDetailSendRecordListEntity.ResultEntity.LogsEntity) parent.getAdapter().getItem(position);
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
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mType = intent.getStringExtra(Constants.EXTRA_KEY);
            mClassify = intent.getStringExtra(Constants.EXTRA_KEY2);
        }
        initTitleBar(mClassify, "", "", Constants.TITLE_TYPE_SAMPLE);
    }

    public void getData() {
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
        }
        mPresenter.loadData(mToken, mType, mCurrentPage);
    }

    @Override
    protected void click(View v) {
    }

    @Override
    public void setData(BalanceListEntity.ResultEntity response) {

        List<BalanceListEntity.ResultEntity.LogsEntity> list = response.getLogs();

        mTotal = Integer.parseInt(response.getTotal_count());

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }

        mCurrentPage++;

        mList.addAll(list);

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }


}
