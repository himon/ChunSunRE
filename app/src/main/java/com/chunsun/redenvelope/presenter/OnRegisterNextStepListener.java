package com.chunsun.redenvelope.presenter;

import com.android.volley.VolleyError;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/7/30.
 */
public interface OnRegisterNextStepListener {

    void nextStepSuccess(SampleResponseEntity response);

    void nextStepError(VolleyError error);
}
