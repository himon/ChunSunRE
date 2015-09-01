package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface OnGetInviteRecordListener {

    void onGetInviteRecordSuccess(InviteRecordEntity response);

    void onGetInviteRecordError();
}
