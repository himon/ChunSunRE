package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface IMineInviteCodeView {

    void setData(InviteRecordEntity.ResultEntity result);

    void toInviteRecordList();
}
