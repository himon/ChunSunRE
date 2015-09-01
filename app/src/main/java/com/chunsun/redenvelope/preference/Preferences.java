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
}