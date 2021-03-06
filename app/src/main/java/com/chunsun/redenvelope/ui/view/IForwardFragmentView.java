package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 */
public interface IForwardFragmentView extends LoadingView {

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
     * 跳转转发红包
     *
     * @param id
     */
    void toRepeatRedDetail(String id);

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

    /**
     * 跳转登录
     */
    void toLogin();
}
