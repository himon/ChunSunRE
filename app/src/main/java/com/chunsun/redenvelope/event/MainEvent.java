package com.chunsun.redenvelope.event;

import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;

/**
 * Created by Administrator on 2015/8/1.
 */
public class MainEvent {
    private String msg;
    private int count;
    private RedSuperadditionEntity.ResultEntity mEntity;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RedSuperadditionEntity.ResultEntity getmEntity() {
        return mEntity;
    }

    public void setmEntity(RedSuperadditionEntity.ResultEntity mEntity) {
        this.mEntity = mEntity;
    }

    public MainEvent(String msg) {
        this.msg = msg;
    }

    public MainEvent(String msg, int count) {
        this.msg = msg;
        this.count = count;
    }
}
