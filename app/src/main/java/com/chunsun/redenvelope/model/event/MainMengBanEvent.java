package com.chunsun.redenvelope.model.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/17 14:56
 * @des MainActivity蒙版Event
 */
public class MainMengBanEvent {

    private int msg;

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public MainMengBanEvent(int msg) {
        this.msg = msg;
    }
}
