package com.chunsun.redenvelope.listeners;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.AppManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/26 10:00
 * @des
 */
public abstract class BaseMultiLoadedListenerImpl<T> implements BaseMultiLoadedListener<T> {

    /**
     * 未登录请求错误
     *
     * @param msg     服务器返回消息
     * @param context 上下文
     * @param from    登录成功后跳转到哪
     */
    @Override
    public void onError(String msg, Context context, String from) {
        ShowToast.Short(msg);
        Intent intent = new Intent(context, LoginActivity.class);
        if (!TextUtils.isEmpty(from)) {
            intent.putExtra(Constants.EXTRA_KEY, from);
        }
        context.startActivity(intent);
        AppManager.getAppManager().finishAllActivityExceptMain();
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }


}
