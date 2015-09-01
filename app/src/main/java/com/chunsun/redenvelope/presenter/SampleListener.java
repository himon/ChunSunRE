package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/3.
 */
public interface SampleListener {

    void onSuccess(SampleResponseEntity entity);

    void onError();
}
