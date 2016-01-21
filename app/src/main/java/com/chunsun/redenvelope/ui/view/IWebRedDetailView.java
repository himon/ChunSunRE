package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
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

    void setGrab(GrabEntity entity);

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
