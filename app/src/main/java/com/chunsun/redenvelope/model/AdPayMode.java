package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/8.
 */
public interface AdPayMode {


    void getAdAmountDetail(String mToken, String hb_id, BaseMultiLoadedListener listener);

    void payByBalance(String token, String hb_id, BaseMultiLoadedListener listener);
}
