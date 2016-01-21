package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;
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
    public void getRedData(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getGrabByToken(final String token, String id, final UserLoseMultiLoadedListener listener) {
        mManager.getGrabByToken(token, id, listener, mActivity);
    }

    @Override
    public void shareOpen(final String token, final String grab_id, final String shareType, final UserLoseMultiLoadedListener listener) {
        mManager.shareOpen(token, grab_id, shareType, listener, null, mActivity);
    }

    @Override
    public void justOpen(final String token, final String grab_id, final UserLoseMultiLoadedListener listener) {
        mManager.justOpen(token, grab_id, listener, null, mActivity);
    }

    @Override
    public void setFavorite(String token, String hb_id, UserLoseMultiLoadedListener listener) {
        mManager.setFavorite(token, hb_id, listener, null, mActivity);
    }
}
