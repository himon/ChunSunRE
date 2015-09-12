package com.chunsun.redenvelope.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2015/9/11.
 */
public class ShareSdkHelper implements PlatformActionListener {


    private Activity mContext;
    private RedDetailEntity.ResultEntity.DetailEntity mDetailEntity;
    private ShareLimitEntity.ResultEntity mShareLimitResult;

    public ShareSdkHelper(Activity context, RedDetailEntity.ResultEntity.DetailEntity detail, ShareLimitEntity.ResultEntity shareLimitResult) {
        this.mContext = context;
        this.mDetailEntity = detail;
        this.mShareLimitResult = shareLimitResult;
    }

    public void share(String which, String url) {
        if (Wechat.NAME.equals(which)) {
            wechatShareParams(url, Wechat.NAME);
        } else if (WechatMoments.NAME.equals(which)) {
            wechatShareParams(url, WechatMoments.NAME);
        } else if (QZone.NAME.equals(which)) {
            qzoneShareParams(url);
        } else if (SinaWeibo.NAME.equals(which)) {
            sinaWeiboShareParams(url);
        }
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
        sp.setImageUrl(mShareLimitResult.getShare_host() + mDetailEntity.getCover_img_url());
        //发布分享的网站名称
        sp.setSite(mContext.getString(R.string.app_name));
        //发布分享网站的地址
        sp.setSiteUrl(url);

        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(this); // 设置分享事件回调
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
        if (TextUtils.isEmpty(mDetailEntity.getContent())) {
            sp.setText(mDetailEntity.getTitle() + ">>更多详情请点击：" + url);
        } else if (mDetailEntity.getContent().length() > 100) {
            String content = mDetailEntity.getContent().substring(0, 100);
            content += ">>更多详情请点击：" + url;
            sp.setText(content);
        } else {
            mDetailEntity.setContent(mDetailEntity.getContent() + ">>更多详情请点击：" + url);
        }
        //分享图片
        sp.setImageUrl(mDetailEntity.getCover_img_url());
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
     */
    private void wechatShareParams(String url, String which) {
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
        sp.setImageUrl(mDetailEntity.getCover_img_url());

        Platform qzone = ShareSDK.getPlatform(which);
        qzone.setPlatformActionListener(this); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        // 分享
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        // share_sdk 回调
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "很遗憾，分享失败，请过会再试！", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onCancel(Platform platform, int i) {
        // share_sdk 回调
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "取消分享，将领不到红包哦！", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
