package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.presenter.OnGetCarrierOperatorListener;
import com.chunsun.redenvelope.presenter.OnRechargeMobileListener;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface PhoneRechargeConfirmMode {

    void getCarrierOperator(String mobile, BaseMultiLoadedListener listener);

    void rechargeMobile(String token, String mobile, String yunyingshang,
                        int cz_poundage_id, BaseMultiLoadedListener listener);
}
