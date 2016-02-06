package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/25 14:00
 * @des
 */
public interface IMyCircleListDetailView {

    void setData(ArrayList<String> list,  RedDetailEntity.ResultEntity.DetailEntity detail);

    /**
     * 获取评论列表
     *
     * @param result
     */
    void setCommentData(RedDetailCommentEntity.ResultEntity result);

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

    void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result);
}
