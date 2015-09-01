package com.chunsun.redenvelope.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.telephony.TelephonyManager;


import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/16.
 */
public class MainApplication extends Application {


    private static MainApplication instance;

    private UserInfoEntity mUserEntity;
    private String mImei;
    private DisplayImageOptions mHeadOptions;

    public UserInfoEntity getUserEntity() {
        return mUserEntity;
    }

    public void setmUserEntity(UserInfoEntity entity) {
        this.mUserEntity = entity;
    }

    public String getmImei() {
        return mImei;
    }

    public DisplayImageOptions getHeadOptions() {
        return mHeadOptions;
    }

    public static MainApplication getContext() {
        return instance;
    }

    public MainApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initIMEI();
        initImageLoader();
        initHeadOptions();
    }

    private void initHeadOptions() {
        mHeadOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_default_capture)
                .showImageForEmptyUri(R.drawable.img_default_head)
                .showImageOnFail(R.drawable.img_default_head)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(20))//为图片添加圆角显示在ImageAware中
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取手机信息
     *
     * @return
     */
    public String getPhoneInfomation() {
        PackageManager manager = getPackageManager();

        String systemVersion = Build.VERSION.RELEASE;//系统版本号
        String version = "";//软件版本
        String channelName = Constants.CHANNEL_PACKAGE_NAME;//渠道地址
        String mobile = Build.MODEL;//手机型号

        JSONObject jsonObject = new JSONObject();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;
            jsonObject.put("version", version);
            jsonObject.put("system_name", "android");
            jsonObject.put("system_version", systemVersion);
            jsonObject.put("mobile_model", mobile);
            jsonObject.put("software_download_channel", channelName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public void initIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        mImei = telephonyManager.getDeviceId();
    }
}
