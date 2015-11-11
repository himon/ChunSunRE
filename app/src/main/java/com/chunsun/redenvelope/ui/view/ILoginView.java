package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface ILoginView extends BaseView{

    void toRegisterActivity();

    void toQuickLoginActivity();

    void toForgetPwdActivity();

    void success();

}
