package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdMode;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdModeImpl implements ForgetPwdMode {

    private ForgetPwdActivity mActivity;
    private HttpManager mManager;

    public ForgetPwdModeImpl(ForgetPwdActivity view) {
        this.mActivity = view;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CODE, response);
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
//                params.put("methodName", Constants.LOGIN_GET_VALIDATA_CODE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataSmsToJson(mobile));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getCode(mobile, listener, mActivity);
    }

    @Override
    public void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_NEXT_STEP, response);
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
//                params.put("methodName", Constants.REGISTER_NEXT_STEP_CODE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataVerifyToJson(mobile, verify_code));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.nextStep(mobile, verify_code, listener, mActivity);
    }
}
