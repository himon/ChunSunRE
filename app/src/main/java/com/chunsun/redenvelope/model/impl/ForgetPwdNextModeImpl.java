package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdNextMode;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdNextActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdNextModeImpl implements ForgetPwdNextMode {

    private ForgetPwdNextActivity mActivity;
    private HttpManager mManager;

    public ForgetPwdNextModeImpl(ForgetPwdNextActivity mActivity) {
        this.mActivity = mActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void findPwdSubmit(final String mobile, final String verify_code,
                              final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener) {
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
//                byte[] htmlBodyBytes = error.networkResponse.data;
//                Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
//                listener.onError(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.FIND_PWD_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataFindPasswordToJson(mobile, verify_code, new_pwd, confirm_pwd));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.findPwdSubmit(mobile, verify_code, new_pwd, confirm_pwd, listener, mActivity);
    }
}
