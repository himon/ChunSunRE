package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.RegisterMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.ValiCodeEvent;
import com.chunsun.redenvelope.model.impl.RegisterModeImpl;
import com.chunsun.redenvelope.ui.activity.account.RegisterActivity;
import com.chunsun.redenvelope.ui.view.IRegisterView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/7/29.
 */
public class RegisterPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IRegisterView registerView;
    private RegisterMode registerMode;

    private String mInviteCode = "";
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        registerMode = new RegisterModeImpl((RegisterActivity) registerView);
    }

    /**
     * 验证手机号码
     *
     * @param phonenum
     * @return
     */
    public boolean valiPhonenum(String phonenum) {
        if (TextUtils.isEmpty(phonenum)) {
            return false;
        }
        return true;
    }

    /**
     * 验证验证码
     *
     * @param code
     * @return
     */
    public boolean valiInviteCode(String code) {
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        return true;
    }

    /**
     * 获取验证码
     *
     * @param phonenum
     */
    public void getValiCode(String phonenum) {
        registerView.showLoading();
        if (valiPhonenum(phonenum)) {
            registerMode.registerGetCode(phonenum, this);
        }
    }


    /**
     * 下一步
     *
     * @param phonenum
     * @param code
     * @param inviteCode
     */
    public void next(String phonenum, String code, String inviteCode) {
        mInviteCode = inviteCode;
        if (StringUtil.isPhoneNum(phonenum)) {
            registerView.showLoading();
            registerMode.nextStep(phonenum, code, this);
        } else {
            registerView.phoneNumError();
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

    /**
     * 获取验证码成功
     *
     * @param entity
     */
    public void onRegisterGetValidataSuccess(SampleResponseEntity entity) {
        ShowToast.Short("验证码已发送！");
        if (entity.isSuccess()) {
            countDown();
            registerView.getFocus();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    /**
     * 验证验证码成功
     *
     * @param entity
     */
    public void nextStepSuccess(SampleResponseEntity entity) {
        if (entity.isSuccess()) {
            if (valiInviteCode(mInviteCode)) {
                registerMode.hasInviteCodeNextStep(mInviteCode, this);
            } else {
                registerView.hideLoading();
                registerView.success();
            }
        } else {
            registerView.hideLoading();
            ShowToast.Short(entity.getMsg());
        }
    }

    /**
     * 验证邀请码成功
     *
     * @param entity
     */
    public void inviteCodeSuccess(SampleResponseEntity entity) {
        registerView.hideLoading();
        if (entity.isSuccess()) {
            registerView.success();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }


    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        registerView.hideLoading();
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CODE:
                onRegisterGetValidataSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_INVITE_CODE:
                inviteCodeSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_NEXT_STEP:
                nextStepSuccess((SampleResponseEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        registerView.getCodeEnabled(true);
        ShowToast.Short(msg);
        registerView.hideLoading();
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
        registerView.hideLoading();
    }
}
