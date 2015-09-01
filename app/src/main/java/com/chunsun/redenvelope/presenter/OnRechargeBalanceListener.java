package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.BalanceRechargeEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface OnRechargeBalanceListener {

    void onRechargeBalanceSuccess(BalanceRechargeEntity response);

    void onRechargeBalanceError();
}
