package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.UserPublicInfoEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.UserRewardPresenter;
import com.chunsun.redenvelope.ui.activity.personal.BalanceRechargeActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserRewardView;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.widget.TextButtonDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 用户奖励Activity
 */
public class UserRewardActivity extends BaseActivity implements IUserRewardView, View.OnClickListener {

    @Bind(R.id.iv_head_logo)
    ImageView mIvHeadLogo;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.ll_reward)
    LinearLayout mLLReward;
    @Bind(R.id.et_amount)
    EditText mEtAmount;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.btn_reward)
    Button mBtnReward;
    @Bind(R.id.tv_invitecode)
    TextView mTvInviteCode;
    @Bind(R.id.tv_sex)
    TextView mTvSex;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tv_job)
    TextView mTvJob;
    @Bind(R.id.tv_mobile)
    TextView mTvMobile;
    @Bind(R.id.tv_tel)
    TextView mTvTel;
    @Bind(R.id.tv_weixin)
    TextView mTvWechat;
    @Bind(R.id.tv_qq)
    TextView mTvQQ;
    @Bind(R.id.tv_send_red_total_money)
    TextView mTvSendRedTotal;
    @Bind(R.id.tv_descripe)
    TextView mTvDesc;
    @Bind(R.id.ll_sex_container)
    LinearLayout mLLSex;
    @Bind(R.id.ll_age_container)
    LinearLayout mLLAge;
    @Bind(R.id.ll_job_container)
    LinearLayout mLLJob;

    private String mUserId;
    private String mRedId;

    private UserRewardPresenter mPresenter;
    private String mToken;
    private TextButtonDialog mTextButtonDialog;
    /**
     * 活跃度是否足够
     */
    private boolean mCanTrans;
    private UserPublicInfoEntity.ResultEntity mUserEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reward);
        ButterKnife.bind(this);
        mPresenter = new UserRewardPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("用户信息", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mBtnReward.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mUserId = intent.getStringExtra(Constants.EXTRA_KEY);
            mRedId = intent.getStringExtra(Constants.EXTRA_KEY2);
        }
        mPresenter.getData(mToken, mUserId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_reward:
                mPresenter.reward(mToken, StringUtil.textview2String(mEtAmount), mCanTrans);
                break;
        }
    }

    @Override
    public void setData(UserPublicInfoEntity.ResultEntity entity) {
        mUserEntity = entity;
        mCanTrans = entity.isCan_trans();
        if (entity.isEnable_reward() || !MainApplication.getContext().getUserEntity().getId().equals(mUserId)) {
            mLLReward.setVisibility(View.VISIBLE);
        }
        mPresenter.setSecretInfo(entity, mTvName, mTvMobile, mTvWechat, mTvTel, mTvSendRedTotal, mTvSex, mTvAge, mTvJob, mTvQQ, mTvDesc, mTvInviteCode, mLLSex, mLLAge, mLLJob);
        ImageLoader.getInstance().displayImage(Constants.IMG_HOST_URL + entity.getU_img_url(), mIvHeadLogo, ImageLoaderHelper.getInstance(this).getDisplayOptions(8));
    }

    @Override
    public void showTextButtonDialog(final boolean isEnough) {
        mTextButtonDialog = new TextButtonDialog(this, R.style.progress_dialog, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_confirm_ok:
                        if (isEnough) {//支付
                            payReward();
                        } else {//充值
                            toRechargeActivity();
                            mTextButtonDialog.dismiss();
                        }
                        break;
                    case R.id.btn_confirm_cancel:
                        mTextButtonDialog.dismiss();
                        break;
                }
            }
        });
        mTextButtonDialog.show();
        if (isEnough) {
            mTextButtonDialog.setDialogContent(
                    "您要奖励 “" + mUserEntity.getNick_name() + "”（"
                            + mUserEntity.getInvitation_code()
                            + "） " + StringUtil.textview2String(mEtAmount) + "元", 15);
        } else {
            mTextButtonDialog.setDialogContent("您的余额不够支付奖励金额", 15);
        }
        mTextButtonDialog.diyRechargeDialog(isEnough);
    }

    /**
     * 支付奖励
     */
    private void payReward() {
        if (Constants.INTERACTIVE_PLATFORM_COUNTRY.equals(mRedId)) {
            mPresenter.pay(mToken, mUserId, StringUtil.textview2String(mEtAmount), StringUtil.textview2String(mEtContent), mRedId, "全国", "全国");
        } else if (Constants.INTERACTIVE_PLATFORM_LOCAL.equals(mRedId)) {
            mPresenter.pay(mToken, mUserId, StringUtil.textview2String(mEtAmount), StringUtil.textview2String(mEtContent), mRedId, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity());
        } else {
            mPresenter.pay(mToken, mUserId, StringUtil.textview2String(mEtAmount), StringUtil.textview2String(mEtContent), mRedId, "", "");
        }
    }

    @Override
    public void toRechargeActivity() {
        Intent intent = new Intent(this, BalanceRechargeActivity.class);
        startActivity(intent);
    }

    @Override
    public void paySuccess() {
        back();
    }
}
