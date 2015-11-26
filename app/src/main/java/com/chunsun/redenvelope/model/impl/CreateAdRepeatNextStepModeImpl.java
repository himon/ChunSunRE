package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdRepeatNextStepMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdRepeatNextStepActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/8 12:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CreateAdRepeatNextStepModeImpl implements CreateAdRepeatNextStepMode {

    private CreateAdRepeatNextStepActivity mActivity;
    private HttpManager mManager;

    public CreateAdRepeatNextStepModeImpl(CreateAdRepeatNextStepActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getRepeatMeal(final BaseMultiLoadedListener listener) {
        mManager.getRepeatMeal(listener, mActivity);
    }
}
