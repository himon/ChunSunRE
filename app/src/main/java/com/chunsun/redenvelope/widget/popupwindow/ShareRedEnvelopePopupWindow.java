package com.chunsun.redenvelope.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.callback.GetRepeatHostCallback;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.event.RedDetailEvent;
import com.chunsun.redenvelope.event.ShareRedEnvelopeEvent;
import com.chunsun.redenvelope.event.WebRedDetailEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.helper.ShareSdkHelper;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/9/12.
 */
public class ShareRedEnvelopePopupWindow extends PopupWindow implements View.OnClickListener {

    private Activity mContext;
    private RedDetailEntity.ResultEntity.DetailEntity mDetail;
    private GrabEntity.ResultEntity.ShareLimitEntity mShareLimitResult;
    private GrabEntity mGrabEntity;

    private View mMenuView = null;
    private LinearLayout mLLWechat;
    private LinearLayout mLLWechatmoments;
    private LinearLayout mLLQzone;
    private LinearLayout mLLSina;
    private ImageView mIvWechat;
    private ImageView mIvWechatmoment;
    private ImageView mIvQzone;
    private ImageView mIvSina;
    private Button mBtnCancel;
    private LinearLayout mLLGetMoney;

    private String mShowUrl;
    private ShareSdkHelper mShareSdkHelper;
    private GetRepeatHostCallback mCallback;

    private int mFrom;
    private String mPlatform;
    private String mWhich;

    /**
     * 有奖励分享，有ShareLimit值
     *
     * @param context
     * @param detail
     * @param grabEntity
     * @param shareFrom  标示从哪跳转过来的
     */
    public ShareRedEnvelopePopupWindow(Activity context, RedDetailEntity.ResultEntity.DetailEntity detail, GrabEntity grabEntity, int shareFrom) {
        super(context);
        this.mContext = context;
        this.mDetail = detail;
        this.mGrabEntity = grabEntity;
        this.mShareLimitResult = grabEntity.getResult().getShare_limit();
        this.mFrom = shareFrom;

        initView();
        initShareSDK();
        initData(mShareLimitResult.getShare_host());
    }

    /**
     * 无奖励分享
     *
     * @param context
     * @param detail
     */
    public ShareRedEnvelopePopupWindow(FragmentActivity context, RedDetailEntity.ResultEntity.DetailEntity detail, GrabEntity grabEntity) {
        super(context);
        this.mContext = context;
        this.mDetail = detail;
        this.mGrabEntity = grabEntity;

        initView();
        initShareSDK();
        initData(mDetail.getShare_host());
    }

    /**
     * 无UI微信分享
     *
     * @param context
     * @param detail
     */
    public ShareRedEnvelopePopupWindow(FragmentActivity context, RedDetailEntity.ResultEntity.DetailEntity detail, String string) {
        super(context);
        this.mContext = context;
        this.mDetail = detail;

        // 初始化share_sdk
        ShareSDK.initSDK(mContext);
        initData(mDetail.getShare_host());
    }

    /**
     * 转发分享
     *
     * @param context
     * @param detail
     * @param getRepeatHostCallback
     */
    public ShareRedEnvelopePopupWindow(Activity context, RedDetailEntity.ResultEntity.DetailEntity detail, GetRepeatHostCallback getRepeatHostCallback) {
        super(context);
        this.mContext = context;
        this.mDetail = detail;
        this.mCallback = getRepeatHostCallback;

        initView();
        initShareSDK();
        initData(mDetail.getShare_host());
    }

