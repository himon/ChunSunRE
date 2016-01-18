package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/8/1.
 */
public interface IMainView extends LoadingView {

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

    /**
     * 跳转任务列表
     */
    void toTaskList();
}
