package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RepeatRedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:17
 * @des ${TODO}
 */
public class RepeatRedDetailModeImpl implements RepeatRedDetailMode {

    private RepeatRedDetailActivity mActivity;
    private HttpManager mManager;

    public RepeatRedDetailModeImpl(RepeatRedDetailActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getRedDetail(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getCommentList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getCommentList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void setFavorite(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.setFavorite(token, hb_id, listener, null, mActivity);
    }

    @Override
    public void sendComment(final String token, final String hb_id, final String content, String at, final UserLoseMultiLoadedListener listener) {
        mManager.sendComment(token, hb_id, content, at, listener, null, mActivity);
    }

    @Override
    public void getHost(final String token, final String hb_id, final String platform, final boolean is_valid, final UserLoseMultiLoadedListener listener) {
        mManager.getHost(token, hb_id, platform, is_valid, listener, mActivity);
    }
}
