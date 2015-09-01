package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.ui.view.IWelcomeView;

/**
 * Created by Administrator on 2015/7/16.
 */
public class WelcomePresenter {

    private IWelcomeView welcomeView;

    public WelcomePresenter(IWelcomeView welcomeView) {
        this.welcomeView = welcomeView;
    }

    public void isShowPager(String open){
        //是否跳向引导页

        if ("-1".equals(open)) {
            welcomeView.initPager();
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onSuccess();
        }
    }

    public void onSuccess(){
        welcomeView.toMainActivity();
    }
}
