package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/8/1.
 */
public interface IMainView extends BaseView{

    boolean isLogin(String from);

    /**
     * 跳转发广告的说明
     */
    void toAdExplain();

    /**
     * 跳转互动平台
     */
    void toInteract();

    /**
     * 扫描二维码
     */
    void toScan();
}
