package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface IAdFragment {

    void setDelaySecondsRateData(List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> result);

    void toSelectDelaySeconds();

    void toAdPriceExplain();

    void toNextStep();
}
