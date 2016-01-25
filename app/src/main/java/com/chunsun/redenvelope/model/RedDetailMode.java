package com.chunsun.redenvelope.model;


import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface RedDetailMode {

    void getRedData(String token, String id, UserLoseMultiLoadedListener listener);


}
