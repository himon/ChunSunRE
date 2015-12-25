package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordListMode;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SendRedEnvelopeRecordListModeImpl implements SendRedEnvelopeRecordListMode {

    private Activity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordListModeImpl(Activity sendRedEnvelopeRecordListActivity) {
        this.mActivity = sendRedEnvelopeRecordListActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadRedEnvelopeSendRecordListData(final String token, final String type, final int page_index, final UserLoseMultiLoadedListener listener) {
        mManager.loadRedEnvelopeSendRecordListData(token, type, page_index, listener, mActivity);
    }

    @Override
    public void delRedEnvelope(final String token, final int hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.delRedEnvelope(token, hb_id, listener, mActivity);
    }
}
