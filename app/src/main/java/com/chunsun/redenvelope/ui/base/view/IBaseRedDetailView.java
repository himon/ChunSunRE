package com.chunsun.redenvelope.ui.base.view;

import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/22 11:26
 * @des 红包类总接口
 */
public interface IBaseRedDetailView {

    /**
     * 获取评论列表
     *
     * @param result
     */
    void setCommentData(RedDetailCommentEntity.ResultEntity result);

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

    /**
     * 评论成功
     */
    void commentSuccess();

    /**
     * 评论的时候user为空
     */
    void userIsEmpty();
}
