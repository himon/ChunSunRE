package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface BalanceDetailListMode {

    /**
     * 加载账户明细数据列表
     *
     * @param token
     * @param type
     * @param page_index
     * @param listener
     */
    void loadBalanceDetailLitsData(String token, String type, int page_index, BaseSingleLoadedListener listener);
}
