package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MineInviteCodeMode;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeWebActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MineInviteCodeModeImpl implements MineInviteCodeMode {

    private MineInviteCodeWebActivity mActivity;

    public MineInviteCodeModeImpl(MineInviteCodeWebActivity mineInviteCodeActivity) {
        this.mActivity = mineInviteCodeActivity;
    }

    @Override
    public void getInviteRecord(final String token, final BaseMultiLoadedListener listener) {
        GsonRequest<InviteRecordEntity> request = new GsonRequest<InviteRecordEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InviteRecordEntity.class, null, new Response.Listener<InviteRecordEntity>() {

            @Override
            public void onResponse(InviteRecordEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INVITE_INFO ,response);
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
                params.put("methodName", Constants.USER_INVITE_RECORD_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void getUserInfomation(final String token, final BaseMultiLoadedListener listener) {
        GsonRequest<UserEntity> request = new GsonRequest<UserEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                UserEntity.class, null, new Response.Listener<UserEntity>() {

            @Override
            public void onResponse(UserEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO,response);
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
                params.put("methodName", Constants.GET_USERINFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
