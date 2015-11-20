package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserInfoMode;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserInfoModeImpl implements UserInfoMode {

    private UserInfoActivity mActivity;
    private HttpManager mManager;

    public UserInfoModeImpl(UserInfoActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void editUserInfo(final String token, final String field_name, final String field_value, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_EDIT_USER_INFO, response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                byte[] data = error.networkResponse.data;
//                String string = new String(data);
//                listener.onException(string);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.USER_INFO_EDIT_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataUpdateInfoToJson(token, field_name, field_value));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.editUserInfo(token, field_name, field_value, listener, mActivity);
    }
}
