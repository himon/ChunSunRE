package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserRewardMode;
import com.chunsun.redenvelope.model.entity.json.UserPublicInfoEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserRewardModeImpl implements UserRewardMode {

    private UserRewardActivity mActivity;

    public UserRewardModeImpl(UserRewardActivity userRewardActivity) {
        mActivity = userRewardActivity;
    }

    @Override
    public void getData(final String token, final String user_id, final BaseMultiLoadedListener listener) {
        GsonRequest<UserPublicInfoEntity> request = new GsonRequest<UserPublicInfoEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                UserPublicInfoEntity.class, null, new Response.Listener<UserPublicInfoEntity>() {

            @Override
            public void onResponse(UserPublicInfoEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO, response);
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
                params.put("methodName", Constants.USER_PUBLIC_INFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initUserPublicInfoDataToJson(token, user_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
