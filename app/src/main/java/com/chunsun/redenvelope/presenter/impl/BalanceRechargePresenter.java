package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceRechargeMode;
import com.chunsun.redenvelope.model.entity.json.BalanceRechargeEntity;
import com.chunsun.redenvelope.model.impl.BalanceRechargeModeImpl;
import com.chunsun.redenvelope.presenter.OnRechargeBalanceListener;
import com.chunsun.redenvelope.ui.activity.personal.BalanceRechargeActivity;
import com.chunsun.redenvelope.ui.view.IBalanceRechargeView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/21.
 */
public class BalanceRechargePresenter implements BaseSingleLoadedListener<BalanceRechargeEntity> {

    private IBalanceRechargeView mIBalanceRechargeView;
    private BalanceRechargeMode mBalanceRechargeMode;

    public BalanceRechargePresenter(IBalanceRechargeView iBalanceRechargeView) {
        this.mIBalanceRechargeView = iBalanceRechargeView;
        mBalanceRechargeMode = new BalanceRechargeModeImpl((BalanceRechargeActivity) iBalanceRechargeView);
    }

    public void recharge(String token, String type, String amount) {
        mBalanceRechargeMode.recharge(token, type, amount, this);
    }

    public void validateAmount(String amount) {
        if ("".equals(amount) || ".".equals(amount)) {
            ShowToast.Short("请输入正确的金额格式！");
            return;
        }

        if (amount.startsWith(".")) {
            amount = "0" + amount;
        }

        if (amount.endsWith(".")) {
            amount = amount.substring(0, amount.length() - 1);
        }

        if (amount.indexOf(".") + 2 < amount.length() - 1) {
            amount = amount.substring(0, amount.indexOf(".") + 3);
        }

        if (Double.parseDouble(amount) <= 0) {
            ShowToast.Short("请输入 大于等于0.01的金额！");
            return;
        }
        mIBalanceRechargeView.validateFinish(amount);
    }

    @Override
    public void onSuccess(BalanceRechargeEntity response) {
        mIBalanceRechargeView.getOrderIdFinish(response.getResult().getOrder_no());
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
