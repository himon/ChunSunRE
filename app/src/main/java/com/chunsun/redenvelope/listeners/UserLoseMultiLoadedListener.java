package com.chunsun.redenvelope.listeners;

import android.content.Context;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/19 10:08
 * @des 带验证用户信息已失效的error
 */
public interface UserLoseMultiLoadedListener<T> extends BaseMultiLoadedListener<T>{

    void onError(String msg, Context context, String from);
}
