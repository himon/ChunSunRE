package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.BalancePresenter;
import com.chunsun.redenvelope.ui.adapter.BalanceAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IBalanceView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 余额Activity
 */
public class BalanceActivity extends BaseActivity implements IBalanceView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.lv_main)
    ListView mListView;

    private TextView mTvTotalBalance;
    private TextView mTvRecharge;
    private RelativeLayout mRlBank;
    private RelativeLayout mRlAlipay;
    private RelativeLayout mRlTelephoneFare;

    private BalanceAdapter mAdapter;
    private BalancePresenter mPresenter;
    private List<SampleEntity> mList = new ArrayList<SampleEntity>();

    private String mToken;

    private BalanceEntity.ResultEntity mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        mPresenter = new BalancePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        View view = LayoutInflater.from(this).inflate(R.layout.layout_balance_top_item, null);
        mTvTotalBalance = (TextView) view.findViewById(R.id.tv_total_balance);
        mTvRecharge = (TextView) view.findViewById(R.id.tv_recharge);
        mRlBank = (RelativeLayout) view.findViewById(R.id.ital_withdrawal_bankcard);
        mRlAlipay = (RelativeLayout) view.findViewById(R.id.ital_withdrawal_zhifubao);
        mRlTelephoneFare = (RelativeLayout) view.findViewById(R.id.ital_withdrawal_huafei);

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
        initTitleBar("余额", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mRlBank.setOnClickListener(this);
        mRlAlipay.setOnClickListener(this);
        mRlTelephoneFare.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.ital_withdrawal_bankcard:
                toWithdrawCashByBank();
                break;
            case R.id.ital_withdrawal_zhifubao:
                toWithdrawCashByAlipay();
                break;
            case R.id.ital_withdrawal_huafei:
                toPhoneRecharge();
                break;
            case R.id.tv_recharge:
                toBalanceRecharge();
                break;
        }
    }

    @Override
    public void setData(BalanceEntity.ResultEntity result, List<SampleEntity> list) {
        mResult = result;
        mTvTotalBalance.setText("￥" + result.getAmount());
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
    public void toWithdrawCashByBank() {

    }

    @Override
    public void toWithdrawCashByAlipay() {
        Intent intent = new Intent(this, WithdrawCashAlipayActivity.class);
        List<BalanceEntity.ResultEntity.ZfbPoundageEntity> zfb_poundage = mResult.getZfb_poundage();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_KEY, (ArrayList<? extends Parcelable>) zfb_poundage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toPhoneRecharge() {
        Intent intent = new Intent(this, PhoneRechargeActivity.class);
        List<BalanceEntity.ResultEntity.CzPoundageEntity> cz_poundage = mResult.getCz_poundage();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_KEY, (ArrayList<? extends Parcelable>) cz_poundage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toBalanceRecharge() {
        Intent intent = new Intent(this, BalanceRechargeActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mResult.getEnable_unionpay());
        startActivity(intent);
    }
}
