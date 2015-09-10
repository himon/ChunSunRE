package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/9/10.
 * 支付宝支付回调接口
 */
public interface IAlipayResult {

    /**
     * 获取支付宝支付结果
     *
     * @param result
     * @param msg
     */
    void setAlipayResult(boolean result, String msg);
}
