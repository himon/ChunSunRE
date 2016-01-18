package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.pay.demo.PayPresenter;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.AdPayAmountDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.AdPayPresenter;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IAdPayView;
import com.chunsun.redenvelope.utils.ShowToast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LuckAdPayActivity extends MBaseActivity<IAdPayView, AdPayPresenter> implements IAdPayView {

    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_balance_tips)
    TextView mTvTips;
    @Bind(R.id.btn_pay_balance)
    ImageView mIvBalance;
    @Bind(R.id.btn_pay_zhifubao)
    ImageView mIvAlipay;

    /**
     * 红包ID
     */
    private String mId;
    /**
     * Token
     */
    private String mToken;
    /**
     * 支付明细
     */
    private AdPayAmountDetailEntity.ResultEntity mResult;

    private AdPayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_ad_pay);
        ButterKnife.bind(this);
        mPresenter = (AdPayPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new AdPayPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("支付", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mIvBalance.setOnClickListener(this);
        mIvAlipay.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getStringExtra(Constants.EXTRA_KEY);
        }
        mPresenter.getAdAmountDetail(mToken, mId);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_pay_balance:
                balancePay();
                break;
            case R.id.btn_pay_zhifubao:
                alipay();
                break;
        }
    }

    @Override
    public void setData(AdPayAmountDetailEntity.ResultEntity result) {
        mResult = result;
        mTvPrice.setText(result.getTotal_amount());
        double amount = Double.valueOf(result.getAmount());
        double price = Double.valueOf(result.getTotal_amount());
        if (amount >= price) {
            mIvBalance.setEnabled(true);
            mTvTips.setVisibility(View.GONE);
        } else {
            mIvBalance.setEnabled(false);
            mTvTips.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void bankPay() {

    }

    @Override
    public void alipay() {
        PayPresenter payPresenter = new PayPresenter(this);
        payPresenter.pay(this, mResult.getOrder_no(), "", "", mResult.getTotal_amount());
    }

    @Override
    public void balancePay() {
        mPresenter.payByBalance(mToken, mId);
    }

    @Override
    public void paySuccess(String msg) {
        toResult(msg, true);
    }

    @Override
    public void payError(String msg) {
        toResult(msg, false);
    }

    private void toResult(String msg, boolean value) {
        ShowToast.Short(msg);
        Intent intent = new Intent(this, PayResultActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, value);
        intent.putExtra(Constants.EXTRA_KEY2, mResult.getTotal_amount());
        startActivity(intent);
    }

    @Override
    public void setAlipayResult(boolean result, String msg) {
        if (result) {
            paySuccess(msg);
        } else {
            payError(msg);
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

    @Override
    protected void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }
}
