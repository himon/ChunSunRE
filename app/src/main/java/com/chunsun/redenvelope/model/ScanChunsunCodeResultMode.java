package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/5 16:36
 * @des
 */
public interface ScanChunsunCodeResultMode {

    /**
     * 验证
     *
     * @param sellerToken
     * @param code
     * @param listener
     */
    void validateCoupon(String sellerToken, String code, BaseMultiLoadedListener listener);

    /**
     * 使用春笋券
     */
    void using(String sellerToken, String code, BaseMultiLoadedListener listener);
}
