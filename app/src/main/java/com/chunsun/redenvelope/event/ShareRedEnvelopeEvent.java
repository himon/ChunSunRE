package com.chunsun.redenvelope.event;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/30 15:08
 * @des
 */
public class ShareRedEnvelopeEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ShareRedEnvelopeEvent(String msg) {
        this.msg = msg;
    }
}
