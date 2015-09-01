package com.chunsun.redenvelope.model.impl;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.QuickLoginMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.OnLoginFinishedListener;
import com.chunsun.redenvelope.presenter.OnRegisterGetValidataCodeListener;
import com.chunsun.redenvelope.ui.activity.account.QuickLoginActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/1.
 */
public class QuickLoginModeImpl implements QuickLoginMode {

    private QuickLoginActivity mActivity;

    public QuickLoginModeImpl(QuickLoginActivity quickLoginView) {
        this.mActivity = quickLoginView;
    }

    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
        final GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CODE, response);
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
                params.put("methodName", Constants.LOGIN_GET_VALIDATA_CODE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataSmsToJson(mobile));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void login(final String phone, final String code,
                      final String phoneInfo, final String pushDeviceToken, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_LOGIN,response);
                }else{
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //byte[] htmlBodyBytes = error.networkResponse.data;
                //Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.QUICK_LOGIN_CODE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataQuickLoginToJson(phone, code, phoneInfo, pushDeviceToken));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
