package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdCouponNextStepMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdCouponNextStepActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/4 13:36
 * @des
 */
public class CreateAdCouponNextStepModeImpl implements CreateAdCouponNextStepMode {

    private CreateAdCouponNextStepActivity mActivity;
    private HttpManager mManager;

    public CreateAdCouponNextStepModeImpl(CreateAdCouponNextStepActivity createAdCouponNextStepActivity) {
        this.mActivity = createAdCouponNextStepActivity;
        mManager = new HttpManager();
    }

    @Override
    public void getAdDelaySecondsRate(final BaseMultiLoadedListener listener) {
        mManager.getAdDelaySecondsRate(listener, mActivity);
    }
}
