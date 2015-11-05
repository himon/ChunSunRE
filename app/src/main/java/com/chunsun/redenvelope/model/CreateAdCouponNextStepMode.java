package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/4 13:35
 * @des
 */
public interface CreateAdCouponNextStepMode {

    /**
     * 获取广告延时时间
     */
    void getAdDelaySecondsRate(BaseMultiLoadedListener listener);
}
