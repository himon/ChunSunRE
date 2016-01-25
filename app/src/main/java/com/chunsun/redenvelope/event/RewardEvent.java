package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/25 12:47
 * @des 互相奖励event
 */
public class RewardEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RewardEvent(String msg) {
        this.msg = msg;
    }
}
