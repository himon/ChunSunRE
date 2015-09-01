package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface OnGetHbAdListener {

    void onGetSuccess(RedAutoAdEntity entity);

    void onGetError();
}
