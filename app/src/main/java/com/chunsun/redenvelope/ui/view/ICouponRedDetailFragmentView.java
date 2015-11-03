package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/3 14:07
 * @des
 */
public interface ICouponRedDetailFragmentView {

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
     * 跳转举报Activity
     */
    void toComplaintActivity();

    /**
     * 评论成功
     */
    void commentSuccess();

    /**
     * 收藏成功
     *
     * @param entity
     */
    void setFavoriteSuccess(SampleResponseEntity entity);

    /**
     * 分享成功
     */
    void shareSuccess();

}
