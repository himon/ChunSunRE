package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordDetailMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SendRedEnvelopeRecordDetailModeImpl implements SendRedEnvelopeRecordDetailMode {

    private SendRedEnvelopeRecordDetailActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordDetailModeImpl(SendRedEnvelopeRecordDetailActivity sendRedEnvelopeRecordDetailActivity) {
        this.mActivity = sendRedEnvelopeRecordDetailActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getRedEnvelopeDetail(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedDetailEntity> request = new GsonRequest<RedDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailEntity.class, null, new Response.Listener<RedDetailEntity>() {
//
//            @Override
//            public void onResponse(RedDetailEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL, response);
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
//                params.put("methodName", Constants.HB_DETAIL_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getCommentList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedDetailCommentEntity> request = new GsonRequest<RedDetailCommentEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailCommentEntity.class, null, new Response.Listener<RedDetailCommentEntity>() {
//
//            @Override
//            public void onResponse(RedDetailCommentEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_COMMENT_LIST, response);
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
//                params.put("methodName", Constants.HB_DETAIL_COMMENT_LIST_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initRecordToJson(hb_id, page_index));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getCommentList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void getRedRecordList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedDetailGetRedRecordEntity> request = new GsonRequest<RedDetailGetRedRecordEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailGetRedRecordEntity.class, null, new Response.Listener<RedDetailGetRedRecordEntity>() {
//
//            @Override
//            public void onResponse(RedDetailGetRedRecordEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RECORD_LIST, response);
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
//                params.put("methodName", Constants.HB_DETAIL_RED_RECORD_LIST_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initRecordToJson(hb_id, page_index));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getRedRecordList(hb_id, page_index, listener, null, mActivity);
    }

    @Override
    public void superaddition(final String hb_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedSuperadditionEntity> request = new GsonRequest<RedSuperadditionEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedSuperadditionEntity.class, null, new Response.Listener<RedSuperadditionEntity>() {
//
//            @Override
//            public void onResponse(RedSuperadditionEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_RED_ENVELOPE_SUPERADDITION, response);
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
//                params.put("methodName", Constants.RED_ENVELOPE_SUPERADDITION_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initSuperadditionDataToJson(hb_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.superaddition(hb_id, listener, mActivity);
    }
}
