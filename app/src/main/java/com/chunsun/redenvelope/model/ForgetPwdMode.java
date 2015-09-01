package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.presenter.OnRegisterGetValidataCodeListener;
import com.chunsun.redenvelope.presenter.OnRegisterNextStepListener;

/**
 * Created by Administrator on 2015/8/3.
 */
public interface ForgetPwdMode {

    void getCode(String mobile, BaseMultiLoadedListener listener);

    void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener);
}
