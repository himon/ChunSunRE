package com.chunsun.redenvelope.presenter;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.ApkVersionEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.SettingMode;
import com.chunsun.redenvelope.model.impl.SettingModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IMainView;
import com.chunsun.redenvelope.ui.view.ISettingView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

/**
 * Created by Administrator on 2015/8/22.
 */
public class SettingPresenter extends UserLosePresenter<IMainView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private ISettingView mISettingView;
    private SettingMode mSettingMode;


    public SettingPresenter(ISettingView iSettingView) {
        this.mISettingView = iSettingView;
        this.mSettingMode = new SettingModeImpl((SettingActivity) iSettingView);
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
        ///storage/emulated/0/Chunsun/image/
        String path = Environment.getExternalStorageDirectory()
                + Constants.CHUNSUN_IMAGE_FILE_PATH;
        File file = new File(path);
        if (file.exists()) {
            deleteChunsunFile(file);
        }
        ///data/data/com.chunsun.redenvelope/cache
        File cacheDir = ((Activity) mISettingView).getCacheDir();
        if (cacheDir.exists()) {
            deleteChunsunFile(cacheDir);
        }

        ShowToast.Short("清除缓存成功");
    }


    /**
     * 退出
     */
    public void logout(String token) {
        mSettingMode.logout(token, this);
    }


    /**
     * 删除sd卡文件
     *
     * @param oldPath
     */
    private void deleteChunsunFile(File oldPath) {
        if (oldPath.isDirectory()) {
            File[] files = oldPath.listFiles();
            for (File file : files) {
                deleteChunsunFile(file);
                file.delete();
            }
        } else if (oldPath.isFile()) {
            oldPath.delete();
        }
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity entity) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_LOGOUT:
                ShowToast.Short(entity.getMsg());
                mISettingView.toLogout();
                break;
            case Constants.LISTENER_TYPE_GET_APK_VERSION:
                ApkVersionEntity apk = (ApkVersionEntity) entity;
                mISettingView.isUpGrade(apk);
                break;
        }
    }

    public void upGrade() {
        mSettingMode.upGrade(this);
    }

    private NotificationManager updateNotificationManager = null;
    private Notification updateNotification = null;
    private PendingIntent updatePendingIntent = null;

    /**
     * 下载最新apk
     *
     * @param mApkInfo
     */
    public void downloadApk(ApkVersionEntity mApkInfo, final Context context) {

        initNotification(context);

        OkHttpUtils.get().url(mApkInfo.getUrl()).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "ChunSunRE.apk") {
            @Override
            public void inProgress(float progress) {
                int b = (int) (progress * 100);
                updateNotification.setLatestEventInfo(context, "正在下载", b + "%", updatePendingIntent);
                updateNotificationManager.notify(0, updateNotification);
            }

            @Override
            public void onError(Request request, Exception e) {
                ShowToast.Short("下载到失败");
            }

            @Override
            public void onResponse(File file) {
                ShowToast.Short("下载到：" + file.getAbsolutePath());

                Uri uri = Uri.fromFile(file);
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(uri,
                        "application/vnd.android.package-archive");
                updatePendingIntent = PendingIntent.getActivity(context, 0,
                        installIntent, 0);
                updateNotification.defaults = Notification.DEFAULT_SOUND;
                updateNotification.setLatestEventInfo(context, "ChunSunRE.apk", "下载完成,点击安装",
                        updatePendingIntent);
                updateNotificationManager.notify(0, updateNotification);

                //
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(installIntent);
            }
        });
    }

    private void initNotification(Context context) {
        this.updateNotificationManager = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);
        this.updateNotification = new Notification();
        updateNotification.icon = R.mipmap.ic_launcher;
        updateNotification.tickerText = "开始下载";
        updateNotification.setLatestEventInfo(context, "System", "0%",
                updatePendingIntent);
        updateNotificationManager.notify(0, updateNotification);
    }
}
