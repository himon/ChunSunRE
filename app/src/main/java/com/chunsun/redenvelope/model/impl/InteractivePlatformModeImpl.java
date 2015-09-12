package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.InteractivePlatformMode;
import com.chunsun.redenvelope.model.entity.json.InteractiveEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractivePlatformModeImpl implements InteractivePlatformMode {

    private InteractivePlatformActivity mActivity;

    public InteractivePlatformModeImpl(InteractivePlatformActivity activity) {
        mActivity = activity;
    }

    @Override
    public void getCountryList(final String token, final int page_index, final BaseMultiLoadedListener listener) {
        GsonRequest<InteractiveEntity> request = new GsonRequest<InteractiveEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InteractiveEntity.class, null, new Response.Listener<InteractiveEntity>() {

            @Override
            public void onResponse(InteractiveEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INTERACTIVE_COUNTRY, response);
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST);
                params.put("parames", JsonManager.initInterractiveDataToJson(token, "全国", "全国", page_index + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void getLocalList(final String token, final int page_index, final BaseMultiLoadedListener listener) {
        GsonRequest<InteractiveEntity> request = new GsonRequest<InteractiveEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InteractiveEntity.class, null, new Response.Listener<InteractiveEntity>() {

            @Override
            public void onResponse(InteractiveEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INTERACTIVE_LOCAL, response);
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST);
                params.put("parames", JsonManager.initInterractiveDataToJson(token, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), page_index + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void sendComment(final String token, final String comment, final String province, final String city, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMMENT, response);
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST_COMMENT);
                params.put("parames", JsonManager.initInterractiveCommentDataToJson(token, province, city, comment));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}