package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CollectRedEnvelopeListPresenter;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.adapter.NotReceivingAndCollectRedListAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICollectRedEnvelopeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 收藏Activity
 */
public class CollectRedEnvelopeListActivity extends BaseActivity implements ICollectRedEnvelopeListView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    ListView mListView;

    private NotReceivingAndCollectRedListAdapter mAdapter;
    private CollectRedEnvelopeListPresenter mPresenter;
    private List<RedDetailUnReceiveAndCollectEntity.ResultEntity> mList = new ArrayList<RedDetailUnReceiveAndCollectEntity.ResultEntity>();
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_red_envelope_list);
        ButterKnife.bind(this);
        mPresenter = new CollectRedEnvelopeListPresenter(this);
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
                toRedDetail(entity.getId() + "", entity.getType());
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
        initTitleBar("收藏", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
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
    public void toRedDetail(String id, int type) {
        if (Constants.RED_DETAIL_TYPE_LINK == type || Constants.RED_DETAIL_TYPE_CIRCLE_LINK == type || Constants.RED_DETAIL_TYPE_lUCK_LINK == type) {
            Intent intent = new Intent(this, WebRedDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY, id);
            intent.putExtra(Constants.EXTRA_KEY2, type);
            startActivity(intent);
        }else if(Constants.RED_DETAIL_TYPE_REPEAT == type){
            Intent intent = new Intent(this, RepeatRedDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY, id);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, RedDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY, id);
            startActivity(intent);
        }
    }

    @Override
    protected void click(View v) {

    }
}
