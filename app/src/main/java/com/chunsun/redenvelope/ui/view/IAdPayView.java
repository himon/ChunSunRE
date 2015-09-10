package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.AdPayAmountDetailEntity;

/**
 * Created by Administrator on 2015/9/8.
 */
public interface IAdPayView extends IAlipayResult{

    void setData(AdPayAmountDetailEntity.ResultEntity result);

    void bankPay();

    void alipay();

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
