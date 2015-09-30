package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.SendRedEnvelopeRecordListPresenter;
import com.chunsun.redenvelope.ui.adapter.SendRedEnvelopeRecordListAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordListView;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 发广告记录列表Activity
 */
public class SendRedEnvelopeRecordListActivity extends BaseActivity implements ISendRedEnvelopeRecordListView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private SendRedEnvelopeRecordListPresenter mPresenter;
    private SendRedEnvelopeRecordListAdapter mAdapter;
    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> mList = new ArrayList<RedDetailSendRecordListEntity.ResultEntity.LogsEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;
    private String mToken;
    //红包状态
    private SampleEntity mClassify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_red_envelope_record_list);
        ButterKnife.bind(this);
        mPresenter = new SendRedEnvelopeRecordListPresenter(this);
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

        mAdapter = new SendRedEnvelopeRecordListAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedDetailSendRecordListEntity.ResultEntity.LogsEntity entity = (RedDetailSendRecordListEntity.ResultEntity.LogsEntity) parent.getAdapter().getItem(position);
                toClassifyListDetail(entity.getId() + "");
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
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mClassify = intent.getParcelableExtra(Constants.EXTRA_KEY);
        }
        initTitleBar(mClassify.getValue() + "广告", "", "", Constants.TITLE_TYPE_SAMPLE);
        mAdapter.setClassifyType(mClassify.getKey());
    }

    public void getData() {
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
        }
        mPresenter.loadData(mToken, mClassify.getKey(), mCurrentPage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
        }
    }

    @Override
    public void setData(RedDetailSendRecordListEntity.ResultEntity result) {
        List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> list = result.getLogs();

        mTotal = Integer.parseInt(result.getTotal_count());

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
    public void toClassifyListDetail(String id) {
        Intent intent = new Intent(this, SendRedEnvelopeRecordDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        intent.putExtra(Constants.EXTRA_KEY2, mClassify.getKey());
        intent.putExtra(Constants.EXTRA_KEY3, mClassify.getValue());
        startActivity(intent);
    }
}
