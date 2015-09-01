package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface BalanceMode {

    void loadData(String token, BaseSingleLoadedListener listener);
}
