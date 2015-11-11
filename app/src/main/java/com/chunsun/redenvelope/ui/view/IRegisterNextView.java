package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/7/30.
 */
public interface IRegisterNextView extends BaseView{

    void success();

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
