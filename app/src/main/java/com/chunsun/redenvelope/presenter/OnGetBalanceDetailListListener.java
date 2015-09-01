package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.BalanceListEntity;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface OnGetBalanceDetailListListener {

    void onGetBalanceListSuccess(BalanceListEntity response);

    void onGetBalanceListError();
}
