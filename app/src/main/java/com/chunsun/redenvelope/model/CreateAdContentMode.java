package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface CreateAdContentMode {

    /**
     * 红包创建
     *
     * @param token
     * @param mAdEntity
     * @param title
     * @param content
     * @param listener
     */
    void commitAdCreate(String token, AdEntity mAdEntity, String title, String content, UserLoseMultiLoadedListener listener);

    /**
     * 圈子创建
     *
     * @param mToken
     * @param mAdEntity
     * @param title
     * @param content
     * @param listener
     */
    void commitCircleCreate(String mToken, AdEntity mAdEntity, String title, String content, UserLoseMultiLoadedListener listener);
}
