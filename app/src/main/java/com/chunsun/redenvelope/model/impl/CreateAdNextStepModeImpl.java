package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdNextStepMode;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextStepActivity;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/24.
 */
public class CreateAdNextStepModeImpl implements CreateAdNextStepMode {

    private CreateAdNextStepActivity mActivity;

    public CreateAdNextStepModeImpl(CreateAdNextStepActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void getAdDelaySecondsRate(final BaseMultiLoadedListener listener) {
        GsonRequest<AdDelaySecondsRateEntity> request = new GsonRequest<AdDelaySecondsRateEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                AdDelaySecondsRateEntity.class, null, new Response.Listener<AdDelaySecondsRateEntity>() {

            @Override
            public void onResponse(AdDelaySecondsRateEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CREATE_AD_DELAY_SECONDS , response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onException(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.GET_AD_DELAY_SECONDS_RATE_JSON_REQUEST_URL);
                params.put("parames", "");
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
