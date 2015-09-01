package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/8/11.
 * 链接红包详情
 */
public class WebRedDetailEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WebRedDetailEvent(String msg) {
        this.msg = msg;
    }
}
