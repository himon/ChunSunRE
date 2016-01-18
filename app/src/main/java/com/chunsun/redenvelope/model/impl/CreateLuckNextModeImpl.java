package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateLuckNextMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/18 11:30
 * @des
 */
public class CreateLuckNextModeImpl extends BaseModeImpl implements CreateLuckNextMode{

    private Activity mAcitvity;

    public CreateLuckNextModeImpl(Activity acitvity) {
        this.mAcitvity = acitvity;
    }

    /**
     * 获取拼手气套餐列表
     * @param listener
     */
    @Override
    public void getFightLuckPackageList(BaseMultiLoadedListener listener) {
        mManager.getFightLuckPackageList(listener, mAcitvity);
    }
}
