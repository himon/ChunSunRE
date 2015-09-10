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

    /**
     * 验证密码
     *
     * @param pwd
     * @param repwd
     * @return
     */
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

    /**
     * 验证是否是无效的金额
     *
     * @param amount
     * @return
     */
    public static boolean isAvailAmout(String amount) {
        amount = backValidAmount(amount);
        if ("0".equals(amount) || "0.0".equals(amount) || "0.00".equals(amount)) {
            return false;
        }
        return true;
    }

    /**
     * 返回有效的金额
     *
     * @param amount
     * @return
     */
    public static String backValidAmount(String amount) {
        if ("".equals(amount) || ".".equals(amount)) {
            return "0";
        }

        if (amount.startsWith(".")) {
            amount = "0" + amount;
        }

        if (amount.endsWith(".")) {
            amount = amount.substring(0, amount.length() - 1);
        }
        if (amount.indexOf(".") != -1) {
            if (amount.indexOf(".") + 2 < amount.length() - 1) {
                amount = amount.substring(0, amount.indexOf(".") + 3);
            }
        }
        return amount;
    }
}
