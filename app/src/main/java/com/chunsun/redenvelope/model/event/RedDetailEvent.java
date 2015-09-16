package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/8/14.
 */
public class RedDetailEvent {
    private int second;
    private String msg;
    private String content;

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

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

    public RedDetailEvent(int second) {
        this.second = second;
    }

    public RedDetailEvent(String msg, String content) {
        this.msg = msg;
        this.content = content;
    }
}
