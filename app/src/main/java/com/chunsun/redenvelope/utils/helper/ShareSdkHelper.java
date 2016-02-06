package com.chunsun.redenvelope.utils.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.event.CreateCircleResultEvent;
import com.chunsun.redenvelope.event.RedDetailEvent;
import com.chunsun.redenvelope.event.WebRedDetailEvent;
import com.chunsun.redenvelope.utils.BitmapClipUtils;
import com.chunsun.redenvelope.utils.ShowToast;

import java.io.File;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/9/11.
 */
public class ShareSdkHelper implements PlatformActionListener {


    private Activity mContext;
    private RedDetailEntity.ResultEntity.DetailEntity mDetailEntity;
    private String mShareHost;

    private int mFrom;
    /**
     * 当前点击的分享类型 wf:微信好友，wc:微信朋友圈，qz:qq空间，sw:新浪微博
     */
    private String shareType;
    /**
     * 标示是否有奖励 true 有， false 没有
     */
    private boolean mFlag;

    /**
     * 创建圈子后的朋友圈分享
     */
    private boolean mCircleShare;

    public ShareSdkHelper(Activity context, RedDetailEntity.ResultEntity.DetailEntity detail, String shareHost, int from, boolean b) {
        this.mContext = context;
        this.mDetailEntity = detail;
        this.mShareHost = shareHost;
        this.mFrom = from;
        mFlag = b;
    }

    public void share(String which, String url) {
        if (Wechat.NAME.equals(which)) {
            wechatShareParams(url, Wechat.NAME, true);
            shareType = "wf";
        } else if (WechatMoments.NAME.equals(which)) {
            wechatShareParams(url, WechatMoments.NAME, true);
            shareType = "wc";
        } else if (QZone.NAME.equals(which)) {
            qzoneShareParams(url);
            shareType = "qz";
        } else if (SinaWeibo.NAME.equals(which)) {
            sinaWeiboShareParams(url);
            shareType = "sw";
        }
    }

    public void circleShare(String url) {
        mCircleShare = true;
        wechatShareParams(url, WechatMoments.NAME, false);
        shareType = "wc";
    }

    /**
     * QQ空间分享
     *
     * @param url
     */
    private void qzoneShareParams(String url) {
        ShareParams sp = new ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        //分享的标题
        sp.setTitle(mDetailEntity.getTitle());
        // 标题的超链接
        sp.setTitleUrl(url);
        //分享的文本
        if (TextUtils.isEmpty(mDetailEntity.getContent())) {
            sp.setText(mDetailEntity.getTitle());
        } else {
            sp.setText(mDetailEntity.getContent());
        }
        //图片网络地址
        sp.setImageUrl(mShareHost + mDetailEntity.getCover_img_url());
        //sp.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
        sp.setImagePath(null);
        //发布分享的网站名称
        sp.setSite(mContext.getString(R.string.app_name));
        //发布分享网站的地址
        sp.setSiteUrl(url);
        sp.setUrl(url);
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        // 设置分享事件回调
        qzone.setPlatformActionListener(this);
        // 执行图文分享
        qzone.share(sp);
    }

    /**
     * 新浪微博分享
     *
     * @param url
     */
    private void sinaWeiboShareParams(String url) {
        ShareParams sp = new ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setText(mDetailEntity.getTitle());
        // 标题的超链接
        sp.setTitleUrl(url);
        //分享的文本
        if (!TextUtils.isEmpty(mDetailEntity.getContent()) && mDetailEntity.getContent().contains(">>更多详情请点击：")) {
            sp.setText(mDetailEntity.getContent());
        } else {
            if (TextUtils.isEmpty(mDetailEntity.getContent())) {
                sp.setText(mDetailEntity.getTitle() + ">>更多详情请点击：" + url);
            } else if (mDetailEntity.getContent().length() > 100) {
                String content = mDetailEntity.getContent().substring(0, 100);
                content += ">>更多详情请点击：" + url;
                sp.setText(content);
            } else {
                if(url.contains(">>更多详情请点击：")){
                    sp.setText(url);
                }else {
                    mDetailEntity.setContent(mDetailEntity.getContent() + ">>更多详情请点击：" + url);
                    sp.setText(mDetailEntity.getContent());
                }
            }
        }
        //分享图片
        if (TextUtils.isEmpty(mDetailEntity.getCover_img_url())) {
            Bitmap bitmap = BitmapFactory.decodeResource(
                    mContext.getResources(), R.mipmap.ic_launcher);
            sp.setImageData(bitmap);
        } else {
            sp.setImageUrl(mShareHost + mDetailEntity.getCover_img_url());
        }
        sp.setSiteUrl(url);
        sp.setUrl(url);
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        // 设置分享事件回调
        weibo.setPlatformActionListener(this);
        // 执行图文分享
        weibo.share(sp);
    }

