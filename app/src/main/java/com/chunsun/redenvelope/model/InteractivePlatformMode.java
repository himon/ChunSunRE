package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/12.
 */
public interface InteractivePlatformMode {

    void getCountryCommentList(String token, int page_index, UserLoseMultiLoadedListener listener);

    void getLocalCommentList(String token, int page_index, UserLoseMultiLoadedListener listener);

    /**
     * 评论
     * @param token
     * @param comment
     * @param province
     * @param city
     * @param at
     * @param listener
     */
    void sendComment(String token, String comment, String province, String city, String at, UserLoseMultiLoadedListener listener);
}
