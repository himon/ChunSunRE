package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailSendRecordListEntity;

/**
 * Created by Administrator on 2015/8/17.
 */
public interface ISendRedEnvelopeRecordListView {

    void setData(RedDetailSendRecordListEntity.ResultEntity result);

    void toClassifyListDetail(String id);

    void delSuccess();
}
