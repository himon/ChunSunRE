package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.InviteRecordEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface IInviteRecordListView {

    void setData(List<InviteRecordEntity.ResultEntity.BaseEntity> list);
}
