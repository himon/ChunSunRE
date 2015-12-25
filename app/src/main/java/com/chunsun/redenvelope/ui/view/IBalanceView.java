package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface IBalanceView {

    /**
     * 获取余额数据
     *
     * @param result
     * @param list
     */
    void setData(BalanceEntity.ResultEntity result, List<SampleEntity> list);

    /**
     * 明细详情
     *
     * @param entity
     */
    void toBalanceDetail(SampleEntity entity);

    /**
     * 充值
     */
    void toBalanceRecharge();

    /**
     * 提现
     */
    void toWithdrawCash();
}
