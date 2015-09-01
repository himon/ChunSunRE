package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;

/**
 * Created by Administrator on 2015/8/5.
 */
public interface OnGetHbPoolListener {

    void onLoadSuccess(RedListDetailEntity entity);

    void onLoadError();
}
