package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface SendRedEnvelopeRecordDetailMode {

    /**
     * 获取红包详情
     *
     * @param token
     * @param hb_id
     */
    void getRedEnvelopeDetail(String token, String hb_id, BaseMultiLoadedListener listener);

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     */
    void getCommentList(String hb_id, int page_index, BaseMultiLoadedListener listener);

    /**
     * 获取领取记录列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     */
    void getRedRecordList(String hb_id, int page_index, BaseMultiLoadedListener listener);

    /**
     * 追加红包
     *
     * @param hb_id
     * @param listener
     */
    void superaddition(String hb_id, BaseMultiLoadedListener listener);
}
