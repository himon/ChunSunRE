package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailCommentMode;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailCommentActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/14.
 */
public class WebRedDetailCommentModeImpl implements WebRedDetailCommentMode {

    private WebRedDetailCommentActivity mActivity;
    private HttpManager mManager;

    public WebRedDetailCommentModeImpl(WebRedDetailCommentActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
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
    public void sendComment(final String token, final String hb_id, final String content, final UserLoseMultiLoadedListener listener) {
        mManager.sendComment(token, hb_id, content, listener, null, mActivity);
    }
}
