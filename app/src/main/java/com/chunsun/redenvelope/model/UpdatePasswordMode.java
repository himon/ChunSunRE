package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.presenter.OnUpdatePasswordListener;
import com.chunsun.redenvelope.presenter.impl.UpdatePasswordPresenter;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface UpdatePasswordMode {

    void updatePassword(String token, String old_pwd, String new_pwd, String confirm_pwd, BaseSingleLoadedListener listener);

    void updatePasswordNotOldPwd(String token, String new_pwd, String confirm_pwd, BaseSingleLoadedListener listener);
}
