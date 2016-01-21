package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface WebRedDetailMode {

    void getRedData(String token, String id, UserLoseMultiLoadedListener listener);

    void getGrabByToken(String token, String id, UserLoseMultiLoadedListener listener);

    void shareOpen(String token, String grab_id, String shareType, UserLoseMultiLoadedListener listener);

    void justOpen(String token, String grab_id, UserLoseMultiLoadedListener listener);

    /**
     * 设置收藏
     *
     * @param token
     * @param hb_id
     * @param listener
     */
    void setFavorite(String token, String hb_id, UserLoseMultiLoadedListener listener);
}
