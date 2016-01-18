package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.SendRedEnvelopeRecordListPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordListView;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.swipe.SwipeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 圈子记录
 */
public class MyCircleListActivity extends BaseActivity implements ISendRedEnvelopeRecordListView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private SendRedEnvelopeRecordListPresenter mPresenter;
    /**
     * 可删除Item的adapter
     */
    private SwipeListAdapter mSwipeListAdapter;
    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> mList = new ArrayList<RedDetailSendRecordListEntity.ResultEntity.LogsEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;
    private String mToken;
    private List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> mLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle_list);
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

        mSwipeListAdapter = new SwipeListAdapter(this, mList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer p = (Integer) v.getTag();
                int id = v.getId();
                if (id == R.id.bt_delete) {
                    mPresenter.delRecord(mToken, p);
                }
            }
        }, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                String id = tag.toString();
                toClassifyListDetail(id);
            }
        });
        mListView.setAdapter(mSwipeListAdapter);

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
        initTitleBar("我的圈子", "", "", Constants.TITLE_TYPE_SAMPLE);
    }

    @Override
    protected void click(View v) {

    }

    public void getData() {
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
        }
        mPresenter.loadData(mToken, "circle_count", mCurrentPage);
    }

    @Override
    public void setData(RedDetailSendRecordListEntity.ResultEntity result) {
        List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> list = result.getLogs();
        mTotal = Integer.valueOf(result.getTotal_count());
        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }
        mCurrentPage++;
        mList.addAll(list);
        mSwipeListAdapter.notifyDataSetChanged();
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void toClassifyListDetail(String id) {
        Intent intent = new Intent(this, MyCircleListDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        startActivity(intent);
    }

    @Override
    public void delSuccess() {
        mCurrentPage = 1;
        mPtr.autoRefresh();
    }
}
