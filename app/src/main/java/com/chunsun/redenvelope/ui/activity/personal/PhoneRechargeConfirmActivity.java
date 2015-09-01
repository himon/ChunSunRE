package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.PhoneRechargeConfirmPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IPhoneRechargeConfirmView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhoneRechargeConfirmActivity extends BaseActivity implements IPhoneRechargeConfirmView, View.OnClickListener {

    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.tv_phone_operator)
    TextView mTvOperator;
    @Bind(R.id.tv_recharge_momney)
    TextView mRechargeAmount;
    @Bind(R.id.tv_pay_price)
    TextView mTvPayAmount;
    @Bind(R.id.btn_apply_withdrawal)
    Button mBtnConfirmPay;

    private String mPhoneNum;
    private String mToken;
    private String mCarrierOperator;
    private BalanceEntity.ResultEntity.CzPoundageEntity mEntity;
    private PhoneRechargeConfirmPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_recharge_confirm);
        ButterKnife.bind(this);
        mPresenter = new PhoneRechargeConfirmPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("手机话费充值", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnConfirmPay.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mPhoneNum = intent.getStringExtra(Constants.EXTRA_KEY);
            mEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);
            mTvPhone.setText(mPhoneNum);
            mRechargeAmount.setText(mEntity.getAmount() + "");
            mTvPayAmount.setText(mEntity.getReal_amount() + "");
        }
        mPresenter.getCarrierOperator(mPhoneNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_apply_withdrawal:
                mPresenter.rechargeMobile(mToken, mPhoneNum, mCarrierOperator, mEntity.getId());
                break;
        }
    }

    @Override
    public void setCarrierOperator(String str) {
        mCarrierOperator = str;
        mTvOperator.setText(str);
    }

    @Override
    public void rechargeMobileFinish() {

    }
}
