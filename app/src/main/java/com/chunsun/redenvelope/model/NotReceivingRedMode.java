package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface NotReceivingRedMode {

    void loadData(String token, BaseMultiLoadedListener listener);

    void grabRedEnvelope(String token, String hb_id, BaseMultiLoadedListener listener);
}