package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.PhoneRechargeConfirmMode;
import com.chunsun.redenvelope.ui.activity.personal.PhoneRechargeConfirmActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PhoneRechargeConfirmModeImpl implements PhoneRechargeConfirmMode {

    private PhoneRechargeConfirmActivity mActivity;
    private HttpManager mManager;

    public PhoneRechargeConfirmModeImpl(PhoneRechargeConfirmActivity phoneRechargeConfirmActivity) {
        this.mActivity = phoneRechargeConfirmActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCarrierOperator(final String mobile, final BaseMultiLoadedListener listener) {

        mManager.getCarrierOperator(mobile, listener, mActivity);
    }

    @Override
    public void rechargeMobile(final String token, final String mobile, final String yunyingshang, final int cz_poundage_id, final BaseMultiLoadedListener listener) {
        mManager.rechargeMobile(token, mobile, yunyingshang, cz_poundage_id, listener, mActivity);
    }
}
