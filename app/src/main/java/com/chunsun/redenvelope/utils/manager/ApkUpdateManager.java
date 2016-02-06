package com.chunsun.redenvelope.utils.manager;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPCheckUpdateCallback;
import com.baidu.autoupdatesdk.CPUpdateDownloadCallback;
import com.chunsun.redenvelope.callback.MyCallback;
import com.chunsun.redenvelope.entities.json.ApkVersionEntity;
import com.chunsun.redenvelope.utils.AppUtil;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/5 15:45
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ApkUpdateManager {

    private ApkVersionEntity mApkInfo;
    private Context mContext;

    public ApkUpdateManager(Context context) {
        this.mContext = context;
    }

    public void upGrade(ApkVersionEntity apk, MyCallback callback) {
        mApkInfo = apk;
        try {
            int code = AppUtil.getCode(mContext);
            if (apk.getVersionCode() > code) {
                if (1 == apk.getUpdate()) {//强制更新
                    callback.callback();
                } else {
                    BDAutoUpdateSDK.cpUpdateCheck(mContext, new MyCPCheckUpdateCallback());
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
        }
    }

    public void upCheckGrade(ApkVersionEntity apk, MyCallback callback, MyCallback noCallback) {
        mApkInfo = apk;
        try {
            int code = AppUtil.getCode(mContext);
            if (apk.getVersionCode() > code) {
                callback.callback();
            }else{
                noCallback.callback();
            }
        } catch (Exception e) {
            String message = e.getMessage();
        }
    }

    private class MyCPCheckUpdateCallback implements CPCheckUpdateCallback {

        @Override
        public void onCheckUpdateCallback(AppUpdateInfo info, AppUpdateInfoForInstall infoForInstall) {
            if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {
                BDAutoUpdateSDK.cpUpdateInstall(mContext, infoForInstall.getInstallPath());
            } else if (info != null) {
                BDAutoUpdateSDK.cpUpdateDownload(mContext, info, new UpdateDownloadCallback());
            } else {
            }
        }
    }

    private class UpdateDownloadCallback implements CPUpdateDownloadCallback {

        @Override
        public void onDownloadComplete(String apkPath) {
            BDAutoUpdateSDK.cpUpdateInstall(mContext, apkPath);
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onPercent(int percent, long rcvLen, long fileSize) {

        }

        @Override
        public void onFail(Throwable error, String content) {

        }

        @Override
        public void onStop() {

        }

    }

}
