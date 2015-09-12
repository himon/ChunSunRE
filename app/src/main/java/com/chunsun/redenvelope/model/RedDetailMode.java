package com.chunsun.redenvelope.model;


import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface RedDetailMode {

    void getRedData(String token, String id, BaseMultiLoadedListener listener);

    void getShareLimit(String token, BaseMultiLoadedListener listener);
}
