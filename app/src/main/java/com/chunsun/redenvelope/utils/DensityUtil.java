package com.chunsun.redenvelope.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.chunsun.redenvelope.app.MainApplication;

public class DensityUtil {

    public static DisplayMetrics getDisplayMetrics() {
        WindowManager windowManager = (WindowManager) MainApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 获取屏幕宽
     *
     * @return
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕像素
     *
     * @return
     */
    public static float getScreenDensity() {
        return getDisplayMetrics().density;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public static float getScreenDensityDpi() {
        return getDisplayMetrics().densityDpi;
    }

    /**
     *
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取状态栏的高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarH(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
}