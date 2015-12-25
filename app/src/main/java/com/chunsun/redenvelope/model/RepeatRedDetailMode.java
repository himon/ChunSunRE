package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:15
 */
public interface RepeatRedDetailMode {

    /**
     * 获取红包详情
     *
     * @param token
     * @param hb_id
     * @param listener
     */
    void getRedDetail(String token, String hb_id, UserLoseMultiLoadedListener listener);

    /**
     * 获取红包评论列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     */
    void getCommentList(String hb_id, int page_index, BaseMultiLoadedListener listener);

    /**
     * 收藏红包
     *
     * @param token
     * @param id
     * @param listener
     */
    void setFavorite(String token, String id, UserLoseMultiLoadedListener listener);

    /**
     * 评论
     *
     * @param token
     * @param id
     * @param comment
     * @param listener
     */
    void sendComment(String token, String id, String comment, UserLoseMultiLoadedListener listener);

    /**
     * 转发类获取host
     * @param token
     * @param hb_id
     * @param platform
     * @param is_valid
     * @param listener
     */
    void getHost(String token, String hb_id, String platform, boolean is_valid, UserLoseMultiLoadedListener listener);
}
