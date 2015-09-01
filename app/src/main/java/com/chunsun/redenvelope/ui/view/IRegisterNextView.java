package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/7/30.
 */
public interface IRegisterNextView {

    void success();

    void showLoading();

    void hideLoading();

    /**
     * 注册成功显示的Dialog
     *
     * @param content
     */
    void successShowDialog(String content);

    /**
     * 注册失败显示的Dialog
     *
     * @param content
     */
    void errorShowDialog(String content);
}
