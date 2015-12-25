package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.QuickLoginMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.ValiCodeEvent;
import com.chunsun.redenvelope.model.impl.QuickLoginModeImpl;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.ui.activity.account.QuickLoginActivity;
import com.chunsun.redenvelope.ui.view.IQuickLoginView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;


/**
 * Created by Administrator on 2015/8/1.
 */
public class QuickLoginPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IQuickLoginView quickLoginView;
    private QuickLoginMode quickLoginMode;

    public QuickLoginPresenter(IQuickLoginView quickLoginView) {
        this.quickLoginView = quickLoginView;
        quickLoginMode = new QuickLoginModeImpl((QuickLoginActivity) quickLoginView);
    }

    /**
     * 获取验证码
     *
     * @param phonenum
     */
    public void getValiCode(String phonenum) {

        if (!TextUtils.isEmpty(phonenum)) {
            quickLoginView.showLoading();
            quickLoginMode.getCode(phonenum, this);
        }
    }

    /**
     * 登录
     *
     * @param phonenum
     * @param code
     */
    public void login(String phonenum, String code) {
        quickLoginView.showLoading();
        quickLoginMode.quickLogin(phonenum, code, MainApplication.getContext().getPhoneInfomation(), "afasfasdfasfsafasfsadfas", this);
    }


    /**
     * 获取验证码成功
     *
     * @param entity
     */
    public void onRegisterGetValidataSuccess(SampleResponseEntity entity) {
        quickLoginView.hideLoading();
        if (entity.isSuccess()) {
            countDown();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    /**
     * 刷新页面倒计时
     */
    private void countDown() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int time = Constants.COUNT_DONW;

                while (time > 0 && time <= Constants.COUNT_DONW) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time--;
                    EventBus.getDefault().post(new ValiCodeEvent(time + "s"));
                }

                EventBus.getDefault().post(new ValiCodeEvent("获取验证码"));
            }
        }).start();
    }

    public void onLoginSuccess(SampleResponseEntity entity) {
        quickLoginView.hideLoading();
        if (entity.isSuccess()) {
            new Preferences(MainApplication.getContext()).setToken(entity.getResult());
            quickLoginView.success();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CODE://获取验证码成功
                SampleResponseEntity entity = (SampleResponseEntity) data;
                onRegisterGetValidataSuccess(entity);
                break;
            case Constants.LISTENER_TYPE_LOGIN:
                SampleResponseEntity entity1 = (SampleResponseEntity) data;
                onLoginSuccess(entity1);
                break;
        }
    }
}
