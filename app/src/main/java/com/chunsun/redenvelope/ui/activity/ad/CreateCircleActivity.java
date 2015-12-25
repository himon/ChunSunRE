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
import com.chunsun.redenvelope.presenter.CreateAdPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseCreateActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.dpizarro.uipicker.library.picker.PickerUI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建Circle
 */
public class CreateCircleActivity extends BaseCreateActivity<ICreateAdView, CreateAdPresenter> implements View.OnClickListener, ICreateAdView {

    @Bind(R.id.rb_image_text)
    RadioButton mRbImageText;
    @Bind(R.id.rb_link)
    RadioButton mRbLink;
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

                        if (mCurrentSelectType == "range") {
                            if (position != 2) {
                                mRlProvince.setEnabled(false);
                                mRlCity.setEnabled(false);
                                mIvProvince.setVisibility(View.INVISIBLE);
                                mIvCity.setVisibility(View.INVISIBLE);
                                mTvProvince.setText(mCurrentProvince.getP());
                                mTvCity.setText(mCurrentCity.getC());
                            } else {
                                mRlProvince.setEnabled(true);
                                mRlCity.setEnabled(true);
                                mIvProvince.setVisibility(View.VISIBLE);
                                mIvCity.setVisibility(View.VISIBLE);
                            }
                        }
                        chooseFinish(position - 2);
                    }
                });
    }

    @Override
    protected void initData() {
        SampleEntity entity = new SampleEntity();
        entity.setKey(Constants.RED_DETAIL_TYPE_CIRCLE + "");
        mAdEntity.setType(entity);
        mPresenter.getData(mAdEntity, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.rb_image_text:
                mAdEntity.setType(mTypeList.get(0));
                break;
            case R.id.rb_link:
                mAdEntity.setType(mTypeList.get(1));
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
        Intent intent = new Intent(this, CreateAdContentActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        startActivity(intent);
    }

    /**
     * 设置页面控件显示数据
     */
    public void setStatus() {
        mTvProvince.setText(mAdEntity.getProvince().getP());
        mTvCity.setText(mAdEntity.getCity().getC());
        mTvRange.setText(mAdEntity.getDistance().getValue());
    }
}
