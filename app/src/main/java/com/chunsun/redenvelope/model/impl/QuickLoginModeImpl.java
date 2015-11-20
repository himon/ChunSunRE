package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.QuickLoginMode;
import com.chunsun.redenvelope.ui.activity.account.QuickLoginActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/1.
 */
public class QuickLoginModeImpl implements QuickLoginMode {

    private QuickLoginActivity mActivity;
    private HttpManager mManager;

    public QuickLoginModeImpl(QuickLoginActivity quickLoginView) {
        this.mActivity = quickLoginView;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
//        final GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
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
    public void quickLogin(final String phone, final String code,
                           final String phoneInfo, final String pushDeviceToken, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_LOGIN, response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //byte[] htmlBodyBytes = error.networkResponse.data;
//                //Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
//                listener.onError(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.QUICK_LOGIN_CODE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataQuickLoginToJson(phone, code, phoneInfo, pushDeviceToken));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.quickLogin(phone, code, phoneInfo, pushDeviceToken, listener, mActivity);
    }


}
