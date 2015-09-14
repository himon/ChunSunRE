package com.chunsun.redenvelope.ui.activity.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.chunsun.redenvelope.presenter.impl.UserInfoPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserInfoView;
import com.chunsun.redenvelope.widget.SettingItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserInfoActivity extends BaseActivity implements IUserInfoView, View.OnClickListener {

    @Bind(R.id.ital_logo_info)
    LinearLayout mLLHeadLogo;
    @Bind(R.id.tv_logo_name)
    TextView mTvLogoDesc;
    @Bind(R.id.iv_logo)
    ImageView mIvLogo;
    @Bind(R.id.ital_name)
    SettingItem mSiName;
    @Bind(R.id.ital_chunsun_account)
    SettingItem mSiAccount;
    @Bind(R.id.ital_sex)
    SettingItem mSiSex;
    @Bind(R.id.ital_birthday)
    SettingItem mSiBirthday;
    @Bind(R.id.ital_job)
    SettingItem mSiJob;
    @Bind(R.id.ital_phone)
    SettingItem mSiPhone;
    @Bind(R.id.ital_tel)
    SettingItem mSiTel;
    @Bind(R.id.ital_weixin)
    SettingItem mSiWechat;
    @Bind(R.id.ital_qq)
    SettingItem mSiQQ;
    @Bind(R.id.ital_zhifubao)
    SettingItem mSiAlipay;
    @Bind(R.id.ital_identify_code)
    SettingItem mSiId;
    @Bind(R.id.ital_description)
    SettingItem mSiDesc;
    @Bind(R.id.ital_authentication)
    SettingItem mSiAuthentication;


    private UserInfoEntity mUserEntity;
    private String[] mMoreContentList;

    private UserInfoPresenter mPresenter;
    private SampleEntity mSexEntity;
    private SampleEntity mJsoEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mPresenter = new UserInfoPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        //
        mLLHeadLogo.setOnClickListener(this);
        mIvLogo.setOnClickListener(this);
        mSiName.setOnClickListener(this);
        mSiAccount.setOnClickListener(this);
        mSiPhone.setOnClickListener(this);
        mSiTel.setOnClickListener(this);
        mSiWechat.setOnClickListener(this);
        mSiAlipay.setOnClickListener(this);
        mSiDesc.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mUserEntity = MainApplication.getContext().getUserEntity();
        if (Constants.REGISTER_TYPE_PERSONAL.equals(mUserEntity.getType())) {
            initTitleBar("个人信息", "", "", Constants.TITLE_TYPE_SAMPLE);
            mMoreContentList = new String[]{"头像", "名字", "春笋号", "手机号",
                    "电话", "微信", "支付宝", "身份证号", "个性签名", "", "性别", "生日",
                    "职业", "QQ"};
        } else if (Constants.REGISTER_TYPE_COMPANY.equals(mUserEntity.getType())) {
            initTitleBar("个人信息", "", "", Constants.TITLE_TYPE_SAMPLE);
            mMoreContentList = new String[]{"企业形象", "名称", "春笋号", "手机号",
                    "电话", "微信", "支付宝", "", "企业介绍", "认证", "", "", "", "QQ"};
        }

        mPresenter.initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
        }
    }

    @Override
    public void setData(List<SampleEntity> sexList, List<SampleEntity> jobList) {
        //头像
        mTvLogoDesc.setText(mMoreContentList[0]);
        //名称
        mSiName.setContent(mMoreContentList[1], mUserEntity.getNick_name());
        //春笋号
        mSiAccount.setContent(mMoreContentList[2], mUserEntity.getUser_name());
        //手机
        mSiPhone.setContent(mMoreContentList[3], mUserEntity.getMobile());
        //电话
        mSiTel.setContent(mMoreContentList[4], mUserEntity.getTelphone());
        //微信
        mSiWechat.setContent(mMoreContentList[5], mUserEntity.getWeixin());
        //支付宝
        mSiAlipay.setContent(mMoreContentList[6], mUserEntity.getZhifubao());
        //身份证
        if (TextUtils.isEmpty(mMoreContentList[7])) {
            mSiId.setVisibility(View.GONE);
        } else {
            mSiId.setContent(mMoreContentList[7], mUserEntity.getID_num());
            mSiId.setOnClickListener(this);
            mSiId.setVisibility(View.VISIBLE);
        }
        //签名介绍
        mSiDesc.setContent(mMoreContentList[8], mUserEntity.getRemark());
        //认证
        if (TextUtils.isEmpty(mMoreContentList[9])) {
            mSiAuthentication.setVisibility(View.GONE);
        } else {
            String is_v = mUserEntity.getIs_v();
            if ("0".equals(is_v)) {
                is_v = "未认证";
            } else if ("1".equals(is_v)) {
                is_v = "审核中";
            } else if ("2".equals(is_v)) {
                is_v = "已认证";
            } else if ("3".equals(is_v)) {
                is_v = "认证驳回";
            }
            mSiAuthentication.setContent(mMoreContentList[9], is_v);
            mSiAuthentication.setOnClickListener(this);
            mSiAuthentication.setVisibility(View.VISIBLE);
        }
        //性别
        if (TextUtils.isEmpty(mMoreContentList[10])) {
            mSiSex.setVisibility(View.GONE);
        } else {
            mSiSex.setContent(mMoreContentList[10], mUserEntity.getSex());
            mSiSex.setOnClickListener(this);
            mSiSex.setVisibility(View.VISIBLE);

            for (SampleEntity item : sexList) {
                if (item.getValue().equals(mUserEntity.getSex())) {
                    mSexEntity = item;
                    break;
                }
            }
        }
        //生日
        if (TextUtils.isEmpty(mMoreContentList[11])) {
            mSiBirthday.setVisibility(View.GONE);
        } else {
            String birthday = "";
            if (!TextUtils.isEmpty(mUserEntity.getBirthday())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                Date date = new Date(mUserEntity.getBirthday());
                birthday = simpleDateFormat.format(date);
            }
            mSiBirthday.setContent(mMoreContentList[11], birthday);
            mSiBirthday.setOnClickListener(this);
            mSiBirthday.setVisibility(View.VISIBLE);
        }
        //职业
        if(TextUtils.isEmpty(mMoreContentList[12])){
            mSiJob.setVisibility(View.GONE);
        }else{
            mSiJob.setContent(mMoreContentList[12], mUserEntity.getJob());
            mSiJob.setOnClickListener(this);
            mSiJob.setVisibility(View.VISIBLE);

            for(SampleEntity item : jobList){
                if(item.getValue().equals(mUserEntity.getJob())){
                    mJsoEntity = item;
                    break;
                }
            }
        }
        //QQ
        if(TextUtils.isEmpty(mMoreContentList[13])){
            mSiQQ.setVisibility(View.GONE);
        }else{
            mSiQQ.setContent(mMoreContentList[13], mUserEntity.getQq());
            mSiQQ.setVisibility(View.VISIBLE);
            mSiQQ.setOnClickListener(this);
        }
    }
}
