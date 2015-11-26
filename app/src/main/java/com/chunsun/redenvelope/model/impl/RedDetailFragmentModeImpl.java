package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailFragmentMode;
import com.chunsun.redenvelope.ui.fragment.RedDetailFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RedDetailFragmentModeImpl implements RedDetailFragmentMode {

    private RedDetailFragment mFragment;
    private HttpManager mManager;

    public RedDetailFragmentModeImpl(RedDetailFragment redDetailFragmentView) {
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
    public void setFavorite(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        mManager.setFavorite(token, hb_id, listener, mFragment, null);
    }

    @Override
    public void sendComment(final String token, final String hb_id, final String content, final BaseMultiLoadedListener listener) {
        mManager.sendComment(token, hb_id, content, listener, mFragment, null);
    }

    @Override
    public void shareOpen(final String token, final String grab_id, final String shareType, final BaseMultiLoadedListener listener) {
        mManager.shareOpen(token, grab_id, shareType, listener, mFragment, null);
    }

    @Override
    public void justOpen(final String token, final String grab_id, final BaseMultiLoadedListener listener) {
        mManager.justOpen(token, grab_id, listener, mFragment, null);
    }
}
