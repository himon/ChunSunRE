package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface OnGetRedDetailListener {

    void onGetDetailSuccess(BaseSingleLoadedListener entity);

    void onGetDetailError();
}
