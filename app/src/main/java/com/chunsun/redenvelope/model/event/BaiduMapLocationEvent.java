package com.chunsun.redenvelope.model.event;

/**
 * Created by Administrator on 2015/9/9.
 * 获取位置后刷新首页位置
 */
public class BaiduMapLocationEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaiduMapLocationEvent(String msg) {
        this.msg = msg;
    }
}
