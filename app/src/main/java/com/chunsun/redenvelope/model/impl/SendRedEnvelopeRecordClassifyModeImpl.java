package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordClassifyMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/15.
 */
public class SendRedEnvelopeRecordClassifyModeImpl implements SendRedEnvelopeRecordClassifyMode {

    private SendRedEnvelopeRecordClassifyActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordClassifyModeImpl(SendRedEnvelopeRecordClassifyActivity sendRedEnvelopeRecordClassifyActivity) {
        this.mActivity = sendRedEnvelopeRecordClassifyActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadRedEnvelopeSendRecordClassifyData(final String token, final BaseSingleLoadedListener listener) {
        mManager.loadRedEnvelopeSendRecordClassifyData(token, listener, mActivity);
    }
}
