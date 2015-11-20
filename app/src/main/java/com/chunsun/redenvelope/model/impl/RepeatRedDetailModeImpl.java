package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RepeatRedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:17
 * @des ${TODO}
 */
public class RepeatRedDetailModeImpl implements RepeatRedDetailMode {

    private RepeatRedDetailActivity mActivity;
    private HttpManager mManager;

    public RepeatRedDetailModeImpl(RepeatRedDetailActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getRedDetail(final String token, final String hb_id, final BaseMultiLoadedListener listener) {

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
    public void setFavorite(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_FAVORITE, response);
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
//                params.put("methodName", Constants.HB_DETAIL_SET_FAVORITE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.setFavorite(token, hb_id, listener, null, mActivity);
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

    @Override
    public void getHost(final String token, final String hb_id, final String platform, final boolean is_valid, final BaseMultiLoadedListener listener) {
//        GsonRequest<RepeatRedEnvelopeGetHostEntity> request = new GsonRequest<RepeatRedEnvelopeGetHostEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RepeatRedEnvelopeGetHostEntity.class, null, new Response.Listener<RepeatRedEnvelopeGetHostEntity>() {
//
//            @Override
//            public void onResponse(RepeatRedEnvelopeGetHostEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_REPEAT_GET_HOST, response);
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
//                params.put("methodName", Constants.REPEAT_HB_DETAIL_GET_HOST_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRepeatRedEnvelopeDetailGetHostToJson(token, hb_id, platform, is_valid));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getHost(token, hb_id, platform, is_valid, listener, mActivity);
    }
}
