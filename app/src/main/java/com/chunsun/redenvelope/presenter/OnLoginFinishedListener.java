package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/7/29.
 */
public interface OnLoginFinishedListener {

    void onError();

    void onSuccess(SampleResponseEntity entity);
}
