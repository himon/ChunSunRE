package com.chunsun.redenvelope.utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.CustomProgressDialog;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/25 17:23
 * @des
 */
public class ProgressUtil {

    private static CustomProgressDialog instance;

    public static CustomProgressDialog getAppManager(Context context) {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new CustomProgressDialog(context, "努力加载ing");
                }
            }
        }
        return instance;
    }

    public static void showCircleLoading(Context context) {
        if (instance != null) {
            instance.show();
            Window dialogWindow = instance.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = DensityUtils.dip2px(context, 180);
            lp.height = DensityUtils.dip2px(context, 180);
            dialogWindow.setAttributes(lp);
        }else{
            getAppManager(context);
        }
    }

    public static void hideCircleLoading() {
        if (instance != null && instance.isShowing()) {
            instance.dismiss();
        }
    }

}
