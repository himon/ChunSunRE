package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.entities.json.RepeatMealEntity;
import com.chunsun.redenvelope.presenter.CreateAdRepeatNextStepPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdRepeatNextStepView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAdRepeatNextStepActivity extends BaseActivity implements ICreateAdRepeatNextStepView {

    @Bind(R.id.ll_first)
    LinearLayout mLLFirst;
    @Bind(R.id.tv_first_total)
    TextView mTvFirstTotal;
    @Bind(R.id.tv_first_num)
    TextView mTvFirstNum;
    @Bind(R.id.iv_first)
    ImageView mIvFirst;
    @Bind(R.id.ll_second)
    LinearLayout mLLSecond;
    @Bind(R.id.tv_second_total)
    TextView mTvSecondTotal;
    @Bind(R.id.tv_second_num)
    TextView mTvSecondNum;
    @Bind(R.id.iv_second)
    ImageView mIvSecond;
    @Bind(R.id.ll_third)
    LinearLayout mLLThird;
    @Bind(R.id.tv_third_total)
    TextView mTvThirdTotal;
    @Bind(R.id.tv_third_num)
    TextView mTvThirdNum;
    @Bind(R.id.iv_third)
    ImageView mIvThird;
    @Bind(R.id.ll_fourth)
    LinearLayout mLLFourth;
    @Bind(R.id.tv_fourth_total)
    TextView mTvFourthTotal;
    @Bind(R.id.tv_fourth_num)
    TextView mTvFourthNum;
    @Bind(R.id.iv_fourth)
    ImageView mIvFourth;
    @Bind(R.id.tv_selected_price)
    TextView mTvSelectedPrice;
    @Bind(R.id.iv_subtraction)
    ImageView mIvSub;
    @Bind(R.id.et_multiple)
    EditText mEtMultiple;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_total)
    TextView mTvTotal;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private DecimalFormat mFormat;
    private AdEntity mAdEntity;
    private CreateAdRepeatNextStepPresenter mPresenter;
    /**
     * 套餐列表
     */
    private List<RepeatMealEntity.ResultEntity> mList;
    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad_repeat_next_step);
        ButterKnife.bind(this);
        mPresenter = new CreateAdRepeatNextStepPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitle();
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mLLFirst.setOnClickListener(this);
        mLLSecond.setOnClickListener(this);
        mLLThird.setOnClickListener(this);
        mLLFourth.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mIvSub.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

        mEtMultiple.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String str = s.toString();
                if (!TextUtils.isEmpty(str) && str.contains(".")) {
                    int index = str.indexOf(".");
                    if (str.length() - index > 2) {
                        str = str.substring(0, str.length() - 1);
                        mEtMultiple.setText(str);
                    }
                } else if (TextUtils.isEmpty(str)) {
                    mEtMultiple.setText("1");
                }
                calcTotalPrice();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

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
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);
        }

        mFormat = new DecimalFormat("#.##");
        mPresenter.initData();
    }

    @Override
    public void getRepeatMealSuccess(List<RepeatMealEntity.ResultEntity> list) {
        mList = list;

        RepeatMealEntity.ResultEntity entity1 = list.get(0);
        mTvFirstTotal.setText(entity1.getTotal() + "元");
        mTvFirstNum.setText(entity1.getPrice() + "元 * "
                + entity1.getNumber_of_copies() + "份");

        RepeatMealEntity.ResultEntity entity2 = list.get(1);
        mTvSecondTotal.setText(entity2.getTotal() + "元");
        mTvSecondNum.setText(entity2.getPrice() + "元 * "
                + entity2.getNumber_of_copies() + "份");

        RepeatMealEntity.ResultEntity entity3 = list.get(2);
        mTvThirdTotal.setText(entity3.getTotal() + "元");
        mTvThirdNum.setText(entity3.getPrice() + "元 * "
                + entity3.getNumber_of_copies() + "份");

        RepeatMealEntity.ResultEntity entity4 = list.get(3);
        mTvFourthTotal.setText(entity4.getTotal() + "元");
        mTvFourthNum.setText(entity4.getPrice() + "元 * "
                + entity4.getNumber_of_copies() + "份");

        if (mSuperadditionEntity != null) {
            for (int i = 0; i < mList.size(); i++) {
                RepeatMealEntity.ResultEntity item = mList.get(i);
                if (("" + item.getId()).equals(mSuperadditionEntity.getForwarding_packages_id())) {
                    mAdEntity.setMeal(item);
                    mEtMultiple.setText(mSuperadditionEntity.getFormula_multiple());
                    switch (i) {
                        case 0:
                            mLLFirst.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                            mIvFirst.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            mLLSecond.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                            mIvSecond.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            mLLThird.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                            mIvThird.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            mLLFourth.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                            mIvFourth.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
                }
            }
        } else {
            mLLFirst.setBackgroundResource(R.drawable.shape_radius_stroke_red);
            mIvFirst.setVisibility(View.VISIBLE);
            mAdEntity.setMeal(list.get(0));
        }
        //显示总价
        RepeatMealEntity.ResultEntity meal = mAdEntity.getMeal();
        mTvSelectedPrice.setText(meal.getTotal() + "元");
        float multiple = Float.parseFloat(mEtMultiple.getText()
                .toString().trim());
        mTvTotal.setText(meal.getTotal() * multiple + "元");
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
            case R.id.rl_nav_right:
                toIllustrate();
                break;
            case R.id.ll_first:
                unSelectMeal();
                mLLFirst.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                mIvFirst.setVisibility(View.VISIBLE);
                mAdEntity.setMeal(mList.get(0));
                calcTotalPrice();
                break;
            case R.id.ll_second:
                unSelectMeal();
                mLLSecond.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                mIvSecond.setVisibility(View.VISIBLE);
                mAdEntity.setMeal(mList.get(1));
                calcTotalPrice();
                break;
            case R.id.ll_third:
                unSelectMeal();
                mLLThird.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                mIvThird.setVisibility(View.VISIBLE);
                mAdEntity.setMeal(mList.get(2));
                calcTotalPrice();
                break;
            case R.id.ll_fourth:
                unSelectMeal();
                mLLFourth.setBackgroundResource(R.drawable.shape_radius_stroke_red);
                mIvFourth.setVisibility(View.VISIBLE);
                mAdEntity.setMeal(mList.get(3));
                calcTotalPrice();
                break;
            case R.id.iv_add:
                add();
                break;
            case R.id.iv_subtraction:
                subtraction();
                break;
            case R.id.btn_next_step:
                nextStep();
                break;
        }
    }

    private void nextStep() {
        float m = Float.parseFloat(mEtMultiple.getText().toString().trim());
        if (m < 1) {
            ShowToast.Short("倍数不能小于1！");
            return;
        }

        mAdEntity.setPrice("0.03");
        mAdEntity.setDays("1");
        mAdEntity.setNum("2000");
        mAdEntity.setStartTime("");
        //延迟时间
        AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delay = new AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity();
        delay.setId(4);
        mAdEntity.setDelaySeconds(delay);
        mAdEntity.setIsReceipt("false");
        mAdEntity.setFormula_multiple(mEtMultiple.getText().toString().trim());
        Intent intent = new Intent(this, CreateAdContentActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        // 追加的数据
        intent.putExtra(Constants.EXTRA_KEY2, mSuperadditionEntity);
        startActivity(intent);
    }

    /**
     * 跳转说明Activity
     */
    private void toIllustrate() {
        Intent intentWeb = new Intent(this, CommonWebActivity.class);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                Constants.REPEAT_SEND_RED_INSTRUCTION_URL);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                "说明");
        startActivity(intentWeb);
    }

    /**
     * 取消所有选中状态
     */
    private void unSelectMeal() {
        mLLFirst.setBackgroundResource(R.drawable.shape_radius_white);
        mLLSecond.setBackgroundResource(R.drawable.shape_radius_white);
        mLLThird.setBackgroundResource(R.drawable.shape_radius_white);
        mLLFourth.setBackgroundResource(R.drawable.shape_radius_white);
        mIvFirst.setVisibility(View.INVISIBLE);
        mIvSecond.setVisibility(View.INVISIBLE);
        mIvThird.setVisibility(View.INVISIBLE);
        mIvFourth.setVisibility(View.INVISIBLE);
    }

    /**
     * 计算总价
     */
    private void calcTotalPrice() {
        RepeatMealEntity.ResultEntity meal = mAdEntity.getMeal();
        mTvSelectedPrice.setText(meal.getTotal() + "元");
        float multiple = Float.parseFloat(mEtMultiple.getText().toString()
                .trim());
        mTvTotal.setText(mFormat.format(meal.getTotal() * multiple) + "元");
    }

    /**
     * 增加倍数
     */
    private void add() {
        float num = Float.parseFloat(mEtMultiple.getText().toString().trim());
        mEtMultiple.setText(mFormat.format(num + 0.1));
        calcTotalPrice();
    }

    /**
     * 减少倍数
     */
    private void subtraction() {
        float num = Float.parseFloat(mEtMultiple.getText().toString().trim());
        if (num - 0.1 >= 1) {
            mEtMultiple.setText(mFormat.format(num - 0.1));
        }
        calcTotalPrice();
    }
}
