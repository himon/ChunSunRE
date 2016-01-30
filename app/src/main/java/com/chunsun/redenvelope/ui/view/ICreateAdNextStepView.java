package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.AdDelaySecondsRateEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface ICreateAdNextStepView {

    void setDelaySecondsRateData(AdDelaySecondsRateEntity.ResultEntity result);

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
