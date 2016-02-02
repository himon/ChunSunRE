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
import com.chunsun.redenvelope.entities.json.UserNoReadCountEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.MainMode;
import com.chunsun.redenvelope.model.impl.MainModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IMainView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/21 16:39
 * @des
 */
public class MainPresenter extends UserLosePresenter<IMainView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IMainView mIMainView;
    private MainMode mMainMode;

    public MainPresenter(IMainView view) {
        this.mIMainView = view;
        mMainMode = new MainModeImpl((Activity) view);
    }

    /**
     * 获取用户未读信息数量
     *
     * @param token
     */
    public void getUserNoReadCount(String token) {
        mMainMode.getUserNoReadCount(token, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_NO_READ_COUNT:
                UserNoReadCountEntity entity = (UserNoReadCountEntity) data;
                mIMainView.setUserNoReadCount(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_GET_APK_VERSION:
                ApkVersionEntity apk = (ApkVersionEntity) data;
                mIMainView.isUpGrade(apk);
                break;
        }
    }

    /**
     * 获取升级信息
     */
    public void upGrade() {
        mMainMode.upGrade(this);
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
                mIMainView.setDownloadProgress(b);
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
