package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/11.
 */
public class WebRedDetailModeImpl implements WebRedDetailMode {

    private WebRedDetailActivity mActivity;

    public WebRedDetailModeImpl(WebRedDetailActivity webRedDetailActivity) {
        this.mActivity = webRedDetailActivity;
    }

    @Override
    public void getRedData(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<RedDetailEntity> request = new GsonRequest<RedDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailEntity.class, null, new Response.Listener<RedDetailEntity>() {

            @Override
            public void onResponse(RedDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL, response);
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
                params.put("methodName", Constants.HB_DETAIL_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void getShareLimit(final String token, final BaseMultiLoadedListener listener) {
        GsonRequest<ShareLimitEntity> request = new GsonRequest<ShareLimitEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                ShareLimitEntity.class, null, new Response.Listener<ShareLimitEntity>() {

            @Override
            public void onResponse(ShareLimitEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_LIMIT, response);
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
                params.put("methodName", Constants.HB_SHARE_LIMIT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
