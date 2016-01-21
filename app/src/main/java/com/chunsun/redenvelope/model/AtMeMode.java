package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/19 18:05
 * @des
 */
public interface AtMeMode {
    /**
     * 获取未读消息
     *
     * @param token
     * @param page_index
     * @param listener
     */
    void getUserNoReadMessage(String token, int page_index, UserLoseMultiLoadedListener listener);

    /**
     * 清空未读消息
     *
     * @param token
     * @param type
     * @param listener
     */
    void userReadMessage(String token, int type, UserLoseMultiLoadedListener listener);
}
