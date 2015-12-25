package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/24 14:18
 * @des
 */
public class CreateCircleResultEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CreateCircleResultEvent(String msg) {
        this.msg = msg;
    }

}
