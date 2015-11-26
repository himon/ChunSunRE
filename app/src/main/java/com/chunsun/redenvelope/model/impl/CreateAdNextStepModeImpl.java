package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdNextStepMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextStepActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/24.
 */
public class CreateAdNextStepModeImpl implements CreateAdNextStepMode {

    private CreateAdNextStepActivity mActivity;
    private HttpManager mManager;

    public CreateAdNextStepModeImpl(CreateAdNextStepActivity activity) {
        this.mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getAdDelaySecondsRate(final BaseMultiLoadedListener listener) {
        mManager.getAdDelaySecondsRate(listener, mActivity);
    }
}
