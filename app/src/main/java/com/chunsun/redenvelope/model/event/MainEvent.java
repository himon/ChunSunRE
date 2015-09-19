package com.chunsun.redenvelope.model.event;

import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;

/**
 * Created by Administrator on 2015/8/1.
 */
public class MainEvent {
    private String msg;
    private RedSuperadditionEntity.ResultEntity mEntity;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RedSuperadditionEntity.ResultEntity getEntity() {
        return mEntity;
    }

    public void setEntity(RedSuperadditionEntity.ResultEntity entity) {
        mEntity = entity;
    }

    public MainEvent(String msg) {
        this.msg = msg;
    }
}
