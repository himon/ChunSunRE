package com.chunsun.redenvelope.event;

/**
 * Created by Administrator on 2015/9/17.
 * 红包详情查看图片
 */
public class RedDetailShowPicBackEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RedDetailShowPicBackEvent(String msg) {
        this.msg = msg;
    }
}
