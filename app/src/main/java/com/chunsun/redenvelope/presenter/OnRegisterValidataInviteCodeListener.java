package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/7/30.
 */
public interface OnRegisterValidataInviteCodeListener {

    void inviteCodeSuccess(SampleResponseEntity entity);

    void inviteCodeError();

}
