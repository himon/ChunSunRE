package com.chunsun.redenvelope.app.context;

import android.content.Context;

import com.chunsun.redenvelope.app.state.UserState;
import com.chunsun.redenvelope.app.state.impl.LogoutState;
import com.chunsun.redenvelope.callback.LoginCallback;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/14 16:07
 * @des 状态模式中Context角色
 */
public class LoginContext {

    /**
     * 用户状态，默认为未登录状态
     */
    UserState mState = new LogoutState();

    /**
     * 单例
     */
    static LoginContext sLoginContext = new LoginContext();

    public static LoginContext getLoginContext() {
        return sLoginContext;
    }

    public void setState(UserState state) {
        mState = state;
    }

    /**
     * tab转换
     * @param context
     * @param from
     */
    public void tabChange(Context context, String from, LoginCallback callback) {
        this.mState.tabChange(context, from, callback);
    }

    /**
     * 跳转
     *
     * @param context
     */
    public boolean forward(Context context) {
        return this.mState.forward(context);
    }

    /**
     * 评论
     *
     * @param context
     * @param from
     */
    public boolean comment(Context context, String from) {
        return this.mState.comment(context, from);
    }
}
