package com.chunsun.redenvelope.utils;

import android.content.res.Resources;
import android.widget.Button;

import com.chunsun.redenvelope.R;

/**
 * Created by Administrator on 2015/8/1.
 */
public class CountDownUtil {

    /**
     * 验证是否激活获取验证码按钮
     */
    public static void getCodeIsEnable(String phoneNum, Button btnGetCode, Resources resources) {

        if (StringUtil.isPhoneNum(phoneNum)) {
            btnGetCode.setEnabled(true);
            btnGetCode.setTextColor(resources
                    .getColor(R.color.font_blue));
        } else {
            btnGetCode.setEnabled(false);
            btnGetCode.setTextColor(resources.getColor(
                    R.color.font_hint_gray));
        }
    }
}
