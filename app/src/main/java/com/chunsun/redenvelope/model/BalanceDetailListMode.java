package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface BalanceDetailListMode {

    void loadData(String token, String type, int page_index, BaseSingleLoadedListener listener);
}
