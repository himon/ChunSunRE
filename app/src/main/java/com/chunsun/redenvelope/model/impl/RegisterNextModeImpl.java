package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RegisterNextMode;
import com.chunsun.redenvelope.ui.activity.account.RegisterNextActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/7/30.
 */
public class RegisterNextModeImpl implements RegisterNextMode {

    private RegisterNextActivity mActivity;
    private HttpManager mManager;

    public RegisterNextModeImpl(RegisterNextActivity registerNextView) {
        this.mActivity = registerNextView;
        this.mManager = new HttpManager();
    }

    @Override
    public void register(final String type, final String mobile, final String verify_code, final String password, final String confirm_pwd, final String push_device_token, final String json_str, final String invitation_code, final BaseMultiLoadedListener listener) {

//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if(response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_REGISTER,response);
//                }else{
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
//                params.put("methodName", Constants.REGISTER_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRegisterToJson(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.register(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code, listener, mActivity);
    }

    @Override
    public void getUserInfo(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<UserEntity> request = new GsonRequest<UserEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                UserEntity.class, null, new Response.Listener<UserEntity>() {
//
//            @Override
//            public void onResponse(UserEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO, response);
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
//                params.put("methodName", Constants.GET_USERINFO_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getUserInfomation(token, listener, null, mActivity);
    }
}
