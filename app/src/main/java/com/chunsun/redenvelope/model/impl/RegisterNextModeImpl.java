package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RegisterNextMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.account.RegisterNextActivity;
import com.chunsun.redenvelope.utils.manager.JsonManager;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/30.
 */
public class RegisterNextModeImpl implements RegisterNextMode {

    private RegisterNextActivity mActivity;

    public RegisterNextModeImpl(RegisterNextActivity registerNextView) {
        this.mActivity = registerNextView;
    }

    @Override
    public void register(final String type, final String mobile, final String verify_code, final String password, final String confirm_pwd, final String push_device_token, final String json_str, final String invitation_code, final BaseMultiLoadedListener listener) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_REGISTER,response);
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
                params.put("methodName", Constants.REGISTER_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRegisterToJson(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void getUserInfo(final String token, final BaseMultiLoadedListener listener) {
        GsonRequest<UserEntity> request = new GsonRequest<UserEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                UserEntity.class, null, new Response.Listener<UserEntity>() {

            @Override
            public void onResponse(UserEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO,response);
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
                params.put("methodName", Constants.GET_USERINFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
