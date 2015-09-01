package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface IBalanceView {

    void setData(BalanceEntity.ResultEntity result, List<SampleEntity> list);

    void toBalanceDetail(SampleEntity entity);

    void toWithdrawCashByBank();

    void toWithdrawCashByAlipay();

    void toPhoneRecharge();

    void toBalanceRecharge();
}
