package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/14.
 */
public interface OnRedDetailSetFavoriteListener {

    void onSetFavoriteSuccess(SampleResponseEntity response);

    void onSetFavoriteError();
}
