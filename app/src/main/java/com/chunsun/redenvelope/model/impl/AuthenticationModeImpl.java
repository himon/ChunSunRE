package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AuthenticationMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 10:48
 * @des
 */
public class AuthenticationModeImpl extends BaseModeImpl implements AuthenticationMode{

    private Activity mActivity;

    public AuthenticationModeImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void userCmp(String token, String cmp_name, String cmp_tel, String address, String contact_mobile, String cmp_contact, String bank_no, String bank_name, String tax_no, String licence_img_byte_str, String ID_img_byte_str, UserLoseMultiLoadedListener listener) {
        mManager.userCmp(token, cmp_name, cmp_tel, address, contact_mobile, cmp_contact, bank_no, bank_name, tax_no, licence_img_byte_str, ID_img_byte_str, listener, mActivity);
    }
}
