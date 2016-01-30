package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.WithdrawcashAlipayConfirmMode;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.WithdrawcashAlipayConfirmModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.WithdrawcashAlipayConfirmActivity;
import com.chunsun.redenvelope.ui.view.IWithdrawcashAlipayConfirmView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawcashAlipayConfirmPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private IWithdrawcashAlipayConfirmView mIWithdrawcashAlipayConfirmView;
    private WithdrawcashAlipayConfirmMode mWithdrawcashAlipayConfirmMode;

    public WithdrawcashAlipayConfirmPresenter(IWithdrawcashAlipayConfirmView iWithdrawcashAlipayConfirmView) {
        this.mIWithdrawcashAlipayConfirmView = iWithdrawcashAlipayConfirmView;
        mWithdrawcashAlipayConfirmMode = new WithdrawcashAlipayConfirmModeImpl((WithdrawcashAlipayConfirmActivity) iWithdrawcashAlipayConfirmView);
    }

    public void rechargeByAlipay(String token, String zfb_no, String zfb_name,
                                 int zfb_poundage_id) {
        mWithdrawcashAlipayConfirmMode.rechargeByAlipay(token, zfb_no, zfb_name, zfb_poundage_id, this);
    }

    @Override
    public void onSuccess(SampleResponseEntity response) {
        ShowToast.Short(response.getMsg());
        mIWithdrawcashAlipayConfirmView.commitFinish();
    }

    @Override
    public void onError(String msg) {
        mIWithdrawcashAlipayConfirmView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        mIWithdrawcashAlipayConfirmView.hideLoading();
        ShowToast.Short(msg);
    }
}
