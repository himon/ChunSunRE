package com.chunsun.redenvelope.model.impl;

import android.support.v4.app.Fragment;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailFragmentMode;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RedDetailFragmentModeImpl implements RedDetailFragmentMode {

    private Fragment mFragment;
    private HttpManager mManager;

    public RedDetailFragmentModeImpl(Fragment redDetailFragmentView) {
        this.mFragment = redDetailFragmentView;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCommentList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getCommentList(hb_id, page_index, listener, mFragment, null);
    }

    @Override
    public void getRedRecordList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getRedRecordList(hb_id, page_index, listener, mFragment, null);
    }

    @Override
    public void setFavorite(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.setFavorite(token, hb_id, listener, mFragment, null);
    }

    @Override
    public void sendComment(final String token, final String hb_id, final String content, String at, final UserLoseMultiLoadedListener listener) {
        mManager.sendComment(token, hb_id, content, at, listener, mFragment, null);
    }

    @Override
    public void shareOpen(final String token, final String grab_id, final String shareType, final UserLoseMultiLoadedListener listener) {
        mManager.shareOpen(token, grab_id, shareType, listener, mFragment, null);
    }

    @Override
    public void justOpen(final String token, final String grab_id, final UserLoseMultiLoadedListener listener) {
        mManager.justOpen(token, grab_id, listener, mFragment, null);
    }
}
