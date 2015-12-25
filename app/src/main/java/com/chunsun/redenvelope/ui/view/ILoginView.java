package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface ILoginView extends LoadingView {

    void toRegisterActivity();

    void toQuickLoginActivity();

    void toForgetPwdActivity();

    void success();

}
