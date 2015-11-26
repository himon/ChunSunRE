package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.RegisterNextMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.model.impl.RegisterNextModeImpl;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.ui.activity.account.RegisterNextActivity;
import com.chunsun.redenvelope.ui.view.IRegisterNextView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

/**
 * Created by Administrator on 2015/7/30.
 */
public class RegisterNextPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IRegisterNextView registerNextView;
    private RegisterNextMode registerNextMode;

    public RegisterNextPresenter(IRegisterNextView registerNextView) {
        this.registerNextView = registerNextView;
        registerNextMode = new RegisterNextModeImpl((RegisterNextActivity) registerNextView);
    }


    public void register(String type, String mobile, String verify_code,
                         String password, String confirm_pwd, String push_device_token,
                         String json_str, String invitation_code) {

        if (StringUtil.validataPwd(password, confirm_pwd)) {
            registerNextView.showLoading();
            registerNextMode.register(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code, this);
        }
    }

    /**
     * 注册成功
     *
     * @param entity
     */
    public void onRegisterSuccess(SampleResponseEntity entity) {
        registerNextView.hideLoading();
        if (entity.isSuccess()) {
            new Preferences(MainApplication.getContext()).setToken(entity.getResult());
            registerNextMode.getUserInfo(entity.getResult(), this);
            registerNextView.successShowDialog(entity.getMsg());
        } else {
            registerNextView.errorShowDialog(entity.getMsg());
        }
    }

    /**
     * 获取用户数据
     *
     * @param entity
     */
    public void onGetUserInfoSuccess(UserEntity entity) {
        MainApplication.getContext().setmUserEntity(entity.getResult());
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_REGISTER:
                onRegisterSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                onGetUserInfoSuccess((UserEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
        registerNextView.hideLoading();
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
        registerNextView.hideLoading();
    }
}
