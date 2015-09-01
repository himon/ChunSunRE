package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/3.
 */
public interface ForgetPwdNextMode {

    void onSuccess(String mobile, String verify_code,
                   String new_pwd, String confirm_pwd, BaseSingleLoadedListener listener);
}
