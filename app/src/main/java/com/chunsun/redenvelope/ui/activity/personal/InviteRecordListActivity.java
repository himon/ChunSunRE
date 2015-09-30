package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.presenter.InviteRecordListPresenter;
import com.chunsun.redenvelope.ui.adapter.InviteRecordListAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IInviteRecordListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class InviteRecordListActivity extends BaseActivity implements IInviteRecordListView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    ListView mListView;

    private InviteRecordListPresenter mPresenter;
    private InviteRecordEntity.ResultEntity mEntity;
    private InviteRecordListAdapter mAdapter;
    private List<InviteRecordEntity.ResultEntity.BaseEntity> mList = new ArrayList<InviteRecordEntity.ResultEntity.BaseEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_record_list);
        ButterKnife.bind(this);
        mPresenter = new InviteRecordListPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        mAdapter = new InviteRecordListAdapter(this, mList);
        mListView.setAdapter(mAdapter);

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        initTitleBar("邀请返现记录", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
        }
    }

    public void getData() {
        mList.clear();
        mPresenter.initData(mEntity);
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

    @Override
    public void setData(List<InviteRecordEntity.ResultEntity.BaseEntity> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mPtr.refreshComplete();
    }
}
