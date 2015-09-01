package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.CarrierOperatorEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface OnGetCarrierOperatorListener {

    void onGetCarrierOperatorSuccess(CarrierOperatorEntity entity);

    void onGetCarrierOperatorError();
}
