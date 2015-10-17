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
                mySetting = context.getSharedPreferences("", Context.MODE_PRIVATE);
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
     * 设置初次显示tab1
     *
     * @param first
     */
    public void setFirstShowTab1(boolean first) {
        mySetting.edit().putBoolean("first_open_tab1", first).commit();
    }

    public boolean getFirstShowTab1() {
        return mySetting.getBoolean("first_open_tab1", true);
    }

    /**
     * 设置初次显示tab3
     *
     * @param first
     */
    public void setFirstShowTab3(boolean first) {
        mySetting.edit().putBoolean("first_open_tab3", first).commit();
    }

    public boolean getFirstShowTab3() {
        return mySetting.getBoolean("first_open_tab3", true);
    }

    /**
     * 设置初次显示tab4
     *
     * @param first
     */
    public void setFirstShowPersonal(boolean first) {
        mySetting.edit().putBoolean("first_open_tab4", first).commit();
    }

    public boolean getFirstShowPersonal() {
        return mySetting.getBoolean("first_open_tab4", true);
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
}
