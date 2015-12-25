package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity.ResultEntity.ZfbPoundageEntity;
import com.chunsun.redenvelope.presenter.WithdrawCashAlipayPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWithdrawCashAlipayView;
import com.chunsun.redenvelope.utils.DensityUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 支付宝提现
 */
public class WithdrawCashAlipayActivity extends BaseActivity implements IWithdrawCashAlipayView, View.OnClickListener {

    @Bind(R.id.et_zhifubao_account)
    EditText mEtAlipayAccount;
    @Bind(R.id.et_zhifubao_name)
    EditText mEtAlipayName;
    @Bind(R.id.rg_withdrawal_money)
    RadioGroup mRgWithdrawCash;
    @Bind(R.id.btn_apply_withdrawal)
    Button mBtnCommit;

    private WithdrawCashAlipayPresenter mPresenter;
    private ArrayList<BalanceEntity.ResultEntity.ZfbPoundageEntity> mRateList;
    private ZfbPoundageEntity mAlipayRateEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash_alipay);
        ButterKnife.bind(this);
        mPresenter = new WithdrawCashAlipayPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("提现到支付宝", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnCommit.setOnClickListener(this);
        mRgWithdrawCash.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mRateList != null && mRateList.size() > 0) {
                    mAlipayRateEntity = mRateList.get(checkedId);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            mRateList = bundle.getParcelableArrayList(Constants.EXTRA_KEY);
            buildMoneyGroup(mRateList);
        }
    }

    private void buildMoneyGroup(ArrayList<BalanceEntity.ResultEntity.ZfbPoundageEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            BalanceEntity.ResultEntity.ZfbPoundageEntity info = list.get(i);
            RadioButton rbItem = new RadioButton(this);
            rbItem.setId(i);
            rbItem.setBackgroundResource(R.drawable.selector_withdraw_cash_money);
            rbItem.setButtonDrawable(R.color.transparency);
            rbItem.setGravity(Gravity.CENTER);
            rbItem.setPadding(0, DensityUtils.dip2px(this, 5), 0,
                    DensityUtils.dip2px(this, 5));
            rbItem.setText(info.getAmount() + "元");
            rbItem.setTextSize(16);

            try {
                // 设置颜色选择器
                Resources resource = (Resources) getBaseContext()
                        .getResources();
                ColorStateList csl = (ColorStateList) resource
                        .getColorStateList(R.color.selector_radio_button_text_red_white);
                rbItem.setTextColor(csl);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(0,
                    RadioGroup.LayoutParams.WRAP_CONTENT, 1);
            params.rightMargin = DensityUtils.dip2px(this, 3);

            mRgWithdrawCash.addView(rbItem, params);
        }

        if (mRgWithdrawCash.getChildCount() > 0) {
            mRgWithdrawCash.check(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_apply_withdrawal:
                String account = mEtAlipayAccount.getText().toString().trim();
                String name = mEtAlipayName.getText().toString().trim();
                mPresenter.commit(account, name);
                break;
        }
    }

    @Override
    public void toWithdrawCashConfirm(String account, String name) {
        Intent intent = new Intent(this, WithdrawcashAlipayConfirmActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, account);
        intent.putExtra(Constants.EXTRA_KEY2, name);
        intent.putExtra(Constants.EXTRA_KEY3, mAlipayRateEntity);
        startActivity(intent);
    }
}
