package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/4 13:33
 * @des
 */
public interface ICreateAdCouponNextStepView {

    /**
     * 获取延时时间
     *
     * @param result
     */
    void setDelaySecondsRateData(List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> result);

    /**
     * 跳转价格说明
     */
    void toAdPriceExplain();

    /**
     * 跳转说明
     */
    void toIllustrate();

    /**
     * 下一步
     */
    void toNextStep();

}
