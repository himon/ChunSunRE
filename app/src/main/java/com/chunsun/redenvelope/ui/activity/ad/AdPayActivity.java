package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.pay.demo.PayPresenter;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.AdPayAmountDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.AdPayPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IAdPayView;
import com.chunsun.redenvelope.utils.ShowToast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdPayActivity extends BaseActivity implements IAdPayView, View.OnClickListener {

    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_delay_cost)
    TextView mTvDelayCost;
    @Bind(R.id.tv_tax_cost)
    TextView mTvTaxCost;
    @Bind(R.id.tv_service_cost)
    TextView mTvServiceCost;
    @Bind(R.id.tv_invoice_cost)
    TextView mTvInvoiceFee;
    @Bind(R.id.tv_total_cost)
    TextView mTvTotalPrice;
    @Bind(R.id.tv_account_balance_money)
    TextView mTvAccountBalance;
    @Bind(R.id.tv_balance_tips)
    TextView mTvBalanceTips;
    @Bind(R.id.btn_pay_bankcard)
    ImageView mIvBankCard;
    @Bind(R.id.btn_pay_balance)
    ImageView mIvBalance;
    @Bind(R.id.btn_pay_zhifubao)
    ImageView mIvAlipay;

    private String mToken;
    private String mAdId;
    private AdPayAmountDetailEntity.ResultEntity mResult;

    private AdPayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_pay);
        ButterKnife.bind(this);
        mPresenter = new AdPayPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("新广告", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mIvBankCard.setOnClickListener(this);
        mIvBalance.setOnClickListener(this);
        mIvAlipay.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mAdId = intent.getStringExtra(Constants.EXTRA_KEY);
        }

        mPresenter.getAdAmountDetail(mToken, mAdId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                back();
                break;
            case R.id.btn_pay_bankcard:
                bankPay();
                break;
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

        mTvPrice.setText("￥" + result.getPayable_amount() + "元");
        mTvDelayCost.setText("￥" + result.getDelay_amount() + "元");
        mTvTaxCost.setText("￥" + result.getReceipt_fee() + "元");
        mTvServiceCost.setText("￥" + result.getPoundage() + "元");
        mTvInvoiceFee.setText("￥" + result.getReceipt_express_fee() + "元");
        mTvTotalPrice.setText("￥" + result.getTotal_amount() + "元");
        mTvAccountBalance.setText("￥" + result.getAmount() + "元");

        double amount = Double.valueOf(result.getAmount());
        double price = Double.valueOf(result.getTotal_amount());
        if (amount >= price) {
            mIvBalance.setEnabled(true);
            mTvBalanceTips.setVisibility(View.GONE);
        } else {
            mIvBalance.setEnabled(false);
            mTvBalanceTips.setVisibility(View.VISIBLE);
        }
        if (!result.isEnable_unionpay()) {
            mIvBankCard.setEnabled(false);
        }
    }

    @Override
    public void bankPay() {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.BACK_PAY_URL + mResult.getOrder_no());
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "银联支付");
        startActivity(intent);
    }

    @Override
    public void alipay() {
        PayPresenter payPresenter = new PayPresenter(this);
        payPresenter.pay(this, mResult.getOrder_no(), "", "", mResult.getTotal_amount());
    }

    @Override
    public void balancePay() {
        if (!TextUtils.isEmpty(mResult.getId())) {
            mPresenter.payByBalance(mToken, mResult.getId());
        }
    }

    @Override
    public void paySuccess(String msg) {
        ShowToast.Short(msg);
        Intent intent = new Intent(this, PayResultActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, true);
        intent.putExtra(Constants.EXTRA_KEY2, mResult.getTotal_amount());
        startActivity(intent);
    }

    @Override
    public void payError(String msg) {
        ShowToast.Short(msg);
        Intent intent = new Intent(this, PayResultActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, false);
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
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }
    }
}
