package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.LoginMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.utils.manager.JsonManager;
import com.chunsun.redenvelope.utils.StringUtil;
import com.android.volley.Request.Method;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2015/7/28.
 */
public class LoginModeImpl implements LoginMode {

    private LoginActivity mActivity;

    public LoginModeImpl(LoginActivity loginActivity) {
        this.mActivity = loginActivity;
    }

    @Override
    public void login(final String mobile_or_chunsun, final String password, final String push_device_token, final String json_str, final BaseSingleLoadedListener listener) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
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
                listener.onException(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.LOGIN_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataLoginToJson(mobile_or_chunsun, password, push_device_token, json_str));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
