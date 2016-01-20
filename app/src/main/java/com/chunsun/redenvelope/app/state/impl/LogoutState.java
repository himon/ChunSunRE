package com.chunsun.redenvelope.app.state.impl;

import android.content.Context;
import android.content.Intent;

import com.chunsun.redenvelope.app.state.UserState;
import com.chunsun.redenvelope.callback.LoginCallback;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/14 16:12
 * @des 未登录状态
 */
public class LogoutState implements UserState {

    @Override
    public void tabChange(Context context, String from, LoginCallback callback) {
        gotoLoginActivity(context, from);
    }

    @Override
    public boolean forward(Context context, String from) {
        gotoLoginActivity(context, from);
        return false;
    }

    @Override
    public boolean comment(Context context, String from) {
        gotoLoginActivity(context, from);
        return false;
    }

    private void gotoLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private void gotoLoginActivity(Context context, String from) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, from);
        context.startActivity(intent);
    }
}
