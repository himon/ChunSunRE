package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/9/9.
 */
public class WelcomeEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WelcomeEvent(String msg) {
        this.msg = msg;
    }
}
