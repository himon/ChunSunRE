package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/8/14.
 */
public class RedDetailEvent {
    private int msg;

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public RedDetailEvent(int msg) {
        this.msg = msg;
    }
}
