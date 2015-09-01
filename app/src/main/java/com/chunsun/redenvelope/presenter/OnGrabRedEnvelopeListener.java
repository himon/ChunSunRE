package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface OnGrabRedEnvelopeListener {

    void onGrabSuccess(SampleResponseEntity responseEntity);

    void onGrabError();
}
