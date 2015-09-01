package com.chunsun.redenvelope.utils;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/28.
 */
public class StringUtil {

    /**
     * 处理url
     *
     * @param url
     * @return
     */
    public static String preUrl(String url) {
        if (url == null) {
            return null;
        }
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        } else {
            return "http://" + url;
        }
    }

    public static String textview2String(TextView view) {

        if (view == null) {
            return "";
        }
        return view.getText().toString().trim();
    }

    /**
     * 判断是否是手机号
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {

        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }
        if (phoneNum.length() == 11 && phoneNum.startsWith("1")) {
            return true;
        }
        return false;
    }

    public static boolean validataPwd(String pwd, String repwd) {
        if (TextUtils.isEmpty(pwd)) {
            ShowToast.Short("请输入密码！");
            return false;
        } else if (TextUtils.isEmpty(repwd)) {
            ShowToast.Short("请输入确认密码！");
            return false;
        } else if (!pwd.equals(repwd)) {
            ShowToast.Short("两次输入的密码不一致");
            return false;
        }
        return true;
    }
}
