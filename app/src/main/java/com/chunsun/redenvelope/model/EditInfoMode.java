package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/14.
 */
public interface EditInfoMode {

    /**
     * 举报红包
     *
     * @param token
     * @param hb_id 红包id
     * @param reason 内容
     */
    void complaintRedEnvelope(String token, String hb_id, String reason, BaseSingleLoadedListener listener);
}
