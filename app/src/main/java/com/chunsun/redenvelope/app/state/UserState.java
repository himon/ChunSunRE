package com.chunsun.redenvelope.app.state;

import android.content.Context;

import com.chunsun.redenvelope.callback.LoginCallback;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/14 16:09
 * @des User的状态
 */
public interface UserState {

    /**
     * tab转换
     * @param context
     * @param from
     */
    void tabChange(Context context, String from, LoginCallback callback);

    /**
     * 跳转
     * @param context
     */
    boolean forward(Context context, String from);

    /**
     * 评论
     * @param context
     * @param from
     */
    boolean comment(Context context, String from);
}
