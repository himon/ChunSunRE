package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/12.
 */
public interface RedDetailFragmentMode {

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
    void setFavorite(String token, String hb_id, UserLoseMultiLoadedListener listener);

    /**
     * 发送评论
     *
     * @param token
     * @param hb_id
     * @param content
     */
    void sendComment(String token, String hb_id, String content, UserLoseMultiLoadedListener listener);

    /**
     * 拆红包
     *
     * @param token
     * @param grab_id
     * @param shareType
     * @param listener
     */
    void shareOpen(String token, String grab_id, String shareType, UserLoseMultiLoadedListener listener);

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     * @param listener
     */
    void justOpen(String token, String grab_id, UserLoseMultiLoadedListener listener);
}
