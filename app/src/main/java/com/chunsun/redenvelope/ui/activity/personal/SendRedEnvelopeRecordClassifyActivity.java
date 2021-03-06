package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.SendRedEnvelopeRecordClassifyPresenter;
import com.chunsun.redenvelope.ui.adapter.SendRedEnvelopeRecordClassifyAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordClassifyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 发广告记录Activity
 */
public class SendRedEnvelopeRecordClassifyActivity extends BaseActivity implements ISendRedEnvelopeRecordClassifyView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    ListView mListView;

    private SendRedEnvelopeRecordClassifyAdapter mAdapter;
    private SendRedEnvelopeRecordClassifyPresenter mPresenter;
    private List<SampleEntity> mList = new ArrayList<SampleEntity>();
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_red_envelope_record_classify);
        ButterKnife.bind(this);
        mPresenter = new SendRedEnvelopeRecordClassifyPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        mAdapter = new SendRedEnvelopeRecordClassifyAdapter(this, mList, R.layout.adapter_me_fragment);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SampleEntity entity = (SampleEntity) parent.getAdapter().getItem(position);
                toChildListActivity(entity);
            }
        });

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

        //设置titleBar
        initTitleBar("发广告记录", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
    }

    @Override
    protected void click(View v) {

    }

    public void getData() {
        mList.clear();
        mPresenter.loadData(mToken);
    }

    @Override
    public void setData(List<SampleEntity> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mPtr.refreshComplete();
    }

    @Override
    public void toChildListActivity(SampleEntity entity) {
        Intent intent = new Intent(SendRedEnvelopeRecordClassifyActivity.this, SendRedEnvelopeRecordListActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, entity);
        startActivity(intent);
    }
}
