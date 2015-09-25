package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 15:55
 * @des ${TODO}
 */
public interface IRepeatRedDetailView {

    void getRedDetailSuccess(RedDetailEntity.ResultEntity.DetailEntity  entity);

    /**
     * 获取评论列表
     *
     * @param result
     */
    void setCommentData(RedDetailCommentEntity.ResultEntity result);
}
