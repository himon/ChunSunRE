package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface WebRedDetailMode {

    void getRedData(String token, String id, BaseSingleLoadedListener listener);
}
