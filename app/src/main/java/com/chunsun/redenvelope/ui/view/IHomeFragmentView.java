package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface IHomeFragmentView extends LoadingView {

    /**
     * 设置列表数据
     *
     * @param entity
     */
    void setData(RedListDetailEntity.ResultEntity entity);

    /**
     * 设置轮播广告数据
     *
     * @param advert
     */
    void setAdData(RedAutoAdEntity.ResultEntity advert);

    /**
     * 跳转红包详情
     *
     * @param id
     */
    void toRedDetail(String id);

    /**
     * 跳转链接红包
     *
     * @param id
     */
    void toWebRedDetail(String id);

    /**
     * 跳转web广告页面
     *
     * @param title
     * @param url
     */
    void toAdWebView(String title, String url);

    /**
     * 跳转转发类详情
     *
     * @param id
     */
    void toRepeatRedDetail(String id);
}
