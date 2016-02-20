package com.chunsun.redenvelope.app;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.context.LoginContext;
import com.chunsun.redenvelope.app.state.impl.LoginState;
import com.chunsun.redenvelope.app.state.impl.LogoutState;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.BaiduMapLocationEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.utils.AppUtil;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/7/16.
 */
public class MainApplication extends Application {

    private static MainApplication instance;
    private UserInfoEntity mUserEntity;
    private String mImei;
    private DisplayImageOptions mHeadOptions;
    private String mShareHost;

    /**
     * 百度地图
     */
    private LocationClient mLocationClient;
    private MyLocationListener mMyLocationListener;
    private Vibrator mVibrator;
    private double mLatitude = 0;
    private double mLongitude = 0;
    private String mProvince = "不限";
    private String mCity = "不限";
    private Preferences mPreferences;
    /**
     * app当前版本号
     */
    private String mAppVersion;

    public LocationClient getLocationClient() {
        return mLocationClient;
    }

    public String getmShareHost() {
        return mShareHost;
    }

    public void setmShareHost(String mShareHost) {
        this.mShareHost = mShareHost;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoEntity getUserEntity() {
        return mUserEntity;
    }

    /**
     * 设置用户信息
     *
     * @param entity
     */
    public void setmUserEntity(UserInfoEntity entity) {
        this.mUserEntity = entity;
    }

    /**
     * 获取Imei码
     *
     * @return
     */
    public String getmImei() {
        return mImei;
    }

    public DisplayImageOptions getHeadOptions() {
        return mHeadOptions;
    }

    /**
     * 获取当前城市
     *
     * @return
     */
    public String getCity() {
        return mCity;
    }

    /**
     * 获取当前省份
     *
     * @return
     */
    public String getProvince() {
        return mProvince;
    }

    /**
     * 获取经度
     *
     * @return
     */
    public double getLongitude() {
        return mLongitude;
    }

    /**
     * 获取纬度
     *
     * @return
     */
    public double getLatitude() {
        return mLatitude;
    }

    public String getmAppVersion() {
        return mAppVersion;
    }

    public void setmAppVersion(String mAppVersion) {
        this.mAppVersion = mAppVersion;
    }

    /**
     * 获取MainApplication对象
     *
     * @return
     */
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
        initHeadOptions();
        initBaiduMap();

        initUserState();

        mAppVersion = AppUtil.getVersion(getContext());

        ImageLoader.getInstance().init(ImageLoaderHelper.getInstance(this).getImageLoaderConfiguration(Constants.IMAGE_LOADER_CACHE_PATH));

        mPreferences = new Preferences(getApplicationContext());
        String province = mPreferences.getProvinceData();
        String city = mPreferences.getCityData();
        String longitude = mPreferences.getLongitude();
        String latitude = mPreferences.getLatitude();

        if (!TextUtils.isEmpty(province)) {
            mProvince = province;
        }
        if (!TextUtils.isEmpty(city)) {
            mCity = city;
        }
        if (!TextUtils.isEmpty(longitude)) {
            mLongitude = Double.parseDouble(longitude);
        }
        if (!TextUtils.isEmpty(latitude)) {
            mLatitude = Double.parseDouble(latitude);
        }

        // 全局捕获异常
        //MyCrashHandler handler = MyCrashHandler.getInstance();
        //Thread.currentThread().setUncaughtExceptionHandler(handler);
    }

    /**
     * 初始化登录状态
     */
    private void initUserState() {
        if(TextUtils.isEmpty(new Preferences(this).getToken())){
            LoginContext.getLoginContext().setState(new LogoutState());
        }else{
            LoginContext.getLoginContext().setState(new LoginState());
        }
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

    /**
     * 初始化IMEI码
     */
    public void initIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        mImei = telephonyManager.getDeviceId();
    }

    public void exitApp() {
        AppManager.getAppManager().finishAllActivityAndExit();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /*--------------------------------------------------- 百度地图 -----------------------------------------------------------*/

    /**
     * 初始化百度地图所需的信息
     */
    public void initBaiduMap() {
        //声明LocationClient类
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        ////注册监听函数
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        initLocation();
        mLocationClient.start();
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            mProvince = location.getProvince() == null ? "不限" : location.getProvince();
            mCity = location.getCity() == null ? "不限" : location.getCity();

            mPreferences.setProvinceData(mProvince);
            mPreferences.setCityData(mCity);
            mPreferences.setLatitude(mLatitude + "");
            mPreferences.setLongitude(mLongitude + "");

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                System.out.println("GPS定位结果");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                System.out.println("网络定位结果");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                System.out.println("离线定位结果");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                System.out.println("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                System.out.println("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                System.out.println("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            mLocationClient.stop();
            EventBus.getDefault().post(new BaiduMapLocationEvent(""));
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }
}
