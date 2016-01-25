package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/25 15:20
 * @des
 */
public class MeFragmentRefreshEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MeFragmentRefreshEvent(String msg) {
        this.msg = msg;
    }
}
