package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/8/1.
 */
public interface IMainView {

    boolean isLogin(String from);

    /**
     * 跳转发广告的说明
     */
    void toAdExplain();

    /**
     * 跳转互动平台
     */
    void toInteract();
}
