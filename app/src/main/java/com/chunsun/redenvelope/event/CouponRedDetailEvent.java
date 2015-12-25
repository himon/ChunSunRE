package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/3 16:08
 * @des
 */
public class CouponRedDetailEvent {

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

    public CouponRedDetailEvent(int second) {
        this.second = second;
    }

    public CouponRedDetailEvent(String msg, String content) {
        this.msg = msg;
        this.content = content;
    }
}
