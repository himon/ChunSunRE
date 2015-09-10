package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.AdPayMode;
import com.chunsun.redenvelope.model.entity.json.AdPayAmountDetailEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.ad.AdPayActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/8.
 */
public class AdPayModeImpl implements AdPayMode {

    private AdPayActivity mActivity;

    public AdPayModeImpl(AdPayActivity adPayActivity) {
        this.mActivity = adPayActivity;
    }

    @Override
    public void getAdAmountDetail(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<AdPayAmountDetailEntity> request = new GsonRequest<AdPayAmountDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                AdPayAmountDetailEntity.class, null, new Response.Listener<AdPayAmountDetailEntity>() {

            @Override
            public void onResponse(AdPayAmountDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_AD_AMOUNT_DETAIL, response);
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
                params.put("methodName", Constants.GET_AD_AMOUNT_DETAIL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void payByBalance(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_PAY_BY_BANLANCE, response);
                } else {
                    listener.onError(Constants.LISTENER_TYPE_PAY_BY_BANLANCE, response.getMsg());
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
                params.put("methodName", Constants.AD_PAY_BY_BALANCE);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
