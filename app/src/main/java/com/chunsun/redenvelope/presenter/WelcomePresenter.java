package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.event.WelcomeEvent;
import com.chunsun.redenvelope.ui.view.IWelcomeView;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/7/16.
 */
public class WelcomePresenter {

    private IWelcomeView welcomeView;

    public WelcomePresenter(IWelcomeView welcomeView) {
        this.welcomeView = welcomeView;
    }

    public void isShowPager(String open) {
        //是否跳向引导页
        if ("-1".equals(open)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        EventBus.getDefault().post(new WelcomeEvent("2"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        EventBus.getDefault().post(new WelcomeEvent("1"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
