package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
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
import com.chunsun.redenvelope.model.event.EditUserInfoEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.UserInfoPresenter;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.ui.activity.SelectListInfoActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserInfoView;
import com.chunsun.redenvelope.widget.SettingItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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
    private ArrayList<SampleEntity> mSexList;
    private ArrayList<SampleEntity> mJsoList;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
        //mSiPhone.setOnClickListener(this);
        mSiTel.setOnClickListener(this);
        mSiWechat.setOnClickListener(this);
        mSiAlipay.setOnClickListener(this);
        mSiDesc.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

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
            case R.id.ital_name:
                toEdit(mMoreContentList[1], mSiName.getData(), "1", Constants.EDIT_TYPE_NICK_NAME);
                break;
            case R.id.ital_logo_info://头像设置
                break;
            case R.id.iv_logo://头像查看
                break;
            case R.id.ital_chunsun_account://春笋号
                toEdit(mMoreContentList[2], mSiAccount.getData(), "1", Constants.EDIT_TYPE_CHUNSUN_ACCOUNT);
                break;
            case R.id.ital_sex:
                toEditSex();
                break;
            case R.id.ital_birthday:
                toEditBirthday();
                break;
            case R.id.ital_job:
                toEditJob();
                break;
            case R.id.ital_phone:
                toEdit(mMoreContentList[3], mSiPhone.getData(), "1", Constants.EDIT_TYPE_PHONE);
                break;
            case R.id.ital_tel:
                toEdit(mMoreContentList[4], mSiTel.getData(), "1", Constants.EDIT_TYPE_TEL);
                break;
            case R.id.ital_weixin:
                toEdit(mMoreContentList[4], mSiWechat.getData(), "1", Constants.EDIT_TYPE_WECHAT);
                break;
            case R.id.ital_qq:
                toEdit(mMoreContentList[13], mSiQQ.getData(), "1", Constants.EDIT_TYPE_QQ);
                break;
            case R.id.ital_zhifubao:
                toEdit(mMoreContentList[6], mSiAlipay.getData(), "1", Constants.EDIT_TYPE_ALIPAY);
                break;
            case R.id.ital_identify_code:
                toEdit(mMoreContentList[7], mSiId.getData(), "1", Constants.EDIT_TYPE_ID_CARD);
                break;
            case R.id.ital_description://个性签名
                toEdit(mMoreContentList[7], mSiDesc.getData(), "1", Constants.EDIT_TYPE_DESC);
                break;
            case R.id.ital_authentication://认证
                break;
        }
    }

    private void toEditJob() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, mMoreContentList[12]);
        if (mJsoList != null) {
            intent.putExtra(Constants.EXTRA_LIST_KEY, mJsoList);
        }
        startActivity(intent);
    }

    private void toEditSex() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, mMoreContentList[10]);
        if (mSexList != null) {
            intent.putExtra(Constants.EXTRA_LIST_KEY, mSexList);
        }
        startActivity(intent);
    }

    private void toEditBirthday() {
        mPresenter.editBirthday(mToken);
    }

    private void toEdit(String title, String content, String lines, int requestCode) {
        Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_LINES, lines);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, title);
        intent.putExtra(Constants.EXTRA_KEY_TEXT, content);
        intent.putExtra(Constants.EXTRA_KEY_TYPE, requestCode);
        startActivity(intent);
    }

    @Override
    public void setData(ArrayList<SampleEntity> sexList, ArrayList<SampleEntity> jobList) {
        mSexList = sexList;
        mJsoList = jobList;

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
                    item.setCheck(true);
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
        if (TextUtils.isEmpty(mMoreContentList[12])) {
            mSiJob.setVisibility(View.GONE);
        } else {
            mSiJob.setContent(mMoreContentList[12], mUserEntity.getJob());
            mSiJob.setOnClickListener(this);
            mSiJob.setVisibility(View.VISIBLE);

            for (SampleEntity item : jobList) {
                if (item.getValue().equals(mUserEntity.getJob())) {
                    item.setCheck(true);
                    break;
                }
            }
        }
        //QQ
        if (TextUtils.isEmpty(mMoreContentList[13])) {
            mSiQQ.setVisibility(View.GONE);
        } else {
            mSiQQ.setContent(mMoreContentList[13], mUserEntity.getQq());
            mSiQQ.setVisibility(View.VISIBLE);
            mSiQQ.setOnClickListener(this);
        }
    }

    @Override
    public void editUserBirthdaySuccess(String birthday) {
        mSiBirthday.setData(birthday);
        MainApplication.getContext().getUserEntity().setBirthday(birthday);
    }

    public void onEvent(EditUserInfoEvent event) {
        switch (event.getMsg()) {
            case Constants.EDIT_TYPE_CHUNSUN_ACCOUNT:
                mSiAccount.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setUser_name(event.getContent());
                break;
            case Constants.EDIT_TYPE_NICK_NAME:
                mSiName.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setNick_name(event.getContent());
                break;
            case Constants.EDIT_TYPE_PHONE:
                mSiPhone.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setMobile(event.getContent());
                break;
            case Constants.EDIT_TYPE_TEL:
                mSiTel.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setTelphone(event.getContent());
                break;
            case Constants.EDIT_TYPE_WECHAT:
                mSiWechat.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setWeixin(event.getContent());
                break;
            case Constants.EDIT_TYPE_QQ:
                mSiQQ.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setQq(event.getContent());
                break;
            case Constants.EDIT_TYPE_ALIPAY:
                mSiAlipay.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setZhifubao(event.getContent());
                break;
            case Constants.EDIT_TYPE_ID_CARD:
                mSiId.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setID_num(event.getContent());
                break;
            case Constants.EDIT_TYPE_DESC:
                mSiDesc.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setRemark(event.getContent());
                break;
            case Constants.EDIT_TYPE_SEX:
                mSiSex.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setSex(event.getContent());
                for (SampleEntity item : mSexList) {
                    if (item.getValue().equals(event.getContent())) {
                        item.setCheck(true);
                    } else {
                        item.setCheck(false);
                    }
                }
                break;
            case Constants.EDIT_TYPE_JOB:
                mSiJob.setData(event.getContent());
                MainApplication.getContext().getUserEntity().setJob(event.getContent());
                for (SampleEntity item : mJsoList) {
                    if (item.getValue().equals(event.getContent())) {
                        item.setCheck(true);
                    } else {
                        item.setCheck(false);
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
