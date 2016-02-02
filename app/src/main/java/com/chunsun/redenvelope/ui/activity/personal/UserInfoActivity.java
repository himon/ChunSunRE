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
import com.chunsun.redenvelope.clip.PicClipActivity;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.EditUserInfoEvent;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.UserInfoPresenter;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.ui.activity.SelectListInfoActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailPicShowActivity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserInfoView;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.widget.SettingItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

public class UserInfoActivity extends BaseActivity implements IUserInfoView {

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
    @Bind(R.id.ll_description)
    LinearLayout mSiDesc;
    @Bind(R.id.tv_description)
    TextView mTvDesc;
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
        if (Constants.USER_REGISTER_TYPE_PERSONAL.equals(mUserEntity.getType())) {
            initTitleBar("个人信息", "", "", Constants.TITLE_TYPE_SAMPLE);
            mMoreContentList = new String[]{"头像", "名字", "春笋号", "手机号",
                    "电话", "微信", "支付宝", "身份证号", "个性签名", "", "性别", "生日",
                    "职业", "QQ"};
        } else if (Constants.USER_REGISTER_TYPE_ENTERPRISE.equals(mUserEntity.getType())) {
            initTitleBar("个人信息", "", "", Constants.TITLE_TYPE_SAMPLE);
            mMoreContentList = new String[]{"企业形象", "名称", "春笋号", "手机号",
                    "电话", "微信", "支付宝", "", "企业介绍", "认证", "", "", "", "QQ"};
        }
        mPresenter.initData();


    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.ital_name:
                toEdit(mMoreContentList[1], mSiName.getData(), 1, Constants.EDIT_TYPE_NICK_NAME);
                break;
            case R.id.ital_logo_info://头像设置
                selectHeadImage();
                break;
            case R.id.iv_logo://头像查看
                seeHead();
                break;
            case R.id.ital_chunsun_account://春笋号
                toEdit(mMoreContentList[2], mSiAccount.getData(), 1, Constants.EDIT_TYPE_CHUNSUN_ACCOUNT);
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
                toEdit(mMoreContentList[3], mSiPhone.getData(), 1, Constants.EDIT_TYPE_PHONE);
                break;
            case R.id.ital_tel:
                toEdit(mMoreContentList[4], mSiTel.getData(), 1, Constants.EDIT_TYPE_TEL);
                break;
            case R.id.ital_weixin:
                toEdit(mMoreContentList[5], mSiWechat.getData(), 1, Constants.EDIT_TYPE_WECHAT);
                break;
            case R.id.ital_qq:
                toEdit(mMoreContentList[13], mSiQQ.getData(), 1, Constants.EDIT_TYPE_QQ);
                break;
            case R.id.ital_zhifubao:
                toEdit(mMoreContentList[6], mSiAlipay.getData(), 1, Constants.EDIT_TYPE_ALIPAY);
                break;
            case R.id.ital_identify_code:
                toEdit(mMoreContentList[7], mSiId.getData(), 1, Constants.EDIT_TYPE_ID_CARD);
                break;
            case R.id.ll_description://个性签名
                toEdit(mMoreContentList[8], mTvDesc.getText().toString(), 10, Constants.EDIT_TYPE_DESC);
                break;
            case R.id.ital_authentication://认证
                Intent intent = new Intent(this, AuthenticationActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void seeHead() {

        ArrayList<String> list = new ArrayList<>();
        list.add(Constants.IMG_HOST_URL + mUserEntity.getImg_url());

        Intent intent = new Intent(this, RedDetailPicShowActivity.class);
        intent.putExtra(Constants.EXTRA_LIST_KEY, list);
        intent.putExtra(Constants.EXTRA_KEY, 0);
        intent.putExtra(Constants.EXTRA_KEY2, true);
        startActivity(intent);
    }

    /**
     * 设置头像
     */
    private void selectHeadImage() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setPhotoCount(1);
        intent.setShowCamera(true);
        startActivityForResult(intent, Constants.REQUEST_CODE);
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

    private void toEdit(String title, String content, int lines, int requestCode) {
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

        setHeadImage(Constants.IMG_HOST_URL + mUserEntity.getImg_url());
        //头像
        mTvLogoDesc.setText(mMoreContentList[0]);
        //名称
        mSiName.setContentTextBlack(mMoreContentList[1], mUserEntity.getNick_name());
        //春笋号
        mSiAccount.setContentTextBlack(mMoreContentList[2], mUserEntity.getUser_name());
        //手机
        mSiPhone.setContentNoArrow(mMoreContentList[3], mUserEntity.getMobile());
        //电话
        mSiTel.setContentTextBlack(mMoreContentList[4], mUserEntity.getTelphone());
        //微信
        mSiWechat.setContentTextBlack(mMoreContentList[5], mUserEntity.getWeixin());
        //支付宝
        mSiAlipay.setContentTextBlack(mMoreContentList[6], mUserEntity.getZhifubao());
        //身份证
        if (TextUtils.isEmpty(mMoreContentList[7])) {
            mSiId.setVisibility(View.GONE);
        } else {
            mSiId.setContentTextBlack(mMoreContentList[7], mUserEntity.getID_num());
            mSiId.setOnClickListener(this);
            mSiId.setVisibility(View.VISIBLE);
        }
        //签名介绍
        mTvDesc.setText(mUserEntity.getRemark());
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
            mSiAuthentication.setContentTextBlack(mMoreContentList[9], is_v);
            mSiAuthentication.setOnClickListener(this);
            mSiAuthentication.setVisibility(View.VISIBLE);
        }
        //性别
        if (TextUtils.isEmpty(mMoreContentList[10])) {
            mSiSex.setVisibility(View.GONE);
        } else {
            mSiSex.setContentTextBlack(mMoreContentList[10], mUserEntity.getSex());
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
            mSiBirthday.setContentTextBlack(mMoreContentList[11], birthday);
            mSiBirthday.setOnClickListener(this);
            mSiBirthday.setVisibility(View.VISIBLE);
        }
        //职业
        if (TextUtils.isEmpty(mMoreContentList[12])) {
            mSiJob.setVisibility(View.GONE);
        } else {
            mSiJob.setContentTextBlack(mMoreContentList[12], mUserEntity.getJob());
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
            mSiQQ.setContentTextBlack(mMoreContentList[13], mUserEntity.getQq());
            mSiQQ.setVisibility(View.VISIBLE);
            mSiQQ.setOnClickListener(this);
        }
    }

