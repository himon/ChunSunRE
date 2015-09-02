package com.chunsun.redenvelope.ui.activity.ad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.presenter.impl.CreateAdNextPagePresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdNextPageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAdNextPageActivity extends BaseActivity implements ICreateAdNextPageView, View.OnClickListener {

    @Bind(R.id.ll_type_container)
    LinearLayout mLLTypeContainer;
    @Bind(R.id.tv_type)
    TextView mTvType;
    @Bind(R.id.ll_province_container)
    LinearLayout mLLProvince;
    @Bind(R.id.tv_province)
    TextView mTvProvince;
    @Bind(R.id.ll_city_container)
    LinearLayout mLLCity;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.ll_distance_container)
    LinearLayout mLLDistance;
    @Bind(R.id.tv_distance)
    TextView mTvDistance;
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.iv_add_title_img)
    ImageView mIvCover;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private CreateAdNextPagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad_next_page);
        ButterKnife.bind(this);
        mPresenter = new CreateAdNextPagePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("发广告", "", "预览", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mLLTypeContainer.setOnClickListener(this);
        mLLProvince.setOnClickListener(this);
        mLLCity.setOnClickListener(this);
        mLLDistance.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                back();
                break;
            case R.id.ll_type_container:
                break;
            case R.id.ll_distance_container:
                break;
            case R.id.ll_city_container:
                break;
            case R.id.ll_province_container:
                break;
        }
    }
}
