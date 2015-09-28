package com.chunsun.redenvelope.ui.fragment.tab;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.MeFragmentPresenter;
import com.chunsun.redenvelope.scanlibrary.constants.Constants;
import com.chunsun.redenvelope.ui.activity.personal.BalanceRechargeActivity;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeActivity;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMeFragment extends BaseFragment implements IMeFragmentView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.iv_head_icon)
    ImageView mIvHeadLogo;
    @Bind(R.id.iv_proxy_icon)
    ImageView mIvProxyIcon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.ll_invite_code)
    LinearLayout mLLInviteCode;
    @Bind(R.id.tv_account)
    TextView mTvAccount;
    @Bind(R.id.tv_payoff)
    TextView mTvPayOff;
    @Bind(R.id.tv_invite_code)
    TextView mTvInviteCode;
    @Bind(R.id.ib_wallet)
    ImageButton mIbWallet;
    @Bind(R.id.ib_send_ad_record)
    ImageButton mIbSendAdRecord;
    @Bind(R.id.ib_not_receiving)
    ImageButton mIbNotReceiving;
    @Bind(R.id.ib_phone_recharge)
    ImageButton mIbRecharge;
    @Bind(R.id.ib_collect)
    ImageButton mIbCollect;
    @Bind(R.id.ib_setting)
    ImageButton mIbSetting;


    private MeFragmentPresenter mPresenter;
    private DisplayImageOptions mDisplayOptions;
    private DecimalFormat mFormat;
    private UserInfoEntity mUserInfoEntity;

    public NewMeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_me, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new MeFragmentPresenter(this);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {
        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        initEvent();
    }

    private void initEvent() {
        mIvHeadLogo.setOnClickListener(this);
        mLLInviteCode.setOnClickListener(this);
        mIbWallet.setOnClickListener(this);
        mIbSendAdRecord.setOnClickListener(this);
        mIbNotReceiving.setOnClickListener(this);
        mIbRecharge.setOnClickListener(this);
        mIbCollect.setOnClickListener(this);
        mIbSetting.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mDisplayOptions = ImageLoaderHelper.getInstance(getActivity()).getDisplayOptions(8);
    }

    public void getData() {
        mUserInfoEntity = mPresenter.getData(new Preferences(getActivity()).getToken());
        setData();
    }

    /**
     * 设置用户信息
     */
    private void setData() {
        if (mUserInfoEntity != null) {
            if (mFormat == null) {
                mFormat = new DecimalFormat("#.##");
            }
            mTvName.setText(mUserInfoEntity.getNick_name());
            mTvAccount.setText("春笋号：" + mUserInfoEntity.getUser_name());
            mTvInviteCode.setText(mUserInfoEntity.getInvitation_code());
            mTvPayOff.setText(mFormat.format(mUserInfoEntity.getCumulative_gain()));
            if (mUserInfoEntity.is_proxy()) {
                mIvProxyIcon.setVisibility(View.VISIBLE);
            }
            ImageLoader.getInstance().displayImage(mUserInfoEntity.getThumb_img_url(), mIvHeadLogo, mDisplayOptions);
            mPtr.refreshComplete();
        }
    }


    /**
     * 跳转个人中心
     */
    @Override
    public void toMeInfomation() {
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转我的邀请码
     */
    @Override
    public void toMineInviteCode() {
        Intent intent = new Intent(getActivity(), MineInviteCodeActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转余额
     */
    @Override
    public void toBalance() {
        Intent intent = new Intent(getActivity(), WalletActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转发广告记录
     */
    @Override
    public void toAdRecord() {
        Intent intent = new Intent(getActivity(), SendRedEnvelopeRecordClassifyActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转未领取红包
     */
    @Override
    public void toNotReceivingRed() {
        Intent intent = new Intent(getActivity(), NotReceivingRedActivity.class);
        startActivity(intent);
    }

    /**
     * 充值
     */
    @Override
    public void toRecharge() {
        Intent intentRecharge = new Intent(getActivity(),
                BalanceRechargeActivity.class);
        intentRecharge.putExtra(Constants.MESSAGE_EXTRA,
                mUserInfoEntity.isEnable_unionpay());
        startActivity(intentRecharge);
    }

    /**
     * 跳转收藏
     */
    @Override
    public void toCollect() {
        Intent intent = new Intent(getActivity(), CollectRedEnvelopeListActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转设置
     */
    @Override
    public void toSetting() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void refresh(UserInfoEntity entity) {
        mUserInfoEntity = entity;
        setData();
    }

    @Override
    public void toLogin() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_icon:
                toMeInfomation();
                break;
            case R.id.ll_invite_code:
                toMineInviteCode();
                break;
            case R.id.ib_wallet:
                toBalance();
                break;
            case R.id.ib_send_ad_record:
                toAdRecord();
                break;
            case R.id.ib_not_receiving:
                toNotReceivingRed();
                break;
            case R.id.ib_phone_recharge:
                toRecharge();
                break;
            case R.id.ib_collect:
                toCollect();
                break;
            case R.id.ib_setting:
                toSetting();
                break;
        }
    }
}
