package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/8/11.
 * 链接红包详情
 */
public class WebRedDetailEvent {
    private String msg;
    private String content;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WebRedDetailEvent(String msg) {
        this.msg = msg;
    }
}
