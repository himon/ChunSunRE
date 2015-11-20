package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.entity.AdEntity;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface CreateAdContentMode {

    void commitAdCreate(String token, AdEntity mAdEntity, String title, String content, BaseMultiLoadedListener listener);
}
