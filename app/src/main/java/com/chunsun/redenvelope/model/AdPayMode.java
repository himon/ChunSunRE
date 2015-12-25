package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/8.
 */
public interface AdPayMode {


    void getAdAmountDetail(String mToken, String hb_id, UserLoseMultiLoadedListener listener);

    void payByBalance(String token, String hb_id, UserLoseMultiLoadedListener listener);
}
