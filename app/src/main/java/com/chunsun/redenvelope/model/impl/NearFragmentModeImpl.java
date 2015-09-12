package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.NearFragmentMode;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.fragment.tab.NearFragment;
import com.chunsun.redenvelope.utils.manager.JsonManager;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/10.
 */
public class NearFragmentModeImpl implements NearFragmentMode {
    private NearFragment mNearFragment;

    public NearFragmentModeImpl(NearFragment nearFragment) {
        this.mNearFragment = nearFragment;
    }

    @Override
    public void loadData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener) {
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
                params.put("parames", JsonManager.initDataRedEnvelopePoolToJson(token, type, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), MainApplication.getContext().getLongitude() + "", MainApplication.getContext().getLatitude() + "", page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, mNearFragment);
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
                params.put("parames", JsonManager.initAdDataToJson(type, "", ""));
                return params;
            }
        };
        RequestManager.addRequest(request, mNearFragment);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE, response);
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
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mNearFragment);
    }
}
