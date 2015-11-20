package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/12.
 */
public interface InteractivePlatformMode {

    void getCountryCommentList(String token, int page_index, BaseMultiLoadedListener listener);

    void getLocalCommentList(String token, int page_index, BaseMultiLoadedListener listener);

    void sendComment(String token, String comment, String province, String city, BaseMultiLoadedListener listener);
}
