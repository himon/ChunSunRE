package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/12.
 */
public interface IRedDetailFragmentView {

    /**
     * 获取评论列表
     *
     * @param result
     */
    void setCommentData(RedDetailCommentEntity.ResultEntity result);

    /**
     * 获取领取记录
     *
     * @param result
     */
    void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result);

    /**
     * 收藏成功
     *
     * @param entity
     */
    void setFavoriteSuccess(SampleResponseEntity entity);

    /**
     * 跳转举报Activity
     */
    void toComplaintActivity();

    /**
     * 查看担保交易
     */
    void toGuaranteeActivity();

    /**
     * 评论成功
     */
    void commentSuccess();

    /**
     * 分享成功
     */
    void shareSuccess();
}
