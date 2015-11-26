package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordDetailMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SendRedEnvelopeRecordDetailModeImpl implements SendRedEnvelopeRecordDetailMode {

    private SendRedEnvelopeRecordDetailActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordDetailModeImpl(SendRedEnvelopeRecordDetailActivity sendRedEnvelopeRecordDetailActivity) {
        this.mActivity = sendRedEnvelopeRecordDetailActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getRedEnvelopeDetail(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getCommentList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getCommentList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void getRedRecordList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getRedRecordList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void superaddition(final String hb_id, final BaseMultiLoadedListener listener) {
        mManager.superaddition(hb_id, listener, mActivity);
    }
}
