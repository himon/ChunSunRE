package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface BalanceMode {

    /**
     * 获取钱包页面显示的数据
     *
     * @param token
     * @param listener
     */
    void loadWalletData(String token, BaseMultiLoadedListenerImpl listener);
}
