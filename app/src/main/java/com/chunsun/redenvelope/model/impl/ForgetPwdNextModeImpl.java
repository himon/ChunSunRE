package com.chunsun.redenvelope.model.impl;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdNextMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.SampleListener;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdNextActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdNextModeImpl implements ForgetPwdNextMode {

    private ForgetPwdNextActivity mActivity;

    public ForgetPwdNextModeImpl(ForgetPwdNextActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onSuccess(final String mobile, final String verify_code,
                          final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                byte[] htmlBodyBytes = error.networkResponse.data;
                Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.FIND_PWD_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataFindPasswordToJson(mobile, verify_code, new_pwd, confirm_pwd));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
