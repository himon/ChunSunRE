package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.presenter.impl.CreateAdNextPagePresenter;

import java.util.List;

import me.iwf.photopicker.entity.Photo;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface CreateAdNextPageMode {

    void initProvinceAndCity(BaseMultiLoadedListener listener);

    void commit(String token, AdEntity mAdEntity, String title, String content, BaseMultiLoadedListener listener);
}
