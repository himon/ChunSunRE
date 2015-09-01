package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface ILoginView {

    void toRegisterActivity();

    void toQuickLoginActivity();

    void toForgetPwdActivity();

    void success();

    void showLoading();

    void hideLoading();

}
