package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface HomeFragmentMode {

    /**
     * 红包列表
     *
     * @param token
     * @param type       -1（生活、企业），3（附近）
     * @param page_index
     * @param listener
     */
    void loadData(String token, int type, int page_index, int order_type, String keywords, UserLoseMultiLoadedListener listener);

    /**
     * 红包列表广告
     *
     * @param type
     * @param listener
     */
    void getAdData(String type, BaseMultiLoadedListener listener);

    /**
     * 抢红包
     *
     * @param mToken
     * @param id
     * @param listener
     */
    void grabRedEnvelope(String mToken, String id, UserLoseMultiLoadedListener listener);
}
