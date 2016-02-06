package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.clip.BitmapClipUtils;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.EditUserInfoEvent;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.AuthenticationPresenter;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IAuthenticationView;
import com.chunsun.redenvelope.utils.Base64Utils;
import com.chunsun.redenvelope.utils.ImageUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

/**
 * 企业账号认证Activity
 */
public class AuthenticationActivity extends MBaseActivity<IAuthenticationView, AuthenticationPresenter> implements IAuthenticationView {

    @Bind(R.id.iv_business_license)
    ImageView mIvBusiness;
    @Bind(R.id.iv_hand_on_identify_card)
    ImageView mIvIdentify;
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_tel)
    EditText mEtTel;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.et_bank_num)
    EditText mEtBankNum;
    @Bind(R.id.et_bank_name)
    EditText mEtBankName;
    @Bind(R.id.et_tax_num)
    EditText mEtTaxNum;
    @Bind(R.id.et_operator)
    EditText mEtOperator;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.btn_commit_apply)
    Button mBtnCommit;

    private AuthenticationPresenter mPresenter;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        mPresenter = (AuthenticationPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new AuthenticationPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("认证", "", "说明", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavRight.setOnClickListener(this);
        mIvBusiness.setOnClickListener(this);
        mIvIdentify.setOnClickListener(this);
        mBtnCommit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        String is_v = userEntity.getIs_v();

        mEtName.setText(userEntity.getCompany_name());
        //光标放到最末尾
        Editable etext = mEtName.getText();
        Selection.setSelection(etext, etext.length());

        mEtTel.setText(userEntity.getCompany_tel());
        mEtAddress.setText(userEntity.getAddress());
        mEtOperator.setText(userEntity.getCompany_contact());
        mEtPhone.setText(userEntity.getContact_mobile());
        mEtBankNum.setText(userEntity.getBank_no());
        mEtBankName.setText(userEntity.getBank_name());
        mEtTaxNum.setText(userEntity.getTax_no());

        setImage(Constants.IMG_HOST_URL + userEntity.getLicence_img_url(), mIvBusiness, 1);
        setImage(Constants.IMG_HOST_URL + userEntity.getID_img_url(), mIvIdentify, 2);

        if ("1".equals(is_v) || "2".equals(is_v)) {
            mIvBusiness.setEnabled(false);
            mIvIdentify.setEnabled(false);
            mEtName.setEnabled(false);
            mEtTel.setEnabled(false);
            mEtAddress.setEnabled(false);
            mEtOperator.setEnabled(false);
            mEtPhone.setEnabled(false);
            mEtBankNum.setEnabled(false);
            mEtBankName.setEnabled(false);
            mEtTaxNum.setEnabled(false);
            mBtnCommit.setVisibility(View.GONE);
        } else {
            mIvBusiness.setEnabled(true);
            mIvIdentify.setEnabled(true);
            mEtName.setEnabled(true);
            mEtTel.setEnabled(true);
            mEtAddress.setEnabled(true);
            mEtOperator.setEnabled(true);
            mEtPhone.setEnabled(true);
            mEtBankNum.setEnabled(true);
            mEtBankName.setEnabled(true);
            mEtTaxNum.setEnabled(true);
            mBtnCommit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
                toAuthenticationInstruction();
                break;
            case R.id.iv_business_license:
                sign = 1;
                selectImage();
                break;
            case R.id.iv_hand_on_identify_card:
                sign = 2;
                selectImage();
                break;
            case R.id.btn_commit_apply:
                commit();
                break;
        }
    }

    private void commit() {
        String name = mEtName.getText().toString();
        String tel = mEtTel.getText().toString();
        String addr = mEtAddress.getText().toString();
        String phone = mEtPhone.getText().toString();
        String operator = mEtOperator.getText().toString();
        String bankNum = mEtBankNum.getText().toString();
        String bankName = mEtBankName.getText().toString();
        String taxNum = mEtTaxNum.getText().toString();
        mPresenter.commit(mToken, name, tel, addr, phone, operator, bankNum, bankName, taxNum, mBusinessLicenseBase64, mIdentifyCardBase64);
    }

    private String mBusinessLicenseBase64;
    private String mIdentifyCardBase64;
    private int sign = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE) {
            if (data != null) {
                List<Photo> photos = data.getParcelableArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                String path = photos.get(0).getPath();
                toImageCutActivity(path);
            }
        }
    }

    /**
     * 压缩图片，转base64
     *
     * @param path
     */
    private void toImageCutActivity(String path) {
        int degree = ImageUtils.readPictureDegree(path);
        Bitmap bitmap = ImageUtils.rotaingImageView(degree,
                BitmapClipUtils
                        .createImageThumbnailScale(path, 800));
        if (sign == 1) {
            mBusinessLicenseBase64 = Base64Utils.bitmapToBase64(bitmap);
            setImage(path, mIvBusiness, 0);
        } else if (sign == 2) {
            mIdentifyCardBase64 = Base64Utils.bitmapToBase64(bitmap);
            setImage(path, mIvIdentify, 0);
        }
    }

    /**
     * 设置图片
     *
     * @param path
     */
    private void setImage(String path, final ImageView imageView, final int index) {
        if (!TextUtils.isEmpty(path)) {
            Glide.with(this)
                    .load(path)
                    .asBitmap()
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_default_capture)
                    .error(R.drawable.img_default_error)
                    .into(new SimpleTarget<Bitmap>(800, 480) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            imageView.setImageBitmap(bitmap);
                            if (index == 1) {
                                mBusinessLicenseBase64 = Base64Utils.bitmapToBase64(bitmap);
                            } else if (index == 2) {
                                mIdentifyCardBase64 = Base64Utils.bitmapToBase64(bitmap);
                            }
                        }
                    });
        }
    }

    /**
     * 跳转认证说明
     */
    private void toAuthenticationInstruction() {
        Intent intent = new Intent(this, AuthenticationInstructionActivity.class);
        startActivity(intent);
    }

    /**
     * 选择广告封面图
     */
    private void selectImage() {
        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setPhotoCount(1);
        intent.setShowCamera(true);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }


    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    @Override
    public void setCommitSuccess() {
        EventBus.getDefault().post(new MainEvent(Constants.REFRESH_ME));
        EventBus.getDefault().post(new EditUserInfoEvent(Constants.EDIT_TYPE_AUTHENTICATION, "审核中"));
        back();
    }
}
