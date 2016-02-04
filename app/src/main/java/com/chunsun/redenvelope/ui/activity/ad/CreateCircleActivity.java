package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.presenter.CreateAdPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseCreateActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.dpizarro.uipicker.library.picker.PickerUI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建Circle
 */
public class CreateCircleActivity extends BaseCreateActivity<ICreateAdView, CreateAdPresenter> implements ICreateAdView {

    @Bind(R.id.tv_create_title)
    TextView mTvTitle;
    @Bind(R.id.rb_image_text)
    RadioButton mRbImageText;
    @Bind(R.id.rb_link)
    RadioButton mRbLink;
    @Bind(R.id.iv_province)
    ImageView mIvProvince;
    @Bind(R.id.iv_city)
    ImageView mIvCity;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private int mType;
    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);
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
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mRbImageText.setOnClickListener(this);
        mRbLink.setOnClickListener(this);
        mTvProvince.setOnClickListener(this);
        mTvCity.setOnClickListener(this);
        mTvRange.setOnClickListener(this);
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

                        if (mCurrentSelectType == "range") {
                            if (position != 2) {
                                mIvProvince.setVisibility(View.INVISIBLE);
                                mIvCity.setVisibility(View.INVISIBLE);
                                mTvProvince.setText(mCurrentProvince.getP());
                                mTvCity.setText(mCurrentCity.getC());
                                mTvProvince.setEnabled(false);
                                mTvCity.setEnabled(false);
                                //选中当前位置
                                mAdEntity.setProvince(mCurrentProvince);
                                mAdEntity.setCity(mCurrentCity);
                            } else {
                                mIvProvince.setVisibility(View.VISIBLE);
                                mIvCity.setVisibility(View.VISIBLE);
                                mTvProvince.setEnabled(true);
                                mTvCity.setEnabled(true);
                            }
                        }
                        chooseFinish(position - 2);
                    }
                });
    }

    @Override
    protected void initData() {
        SampleEntity entity = new SampleEntity();
        Intent intent = getIntent();
        if (intent != null) {
            mType = intent.getIntExtra(Constants.EXTRA_KEY, 0);
            if (mType == 0) {
                mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);
                if (mSuperadditionEntity != null) {
                    mType = Integer.parseInt(mSuperadditionEntity.getType());
                }
            }
            if (mType == 7) {//圈子
                entity.setKey(Constants.RED_DETAIL_TYPE_CIRCLE + "");
            } else if (mType == 9) {//拼手气红包
                entity.setKey(Constants.RED_DETAIL_TYPE_lUCK + "");
                mTvTitle.setText("定制拼手气红包广告");
            }
        }
        mAdEntity.setType(entity);
        mPresenter.getData(mAdEntity, mSuperadditionEntity);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.rb_image_text:
                if (mType == 9) {
                    mAdEntity.setType(mTypeList.get(2));
                } else {
                    mAdEntity.setType(mTypeList.get(0));
                }
                break;
            case R.id.rb_link:
                if (mType == 9) {
                    mAdEntity.setType(mTypeList.get(3));
                } else {
                    mAdEntity.setType(mTypeList.get(1));
                }
                break;
            case R.id.tv_province:
                mCurrentSelectType = "province";
                options = getProvince();
                select();
                break;
            case R.id.tv_city:
                mCurrentSelectType = "city";
                options = getCity();
                select();
                break;
            case R.id.tv_range:
                mCurrentSelectType = "range";
                options = getDistance();
                select();
                break;
            case R.id.btn_next_step:
                nextCreateActivity();
                break;
        }
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

        String province = MainApplication.getContext().getProvince();
        if (TextUtils.isEmpty(province)) {
            ShowToast.Short("请开启GPS定位！");
            return;
        }

        Intent intent = null;
        if (mType == 9) {
            intent = new Intent(this, CreateLuckNextActivity.class);
        } else {
            intent = new Intent(this, CreateAdContentActivity.class);
        }
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        intent.putExtra(Constants.EXTRA_KEY2, mSuperadditionEntity);
        startActivity(intent);
    }

    /**
     * 设置页面控件显示数据
     */
    public void setStatus() {
        mTvProvince.setText(mAdEntity.getProvince().getP());
        mTvCity.setText(mAdEntity.getCity().getC());
        mTvRange.setText(mAdEntity.getDistance().getValue());

        String type = mAdEntity.getType().getKey();
        if (("" + Constants.RED_DETAIL_TYPE_CIRCLE).equals(type) || ("" + Constants.RED_DETAIL_TYPE_lUCK).equals(type)) {
            mRbImageText.setChecked(true);
        } else {
            mRbImageText.setChecked(true);
        }
    }
}
