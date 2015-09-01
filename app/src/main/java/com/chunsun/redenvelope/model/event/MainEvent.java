package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/8/1.
 */
public class MainEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MainEvent(String msg) {
        this.msg = msg;
    }
}
