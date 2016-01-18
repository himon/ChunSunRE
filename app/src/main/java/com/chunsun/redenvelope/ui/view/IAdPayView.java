package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.AdPayAmountDetailEntity;

/**
 * Created by Administrator on 2015/9/8.
 */
public interface IAdPayView extends IAlipayResult{

    void setData(AdPayAmountDetailEntity.ResultEntity result);

    /**
     * 网上银行支付
     */
    void bankPay();

    /**
     * 支付宝支付
     */
    void alipay();

    /**
     * 余额支付
     */
    void balancePay();

    /**
     * 支付成功
     *
     * @param msg
     */
    void paySuccess(String msg);

    /**
     * 支付失败
     *
     * @param msg
     */
    void payError(String msg);


}
