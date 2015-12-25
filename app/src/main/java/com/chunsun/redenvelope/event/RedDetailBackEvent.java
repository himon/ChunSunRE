package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/8/12.
 * 红包详情Back键
 */
public class RedDetailBackEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RedDetailBackEvent(String msg) {
        this.msg = msg;
    }
}
