package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.ui.view.IPhoneRechargeView;
import com.chunsun.redenvelope.utils.RegexUtil;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PhoneRechargePresenter {

    private IPhoneRechargeView mIPhoneRechargeView;

    public PhoneRechargePresenter(IPhoneRechargeView iPhoneRechargeView) {
        this.mIPhoneRechargeView = iPhoneRechargeView;
    }

    public void validatePhoneNum(BalanceEntity.ResultEntity.CzPoundageEntity entity, String phonenum){
        if(RegexUtil.matcherPhone(phonenum)){
            mIPhoneRechargeView.toRechargePhoneConfirm(entity, phonenum);
        }else{
            ShowToast.Short("手机号码格式不正确！");
        }
    }
}
