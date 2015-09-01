package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CollectRedEnvelopeListMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CollectRedEnvelopeListModeImpl implements CollectRedEnvelopeListMode {

    private CollectRedEnvelopeListActivity mActivity;

    public CollectRedEnvelopeListModeImpl(CollectRedEnvelopeListActivity collectRedEnvelopeListActivity) {
        this.mActivity = collectRedEnvelopeListActivity;
    }

    @Override
    public void loadData(final String token, final BaseMultiLoadedListener listener) {
        GsonRequest<RedDetailUnReceiveAndCollectEntity> request = new GsonRequest<RedDetailUnReceiveAndCollectEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailUnReceiveAndCollectEntity.class, null, new Response.Listener<RedDetailUnReceiveAndCollectEntity>() {

            @Override
            public void onResponse(RedDetailUnReceiveAndCollectEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST, response);
                }else{
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
                params.put("methodName", Constants.HB_FAVORITE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    private String initDataToJson(String token) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE, response);
                }else{
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
        RequestManager.addRequest(request, mActivity);
    }
}
