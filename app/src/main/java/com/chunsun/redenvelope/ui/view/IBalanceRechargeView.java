package com.chunsun.redenvelope.ui.view;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface IBalanceRechargeView {

    /**
     * 验证输入金额
     * @param amount
     */
    void validateFinish(String amount);

    /**
     * 获取订单id
     * @param orderId
     */
    void getOrderIdFinish(String orderId);

    /**
     * 支付宝支付
     */
    void payByAlipay(String orderId);

    /**
     * 银联支付
     */
    void payByBank(String orderId);
}
