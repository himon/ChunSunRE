package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.HomeFragmentMode;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.fragment.HomeFragment;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/5.
 */
public class HomeFragmentModeImpl implements HomeFragmentMode {

    private HomeFragment homeFragment;

    public HomeFragmentModeImpl(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void loadData(final String token, final String type, final String province, final String city, final String longitude, final String latitude, final int page_index, final BaseMultiLoadedListener listener) {
        GsonRequest<RedListDetailEntity> request = new GsonRequest<RedListDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedListDetailEntity.class, null, new Response.Listener<RedListDetailEntity>() {

            @Override
            public void onResponse(RedListDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_RED_ENVELOPE_LIST, response);
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
                params.put("methodName", Constants.HB_POOL_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataRedEnvelopePoolToJson(token, type, province, city, longitude, latitude, page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, homeFragment);
    }

    @Override
    public void getAdData(final String type, final BaseMultiLoadedListener listener) {
        GsonRequest<RedAutoAdEntity> request = new GsonRequest<RedAutoAdEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedAutoAdEntity.class, null, new Response.Listener<RedAutoAdEntity>() {

            @Override
            public void onResponse(RedAutoAdEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_AD, response);
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
                params.put("methodName", Constants.HB_AD_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initAdDataToJson(type, "", ""));
                return params;
            }
        };
        RequestManager.addRequest(request, homeFragment);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE,response);
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
                params.put("methodName", Constants.HB_GRAB_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, homeFragment);
    }
}
