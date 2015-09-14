package com.chunsun.redenvelope.ui.activity.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.UserPrivacyPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserPrivacyView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 隐私设置Activity
 */
public class UserPrivacyActivity extends BaseActivity implements IUserPrivacyView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.tv_name_title_tag)
    TextView mTvNameTitleTag;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tb_name_is_secret)
    ToggleButton mTbName;
    @Bind(R.id.tv_sex)
    TextView mTvGender;
    @Bind(R.id.tb_sex_is_secret)
    ToggleButton mTbGender;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tb_age_is_secret)
    ToggleButton mTbAge;
    @Bind(R.id.tv_job)
    TextView mTvJob;
    @Bind(R.id.tb_job_is_secret)
    ToggleButton mTbJob;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.tb_phone_is_secret)
    ToggleButton mTbPhone;
    @Bind(R.id.tv_tel)
    TextView mTvTel;
    @Bind(R.id.tb_tel_is_secret)
    ToggleButton mTbTel;
    @Bind(R.id.tv_weixin)
    TextView mTvWeChat;
    @Bind(R.id.tb_weixin_is_secret)
    ToggleButton mTbWeChat;
    @Bind(R.id.tv_qq)
    TextView mTvQQ;
    @Bind(R.id.tb_qq_is_secret)
    ToggleButton mTbQQ;
    @Bind(R.id.tv_send_red)
    TextView mTvSend;
    @Bind(R.id.tb_send_red_is_secret)
    ToggleButton mTbSend;
    @Bind(R.id.ll_sex_container)
    LinearLayout mLLSex;
    @Bind(R.id.ll_age_container)
    LinearLayout mLLAge;
    @Bind(R.id.ll_job_container)
    LinearLayout mLLJob;

    private UserPrivacyPresenter mPresenter;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_privacy);
        ButterKnife.bind(this);
        mPresenter = new UserPrivacyPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("隐私", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        if (userEntity != null) {
            mTvName.setText(userEntity.getNick_name());
            mTvPhone.setText(userEntity.getMobile());
            mTvWeChat.setText(userEntity.getWeixin());
            mTvTel.setText(userEntity.getTelphone());
            mTvSend.setText(userEntity.getHas_send_amount());

            mTvGender.setText(userEntity.getSex());
            mTvAge.setText(userEntity.getAge());
            mTvJob.setText(userEntity.getJob());
            mTvQQ.setText(userEntity.getQq());

            if (Constants.USER_REGISTER_TYPE_PERSONAL.equals(userEntity.getType())) {//个人
                mTvNameTitleTag.setText("名字");
                mLLAge.setVisibility(View.VISIBLE);
                mLLSex.setVisibility(View.VISIBLE);
                mLLJob.setVisibility(View.VISIBLE);
            } else if (Constants.USER_REGISTER_TYPE_ENTERPRISE.equals(userEntity.getType())) {//企业
                mTvNameTitleTag.setText("名称");
                mLLAge.setVisibility(View.GONE);
                mLLSex.setVisibility(View.GONE);
                mLLJob.setVisibility(View.GONE);
            }

            initToggle(userEntity.getJson());
        }
    }

    private void initToggle(String private_json) {
        boolean isShowName = true;
        boolean isShowPhone = true;
        boolean isShowWeChat = true;
        boolean isShowTel = true;
        boolean isShowSend = true;
        boolean isShowSex = true;
        boolean isShowAge = true;
        boolean isShowJob = true;
        boolean isShowQQ = true;

        if (!TextUtils.isEmpty(private_json)) {
            try {
                JSONObject obj = new JSONObject(private_json);
                isShowName = obj.getString("nick_name").equalsIgnoreCase("true") ? true : false;
                isShowPhone = obj.getString("mobile").equalsIgnoreCase("true") ? true : false;
                isShowWeChat = obj.getString("weixin").equalsIgnoreCase("true") ? true : false;
                isShowTel = obj.getString("telphone").equalsIgnoreCase("true") ? true : false;
                isShowSend = obj.getString("has_send_amount").equalsIgnoreCase("true") ? true : false;
                isShowSex = obj.getString("sex").equalsIgnoreCase("true") ? true : false;
                isShowAge = obj.getString("birthday").equalsIgnoreCase("true") ? true : false;
                isShowJob = obj.getString("job").equalsIgnoreCase("true") ? true : false;
                isShowQQ = obj.getString("qq").equalsIgnoreCase("true") ? true : false;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mTbName.setChecked(isShowName);
            mTbPhone.setChecked(isShowPhone);
            mTbWeChat.setChecked(isShowWeChat);
            mTbTel.setChecked(isShowTel);
            mTbSend.setChecked(isShowSend);
            mTbGender.setChecked(isShowSex);
            mTbAge.setChecked(isShowAge);
            mTbJob.setChecked(isShowJob);
            mTbQQ.setChecked(isShowQQ);

            mTbName.setOnCheckedChangeListener(this);
            mTbPhone.setOnCheckedChangeListener(this);
            mTbWeChat.setOnCheckedChangeListener(this);
            mTbTel.setOnCheckedChangeListener(this);
            mTbSend.setOnCheckedChangeListener(this);
            mTbGender.setOnCheckedChangeListener(this);
            mTbAge.setOnCheckedChangeListener(this);
            mTbJob.setOnCheckedChangeListener(this);
            mTbQQ.setOnCheckedChangeListener(this);
        }
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.tb_name_is_secret:
            case R.id.tb_phone_is_secret:
            case R.id.tb_weixin_is_secret:
            case R.id.tb_tel_is_secret:
            case R.id.tb_send_red_is_secret:
            case R.id.tb_sex_is_secret:
            case R.id.tb_age_is_secret:
            case R.id.tb_job_is_secret:
            case R.id.tb_qq_is_secret:
                String name = String.valueOf(mTbName.isChecked());
                String phone = String.valueOf(mTbPhone.isChecked());
                String wechat = String.valueOf(mTbWeChat.isChecked());
                String tel = String.valueOf(mTbTel.isChecked());
                String send = String.valueOf(mTbSend.isChecked());
                String sex = String.valueOf(mTbGender.isChecked());
                String age = String.valueOf(mTbAge.isChecked());
                String job = String.valueOf(mTbJob.isChecked());
                String qq = String.valueOf(mTbQQ.isChecked());
                mPresenter.updateUserInfo(mToken, name, phone, wechat, tel, send, sex, age, job, qq);
                break;
        }
    }

    @Override
    public void updateSuccess(String mValue) {
        MainApplication.getContext().getUserEntity().setJob(mValue);
    }
}
