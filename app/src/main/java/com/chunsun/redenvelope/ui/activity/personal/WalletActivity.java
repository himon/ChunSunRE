package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.BalancePresenter;
import com.chunsun.redenvelope.ui.adapter.BalanceAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IBalanceView;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 钱包Activity
 */
public class WalletActivity extends BaseActivity implements IBalanceView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    TextView mTvCumulativeGain;
    TextView mTvInviteCumulativeGain;
    TextView mTvBalance;
    Button mBtnRecharge;
    Button mBtnWithdrawCash;


    private BalanceAdapter mAdapter;
    private BalancePresenter mPresenter;
    private List<SampleEntity> mList = new ArrayList<SampleEntity>();
    private String mToken;
    private BalanceEntity.ResultEntity mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        mPresenter = new BalancePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_wallet_top_item, null);
        mTvCumulativeGain = (TextView) view.findViewById(R.id.tv_cumulative_gain);
        mTvInviteCumulativeGain = (TextView) view.findViewById(R.id.tv_invite_cumulative_gain);
        mTvBalance = (TextView) view.findViewById(R.id.tv_balance);
        mBtnRecharge = (Button) view.findViewById(R.id.btn_recharge);
        mBtnWithdrawCash = (Button) view.findViewById(R.id.btn_withdraw_cash);


        //设置listView headerView不可点击
        mListView.addHeaderView(view, null, false);
        mAdapter = new BalanceAdapter(this, mList, R.layout.adapter_balance);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SampleEntity entity = (SampleEntity) parent.getAdapter().getItem(position);
                toBalanceDetail(entity);
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
        initTitleBar("钱包", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
        mBtnWithdrawCash.setOnClickListener(this);
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
    public void setData(BalanceEntity.ResultEntity result, List<SampleEntity> list) {
        mResult = result;
        mTvCumulativeGain.setText(result.getCumulative_gain());
        mTvInviteCumulativeGain.setText(result.getInvite_cumulative_gain());
        mTvBalance.setText(result.getAmount());
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mPtr.refreshComplete();
    }

    @Override
    public void toBalanceDetail(SampleEntity entity) {
        Intent intent = new Intent(this, BalanceDetailListActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, entity.getCount());
        intent.putExtra(Constants.EXTRA_KEY2, entity.getKey());
        startActivity(intent);
    }

    @Override
    public void toBalanceRecharge() {
        Intent intent = new Intent(this, BalanceRechargeActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mResult.getEnable_unionpay());
        startActivity(intent);
    }

    @Override
    public void toWithdrawCash() {
        Intent intent = new Intent(this, WithdrawCashActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mResult.getCz_poundage());
        intent.putExtra(Constants.EXTRA_KEY2, mResult.getZfb_poundage());
        intent.putExtra(Constants.EXTRA_KEY3, mResult.getCash_poundage_rate());
        intent.putExtra(Constants.EXTRA_KEY4, mResult.getAmount());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_recharge:
                toBalanceRecharge();
                break;
            case R.id.btn_withdraw_cash:
                toWithdrawCash();
                break;
        }
    }
}
