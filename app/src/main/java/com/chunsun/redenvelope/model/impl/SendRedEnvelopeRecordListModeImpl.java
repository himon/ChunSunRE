package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordListMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordListActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SendRedEnvelopeRecordListModeImpl implements SendRedEnvelopeRecordListMode {

    private SendRedEnvelopeRecordListActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordListModeImpl(SendRedEnvelopeRecordListActivity sendRedEnvelopeRecordListActivity) {
        this.mActivity = sendRedEnvelopeRecordListActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadRedEnvelopeSendRecordListData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.loadRedEnvelopeSendRecordListData(token, type, page_index, listener, mActivity);
    }

    @Override
    public void delRedEnvelope(final String token, final int hb_id, final BaseMultiLoadedListener listener) {
        mManager.delRedEnvelope(token, hb_id, listener, mActivity);
    }
}
