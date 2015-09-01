package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface OnGetAdDelaySecondsRateListener {

    void onGetAdDelaySecondsRateSuccess(AdDelaySecondsRateEntity response);

    void onGetAdDelaySecondsRateError();
}
