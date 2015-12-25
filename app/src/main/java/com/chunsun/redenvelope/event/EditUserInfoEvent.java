package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/9/15.
 * 修改用户基本信息
 */
public class EditUserInfoEvent {
    private int msg;
    private String content;

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EditUserInfoEvent(int msg, String content) {
        this.msg = msg;
        this.content = content;
    }
}
