package com.chunsun.redenvelope.app.state.impl;

import android.content.Context;

import com.chunsun.redenvelope.app.state.UserState;
import com.chunsun.redenvelope.callback.LoginCallback;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/14 16:18
 * @des 已登录状态
 */
public class LoginState implements UserState {

    @Override
    public void tabChange(Context context, String from, LoginCallback callback) {
        callback.setCurrent();
    }

    @Override
    public boolean forward(Context context) {
        return true;
    }

    @Override
    public boolean comment(Context context, String from) {
        return true;
    }
}
