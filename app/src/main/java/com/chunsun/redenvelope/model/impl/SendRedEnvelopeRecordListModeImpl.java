package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordListMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordListActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SendRedEnvelopeRecordListModeImpl implements SendRedEnvelopeRecordListMode {

    private SendRedEnvelopeRecordListActivity mActivity;

    public SendRedEnvelopeRecordListModeImpl(SendRedEnvelopeRecordListActivity sendRedEnvelopeRecordListActivity) {
        this.mActivity = sendRedEnvelopeRecordListActivity;
    }

    @Override
    public void loadData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener) {
        GsonRequest<RedDetailSendRecordListEntity> request = new GsonRequest<RedDetailSendRecordListEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailSendRecordListEntity.class, null, new Response.Listener<RedDetailSendRecordListEntity>() {

            @Override
            public void onResponse(RedDetailSendRecordListEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_SEND_RED_ENVELOPE_RECORD ,response);
                }else{
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
                params.put("methodName", Constants.HB_SEND_RECORD_CLASSIFY_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataAccountLogToJson(token, type, page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void delRedEnvelope(final String token, final int hb_id, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_DETAIL_SEND_RED_ENVELOPE_RECORD ,response);
                }else{
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
                params.put("methodName", Constants.HB_RECORD_LIST_DETAIL_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
