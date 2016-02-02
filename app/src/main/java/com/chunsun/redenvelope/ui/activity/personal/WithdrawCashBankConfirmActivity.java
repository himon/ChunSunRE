package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.event.WalletEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.WithdrawcashConfirmPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWithdrawcashConfirmView;
import com.chunsun.redenvelope.utils.manager.AppManager;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class WithdrawCashBankConfirmActivity extends BaseActivity implements IWithdrawcashConfirmView {

    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_bank_num)
    TextView mTvBankNum;
    @Bind(R.id.tv_bank_name)
    TextView mTvBankName;
    @Bind(R.id.tv_open_bank_name)
    TextView mTvOpenBank;
    @Bind(R.id.tv_province)
    TextView mTvProvince;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.tv_withdrawal_money)
    TextView mTvMoney;
    @Bind(R.id.tv_withdrawal_fee)
    TextView mTvFee;
    @Bind(R.id.tv_rate_value)
    TextView mTvRate;
    @Bind(R.id.btn_apply_withdrawal)
    Button mBtnApply;

    private WithdrawcashConfirmPresenter mPresenter;
    private String mToken;
    private String[] mData;
    /**
     * 费率
     */
    private BalanceEntity.ResultEntity.CashPoundageRateEntity mBankEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash_bank_confirm);
        ButterKnife.bind(this);
        mPresenter = new WithdrawcashConfirmPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("提现到银行卡", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mBtnApply.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mData = intent.getStringArrayExtra(Constants.EXTRA_KEY);
            mBankEntity = (BalanceEntity.ResultEntity.CashPoundageRateEntity) intent.getParcelableExtra(Constants.EXTRA_KEY2);

            if (mData != null) {
                mTvName.setText(mData[0]);
                mTvBankNum.setText(mData[1]);
                mTvBankName.setText(mData[2]);
                mTvOpenBank.setText(mData[3]);
                mTvProvince.setText(mData[4]);
                mTvCity.setText(mData[5]);
            }

            if (mBankEntity != null) {
                BigDecimal amount = new BigDecimal(mBankEntity.getAmount());
                BigDecimal rate = new BigDecimal(mBankEntity.getRate());

                BigDecimal fee = amount.multiply(rate);
                mTvMoney.setText("￥" + amount.subtract(fee).toString());
                mTvFee.setText("-￥" + fee.toString());

                BigDecimal percent = new BigDecimal("100");
                mTvRate.setText("提现手续费率为" + rate.multiply(percent).toString() + "%");
            }
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_apply_withdrawal:
                mPresenter.rechargeByBank(mToken, mData[0], mData[2], mData[1], mData[3], mData[4], mData[5], mBankEntity.getId() + "");
                break;
        }
    }

    @Override
    public void commitFinish() {
        hideCircleLoading();
        EventBus.getDefault().post(new WalletEvent("refresh"));
        AppManager.getAppManager().finishActivity(WithdrawCashBankConfirmActivity.class);
        AppManager.getAppManager().finishActivity(WithdrawCashBankActivity.class);
        AppManager.getAppManager().finishActivity(WithdrawCashActivity.class);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
