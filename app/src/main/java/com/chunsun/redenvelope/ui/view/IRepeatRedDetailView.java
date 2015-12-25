package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RepeatRedEnvelopeGetHostEntity;
import com.chunsun.redenvelope.ui.base.view.IBaseRedDetailView;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 15:55
 * @des ${TODO}
 */
public interface IRepeatRedDetailView extends IBaseRedDetailView {

    /**
     * 获取红包信息
     *
     * @param entity
     */
    void getRedDetailSuccess(RedDetailEntity.ResultEntity.DetailEntity entity);

    /**
     * 设置分享host
     * @param entity
     */
    void setShareHost(RepeatRedEnvelopeGetHostEntity entity);
}
