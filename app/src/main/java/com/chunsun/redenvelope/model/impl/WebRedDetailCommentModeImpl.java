package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailCommentMode;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailCommentActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/14.
 */
public class WebRedDetailCommentModeImpl implements WebRedDetailCommentMode {

    private WebRedDetailCommentActivity mActivity;
    private HttpManager mManager;

    public WebRedDetailCommentModeImpl(WebRedDetailCommentActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
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
    public void sendComment(final String token, final String hb_id, final String content, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_COMMENT, response);
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
//                params.put("methodName", Constants.HB_DETAIL_COMMENT_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailCommentToJson(token, hb_id, content));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.sendComment(token, hb_id, content, listener, null, mActivity);
    }
}
