package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.entities.json.ShareLimitEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface IWebRedDetailView extends LoadingView {

    /**
     * 加载网页
     *
     * @param entity
     */
    void loadUrl(RedDetailEntity.ResultEntity.DetailEntity entity);

    /**
     * 获取分享次数限制
     *
     * @param result
     */
    void getShareLimit(ShareLimitEntity.ResultEntity result);

    /**
     * 分享成功
     */
    void shareSuccess();

    /**
     * 跳转举报Activity
     */
    void toComplaintActivity();

    /**
     * 收藏成功
     *
     * @param entity
     */
    void setFavoriteSuccess(SampleResponseEntity entity);
}
