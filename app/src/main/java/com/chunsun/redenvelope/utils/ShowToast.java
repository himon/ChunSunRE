package com.chunsun.redenvelope.utils;

import android.widget.Toast;

import com.chunsun.redenvelope.app.MainApplication;

/**
 * Created by Administrator on 2015/7/23.
 * Toast工具类
 */
public class ShowToast {

    /**
     * @param
     * @return void
     * @throws
     * @Description: 短时间Toast
     */
    public static void Short(CharSequence sequence) {
        Toast.makeText(MainApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 长时间Toast
     */
    public static void Long(CharSequence sequence) {
        Toast.makeText(MainApplication.getContext(), sequence, Toast.LENGTH_LONG).show();
    }
}
