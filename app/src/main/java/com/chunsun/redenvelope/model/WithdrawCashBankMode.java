package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 17:03
 * @des
 */
public interface WithdrawCashBankMode {

    void userCashInfo(String token, UserLoseMultiLoadedListener listener);

    /**
     * 获取省市列表
     *
     * @param listener
     */
    void initProvinceAndCity(UserLoseMultiLoadedListener listener);
}
