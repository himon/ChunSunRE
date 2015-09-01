package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.BalanceEntity;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface OnGetUserAmountListener {

    void onGetAmountSuccess(BalanceEntity response);

    void onGetAmountError();
}
