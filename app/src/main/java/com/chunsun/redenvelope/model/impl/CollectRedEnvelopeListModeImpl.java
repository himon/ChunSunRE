package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.CollectRedEnvelopeListMode;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CollectRedEnvelopeListModeImpl implements CollectRedEnvelopeListMode {

    private CollectRedEnvelopeListActivity mActivity;
    private HttpManager mManager;

    public CollectRedEnvelopeListModeImpl(CollectRedEnvelopeListActivity collectRedEnvelopeListActivity) {
        this.mActivity = collectRedEnvelopeListActivity;
        mManager = new HttpManager();
    }

    @Override
    public void loadData(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.loadData(token, Constants.HB_FAVORITE_JSON_REQUEST_URL, listener, mActivity);
    }
}
