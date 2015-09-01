package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface OnRechargeByAlipayListener {

    void onRechargeByAlipaySuccess(SampleResponseEntity response);

    void onRechargeByAlipayError();
}
