package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/28 11:45
 * @des
 */
public class WalletEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WalletEvent(String msg) {
        this.msg = msg;
    }
}
