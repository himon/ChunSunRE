package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 */
public interface IHomeFragmentView {

    void setData(RedListDetailEntity.ResultEntity entity);

    void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert);

    /**
     * 跳转广告详情
     *
     * @param id
     */
    void toRedDetail(String id);

    /**
     * 跳转web广告详情
     *
     * @param id
     */
    void toWebRedDetail(String id);

    /**
     * 跳转轮播图广告
     *
     * @param title
     * @param url
     */
    void toAdWebView(String title, String url);

    /**
     * 抢红包成功
     *
     * @param entity
     */
    void grabRedEnvelopeSuccess(RedListDetailEntity.ResultEntity.PoolEntity entity);
}
