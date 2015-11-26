package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CouponRedDetailFragmentMode;
import com.chunsun.redenvelope.ui.fragment.CouponRedDetailFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/3 14:11
 * @des
 */
public class CouponRedDetailFragmentModeImpl implements CouponRedDetailFragmentMode {

    private CouponRedDetailFragment mFragment;
    private HttpManager mManager;

    public CouponRedDetailFragmentModeImpl(CouponRedDetailFragment couponRedDetailFragment) {
        this.mFragment = couponRedDetailFragment;
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
