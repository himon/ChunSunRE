package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface CollectRedEnvelopeListMode {

    void loadData(String token, UserLoseMultiLoadedListener listener);

}
