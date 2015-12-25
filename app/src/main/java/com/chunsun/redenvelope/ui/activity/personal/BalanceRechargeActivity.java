package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alipay.sdk.pay.demo.PayPresenter;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.BalanceRechargePresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IBalanceRechargeView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 余额充值Activity
 */
public class BalanceRechargeActivity extends BaseActivity implements IBalanceRechargeView, View.OnClickListener {

    @Bind(R.id.et_recharge)
    EditText mEtRecharge;
    @Bind(R.id.ll_zhifubao)
    LinearLayout mLLAlipay;
    @Bind(R.id.iv_zhifubao)
    ImageView mIvAlipay;
    @Bind(R.id.ll_bankcard)
    LinearLayout mLLBank;
    @Bind(R.id.iv_bankcard)
    ImageView mIvBank;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private String mPayWay = Constants.BALANCE_RECHARGE_TYPE_ALIPAY;
    private BalanceRechargePresenter mPresenter;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_recharge);
        ButterKnife.bind(this);
        mPresenter = new BalanceRechargePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("充值", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mLLAlipay.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

        mEtRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = StringUtil.textview2String(mEtRecharge);
                try {
                    double amount = Double.parseDouble(price);
                    if (amount > 15) {
                        mLLBank.setOnClickListener(BalanceRechargeActivity.this);
                    } else {
                        mLLBank.setOnClickListener(null);
                        mIvAlipay.setVisibility(View.VISIBLE);
                        mIvBank.setVisibility(View.GONE);
                        mPayWay = Constants.BALANCE_RECHARGE_TYPE_ALIPAY;
                    }
                } catch (NumberFormatException e) {
                    mLLBank.setOnClickListener(null);
                    mIvAlipay.setVisibility(View.VISIBLE);
                    mIvBank.setVisibility(View.GONE);
                    mPayWay = Constants.BALANCE_RECHARGE_TYPE_ALIPAY;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            boolean flag = intent.getBooleanExtra(Constants.EXTRA_KEY, false);
            if (!flag) {
                mLLBank.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.ll_zhifubao:
                mIvAlipay.setVisibility(View.VISIBLE);
                mIvBank.setVisibility(View.GONE);
                mPayWay = Constants.BALANCE_RECHARGE_TYPE_ALIPAY;
                break;
            case R.id.ll_bankcard:
                mIvAlipay.setVisibility(View.GONE);
                mIvBank.setVisibility(View.VISIBLE);
                mPayWay = Constants.BALANCE_RECHARGE_TYPE_BANK;
                break;
            case R.id.btn_next_step:
                String amount = StringUtil.textview2String(mEtRecharge);
                mPresenter.validateAmount(amount);
                break;
        }
    }

    @Override
    public void validateFinish(String amount) {
        mPresenter.recharge(mToken, mPayWay, amount);
    }

    @Override
    public void getOrderIdFinish(String orderId, String amount) {
        if (Constants.BALANCE_RECHARGE_TYPE_ALIPAY.equals(mPayWay)) {
            payByAlipay(orderId, amount);
        } else if (Constants.BALANCE_RECHARGE_TYPE_BANK.equals(mPayWay)) {
            payByBank(orderId);
        }
    }

    @Override
    public void payByAlipay(String orderId, String amount) {
        PayPresenter payPresenter = new PayPresenter(this);
        payPresenter.pay(this, orderId, "", "", amount);
    }

    @Override
    public void payByBank(String orderId) {
        Intent intentWeb = new Intent(this, CommonWebActivity.class);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.BACK_RECHARGE_URL + orderId);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "银联支付");
        startActivity(intentWeb);
    }

    @Override
    public void setAlipayResult(boolean result, String msg) {
        if (result) {
            ShowToast.Short(msg);
            back();
        } else {
            ShowToast.Short(msg);
        }
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }
}
