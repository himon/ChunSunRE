package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordClassifyEntity;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface OnGetSendRedEnvelopeRecordClassifyListener {

    void onGetClassifySuccess(RedDetailSendRecordClassifyEntity response);

    void onGetClassifyError();
}
