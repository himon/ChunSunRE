package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.BalanceEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface IPhoneRechargeView {

    void toRechargePhoneConfirm(BalanceEntity.ResultEntity.CzPoundageEntity entity, String phonenum);
}
