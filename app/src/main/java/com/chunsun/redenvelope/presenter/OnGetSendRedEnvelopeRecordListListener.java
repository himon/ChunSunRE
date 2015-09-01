package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordListEntity;

/**
 * Created by Administrator on 2015/8/17.
 */
public interface OnGetSendRedEnvelopeRecordListListener {

    void onGetListSuccess(RedDetailSendRecordListEntity response);

    void onGetListError();
}
