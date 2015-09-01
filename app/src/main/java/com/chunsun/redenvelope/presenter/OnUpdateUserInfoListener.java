package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface OnUpdateUserInfoListener {

    void onUpdateUserInfoSuccess(SampleResponseEntity entity);

    void onUpdateUserInfoError();
}
