package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.utils.manager.AppManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 支付结果
 */
public class PayResultActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.ll_success_container)
    LinearLayout mLLSuccess;
    @Bind(R.id.tv_total_price)
    TextView mTvPrice;
    @Bind(R.id.btn_complete_success)
    Button mBtnSuccess;
    @Bind(R.id.ll_fail_container)
    LinearLayout mLLFail;
    @Bind(R.id.tv_need_pay_price)
    TextView mTvNeedPayPrice;
    @Bind(R.id.btn_complete_fail)
    Button mBtnFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);
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
        mBtnSuccess.setOnClickListener(this);
        mBtnFail.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String price = intent.getStringExtra(Constants.EXTRA_KEY2);
            if (intent.getBooleanExtra(Constants.EXTRA_KEY, false)) {
                mLLSuccess.setVisibility(View.VISIBLE);
                mLLFail.setVisibility(View.GONE);
                mTvPrice.setText("￥" + price);
            } else {
                mLLSuccess.setVisibility(View.GONE);
                mLLFail.setVisibility(View.VISIBLE);
                mTvNeedPayPrice.setText("￥" + price);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
            case R.id.btn_complete_fail:
                back();
                break;
            case R.id.btn_complete_success:
                AppManager appManager = AppManager.getAppManager();
                appManager.finishActivity(PayResultActivity.class);
                appManager.finishActivity(AdPayActivity.class);
                appManager.finishActivity(CreateAdContentActivity.class);
                appManager.finishActivity(CreateAdRepeatNextStepActivity.class);
                appManager.finishActivity(CreateAdCouponNextStepActivity.class);
                appManager.finishActivity(CreateAdNextStepActivity.class);
                appManager.finishActivity(CreateAdActivity.class);

                break;
        }
    }
}
