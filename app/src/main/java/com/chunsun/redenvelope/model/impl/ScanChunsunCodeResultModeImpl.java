package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.ScanChunsunCodeResultMode;
import com.chunsun.redenvelope.ui.activity.scan.ScanChunsunCodeResultActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/5 16:37
 * @des
 */
public class ScanChunsunCodeResultModeImpl implements ScanChunsunCodeResultMode {

    private ScanChunsunCodeResultActivity mActivity;
    private HttpManager mManager;

    public ScanChunsunCodeResultModeImpl(ScanChunsunCodeResultActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }


    @Override
    public void validateCoupon(final String sellerToken, final String code, final BaseMultiLoadedListener listener) {
        mManager.validateCoupon(sellerToken, code, listener, mActivity);
    }

    @Override
    public void using(final String sellerToken, final String code, final BaseMultiLoadedListener listener) {
        mManager.using(sellerToken, code, listener, mActivity);
    }
}
