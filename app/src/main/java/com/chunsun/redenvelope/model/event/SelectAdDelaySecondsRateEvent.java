package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SelectAdDelaySecondsRateEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SelectAdDelaySecondsRateEvent(String msg) {
        this.msg = msg;
    }
}
