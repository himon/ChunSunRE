package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/7/30.
 * 验证码
 */
public class ValiCodeEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ValiCodeEvent(String msg) {
        this.msg = msg;
    }
}
