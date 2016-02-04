package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdNextMode;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.ForgetPwdNextModeImpl;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdNextActivity;
import com.chunsun.redenvelope.ui.view.IForgetPwdNextView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdNextPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {


    private IForgetPwdNextView forgetPwdNextView;
    private ForgetPwdNextMode forgetPwdNextMode;

    public ForgetPwdNextPresenter(IForgetPwdNextView forgetPwdNextView) {
        this.forgetPwdNextView = forgetPwdNextView;
        forgetPwdNextMode = new ForgetPwdNextModeImpl((ForgetPwdNextActivity) forgetPwdNextView);
    }

    public void setPassword(String mobile, String verify_code,
                            String new_pwd, String confirm_pwd) {
        if (StringUtil.validataPwd(new_pwd, confirm_pwd)) {
            forgetPwdNextView.showLoading();
            forgetPwdNextMode.findPwdSubmit(mobile, verify_code, new_pwd, confirm_pwd, this);
        }
    }

    @Override
    public void onSuccess(SampleResponseEntity entity) {
        forgetPwdNextView.hideLoading();
        forgetPwdNextView.success(entity.getMsg());
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {

    }
}
