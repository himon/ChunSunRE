package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.ForgetPwdMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.ValiCodeEvent;
import com.chunsun.redenvelope.model.impl.ForgetPwdModeImpl;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdActivity;
import com.chunsun.redenvelope.ui.view.IForgetPwdView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/1.
 */
public class ForgetPwdPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IForgetPwdView forgetPwdView;
    private ForgetPwdMode forgetPwdMode;
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public ForgetPwdPresenter(ForgetPwdActivity view) {
        forgetPwdView = view;
        forgetPwdMode = new ForgetPwdModeImpl(view);
    }

    /**
     * 获取验证码
     *
     * @param phonenum
     */
    public void getValiCode(String phonenum) {

        if (!TextUtils.isEmpty(phonenum)) {
            forgetPwdView.showLoading();
            forgetPwdMode.getCode(phonenum, this);
        }
    }

    public void nextStep(String phonenum, String code) {
        forgetPwdView.showLoading();
        forgetPwdMode.nextStep(phonenum, code, this);
    }

    /**
     * 获取验证码成功
     *
     * @param entity
     */
    public void onRegisterGetValidataSuccess(SampleResponseEntity entity) {
        forgetPwdView.hideLoading();
        forgetPwdView.enableGetCode();
        if (entity.isSuccess()) {
            countDown();
            forgetPwdView.getFocus();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    /**
     * 验证成功
     *
     * @param response
     */
    public void nextStepSuccess(SampleResponseEntity response) {
        forgetPwdView.hideLoading();
        if (response.isSuccess()) {
            forgetPwdView.nextStep();
        } else {
            ShowToast.Short(response.getMsg());
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

                while (loop && time > 0 && time <= Constants.COUNT_DONW) {
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

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CODE:
                onRegisterGetValidataSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_NEXT_STEP:
                nextStepSuccess((SampleResponseEntity) data);
                break;
        }
    }

    @Override
    public void onError(int event_tag, String msg) {
        super.onError(event_tag, msg);
        forgetPwdView.hideLoading();
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        forgetPwdView.hideLoading();
    }
}
