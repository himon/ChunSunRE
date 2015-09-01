package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.presenter.OnGetHbAdListener;
import com.chunsun.redenvelope.presenter.OnGetHbPoolListener;
import com.chunsun.redenvelope.presenter.OnGrabRedEnvelopeListener;
import com.chunsun.redenvelope.presenter.impl.NearFragmentPresenter;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface NearFragmentMode {

    /**
     * 红包列表
     *
     * @param token
     * @param type       -1（生活、企业），3（附近）
     * @param province
     * @param city
     * @param longitude
     * @param latitude
     * @param page_index
     * @param listener
     */
    void loadData(String token, String type, String province,
                  String city, String longitude, String latitude, int page_index, BaseMultiLoadedListener listener);

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
    void grabRedEnvelope(String mToken, String id, BaseMultiLoadedListener listener);
}
