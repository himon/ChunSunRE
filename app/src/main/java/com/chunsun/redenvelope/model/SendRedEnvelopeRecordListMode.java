package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/17.
 */
public interface SendRedEnvelopeRecordListMode {

    /**
     * 获取红包记录列表
     *
     * @param token
     * @param type
     * @param page_index
     * @param listener
     */
    void loadRedEnvelopeSendRecordListData(String token, String type, int page_index, BaseMultiLoadedListener listener);

    /**
     * 删除红包记录
     *
     * @param token
     * @param hb_id
     * @param listener
     */
    void delRedEnvelope(String token, int hb_id, BaseMultiLoadedListener listener);
}
