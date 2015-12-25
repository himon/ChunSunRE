package com.chunsun.redenvelope.event;

import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/10 13:55
 * @des 追加Event
 */
public class SuperadditionEvent {
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

    public SuperadditionEvent(String msg) {
        this.msg = msg;
    }
}
