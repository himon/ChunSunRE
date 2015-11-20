package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.EditInfoMode;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EditInfoModeImpl implements EditInfoMode {

    private EditInfoActivity mActivity;
    private HttpManager mManager;

    public EditInfoModeImpl(EditInfoActivity activity) {
        this.mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void complaintRedEnvelope(final String token, final String hb_id, final String reason, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_COMLAINT_RED_ENVELOPE, response);
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
//                params.put("methodName", Constants.HB_DETAIL_REPORT_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailReportToJson(token, hb_id, reason));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.complaintRedEnvelope(token, hb_id, reason, listener, mActivity);
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
//                listener.onException(error.getMessage());
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
