package com.chunsun.redenvelope.ui.activity.ad;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.LuckMealsEntity;
import com.chunsun.redenvelope.presenter.CreateLuckNextPresenter;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.ICreateLuckNextView;
import com.chunsun.redenvelope.widget.XCArcProgressBar;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 拼手气红包
 */
public class CreateLuckNextActivity extends MBaseActivity implements ICreateLuckNextView {

    @Bind(R.id.tv_read_count)
    TextView mTvReadCount;
    @Bind(R.id.tv_repeat_count)
    TextView mTvRepeatCount;
    @Bind(R.id.arcProgressBar)
    XCArcProgressBar mProgressBar;
    @Bind(R.id.ll_read_nums)
    LinearLayout mLLPotentialReadNum;
    @Bind(R.id.tv_read_nums)
    TextView mTvPotentialReadNum;
    @Bind(R.id.rg_type)
    RadioGroup mRgType;
    @Bind(R.id.rb_first)
    RadioButton mRbFirst;
    @Bind(R.id.rb_second)
    RadioButton mRbSecond;
    @Bind(R.id.rb_third)
    RadioButton mRbThird;
    @Bind(R.id.rb_forth)
    RadioButton mRbForth;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_subtraction)
    TextView mTvSubtraction;
    @Bind(R.id.tv_multiple)
    TextView mTvMultiple;
    @Bind(R.id.tv_add)
    TextView mTvAdd;
    @Bind(R.id.tv_total)
    TextView mTvTotalPrice;
    @Bind(R.id.rb_once)
    RadioButton mRbOnce;
    @Bind(R.id.rb_at_time)
    RadioButton mRbAtTime;
    @Bind(R.id.ll_at_time)
    LinearLayout mLLAtTime;
    @Bind(R.id.tv_hour)
    TextView mTvHour;
    @Bind(R.id.tv_minutes)
    TextView mTvMinutes;
    @Bind(R.id.et_nums)
    EditText mEtNum;
    @Bind(R.id.tv_days)
    TextView mTvDays;
    @Bind(R.id.btn_next_step)
    Button mBtnNext;

    private CreateLuckNextPresenter mPresenter;
    private DecimalFormat format;

    /**
     * 广告数据
     */
    private AdEntity mAdEntity = new AdEntity();
    /**
     * 套餐列表
     */
    private List<LuckMealsEntity.ResultEntity> mList;
    /**
     * 当前选中的套餐
     */
    private LuckMealsEntity.ResultEntity mEntity;
    /**
     * 定时发放的小时
     */
    private String mHourOfDay;
    /**
     * 定时发放的分钟
     */
    private String mMinute;
    /**
     * 倍数
     */
    private int multiple = 1;
    /**
     * 阅读次数
     */
    private int mReadCount = 0;
    /**
     * 发放天数
     */
    private int mDays;
    /**
     * 每天领取数量
     */
    private int num = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_luck_next);
        ButterKnife.bind(this);
        mPresenter = (CreateLuckNextPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new CreateLuckNextPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("选择套餐", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mRbFirst.setOnClickListener(this);
        mRbSecond.setOnClickListener(this);
        mRbThird.setOnClickListener(this);
        mRbForth.setOnClickListener(this);
        mTvSubtraction.setOnClickListener(this);
        mTvAdd.setOnClickListener(this);
        mRbOnce.setOnClickListener(this);
        mRbAtTime.setOnClickListener(this);
        mTvHour.setOnClickListener(this);
        mTvMinutes.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        mEtNum.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    return;
                }
                try {
                    num = Integer.parseInt(s.toString());
                } catch (Exception e) {
                }
                calcDays();
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

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
        }
        mPresenter.getFightLuckPackageList();
        format = new DecimalFormat("0.0");
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.rb_first:
                changeMeal(0);
                break;
            case R.id.rb_second:
                changeMeal(1);
                break;
            case R.id.rb_third:
                changeMeal(2);
                break;
            case R.id.rb_forth:
                changeMeal(3);
                break;
            case R.id.tv_subtraction:
                changeNums(false);
                break;
            case R.id.tv_add:
                changeNums(true);
                break;
            case R.id.rb_once:
                mLLAtTime.setVisibility(View.GONE);
                break;
            case R.id.rb_at_time:
                mLLAtTime.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_hour:
            case R.id.tv_minutes:
                setTime();
            case R.id.btn_next_step:
                nextStep();
                break;
        }
    }

    private void nextStep() {
        if (mEntity == null) {
            return;
        }

        if (TextUtils.isEmpty(mEtNum.getText().toString().trim())) {
            num = 1;
            mEtNum.setText(num + "");
            calcDays();
        } else {
            int days = Integer
                    .parseInt(mEtNum.getText().toString().trim());
            if (days > mReadCount) {
                days = mReadCount;
                mEtNum.setText(days + "");
            }
        }

        mAdEntity.setCouponStartTime("");
        mAdEntity.setCouponEndTime("");
        mAdEntity.setPrice("0");
        mAdEntity.setIsReceipt("false");
        mAdEntity.setNum(mEtNum.getText().toString().trim());
        mAdEntity.setFight_package_id(mEntity.getId());
        mAdEntity.setFight_multiple(multiple + "");

        if (mRbOnce.isChecked()) {
            mAdEntity.setDays("1");
            mAdEntity.setStartTime("");
        } else {
            mAdEntity.setDays(mTvDays.getText().toString().trim());
            mAdEntity.setStartTime(mTvHour.getText().toString().trim() + ":"
                    + mTvMinutes.getText().toString().trim());
        }

        Intent intent = new Intent(this, CreateAdContentActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        // 追加的数据
        //intent.putExtra(Constants.MESSAGE_EXTRA2, mSuperadditionDetail);
        startActivity(intent);
    }

    @Override
    public void setFightPackageList(List<LuckMealsEntity.ResultEntity> result) {
        mList = result;
        initMeals(result);
    }

    private void initMeals(List<LuckMealsEntity.ResultEntity> result) {
        mEntity = mList.get(0);
        // 定时
        long now = System.currentTimeMillis() + 1800000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mHourOfDay = hour < 10 ? ("0" + hour) : ("" + hour);
        mMinute = minute < 10 ? ("0" + minute) : ("" + minute);
        mTvHour.setText(mHourOfDay);
        mTvMinutes.setText(mMinute);
        //
        calcDays();
        calc();
        mEtNum.setText(num + "");
        //
        LuckMealsEntity.ResultEntity entity = mList.get(0);
        mRbFirst.setText(entity.getPriceStr() + "元");
        entity = mList.get(1);
        mRbSecond.setText(entity.getPriceStr() + "元");
        entity = mList.get(2);
        mRbThird.setText(entity.getPriceStr() + "元");
        entity = mList.get(3);
        mRbForth.setText(entity.getPriceStr() + "元");
        //
        mTvPotentialReadNum.setText(mEntity.getPotentialReadTimes() + "");
        new MyAsyncTask().execute(1);
    }

    /**
     * 计算阅读量
     */
    private void calc() {
        int read = mEntity.getReadTimes();
        int repeat = mEntity.getForwardTimes();
        float potential = mEntity.getPotentialReadTimes() * multiple;

        if (potential > 10000) {
            String nums = "";
            if (potential > 100000) {
                nums = (int) (potential / 10000) + "万";
            } else {
                nums = format.format(potential / 10000) + "万";
            }
            mTvPotentialReadNum.setText(nums);
        } else {
            mTvPotentialReadNum.setText(potential + "");
        }

        mTvReadCount.setText((read * multiple) + " 次阅读");
        mTvRepeatCount.setText((repeat * multiple) + " 次转发");
        int price = Integer.parseInt(mEntity.getPriceStr());
        mTvPrice.setText(mEntity.getPriceStr());
        mTvTotalPrice.setText((price * multiple) + "");

    }

    /**
     * 计算发放天数
     */
    private void calcDays() {
        mReadCount = mEntity.getReadTimes() * multiple;
        mDays = mReadCount % num == 0 ? mReadCount / num : mReadCount / num + 1;
        mTvDays.setText(mDays + "");
    }

    /**
     * 改变套餐
     *
     * @param index
     */
    private void changeMeal(int index) {
        mEntity = mList.get(index);
        new MyAsyncTask().execute(index + 1);
        calc();
        calcDays();
    }

    /**
     * 改变倍数
     *
     * @param b
     */
    private void changeNums(boolean b) {
        if (b) {
            multiple++;
        } else {
            if (multiple > 1) {
                multiple--;
            }
        }
        mTvMultiple.setText(multiple + "");
        calc();
        calcDays();
    }

    /**
     * 设置定时
     */
    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHourOfDay = hourOfDay < 10 ? ("0" + hourOfDay)
                        : ("" + hourOfDay);
                mMinute = minute < 10 ? ("0" + minute) : ("" + minute);
                mTvHour.setText(mHourOfDay);
                mTvMinutes.setText(mMinute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                true).show();
    }

    private int timer = 0;

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            // arcProgressBar.setProgress(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress((int) (values[0]));
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int max = 0;
            if (params[0] == 1) {
                max = 25;
            } else if (params[0] == 2) {
                max = 25 * 2;
            } else if (params[0] == 3) {
                max = 25 * 3;
            } else if (params[0] == 4) {
                max = 25 * 4;
            }

            if (timer < max) {
                while (timer <= max) {
                    try {
                        publishProgress(timer);
                        timer++;
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (timer >= max) {
                    try {
                        publishProgress(timer);
                        timer--;
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}
