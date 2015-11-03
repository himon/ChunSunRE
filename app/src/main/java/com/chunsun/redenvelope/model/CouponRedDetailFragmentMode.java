package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/3 14:08
 * @des
 */
public interface CouponRedDetailFragmentMode {

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
     * 设置收藏
     *
     * @param token
     * @param hb_id
     * @param listener
     */
    void setFavorite(String token, String hb_id, BaseMultiLoadedListener listener);

    /**
     * 发送评论
     *
     * @param token
     * @param hb_id
     * @param content
     */
    void sendComment(String token, String hb_id, String content, BaseMultiLoadedListener listener);

    /**
     * 拆红包
     *
     * @param token
     * @param grab_id
     * @param shareType
     * @param listener
     */
    void shareOpen(String token, String grab_id, String shareType, BaseMultiLoadedListener listener);

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     * @param listener
     */
    void justOpen(String token, String grab_id, BaseMultiLoadedListener listener);
}
