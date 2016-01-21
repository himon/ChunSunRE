package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/21 16:41
 * @des
 */
public interface MainMode {
    /**
     * 获取用户未读消息数量
     * @param token
     * @param listener
     */
    void getUserNoReadCount(String token, BaseMultiLoadedListener listener);
}
