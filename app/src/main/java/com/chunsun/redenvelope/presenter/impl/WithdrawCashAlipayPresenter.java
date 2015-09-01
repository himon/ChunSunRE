package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.ui.view.IWithdrawCashAlipayView;
import com.chunsun.redenvelope.utils.RegexUtil;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawCashAlipayPresenter {

    private IWithdrawCashAlipayView mIWithdrawCashAlipayView;

    public WithdrawCashAlipayPresenter(IWithdrawCashAlipayView iWithdrawCashAlipayView) {
        this.mIWithdrawCashAlipayView = iWithdrawCashAlipayView;
    }

    public void commit(String account, String name){
        if(TextUtils.isEmpty(account)){
            ShowToast.Short("支付宝账号不能为空");
            return;
        }else if(TextUtils.isEmpty(name)){
            ShowToast.Short("姓名不能为空");
            return;
        }
        if (!RegexUtil.matcherPhoneAndEmail(account)) {
            ShowToast.Short("您输入的支付宝账号不正确");
            return;
        }

        mIWithdrawCashAlipayView.toWithdrawCashConfirm(account, name);
    }
}
