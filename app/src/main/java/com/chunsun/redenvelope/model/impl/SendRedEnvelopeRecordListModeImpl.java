package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordListMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordListActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SendRedEnvelopeRecordListModeImpl implements SendRedEnvelopeRecordListMode {

    private SendRedEnvelopeRecordListActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordListModeImpl(SendRedEnvelopeRecordListActivity sendRedEnvelopeRecordListActivity) {
        this.mActivity = sendRedEnvelopeRecordListActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadRedEnvelopeSendRecordListData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedDetailSendRecordListEntity> request = new GsonRequest<RedDetailSendRecordListEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailSendRecordListEntity.class, null, new Response.Listener<RedDetailSendRecordListEntity>() {
//
//            @Override
//            public void onResponse(RedDetailSendRecordListEntity response) {
//                if(response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_SEND_RED_ENVELOPE_RECORD ,response);
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
//                params.put("methodName", Constants.HB_SEND_RECORD_CLASSIFY_LIST_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataAccountLogToJson(token, type, page_index));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.loadRedEnvelopeSendRecordListData(token, type, page_index, listener, mActivity);
    }

    @Override
    public void delRedEnvelope(final String token, final int hb_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_DETAIL_SEND_RED_ENVELOPE_RECORD, response);
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
//                params.put("methodName", Constants.HB_RECORD_LIST_DETAIL_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id + ""));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.delRedEnvelope(token, hb_id, listener, mActivity);
    }
}
