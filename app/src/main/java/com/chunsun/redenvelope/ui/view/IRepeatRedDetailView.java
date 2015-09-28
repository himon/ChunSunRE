package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RepeatRedEnvelopeGetHostEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 15:55
 * @des ${TODO}
 */
public interface IRepeatRedDetailView {

    /**
     * 获取红包信息
     *
     * @param entity
     */
    void getRedDetailSuccess(RedDetailEntity.ResultEntity.DetailEntity entity);

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
     * 设置分享host
     * @param entity
     */
    void setShareHost(RepeatRedEnvelopeGetHostEntity entity);
}