    @Override
    public void editUserBirthdaySuccess(String birthday) {
        mSiBirthday.setData(birthday);
        MainApplication.getContext().getUserEntity().setBirthday(birthday);
    }

    @Override
    public void editUserHeadLogoSuccess(SampleResponseEntity entity) {
        EventBus.getDefault().post(new MainEvent(Constants.FROM_ME));
        ShowToast.Short(entity.getMsg());
        String result = entity.getResult();
        //注意split
        String[] split = result.split("\\|");
        MainApplication.getContext().getUserEntity().setImg_url(split[0]);
        MainApplication.getContext().getUserEntity().setThumb_img_url(split[1]);
        setHeadImage(Constants.IMG_HOST_URL + split[1]);
    }

    /**
     * 设置头像图片
     *
     * @param path
     */
    private void setHeadImage(String path) {
        if (!TextUtils.isEmpty(path)) {
            ImageLoader.getInstance().displayImage(path, mIvLogo, ImageLoaderHelper.getInstance(this).getDisplayOptions(8));
        }
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
                mTvDesc.setText(event.getContent());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE) {
            if (data != null) {
                List<Photo> photos = data.getParcelableArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                toImageCutActivity(photos.get(0).getPath());
            }
        } else if (requestCode == Constants.REQUEST_CODE_IMAGE_CUT) {
            if (data != null) {
                mPresenter.saveHeadLogo(mToken, data);
            }
        }
    }


    /**
     * 跳转到剪切图片Activity
     *
     * @param path
     */
    private void toImageCutActivity(String path) {
        Intent intent = new Intent(this, PicClipActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, path);
        intent.putExtra(Constants.EXTRA_KEY2, false);
        startActivityForResult(intent, Constants.REQUEST_CODE_IMAGE_CUT);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }
}
