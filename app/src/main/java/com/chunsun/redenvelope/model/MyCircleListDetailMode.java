package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/25 14:02
 * @des
 */
public interface MyCircleListDetailMode {

    void getRedData(String token, String id, UserLoseMultiLoadedListener listener);

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     */
    void getCommentList(String hb_id, int page_index, BaseMultiLoadedListener listener);

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
    void sendComment(String token, String hb_id, String content, String at, UserLoseMultiLoadedListener listener);
}
