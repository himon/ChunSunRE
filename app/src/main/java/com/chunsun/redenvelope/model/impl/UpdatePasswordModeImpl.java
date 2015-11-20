package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.UpdatePasswordMode;
import com.chunsun.redenvelope.ui.activity.personal.UpdatePasswordActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/24.
 */
public class UpdatePasswordModeImpl implements UpdatePasswordMode {

    private UpdatePasswordActivity mActivity;
    private HttpManager mManager;

    public UpdatePasswordModeImpl(UpdatePasswordActivity updatePasswordActivity) {
        this.mActivity = updatePasswordActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void updatePassword(final String token, final String old_pwd, final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onException(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.USER_UPDATE_PWD_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initPasswordDataToJson(token, old_pwd, new_pwd, confirm_pwd));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.updatePassword(token, old_pwd, new_pwd, confirm_pwd, listener, mActivity);
    }

    @Override
    public void updatePasswordNotOldPwd(final String token, final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onException(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.USER_UPDATE_PWD_NOT_HAS_OLD_PWD_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataPasswordToJson(token, new_pwd, confirm_pwd));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.updatePasswordNotOldPwd(token, new_pwd, confirm_pwd, listener, mActivity);
    }
}
