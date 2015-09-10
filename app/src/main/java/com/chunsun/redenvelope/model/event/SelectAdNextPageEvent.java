package com.chunsun.redenvelope.model.event;

import com.chunsun.redenvelope.model.entity.SampleEntity;

/**
 * Created by Administrator on 2015/9/6.
 */
public class SelectAdNextPageEvent {

    private SampleEntity msg;

    public SampleEntity getMsg() {
        return msg;
    }

    public void setMsg(SampleEntity msg) {
        this.msg = msg;
    }

    public SelectAdNextPageEvent(SampleEntity msg) {
        this.msg = msg;
    }
}
