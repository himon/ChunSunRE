package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/7/29.
 */
public interface IRegisterView extends LoadingView {

    void success();

    void showServiceProtocol();

    void phoneNumError();

    void getCodeEnabled(boolean enable);

    /**
     * 验证码EditText获取焦点
     */
    void getFocus();
}
