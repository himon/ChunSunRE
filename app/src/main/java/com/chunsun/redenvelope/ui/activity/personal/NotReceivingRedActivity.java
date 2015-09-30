package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.NotReceivingRedPresenter;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.adapter.NotReceivingAndCollectRedListAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.INotReceivingRedView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 未领取红包列表Activity
 */
public class NotReceivingRedActivity extends BaseActivity implements INotReceivingRedView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    ListView mListView;

    private NotReceivingAndCollectRedListAdapter mAdapter;
    private NotReceivingRedPresenter mPresenter;
    private List<RedDetailUnReceiveAndCollectEntity.ResultEntity> mList = new ArrayList<RedDetailUnReceiveAndCollectEntity.ResultEntity>();
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_receiving_red);
        ButterKnife.bind(this);
        mPresenter = new NotReceivingRedPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        mAdapter = new NotReceivingAndCollectRedListAdapter(this, mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedDetailUnReceiveAndCollectEntity.ResultEntity entity = (RedDetailUnReceiveAndCollectEntity.ResultEntity) parent.getAdapter().getItem(position);
                mPresenter.grabRedEnvelope(mToken, entity);
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
        initTitleBar("未领取红包", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
    }

    public void getData() {
        mList.clear();
        mPresenter.loadData(mToken);
    }

    @Override
    public void setData(List<RedDetailUnReceiveAndCollectEntity.ResultEntity> result) {
        mList.addAll(result);
        mAdapter.notifyDataSetChanged();
        mPtr.refreshComplete();
    }

    @Override
    public void grabRedEnvelopeSuccess(RedDetailUnReceiveAndCollectEntity.ResultEntity entity) {
        if (Constants.RED_DETAIL_TYPE_LINK == entity.getType()) {
            toWebRedDetail(entity.getId() + "");
        } else {
            toRedDetail(entity.getId() + "");
        }
    }

    @Override
    public void toWebRedDetail(String id) {
        Intent intent = new Intent(this, WebRedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        startActivity(intent);
    }

    @Override
    public void toRedDetail(String id) {
        Intent intent = new Intent(this, RedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        startActivity(intent);
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
