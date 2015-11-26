package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.PhoneRechargeConfirmMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.CarrierOperatorEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.PhoneRechargeConfirmModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.PhoneRechargeConfirmActivity;
import com.chunsun.redenvelope.ui.view.IPhoneRechargeConfirmView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PhoneRechargeConfirmPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IPhoneRechargeConfirmView mIPhoneRechargeConfirmView;
    private PhoneRechargeConfirmMode mPhoneRechargeConfirmMode;

    public PhoneRechargeConfirmPresenter(IPhoneRechargeConfirmView iPhoneRechargeConfirmView) {
        this.mIPhoneRechargeConfirmView = iPhoneRechargeConfirmView;
        this.mPhoneRechargeConfirmMode = new PhoneRechargeConfirmModeImpl((PhoneRechargeConfirmActivity) iPhoneRechargeConfirmView);
    }

    public void getCarrierOperator(String mobile) {
        mPhoneRechargeConfirmMode.getCarrierOperator(mobile, this);
    }

    public void rechargeMobile(String token, String mobile, String yunyingshang,
                               int cz_poundage_id) {
        mPhoneRechargeConfirmMode.rechargeMobile(token, mobile, yunyingshang, cz_poundage_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CARRIER_OPERATOR:
                CarrierOperatorEntity entity = (CarrierOperatorEntity) data;
                mIPhoneRechargeConfirmView.setCarrierOperator(entity.getAreaName() + entity.getProviderName());
                break;
            case Constants.LISTENER_TYPE_RECHARGE:
                ShowToast.Short(((SampleResponseEntity) data).getMsg());
                mIPhoneRechargeConfirmView.rechargeMobileFinish();
                break;
        }
    }
}
