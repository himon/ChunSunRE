package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/14.
 */
public interface EditInfoMode {

    /**
     * 举报红包
     *
     * @param token
     * @param hb_id  红包id
     * @param reason 内容
     */
    void complaintRedEnvelope(String token, String hb_id, String reason, UserLoseMultiLoadedListener listener);

    /**
     * 修改用户基本信息
     *
     * @param token
     * @param field_name
     * @param field_value
     * @param listener
     */
    void editUserInfo(String token, String field_name, String field_value, UserLoseMultiLoadedListener listener);
}
