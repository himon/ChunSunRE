package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface WebRedDetailMode {

    void getRedData(String token, String id, BaseMultiLoadedListener listener);

    void getShareLimit(String token, BaseMultiLoadedListener listener);

    void shareOpen(String token, String grab_id, String shareType, BaseMultiLoadedListener listener);

    void justOpen(String token, String grab_id, BaseMultiLoadedListener listener);
}
