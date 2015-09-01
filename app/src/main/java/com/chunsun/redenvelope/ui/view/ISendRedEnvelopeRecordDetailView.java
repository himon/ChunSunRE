package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/18.
 */
public interface ISendRedEnvelopeRecordDetailView {

    /**
     * 获取红包详情
     *
     * @param list   轮播图url
     * @param detail
     */
    void setRedEnvelopeDetail(ArrayList<String> list, RedDetailEntity.ResultEntity.DetailEntity detail);

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
}
