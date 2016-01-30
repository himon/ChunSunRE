package com.chunsun.redenvelope.ui.activity.ad;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.presenter.CreateAdCouponNextStepPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdCouponNextStepView;
import com.chunsun.redenvelope.utils.StringUtil;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAdCouponNextStepActivity extends BaseActivity implements ICreateAdCouponNextStepView {

    @Bind(R.id.et_price)
    EditText mEtPrice;
    @Bind(R.id.iv_explain)
    ImageView mIvExplain;
    @Bind(R.id.et_num)
    EditText mEtNum;
    /**
     * 显示时间
     */
    @Bind(R.id.ll_show_time_container)
    LinearLayout mLLShowTimeContainer;
    @Bind(R.id.ll_invoice_container)
    LinearLayout mLLInvoice;
    @Bind(R.id.tb_need_invoice)
    ToggleButton mTbNeedInvoice;
    @Bind(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;
    @Bind(R.id.rg_delayed)
    RadioGroup mRgDelayed;
    @Bind(R.id.rb_delayed_5s)
    RadioButton mRbDelayed5;
    @Bind(R.id.rb_delayed_10s)
    RadioButton mRbDelayed10;
    @Bind(R.id.rb_delayed_15s)
    RadioButton mRbDelayed15;
    @Bind(R.id.rb_delayed_20s)
    RadioButton mRbDelayed20;
    @Bind(R.id.rb_delayed_25s)
    RadioButton mRbDelayed25;
    @Bind(R.id.rb_delayed_30s)
    RadioButton mRbDelayed30;
    @Bind(R.id.rb_delayed_35s)
    RadioButton mRbDelayed35;
    @Bind(R.id.rb_delayed_40s)
    RadioButton mRbDelayed40;
    @Bind(R.id.ll_start_time)
    LinearLayout mLLStartTime;
    @Bind(R.id.ll_end_time)
    LinearLayout mLLEndTime;
    @Bind(R.id.tv_start_time)
    TextView mTvStartTime;
    @Bind(R.id.tv_end_time)
    TextView mTvEndTime;

    private CreateAdCouponNextStepPresenter mPresenter;
    private AdEntity mAdEntity;
    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;
    /**
     * 延时列表
     */
    private List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> mDelaySecondsRate;
    /**
     * 结束时间
     */
    private String mEndTime;
    /**
     * 开始时间
     */
    private String mStartTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad_coupon_next_step);
        ButterKnife.bind(this);
        mPresenter = new CreateAdCouponNextStepPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitle();

        //判断是否是企业用户
        if (MainApplication.getContext().getUserEntity() != null) {
            if (Constants.USER_REGISTER_TYPE_ENTERPRISE.equals(MainApplication.getContext().getUserEntity().getType())) {
                mLLInvoice.setVisibility(View.VISIBLE);
            } else {
                mLLInvoice.setVisibility(View.GONE);
            }
        }
        initEvent();
    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mStartTime = year + "年" + (month + 1) + "月" + day + "日";
        mTvStartTime.setText("开始日期：" + mStartTime);

        if (month == 9) {
            month = 0;
            year++;
        } else if (month == 10) {
            month = 1;
            year++;
        } else if (month == 11) {
            month = 2;
            year++;
        } else {
            year++;
        }

        mEndTime = year + "年" + (month + 1) + "月" + day + "日";
        mTvEndTime.setText("结束日期：" + mEndTime);
        mAdEntity.setCouponStartTime(mStartTime);
        mAdEntity.setCouponEndTime(mEndTime);
    }


    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mIvExplain.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);
        mLLStartTime.setOnClickListener(this);
        mLLEndTime.setOnClickListener(this);
        mRbDelayed5.setOnClickListener(this);
        mRbDelayed10.setOnClickListener(this);
        mRbDelayed15.setOnClickListener(this);
        mRbDelayed20.setOnClickListener(this);
        mRbDelayed25.setOnClickListener(this);
        mRbDelayed30.setOnClickListener(this);
        mRbDelayed35.setOnClickListener(this);
        mRbDelayed40.setOnClickListener(this);

        mEtNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = s.toString();
                calcAdTotalPrice(StringUtil.textview2String(mEtPrice), num);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = s.toString();

                int index = 0;

                if (!TextUtils.isEmpty(price)) {
                    if ("0.01".equals(price)) {
                        showDelayedTimeList(1);
                        mAdEntity.setDelaySeconds(mDelaySecondsRate.get(0));
                    } else if ("0.02".equals(price)) {
                        showDelayedTimeList(2);
                        mAdEntity.setDelaySeconds(mDelaySecondsRate.get(1));
                    } else if ("0.03".equals(price) || "0.04".equals(price)) {
                        showDelayedTimeList(3);
                        mAdEntity.setDelaySeconds(mDelaySecondsRate.get(2));
                    } else if ("0.0".equals(price) || "0.".equals(price) || "0".equals(price)) {
                        return;
                    } else {
                        showDelayedTimeList(0);
                        mAdEntity.setDelaySeconds(mDelaySecondsRate.get(2));
                    }
                    calcAdTotalPrice(price, StringUtil.textview2String(mEtNum));
                    showSelectDelayedTime();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initTitle() {
        initTitleBar("设置红包金额", "", "说明", Constants.TITLE_TYPE_SAMPLE);
        mNavRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mPresenter.getAdDelaySecondsRate();
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);
        }
    }

    @Override
    public void setDelaySecondsRateData(List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> result) {
        mDelaySecondsRate = result;
        initDefaultData();
    }

    /**
     * 跳转价格说明
     */
    @Override
    public void toAdPriceExplain() {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "说明");
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.SEND_PRICE_EXPLAIN_URL);
        startActivity(intent);
    }

    /**
     * 跳转说明Activity
     */
    @Override
    public void toIllustrate() {
        Intent intentWeb = new Intent(this,
                CommonWebActivity.class);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                Constants.SEND_RED_INSTRUCTION_URL);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                "说明");
        startActivity(intentWeb);
    }

    /**
     * 下一步
     */
    @Override
    public void toNextStep() {
        Intent intent = new Intent(this, CreateAdContentActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        intent.putExtra(Constants.EXTRA_KEY2, mSuperadditionEntity);
        startActivity(intent);
    }

    /**
     * 初始化广告数据
     */
    private void initDefaultData() {
        if (mSuperadditionEntity != null) {//追加

            for (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity item : mDelaySecondsRate) {
                if (("" + item.getId()).equals(mSuperadditionEntity.getDelay_seconds())) {
                    mAdEntity.setDelaySeconds(item);
                }
            }
            mAdEntity.setStartTime(mSuperadditionEntity.getTime());

            calcAdTotalPrice(mSuperadditionEntity.getPrice(), mSuperadditionEntity.getEveryday_count());

            mEtPrice.setText(mSuperadditionEntity.getPrice());
            mEtNum.setText(mSuperadditionEntity.getEveryday_count());

            mTvStartTime.setText("开始时间：" + mSuperadditionEntity.getStart_time());
            mTvEndTime.setText("结束时间：" + mSuperadditionEntity.getEnd_time());

        } else {

            mAdEntity.setDelaySeconds(mDelaySecondsRate.get(2));
            mAdEntity.setStartTime("");

            calcAdTotalPrice(Constants.AD_DEFAULT_PRICE, Constants.AD_DEFAULT_NUM);

            mEtPrice.setText(Constants.AD_DEFAULT_PRICE);
            mEtNum.setText(Constants.AD_DEFAULT_NUM);

            initTime();
        }
    }

    /**
     * 计算总价
     *
     * @param priceStr
     * @param numStr
     * @return
     */
    private void calcAdTotalPrice(String priceStr, String numStr) {
        float price = 0;
        if (!TextUtils.isEmpty(priceStr)) {
            price = Float.valueOf(priceStr);
        }

        int num = Integer.valueOf(numStr);

        DecimalFormat format = new DecimalFormat("0.00");
        String total = format.format(price * num);
        mTvTotalPrice.setText("￥" + total + "元");

        mAdEntity.setPrice(priceStr);
        mAdEntity.setNum(numStr);
        mAdEntity.setAdPrice(total);
    }

    /**
     * 设置选中的延时时间
     */
    private void showSelectDelayedTime() {
        if (mAdEntity.getDelaySeconds() != null) {
            int key = mAdEntity.getDelaySeconds().getId();
            switch (key) {
                case 4:
                    mRgDelayed.check(R.id.rb_delayed_5s);
                    break;
                case 5:
                    mRgDelayed.check(R.id.rb_delayed_10s);
                    break;
                case 6:
                    mRgDelayed.check(R.id.rb_delayed_15s);
                    break;
                case 7:
                    mRgDelayed.check(R.id.rb_delayed_20s);
                    break;
                case 8:
                    mRgDelayed.check(R.id.rb_delayed_25s);
                    break;
                case 9:
                    mRgDelayed.check(R.id.rb_delayed_30s);
                    break;
                case 10:
                    mRgDelayed.check(R.id.rb_delayed_35s);
                    break;
                case 11:
                    mRgDelayed.check(R.id.rb_delayed_40s);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 显示时间列表
     *
     * @param type
     */
    private void showDelayedTimeList(int type) {
        switch (type) {
            case 1:
                mRbDelayed10.setVisibility(View.GONE);
            case 2:
                mRbDelayed15.setVisibility(View.GONE);
                if (type == 2) {
                    mRbDelayed10.setVisibility(View.VISIBLE);
                }
            case 3:
                mRbDelayed20.setVisibility(View.GONE);
                mRbDelayed25.setVisibility(View.GONE);
                mRbDelayed30.setVisibility(View.GONE);
                mRbDelayed35.setVisibility(View.GONE);
                mRbDelayed40.setVisibility(View.GONE);
                if (type == 3) {
                    mRbDelayed10.setVisibility(View.VISIBLE);
                    mRbDelayed15.setVisibility(View.VISIBLE);
                }
                break;
            default:
                mRbDelayed10.setVisibility(View.VISIBLE);
                mRbDelayed15.setVisibility(View.VISIBLE);
                mRbDelayed20.setVisibility(View.VISIBLE);
                mRbDelayed25.setVisibility(View.VISIBLE);
                mRbDelayed30.setVisibility(View.VISIBLE);
                mRbDelayed35.setVisibility(View.VISIBLE);
                mRbDelayed40.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 设置定时
     */
    private void setTime(final boolean flag) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (!flag) {
            if (month == 9) {
                month = 0;
                year++;
            } else if (month == 10) {
                month = 1;
                year++;
            } else if (month == 11) {
                month = 2;
                year++;
            } else {
                year++;
            }
        }

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                if (flag) {
                    mStartTime = year + "年" + (monthOfYear + 1) + "月"
                            + dayOfMonth + "日";
                    mTvStartTime.setText("开始时间：" + mStartTime);
                    mAdEntity.setCouponStartTime(mStartTime);
                } else {
                    mEndTime = year + "年" + (monthOfYear + 1) + "月"
                            + dayOfMonth + "日";
                    mTvEndTime.setText("结束时间：" + mEndTime);
                    mAdEntity.setCouponEndTime(mEndTime);
                }
            }
        }, year, month, day).show();

    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
                toIllustrate();
                break;
            case R.id.iv_explain:
                toAdPriceExplain();
                break;
            case R.id.btn_next_step:
                mPresenter.toValidatePrice(mAdEntity);
                break;
            case R.id.ll_start_time:
                setTime(true);
                break;
            case R.id.ll_end_time:
                setTime(false);
                break;
            case R.id.rb_delayed_5s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(0));
                break;
            case R.id.rb_delayed_10s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(1));
                break;
            case R.id.rb_delayed_15s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(2));
                break;
            case R.id.rb_delayed_20s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(3));
                break;
            case R.id.rb_delayed_25s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(4));
                break;
            case R.id.rb_delayed_30s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(5));
                break;
            case R.id.rb_delayed_35s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(6));
                break;
            case R.id.rb_delayed_40s:
                mAdEntity.setDelaySeconds(mDelaySecondsRate.get(7));
                break;


        }
    }
}
