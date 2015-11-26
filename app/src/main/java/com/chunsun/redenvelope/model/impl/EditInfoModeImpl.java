package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.EditInfoMode;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EditInfoModeImpl implements EditInfoMode {

    private EditInfoActivity mActivity;
    private HttpManager mManager;

    public EditInfoModeImpl(EditInfoActivity activity) {
        this.mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void complaintRedEnvelope(final String token, final String hb_id, final String reason, final BaseMultiLoadedListener listener) {
        mManager.complaintRedEnvelope(token, hb_id, reason, listener, mActivity);
    }

    @Override
    public void editUserInfo(final String token, final String field_name, final String field_value, final BaseMultiLoadedListener listener) {
        mManager.editUserInfo(token, field_name, field_value, listener, mActivity);
    }
}
