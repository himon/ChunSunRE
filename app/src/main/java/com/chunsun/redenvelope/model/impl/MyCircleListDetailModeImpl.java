package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.MyCircleListDetailMode;
import com.chunsun.redenvelope.ui.activity.personal.MyCircleListDetailActivity;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/25 14:03
 * @des
 */
public class MyCircleListDetailModeImpl extends BaseModeImpl implements MyCircleListDetailMode {

    private MyCircleListDetailActivity mActivity;

    public MyCircleListDetailModeImpl(MyCircleListDetailActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void getRedData(String token, String hb_id, UserLoseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getCommentList(String hb_id, int page_index, BaseMultiLoadedListener listener) {
        mManager.getCommentList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void setFavorite(String token, String hb_id, UserLoseMultiLoadedListener listener) {
        mManager.setFavorite(token, hb_id, listener, null, mActivity);
    }

    @Override
    public void sendComment(String token, String hb_id, String content, String at, UserLoseMultiLoadedListener listener) {
        mManager.sendComment(token, hb_id, content, at, listener, null, mActivity);
    }
}
