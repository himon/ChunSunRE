package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.UserPrivacyMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.OnUpdateUserInfoListener;
import com.chunsun.redenvelope.ui.activity.personal.UserPrivacyActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/24.
 */
public class UserPrivacyModeImpl implements UserPrivacyMode {

    private UserPrivacyActivity mActivity;

    public UserPrivacyModeImpl(UserPrivacyActivity userPrivacyActivity) {
        this.mActivity = userPrivacyActivity;
    }

    @Override
    public void updateUserInfo(final String token, final String field_value, final BaseSingleLoadedListener listener) {

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
                listener.onException(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.USER_UPDATE_INFO_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataUpdateInfoToJson(token, field_value));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
