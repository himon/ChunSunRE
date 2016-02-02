package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 18:08
 * @des
 */
public class WithdrawCashEvent {
    private int type;

    private String msg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WithdrawCashEvent(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
