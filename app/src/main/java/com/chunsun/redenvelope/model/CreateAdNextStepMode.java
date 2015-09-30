package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface CreateAdNextStepMode {

    /**
     * 获取广告延时时间
     */
    void getAdDelaySecondsRate(BaseMultiLoadedListener listener);
}
