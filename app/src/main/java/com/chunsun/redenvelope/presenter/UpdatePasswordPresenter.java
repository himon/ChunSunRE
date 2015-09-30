package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.UpdatePasswordMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.UpdatePasswordModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.UpdatePasswordActivity;
import com.chunsun.redenvelope.ui.view.IUpdatePasswordView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/24.
 */
public class UpdatePasswordPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private IUpdatePasswordView mIUpdatePasswordView;
    private UpdatePasswordMode mUpdatePasswordMode;

    public UpdatePasswordPresenter(IUpdatePasswordView iUpdatePasswordView) {
        this.mIUpdatePasswordView = iUpdatePasswordView;
        this.mUpdatePasswordMode = new UpdatePasswordModeImpl((UpdatePasswordActivity) iUpdatePasswordView);
    }

    /**
     * 修改密码
     *
     * @param token
     * @param old_pwd
     * @param new_pwd
     * @param confirm_pwd
     * @param ishas       是否有旧密码
     */
    public void updatePassword(String token, String old_pwd, String new_pwd, String confirm_pwd, boolean ishas) {
        if (validate(old_pwd, new_pwd, confirm_pwd, ishas)) {
            if (ishas) {
                mUpdatePasswordMode.updatePassword(token, old_pwd, new_pwd, confirm_pwd, this);
            } else {
                mUpdatePasswordMode.updatePasswordNotOldPwd(token, new_pwd, confirm_pwd, this);
            }
        }
    }

    private boolean validate(String old_pwd, String new_pwd, String confirm_pwd, boolean ishas) {

        if (ishas && TextUtils.isEmpty(old_pwd)) {
            ShowToast.Short("旧密码不能为空！");
            return false;
        }

        if (TextUtils.isEmpty(new_pwd) || TextUtils.isEmpty(confirm_pwd)) {
            ShowToast.Short("密码不能为空！");
            return false;
        }

        if (!new_pwd.equals(confirm_pwd)) {
            ShowToast.Short("两次输入的密码不一致！");
            return false;
        }

        if (new_pwd.length() < 6 || new_pwd.length() > 20) {
            ShowToast.Short("请输入密码的长度在6至20位之间！");
            return false;
        }

        return true;
    }

    @Override
    public void onSuccess(SampleResponseEntity entity) {
        ShowToast.Short(entity.getMsg());
        mIUpdatePasswordView.updateSuccess();
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
