package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface IHomeFragmentView {

    void setData(RedListDetailEntity.ResultEntity entity);

    void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert);

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
     * 跳转券类红包
     *
     * @param id
     */
    void toForwardRedDetail(String id);

    /**
     * 跳转web广告页面
     *
     * @param title
     * @param url
     */
    void toAdWebView(String title, String url);

    void gradRedEnvelopeSuccess(String id);

    /**
     * 跳转登录
     */
    void toLogin();
}
