package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CreateAdPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseCreateActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.fragment.mengban.MengBanCreateAdFragment;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.dpizarro.uipicker.library.picker.PickerUI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建广告首页
 */
public class CreateAdActivity extends BaseCreateActivity<ICreateAdView, CreateAdPresenter> implements ICreateAdView, View.OnClickListener {

    @Bind(R.id.btn_company)
    RadioButton mRbCompany;
    @Bind(R.id.btn_repeat)
    RadioButton mRbRepeat;
    @Bind(R.id.btn_link)
    RadioButton mRbLink;
    @Bind(R.id.btn_lift)
    RadioButton mRbLift;
    @Bind(R.id.btn_near)
    RadioButton mRbNear;
    @Bind(R.id.rl_province)
    RelativeLayout mRlProvince;
    @Bind(R.id.iv_province)
    ImageView mIvProvince;
    @Bind(R.id.rl_city)
    RelativeLayout mRlCity;
    @Bind(R.id.iv_city)
    ImageView mIvCity;
    @Bind(R.id.rl_range)
    RelativeLayout mRlRange;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;
    private MengBanCreateAdFragment mMengBanCreateAdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);
        ButterKnife.bind(this);
        mPresenter = (CreateAdPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new CreateAdPresenter(this);
    }

    @Override
    protected void initView() {
        initTitle();
        initEvent();
        isFirstSendAd();
    }

    private void isFirstSendAd() {
        Preferences preferences = new Preferences(this);
        if (preferences.getFirstSendAd()) {
            preferences.setFirstSendAd(false);
            mMengBanCreateAdFragment = new MengBanCreateAdFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.root, mMengBanCreateAdFragment).commit();
        }
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mRbCompany.setOnClickListener(this);
        mRbLift.setOnClickListener(this);
        mRbLink.setOnClickListener(this);
        mRbNear.setOnClickListener(this);
        mRbRepeat.setOnClickListener(this);
        mRlProvince.setOnClickListener(this);
        mRlCity.setOnClickListener(this);
        mRlRange.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

       setEvent();

        mPickerUI
                .setOnClickItemPickerUIListener(new PickerUI.PickerUIItemClickListener() {

                    @Override
                    public void onItemClickPickerUI(int which, int position,
                                                    String valueResult) {
                        currentPosition = position;
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        if (mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_COUPON + "") && mCurrentSelectType == "range") {
                            if (position != 2) {
                                mRlProvince.setEnabled(false);
                                mRlCity.setEnabled(false);
                                mIvProvince.setVisibility(View.INVISIBLE);
                                mIvCity.setVisibility(View.INVISIBLE);
                            } else {
                                mRlProvince.setEnabled(true);
                                mRlCity.setEnabled(true);
                                mIvProvince.setVisibility(View.VISIBLE);
                                mIvCity.setVisibility(View.VISIBLE);
                            }
                        }

                        String item = (String) parent.getAdapter().getItem(
                                position - 2);
                        chooseFinish(position - 2);
                    }
                });
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
        }
        mPresenter.getData(mAdEntity, mSuperadditionEntity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_company://券类
                mAdEntity.setType(mTypeList.get(5));
                initSelected(2, true);
                break;
            case R.id.btn_repeat:
                mAdEntity.setType(mTypeList.get(4));
                initSelected(0, true);
                break;
            case R.id.btn_link:
                mAdEntity.setType(mTypeList.get(3));
                initSelected(0, true);
                break;
            case R.id.btn_lift:
                mAdEntity.setType(mTypeList.get(0));
                initSelected(0, true);
                break;
            case R.id.btn_near:
                mAdEntity.setType(mTypeList.get(2));
                initSelected(1, true);
                break;
            case R.id.rl_province:
                mCurrentSelectType = "province";
                options = getProvince();
                select();
                break;
            case R.id.rl_city:
                mCurrentSelectType = "city";
                options = getCity();
                select();
                break;
            case R.id.rl_range:
                mCurrentSelectType = "range";
                options = getDistance();
                select();
                break;
            case R.id.btn_next_step:
                nextCreateActivity();
                break;
        }
    }

    /**
     * 切换页面显示
     *
     * @param flag
     * @param isReset 是否重置
     */
    private void initSelected(int flag, boolean isReset) {
        //设置省市可选
        mRlProvince.setEnabled(true);
        mRlCity.setEnabled(true);
        mIvProvince.setVisibility(View.VISIBLE);
        mIvCity.setVisibility(View.VISIBLE);

        switch (flag) {
            case 0:
                mRlProvince.setVisibility(View.VISIBLE);
                mRlCity.setVisibility(View.VISIBLE);
                mRlRange.setVisibility(View.GONE);
                //赋值
                if (isReset) {
                    mAdEntity.setProvince(mDefaultProvince);
                    mAdEntity.setCity(mDefaultCity);
                    mAdEntity.setDistance(mDistanceList.get(1));
                }
                break;
            case 1:
                mRlProvince.setVisibility(View.GONE);
                mRlCity.setVisibility(View.GONE);
                mRlRange.setVisibility(View.VISIBLE);
                //赋值
                if (isReset) {
                    mAdEntity.setProvince(mDefaultProvince);
                    mAdEntity.setCity(mDefaultCity);
                    mAdEntity.setDistance(mDistanceList.get(9));
                }
                break;
            case 2:
                mRlProvince.setVisibility(View.VISIBLE);
                mRlCity.setVisibility(View.VISIBLE);
                mRlRange.setVisibility(View.VISIBLE);
                //赋值
                if (isReset) {
                    if (mCurrentProvince != null && mCurrentCity != null) {
                        mAdEntity.setProvince(mCurrentProvince);
                        mAdEntity.setCity(mCurrentCity);
                    } else {
                        mAdEntity.setProvince(mDefaultProvince);
                        mAdEntity.setCity(mDefaultCity);
                    }
                    mAdEntity.setDistance(mDistanceList.get(0));
                }
        }

        mTvProvince.setText(mAdEntity.getProvince().getP());
        mTvCity.setText(mAdEntity.getCity().getC());
        mTvRange.setText(mAdEntity.getDistance().getValue());
    }

    @Override
    public void setInitData(ArrayList<SampleEntity> distanceList, ArrayList<SampleEntity> typeList, ArrayList<DistrictEntity.AreaEntity> districtList, DistrictEntity.AreaEntity currentProvince, DistrictEntity.AreaEntity.CcEntity currentCity, DistrictEntity.AreaEntity defaultProvince, DistrictEntity.AreaEntity.CcEntity defaultCity, AdEntity adEntity) {
        this.mDistanceList = distanceList;
        this.mTypeList = typeList;
        this.mDistrictList = districtList;
        this.mAdEntity = adEntity;
        this.mCurrentProvince = currentProvince;
        this.mCurrentCity = currentCity;
        this.mDefaultProvince = defaultProvince;
        this.mDefaultCity = defaultCity;
        setStatus();
    }

    @Override
    public void nextCreateActivity() {
        Intent intent = null;
        if ((Constants.RED_DETAIL_TYPE_REPEAT + "").equals(mAdEntity.getType().getKey())) {
            intent = new Intent(this, CreateAdRepeatNextStepActivity.class);
        } else if ((Constants.RED_DETAIL_TYPE_COUPON + "").equals(mAdEntity.getType().getKey())) {
            intent = new Intent(this, CreateAdCouponNextStepActivity.class);
        } else {
            intent = new Intent(this, CreateAdNextStepActivity.class);
        }
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        intent.putExtra(Constants.EXTRA_KEY2, mSuperadditionEntity);
        startActivity(intent);
    }

    /**
     * 设置页面控件显示数据
     */
    public void setStatus() {

        if (("" + Constants.RED_DETAIL_TYPE_LEFT).equals(mAdEntity.getType().getKey())) {
            mRbLift.setChecked(true);
        } else if (("" + Constants.RED_DETAIL_TYPE_COUPON).equals(mAdEntity.getType().getKey())) {
            mRbCompany.setChecked(true);
            initSelected(2, false);
        } else if (("" + Constants.RED_DETAIL_TYPE_NEAR).equals(mAdEntity.getType().getKey())) {
            mRbNear.setChecked(true);
            initSelected(1, false);
        } else if (("" + Constants.RED_DETAIL_TYPE_LINK).equals(mAdEntity.getType().getKey())) {
            mRbLink.setChecked(true);
        } else if (("" + Constants.RED_DETAIL_TYPE_REPEAT).equals(mAdEntity.getType().getKey())) {
            mRbRepeat.setChecked(true);
        }
        mTvProvince.setText(mAdEntity.getProvince().getP());
        mTvCity.setText(mAdEntity.getCity().getC());
        mTvRange.setText(mAdEntity.getDistance().getValue());
    }

    /**
     * 蒙版被点击
     */
    public void mengBanClick() {
        getSupportFragmentManager().beginTransaction()
                .remove(mMengBanCreateAdFragment).commit();
        mMengBanCreateAdFragment = null;
    }
}
