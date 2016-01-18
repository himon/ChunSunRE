package com.chunsun.redenvelope.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/7/16.
 */
public class Preferences {

    private static SharedPreferences mySetting;

    public Preferences(Context context) {

        if (mySetting == null) {
            if (context != null) {
                mySetting = context.getSharedPreferences("louis", Context.MODE_PRIVATE);
            }
        }
    }

    /**
     * 保存token信息
     *
     * @param token
     */
    public void setToken(String token) {
        mySetting.edit().putString("token", token).commit();
    }

    /**
     * 获取信鸽token的信息
     *
     * @return
     */
    public String getXGToken() {
        return mySetting.getString("xg_token", "");
    }

    /**
     * 保存信鸽token信息
     *
     * @param token
     */
    public void setXGToken(String token) {
        mySetting.edit().putString("xg_token", token).commit();
    }

    /**
     * 获取token的信息
     *
     * @return
     */
    public String getToken() {
        return mySetting.getString("token", "");
    }

    /**
     * 设置初次使用的指引画面flag---end
     *
     * @param first
     */
    public void setFirstOpen(String first) {
        mySetting.edit().putString("first_open", first).commit();
    }

    public String getFirstOpen() {
        return mySetting.getString("first_open", "-1");
    }

    /**
     * 设置初次发广告
     *
     * @param first
     */
    public void setFirstSendAd(boolean first) {
        mySetting.edit().putBoolean("first_send_ad", first).commit();
    }

    public boolean getFirstSendAd() {
        return mySetting.getBoolean("first_send_ad", true);
    }

    /**
     * 任务界面缓存数据
     *
     * @param data
     */
    public void setTaskData(String data) {
        mySetting.edit().putString("task_data", data).commit();
    }

    public String getTaskData() {
        return mySetting.getString("task_data", "");
    }

    /**
     * 红包界面缓存数据
     *
     * @param data
     */
    public void setRedDetailListData(String data) {
        mySetting.edit().putString("red_detail_list_data", data).commit();
    }

    public String getRedDetailListData() {
        return mySetting.getString("red_detail_list_data", "");
    }

    /**
     * 圈子界面缓存数据
     *
     * @param data
     */
    public void setCircleListData(String data) {
        mySetting.edit().putString("circle_list_data", data).commit();
    }

    public String getCircleListData() {
        return mySetting.getString("circle_list_data", "");
    }

    /**
     * 任务界面滚动广告缓存数据
     *
     * @param data
     */
    public void setTaskAdsData(String data) {
        mySetting.edit().putString("task_ads_data", data).commit();
    }

    public String getTaskAdsData() {
        return mySetting.getString("task_ads_data", "");
    }

    /**
     * 红包界面滚动广告缓存数据
     *
     * @param data
     */
    public void setRedDetailListAdsData(String data) {
        mySetting.edit().putString("red_detail_list_ads_data", data).commit();
    }

    public String getRedDetailListAdsData() {
        return mySetting.getString("red_detail_list_ads_data", "");
    }

    /**
     * 圈子界面滚动广告缓存数据
     *
     * @param data
     */
    public void setCircleListAdsData(String data) {
        mySetting.edit().putString("circle_list_ads_data", data).commit();
    }

    public String getCircleListAdsData() {
        return mySetting.getString("circle_list_ads_data", "");
    }

    /**
     * 互动平台缓存数据
     *
     * @param data
     */
    public void setInteractivePlatformCountryData(String data) {
        mySetting.edit().putString("interactive_platform_country", data)
                .commit();
    }

    public String getInteractivePlatformCountryData() {
        return mySetting.getString("interactive_platform_country", "");
    }

    public void setInteractivePlatformLocalData(String data) {
        mySetting.edit().putString("interactive_platform_local", data).commit();
    }

    public String getInteractivePlatformLocalData() {
        return mySetting.getString("interactive_platform_local", "");
    }

    /**
     * 省缓存数据
     *
     * @param data
     */
    public void setProvinceData(String data) {
        mySetting.edit().putString("province_data", data).commit();
    }

    public String getProvinceData() {
        return mySetting.getString("province_data", "");
    }

    /**
     * 市缓存数据
     *
     * @param data
     */
    public void setCityData(String data) {
        mySetting.edit().putString("city_data", data).commit();
    }

    public String getCityData() {
        return mySetting.getString("city_data", "");
    }

    /**
     * 经度缓存数据
     *
     * @return
     */
    public void setLongitude(String data) {
        mySetting.edit().putString("longitude_data", data).commit();
    }

    public String getLongitude() {
        return mySetting.getString("longitude_data", "");
    }

    /**
     * 纬度缓存数据
     *
     * @param data
     */
    public void setLatitude(String data) {
        mySetting.edit().putString("latitude_data", data).commit();
    }

    public String getLatitude() {
        return mySetting.getString("latitude_data", "");
    }
}
