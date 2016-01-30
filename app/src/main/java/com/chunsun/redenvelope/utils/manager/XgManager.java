package com.chunsun.redenvelope.utils.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.chunsun.redenvelope.preference.Preferences;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/28 9:31
 * @des
 */
public class XgManager {

    // 信鸽token获取的时候 ，有时候一次获取不到，多调用几次
    private static int XG_TOKEN_GET_TIMES = 5;

    /**
     * 初始化信鸽推送
     */
    public static void initXinGe(final Context context, final boolean b) {

        if (b && !TextUtils.isEmpty(new Preferences(context).getXGToken())) {
            return;
        }

        XG_TOKEN_GET_TIMES--;

        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(context, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int i) {
                Log.d("TPush", "注册成功，设备token为：" + data);
                new Preferences(context).setXGToken(XGPushConfig.getToken(context));
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                if (XG_TOKEN_GET_TIMES > 0) {
                    initXinGe(context, b);
                }
            }
        });

        // 2.36（不包括）之前的版本需要调用以下2行代码
        Intent service = new Intent(context, XGPushService.class);
        context.startService(service);
    }

}
