package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.event.ValiCodeEvent;
import com.chunsun.redenvelope.model.impl.ForgetPwdModeImpl;
import com.chunsun.redenvelope.presenter.OnRegisterGetValidataCodeListener;
import com.chunsun.redenvelope.presenter.OnRegisterNextStepListener;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdActivity;
import com.chunsun.redenvelope.ui.view.IForgetPwdView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/1.
 */
public class ForgetPwdPresenter implements BaseMultiLoadedListener<BaseEntity>, OnRegisterGetValidataCodeListener, OnRegisterNextStepListener {

    private IForgetPwdView forgetPwdView;
    private ForgetPwdMode forgetPwdMode;


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

    @Override
    public void onRegisterGetValidataError() {

    }

    /**
     * 获取验证码成功
     *
     * @param entity
     */
    @Override
    public void onRegisterGetValidataSuccess(SampleResponseEntity entity) {
        forgetPwdView.hideLoading();
        if (entity.isSuccess()) {
            countDown();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    /**
     * 验证成功
     *
     * @param response
     */
    @Override
    public void nextStepSuccess(SampleResponseEntity response) {
        forgetPwdView.hideLoading();
        if(response.isSuccess()){
            forgetPwdView.nextStep();
        }else{
            ShowToast.Short(response.getMsg());
        }
    }

    @Override
    public void nextStepError(VolleyError error) {

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

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onException(String msg) {

    }
}
