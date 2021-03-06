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
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IWithdrawcashConfirmView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.AppManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class WithdrawcashAlipayConfirmActivity extends MBaseActivity<IWithdrawcashConfirmView,WithdrawcashConfirmPresenter> implements IWithdrawcashConfirmView {

    @Bind(R.id.tv_account)
    TextView mTvAccount;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_withdrawal_money)
    TextView mTvMoeny;
    @Bind(R.id.tv_withdrawal_fee)
    TextView mTvWithdrawCashFee;
    @Bind(R.id.btn_apply_withdrawal)
    Button mBtnWithdrawCash;

    private BalanceEntity.ResultEntity.ZfbPoundageEntity mEntity;
    private WithdrawcashConfirmPresenter mPresenter;
    private String mToken;
    private String mAccount;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawcash_confirm);
        ButterKnife.bind(this);
        mPresenter = (WithdrawcashConfirmPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new WithdrawcashConfirmPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("提现到支付宝", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnWithdrawCash.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mAccount = intent.getStringExtra(Constants.EXTRA_KEY);
            mName = intent.getStringExtra(Constants.EXTRA_KEY2);
            mEntity = intent.getParcelableExtra(Constants.EXTRA_KEY3);
            mTvAccount.setText(mAccount);
            mTvName.setText(mName);
            mTvMoeny.setText("￥" + mEntity.getAmount());
            mTvWithdrawCashFee.setText("-￥" + mEntity.getPoundage());
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_apply_withdrawal:
                if (mEntity == null) {
                    ShowToast.Short("提现金额不正确");
                    return;
                }
                showLoading();
                mPresenter.rechargeByAlipay(mToken, mAccount, mName, mEntity.getId());
                break;
        }
    }

    @Override
    public void commitFinish() {
        hideCircleLoading();
        EventBus.getDefault().post(new WalletEvent("refresh"));
        AppManager.getAppManager().finishActivity(WithdrawcashAlipayConfirmActivity.class);
        AppManager.getAppManager().finishActivity(WithdrawCashAlipayActivity.class);
        AppManager.getAppManager().finishActivity(WithdrawCashActivity.class);
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
