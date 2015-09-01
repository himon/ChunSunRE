package com.chunsun.redenvelope.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/8/20.
 */
public class RegexUtil {

    /**
     * 验证Email和手机号码是否正确
     *
     * @param content
     * @return
     */
    public static boolean matcherPhoneAndEmail(String content) {
        if (!TextUtils.isEmpty(content)) {
            String checkMail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern regexMail = Pattern.compile(checkMail);
            Matcher matcherMail = regexMail.matcher(content);
            boolean isMatchedMail = matcherMail.matches();

            String checkTel = "^[1][0-9]{10}$";
            Pattern regexTel = Pattern.compile(checkTel);
            Matcher matcherTel = regexTel.matcher(content);
            boolean isMatchedTel = matcherTel.matches();

            if (!isMatchedMail && !isMatchedTel) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 验证Email和手机号码是否正确
     *
     * @param content
     * @return
     */
    public static boolean matcherPhone(String content) {
        if (!TextUtils.isEmpty(content)) {
            String checkTel = "^[1][0-9]{10}$";
            Pattern regexTel = Pattern.compile(checkTel);
            Matcher matcherTel = regexTel.matcher(content);
            boolean isMatchedTel = matcherTel.matches();

            if (!isMatchedTel) {
                return false;
            }
            return true;
        }
        return false;
    }
}
