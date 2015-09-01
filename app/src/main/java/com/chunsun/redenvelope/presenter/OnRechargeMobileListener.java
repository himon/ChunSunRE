package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface OnRechargeMobileListener {

    void onRechargeMobileSuccess(SampleResponseEntity entity);

    void onRechargeMobileError();
}
