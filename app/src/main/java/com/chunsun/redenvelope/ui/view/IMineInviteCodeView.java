package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface IMineInviteCodeView {

    void setData(InviteRecordEntity.ResultEntity result);

    void toInviteRecordList();

    void getShareUrlSuccess(UserEntity entity);
}