    /**
     * 微信、朋友圈分享
     *
     * @param url
     * @param which
     * @param b
     */
    private void wechatShareParams(String url, String which, boolean b) {
        ShareParams sp = new ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        //分享的标题
        sp.setTitle(mDetailEntity.getTitle());
        // 标题的超链接
        sp.setTitleUrl(url);
        //分享的文本
        if (TextUtils.isEmpty(mDetailEntity.getContent())) {
            sp.setText(mDetailEntity.getTitle());
        } else {
            sp.setText(mDetailEntity.getContent());
        }
        //图片网络地址
        if (TextUtils.isEmpty(mDetailEntity.getCover_img_url())) {
            Bitmap bitmap = BitmapFactory.decodeResource(
                    mContext.getResources(), R.mipmap.ic_launcher);
            sp.setImageData(bitmap);
        } else {
            if (b) {
                sp.setImageUrl(mShareHost + mDetailEntity.getCover_img_url());
                sp.setUrl(mShareHost + mDetailEntity.getCover_img_url());
                //ShowToast.Long(Constants.IMG_HOST_URL + mDetailEntity.getCover_img_url());
            } else {
                File file = new File(mDetailEntity.getCover_img_url());
                if (file.exists()) {
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inJustDecodeBounds = true;
//                    options.inSampleSize = BitmapUtils.calculateInSampleSize(options, 100, 100);
//                    options.inJustDecodeBounds = false;
                    try {
                        //Bitmap bitmap = BitmapFactory.decodeFile(mDetailEntity.getCover_img_url(), options);
                        Bitmap bitmap = BitmapClipUtils.createImageThumbnailScale(mDetailEntity.getCover_img_url(), 150);
                        if (bitmap != null) {
                            sp.setImageData(bitmap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        sp.setSiteUrl(url);
        sp.setUrl(url);
        Platform platform = ShareSDK.getPlatform(which);
        platform.setPlatformActionListener(this); // 设置分享事件回调
        // 执行图文分享
        platform.share(sp);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        // 分享
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mFlag) {
                    switch (mFrom) {
                        case Constants.SHARE_FROM_WEB_RED:
                            WebRedDetailEvent share = new WebRedDetailEvent("share");
                            share.setContent(shareType);
                            EventBus.getDefault().post(share);
                            break;
                        case Constants.SHARE_FROM_RED:
//                            if (Constants.RED_DETAIL_TYPE_COUPON == mDetailEntity.getHb_type()) {
//                                CouponRedDetailEvent couponRedShare = new CouponRedDetailEvent("share", shareType);
//                                EventBus.getDefault().post(couponRedShare);
//                            } else {
                            RedDetailEvent redShare = new RedDetailEvent("share", shareType);
                            EventBus.getDefault().post(redShare);
//                            }
                            break;
                    }
                } else {
                    if (mCircleShare) {
                        EventBus.getDefault().post(new CreateCircleResultEvent("success"));
                    } else {
                        ShowToast.Short("分享成功！");
                    }
                }
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, final Throwable throwable) {
        // share_sdk 回调
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ShowToast.Long(throwable.getMessage());
//                Toast.makeText(mContext, "很遗憾，分享失败，请过会再试\n" + throwable.getMessage(), Toast.LENGTH_LONG)
//                        .show();

            }
        });
    }

    @Override
    public void onCancel(Platform platform, int i) {
        // share_sdk 回调
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mCircleShare) {
                    EventBus.getDefault().post(new CreateCircleResultEvent("cancel"));
                } else {
                    if (mFlag) {
                        Toast.makeText(mContext, "取消分享，将领不到红包哦！", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(mContext, "取消分享", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }
        });
    }
}
