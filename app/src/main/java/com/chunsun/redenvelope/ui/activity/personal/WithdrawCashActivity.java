package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWithdrawCashView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 提现Activity
 */
public class WithdrawCashActivity extends BaseActivity implements IWithdrawCashView {

    @Bind(R.id.tv_cumulative_gain)
    TextView mTvCumulativeGain;
    @Bind(R.id.ital_withdrawal_bankcard)
    RelativeLayout mRlBank;
    @Bind(R.id.ital_withdrawal_zhifubao)
    RelativeLayout mRlAlipay;
    @Bind(R.id.ital_withdrawal_huafei)
    RelativeLayout mRlPhone;
    private ArrayList<BalanceEntity.ResultEntity.CzPoundageEntity> mPhonePoundage;
    private ArrayList<BalanceEntity.ResultEntity.ZfbPoundageEntity> mAlipayPoundage;
    private ArrayList<BalanceEntity.ResultEntity.CashPoundageRateEntity> mBankPoundage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("提现", "", "", Constants.TITLE_TYPE_SAMPLE);
        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        if (!userEntity.is_proxy()) {
            mRlBank.setVisibility(View.GONE);
        }
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mRlAlipay.setOnClickListener(this);
        mRlBank.setOnClickListener(this);
        mRlPhone.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mPhonePoundage = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY);
            mAlipayPoundage = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY2);
            mBankPoundage = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY3);
            String amount = intent.getStringExtra(Constants.EXTRA_KEY4);
            mTvCumulativeGain.setText(amount);
        }
    }

    @Override
    public void toWithdrawCashByBank() {
        Intent intent = new Intent(this, WithdrawCashBankActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_KEY, mBankPoundage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toWithdrawCashByAlipay() {
        Intent intent = new Intent(this, WithdrawCashAlipayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_KEY, mAlipayPoundage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toPhoneRecharge() {
        Intent intent = new Intent(this, PhoneRechargeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_KEY, mPhonePoundage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.ital_withdrawal_bankcard:
                toWithdrawCashByBank();
                break;
            case R.id.ital_withdrawal_zhifubao:
                toWithdrawCashByAlipay();
                break;
            case R.id.ital_withdrawal_huafei:
                toPhoneRecharge();
                break;
        }
    }
}
