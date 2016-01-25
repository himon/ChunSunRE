package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.ui.base.view.IBaseRedDetailView;

/**
 * Created by Administrator on 2015/8/12.
 */
public interface IRedDetailFragmentView extends IBaseRedDetailView{

    /**
     * 获取领取记录
     *
     * @param result
     */
    void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result);

    /**
     * 查看担保交易
     */
    void toGuaranteeActivity();

    /**
     * 分享成功
     */
    void shareSuccess();

    void setGrab(GrabEntity entity);
}
