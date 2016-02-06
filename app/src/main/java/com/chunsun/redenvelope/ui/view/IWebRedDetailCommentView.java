package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;

/**
 * Created by Administrator on 2015/9/14.
 */
public interface IWebRedDetailCommentView {

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
     * 评论成功
     */
    void commentSuccess();

    void userNoEmpty();
}
