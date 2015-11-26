package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/11.
 */
public class WebRedDetailModeImpl implements WebRedDetailMode {

    private WebRedDetailActivity mActivity;
    private HttpManager mManager;

    public WebRedDetailModeImpl(WebRedDetailActivity webRedDetailActivity) {
        this.mActivity = webRedDetailActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getRedData(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getShareLimit(final String token, final BaseMultiLoadedListener listener) {
        mManager.getShareLimit(token, listener, mActivity);
    }

    @Override
    public void shareOpen(final String token, final String grab_id, final String shareType, final BaseMultiLoadedListener listener) {
        mManager.shareOpen(token, grab_id, shareType, listener, null, mActivity);
    }

    @Override
    public void justOpen(final String token, final String grab_id, final BaseMultiLoadedListener listener) {
        mManager.justOpen(token, grab_id, listener, null, mActivity);
    }
}
