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
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUserRewardView;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
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
    /**
     * 活跃度是否足够
     */
    private boolean mCanTrans;

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
        }
    }

    @Override
    public void setData(UserPublicInfoEntity.ResultEntity entity) {
        mCanTrans = entity.isCan_trans();
        if (entity.isEnable_reward() || !MainApplication.getContext().getUserEntity().getId().equals(mUserId)) {
            mLLReward.setVisibility(View.VISIBLE);
        }
        mPresenter.setSecretInfo(entity, mTvName, mTvMobile, mTvWechat, mTvTel, mTvSendRedTotal, mTvSex, mTvAge, mTvJob, mTvQQ, mTvDesc, mTvInviteCode, mLLSex, mLLAge, mLLJob);
        ImageLoader.getInstance().displayImage(Constants.IMG_HOST_URL + entity.getU_img_url(), mIvHeadLogo, ImageLoaderHelper.getInstance(this).getDisplayOptions(8));
    }


}
