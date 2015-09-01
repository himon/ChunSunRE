package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface OnNotReceivingRedListener {

    void onNotEeceivingRedListSuccess(RedDetailUnReceiveAndCollectEntity entity);

    void onNotEeceivingRedListError();
}
