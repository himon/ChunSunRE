package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/7/16.
 * 启动页+引导页的接口
 */
public interface IWelcomeView {

    /**
     * 初始化引导页
     */
    void initPager();

    /**
     * 跳转到MainActivity
     */
    void toMainActivity();
}