    /**
     * 邀请分享
     *
     * @param context
     * @param detail
     */
    public ShareRedEnvelopePopupWindow(Activity context, RedDetailEntity.ResultEntity.DetailEntity detail) {
        this.mContext = context;
        this.mDetail = detail;

        initView();
        initShareSDK();
        initData(mDetail.getShare_host());
    }


    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_share_red, null);
        mLLWechat = (LinearLayout) mMenuView.findViewById(R.id.ll_wechat);
        mLLWechatmoments = (LinearLayout) mMenuView
                .findViewById(R.id.ll_wechatmoments);
        mLLQzone = (LinearLayout) mMenuView.findViewById(R.id.ll_qzone);
        mLLSina = (LinearLayout) mMenuView.findViewById(R.id.ll_sinaweibo);
        mBtnCancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
        mLLGetMoney = (LinearLayout) mMenuView.findViewById(R.id.ll_get_money);

        mIvWechat = (ImageView) mMenuView.findViewById(R.id.iv_wechat);
        mIvWechatmoment = (ImageView) mMenuView
                .findViewById(R.id.iv_wecharmoments);
        mIvQzone = (ImageView) mMenuView.findViewById(R.id.iv_qzone);
        mIvSina = (ImageView) mMenuView.findViewById(R.id.iv_sinaweibo);

        if (mShareLimitResult != null) {
            validateIsCanOnceGet();
        }

        initEvent();
    }

    /**
     * 验证是否显示直接领取
     */
    private void validateIsCanOnceGet() {

        if (mGrabEntity.getResult().isMust_share()) {
            mLLGetMoney.setVisibility(View.GONE);
            mBtnCancel.setVisibility(View.VISIBLE);
        } else {
            mLLGetMoney.setVisibility(View.VISIBLE);
            mBtnCancel.setVisibility(View.GONE);
        }

        if (!mShareLimitResult.isQz()) {
            mIvQzone.setImageResource(R.drawable.logo_qzone_disable);
        }

        if (!mShareLimitResult.isSw()) {
            mIvSina.setImageResource(R.drawable.logo_sina_disable);
        }

        if (!mShareLimitResult.isWc()) {
            mIvWechatmoment.setImageResource(R.drawable.logo_wechatmoments_disable);
        }

        if (!mShareLimitResult.isWf()) {
            mIvWechat.setImageResource(R.drawable.logo_wechat_disable);
        }
    }

    private void initEvent() {
        mLLWechat.setOnClickListener(this);
        mLLWechatmoments.setOnClickListener(this);
        mLLQzone.setOnClickListener(this);
        mLLSina.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mLLGetMoney.setOnClickListener(this);
    }

    private void initShareSDK() {
        // 初始化share_sdk
        ShareSDK.initSDK(mContext);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xB2B2B2);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                EventBus.getDefault().post(new ShareRedEnvelopeEvent(""));
                clear();
            }
        });
    }


    private void initData(String shareHost) {
        if (mDetail != null) {
            if (4 == mDetail.getHb_type() || 8 == mDetail.getHb_type() || 10 == mDetail.getHb_type()) {
                mShowUrl = mDetail.getContent();
                mDetail.setContent("我正在看【" + mDetail.getTitle() + "】分享给你一起来看");
            } else if (-1 == mDetail.getHb_type()) {// 邀请码分享
                mShowUrl = shareHost + "/pages/share/invitation_code.aspx?token=" + new Preferences(MainApplication.getContext()).getToken();
            } else if (7 == mDetail.getHb_type()) {
                mShowUrl = shareHost + "pages/circle/index.html?hbId=" + mDetail.getId();
            }  else {
                if(mGrabEntity != null) {
                    mShowUrl = (shareHost + Constants.SHARE_RED_ENVELOPE_URL + mGrabEntity.getResult().getHb_grab_id());
                }
            }
        }
        mShareSdkHelper = new ShareSdkHelper(mContext, mDetail, shareHost, mFrom, mShareLimitResult != null);
    }

    /**
     * 创建圈子同步朋友圈
     */
    public void circleSyncFriend() {
        mShareSdkHelper.circleShare(mShowUrl);
    }

    /**
     * 转发类分享
     *
     * @param share_url
     */
    public void repeatShare(String share_url) {
        initData(share_url);
        mShareSdkHelper.share(mWhich, mShowUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_qzone:
            case R.id.ll_wechat:
            case R.id.ll_wechatmoments:
            case R.id.ll_sinaweibo:
                if (mShareLimitResult != null) {
                    shareOnClick(v);
                } else {
                    noRewardShareOnClick(v);
                }
                dismiss();
                break;
            case R.id.ll_get_money:
                if (mFrom == Constants.SHARE_FROM_WEB_RED) {
                    EventBus.getDefault().post(new WebRedDetailEvent("no_share"));
                } else if (mFrom == Constants.SHARE_FROM_RED) {
                    EventBus.getDefault().post(new RedDetailEvent("no_share", ""));
                }
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mContext.getWindow().setAttributes(lp);
        super.showAtLocation(parent, gravity, x, y);
    }

    private void clear() {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mContext.getWindow().setAttributes(lp);
    }

    private void shareOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_qzone:
                if (!isValid(mContext)) {
                    ShowToast.Short("用户没有QQ客户端或者版本过低，请下载或者更新至QQ4.6以上！");
                    return;
                }
                if (!mShareLimitResult.isQz()) {
                    ShowToast.Short("QQ空间分享暂时不可用！");
                    return;
                }
                mShareSdkHelper.share(QZone.NAME, mShowUrl);
                break;
            case R.id.ll_wechat:
                if (!mShareLimitResult.isWf()) {
                    ShowToast.Short("微信好友分享暂时不可用！");
                    return;
                }
                mShareSdkHelper.share(Wechat.NAME, mShowUrl);
                break;
            case R.id.ll_wechatmoments:
                if (!mShareLimitResult.isWc()) {
                    ShowToast.Short("微信朋友圈分享暂时不可用！");
                    return;
                }
                mShareSdkHelper.share(WechatMoments.NAME, mShowUrl);
                break;
            case R.id.ll_sinaweibo:
                if (!mShareLimitResult.isSw()) {
                    ShowToast.Short("新浪微博分享暂时不可用！");
                    return;
                }
                mShareSdkHelper.share(SinaWeibo.NAME, mShowUrl);
                break;
        }
    }

    /**
     * 无奖励分享
     *
     * @param view
     */
    private void noRewardShareOnClick(View view) {
        mWhich = "";
        switch (view.getId()) {
            case R.id.ll_qzone:
                if (!isValid(mContext)) {
                    ShowToast.Short("用户没有QQ客户端或者版本过低，请下载或者更新至QQ4.6以上！");
                    return;
                }
                mWhich = QZone.NAME;
                mPlatform = "qz";
                break;
            case R.id.ll_wechat:
                mWhich = Wechat.NAME;
                mPlatform = "wf";
                break;
            case R.id.ll_wechatmoments:
                mWhich = WechatMoments.NAME;
                mPlatform = "wc";
                break;
            case R.id.ll_sinaweibo:
                mWhich = SinaWeibo.NAME;
                mPlatform = "sw";
                break;
        }
        if (mCallback != null) {
            mCallback.getHost(mPlatform);
        } else {
            mShareSdkHelper.share(mWhich, mShowUrl);
        }
    }

    private boolean isValid(Context context) {
        PackageInfo pi = null;
        try {
            String packageName = "com.tencent.mobileqq";

            pi = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Throwable t) {
            pi = null;
            return false;
        }
        if (pi == null) {
            return false;
        }

        String[] ver = pi.versionName.split("\\.");
        int[] verCode = new int[ver.length];
        for (int i = 0; i < verCode.length; i++) {
            try {
                verCode[i] = Integer.parseInt(ver[i]);
            } catch (Throwable t) {
                verCode[i] = 0;
            }
        }
        return ((verCode.length > 0 && verCode[0] >= 5) || (verCode.length > 1
                && verCode[0] >= 4 && verCode[1] >= 6));
    }


}
