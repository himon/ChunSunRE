package com.chunsun.redenvelope.presenter;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WithdrawcashAlipayConfirmMode;
import com.chunsun.redenvelope.model.impl.WithdrawcashAlipayConfirmModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IWithdrawcashConfirmView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawcashConfirmPresenter extends UserLosePresenter<IWithdrawcashConfirmView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IWithdrawcashConfirmView mIWithdrawcashAlipayConfirmView;
    private WithdrawcashAlipayConfirmMode mWithdrawcashAlipayConfirmMode;

    public WithdrawcashConfirmPresenter(IWithdrawcashConfirmView iWithdrawcashAlipayConfirmView) {
        this.mIWithdrawcashAlipayConfirmView = iWithdrawcashAlipayConfirmView;
        mWithdrawcashAlipayConfirmMode = new WithdrawcashAlipayConfirmModeImpl((Activity) iWithdrawcashAlipayConfirmView);
    }

    public void rechargeByAlipay(String token, String zfb_no, String zfb_name, int zfb_poundage_id) {
        mWithdrawcashAlipayConfirmMode.rechargeByAlipay(token, zfb_no, zfb_name, zfb_poundage_id, this);
    }

    public void rechargeByBank(String token, String name, String bank_name, String bank_no, String open_bank_name, String province, String city, String rate_id) {
        mWithdrawcashAlipayConfirmMode.rechargeByBank(token, name, bank_name, bank_no, open_bank_name, province, city, rate_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_RECHARGE_BY_ALIPAY:
                mIWithdrawcashAlipayConfirmView.commitFinish();
                break;
            case Constants.LISTENER_TYPE_RECHARGE_BY_BANK:
                mIWithdrawcashAlipayConfirmView.commitFinish();
        }
    }

    @Override
    public void onError(String msg) {
        mIWithdrawcashAlipayConfirmView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        mIWithdrawcashAlipayConfirmView.hideLoading();
    }
}
