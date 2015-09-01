package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface AdFragmentMode {

    /**
     * 获取广告延时时间
     */
    void getAdDelaySecondsRate(BaseSingleLoadedListener listener);
}
