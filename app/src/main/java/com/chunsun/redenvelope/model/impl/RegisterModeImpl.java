package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RegisterMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.account.RegisterActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/29.
 */
public class RegisterModeImpl implements RegisterMode {

    private RegisterActivity mActivity;

    public RegisterModeImpl(RegisterActivity registerView) {
        this.mActivity = registerView;
    }

    /**
     * 获取验证码
     *
     * @param mobile
     * @param listener
     */
    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CODE, response);
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
                params.put("methodName", Constants.REGISTER_GET_VALIDATA_CODE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataSmsToJson(mobile));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    /**
     * 验证验证码
     *
     * @param mobile
     * @param verify_code
     * @param listener
     */
    @Override
    public void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_NEXT_STEP,response);
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
                params.put("methodName", Constants.REGISTER_NEXT_STEP_CODE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataVerifyToJson(mobile, verify_code));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    /**
     * 验证邀请码
     *
     * @param invitation_code
     * @param listener
     */
    @Override
    public void hasInviteCodeNextStep(final String invitation_code, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INVITE_CODE, response);
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
                params.put("methodName", Constants.REGISTER_VALIDATA_INVITE_CODE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataInviteToJson(invitation_code));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
