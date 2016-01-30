package com.chunsun.redenvelope.ui.fragment.tab;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.event.MeFragmentRefreshEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.MeFragmentPresenter;
import com.chunsun.redenvelope.scanlibrary.CaptureActivity;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.personal.AtMeActivity;
import com.chunsun.redenvelope.ui.activity.personal.ChunsunCouponWebActivity;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeWebActivity;
import com.chunsun.redenvelope.ui.activity.personal.MyCircleListActivity;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.ui.base.fragment.MBaseFragment;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;
import com.chunsun.redenvelope.widget.CircleTransform;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewMeFragment extends MBaseFragment<IMeFragmentView, MeFragmentPresenter> implements IMeFragmentView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.iv_head_icon)
    ImageView mIvHeadLogo;
    @Bind(R.id.iv_proxy_icon)
    ImageView mIvProxyIcon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.ll_my_wallet)
    LinearLayout mLLMyWallet;
    @Bind(R.id.ll_invite_code)
    LinearLayout mLLInviteCode;
    @Bind(R.id.tv_account)
    TextView mTvAccount;
    @Bind(R.id.tv_payoff)
    TextView mTvPayOff;
    @Bind(R.id.tv_invite_code)
    TextView mTvInviteCode;
    @Bind(R.id.ib_at_me)
    ImageButton mIbAtMe;
    @Bind(R.id.tv_point)
    TextView mTvPoint;
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
    @Bind(R.id.ib_scan_quan)
    ImageButton mIbScanCoupon;
    @Bind(R.id.ib_chunsun_quan)
    ImageButton mIbChunsunCoupon;
    @Bind(R.id.ib_novice_guidelines)
    ImageButton mIbNoviceGuidelines;

    private MeFragmentPresenter mPresenter;
    private DecimalFormat mFormat;
    private UserInfoEntity mUserInfoEntity;
    private String mToken;

    public NewMeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_me, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mPresenter = (MeFragmentPresenter) mMPresenter;
        initView();
        initData();
        return view;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MeFragmentPresenter(this);
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
        mLLMyWallet.setOnClickListener(this);
        mLLInviteCode.setOnClickListener(this);
        mIbAtMe.setOnClickListener(this);
        mIbSendAdRecord.setOnClickListener(this);
        mIbNotReceiving.setOnClickListener(this);
        mIbRecharge.setOnClickListener(this);
        mIbCollect.setOnClickListener(this);
        mIbSetting.setOnClickListener(this);
        mIbScanCoupon.setOnClickListener(this);
        mIbChunsunCoupon.setOnClickListener(this);
        mIbNoviceGuidelines.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    public void getData() {
        mToken = new Preferences(getActivity()).getToken();
        mPresenter.getData(mToken);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
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
            mTvInviteCode.setText(mUserInfoEntity.getMobile());
            mTvPayOff.setText(mFormat.format(mUserInfoEntity.getCumulative_gain()));
            if (mUserInfoEntity.is_proxy()) {
                mIvProxyIcon.setVisibility(View.VISIBLE);
            }else{
                mIvProxyIcon.setVisibility(View.GONE);
            }
            Glide.with(this).load(mUserInfoEntity.getThumb_img_url()).error(R.drawable.img_default_circle_head).transform(new CircleTransform(getActivity())).into(mIvHeadLogo);
            mPtr.refreshComplete();
        }
    }


    /**
     * 跳转个人中
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
        //Intent intent = new Intent(getActivity(), MineInviteCodeActivity.class);
        //startActivity(intent);
        Intent intent = new Intent(getActivity(), MineInviteCodeWebActivity.class);
        startActivity(intent);
    }

    @Override
    public void toAtMe() {
        Intent intent = new Intent(getActivity(), AtMeActivity.class);
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
    public void toMyCircle() {
        Intent intentRecharge = new Intent(getActivity(),
                MyCircleListActivity.class);
        intentRecharge.putExtra(Constants.EXTRA_KEY,
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
        MainApplication.getContext().setmShareHost(entity.getShare_host());
        EventBus.getDefault().post(new MainEvent("user_no_read_count"));
        setData();
    }

    /**
     * 新手指引
     */
    @Override
    public void noviceGuidelines() {
        Intent intent = new Intent(getActivity(), CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                Constants.NOVICE_GUIDELINES_URL);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                "新手指引");
        startActivity(intent);
    }

    /**
     * 扫描春笋券
     */
    @Override
    public void scanCoupon() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, 77);
    }

    @Override
    public void chunsunCoupon() {
        Intent intent = new Intent(getActivity(), ChunsunCouponWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                mUserInfoEntity.getShare_host() + "pages/ticket/list.html?token="
                        + new Preferences(getActivity()).getToken());
        startActivity(intent);
    }

    /**
     * 设置用户未读消息数
     *
     * @param count
     */
    public void setPointCount(int count) {
        if (count != 0) {
            mTvPoint.setVisibility(View.VISIBLE);
            mTvPoint.setText(count + "");
        } else {
            mTvPoint.setVisibility(View.GONE);
        }
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
            case R.id.ll_my_wallet:
                toBalance();
                break;
            case R.id.ib_at_me://与我相关
                toAtMe();
                break;
            case R.id.ib_send_ad_record:
                toAdRecord();
                break;
            case R.id.ib_not_receiving:
                toNotReceivingRed();
                break;
            case R.id.ib_phone_recharge://圈子
                toMyCircle();
                break;
            case R.id.ib_collect:
                toCollect();
                break;
            case R.id.ib_setting:
                toSetting();
                break;
            case R.id.ib_scan_quan:
                scanCoupon();
                break;
            case R.id.ib_chunsun_quan:
                chunsunCoupon();
                break;
            case R.id.ib_novice_guidelines:
                noviceGuidelines();
                break;
        }
    }

    public void onEvent(MeFragmentRefreshEvent event) {
        mPresenter.getData(mToken);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
