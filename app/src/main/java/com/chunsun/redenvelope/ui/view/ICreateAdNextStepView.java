package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface ICreateAdNextStepView {

    void setDelaySecondsRateData(List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> result);

    void toSelectDelaySeconds();

    /**
     * 跳转价格说明
     */
    void toAdPriceExplain();

    /**
     * 跳转说明
     */
    void toIllustrate();

    void toNextStep();
}
