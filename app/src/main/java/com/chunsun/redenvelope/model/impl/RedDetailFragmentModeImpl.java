package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailFragmentMode;
import com.chunsun.redenvelope.ui.fragment.RedDetailFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RedDetailFragmentModeImpl implements RedDetailFragmentMode {

    private RedDetailFragment mFragment;
    private HttpManager mManager;

    public RedDetailFragmentModeImpl(RedDetailFragment redDetailFragmentView) {
        this.mFragment = redDetailFragmentView;
        this.mManager = new HttpManager();
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
//        RequestManager.addRequest(request, mFragment);
        mManager.getCommentList(hb_id, page_index, listener, mFragment, null);
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
//        RequestManager.addRequest(request, mFragment);
        mManager.getRedRecordList(hb_id, page_index, listener, mFragment, null);
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
//        RequestManager.addRequest(request, mFragment);
        mManager.setFavorite(token, hb_id, listener, mFragment, null);
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
//        RequestManager.addRequest(request, mFragment);
        mManager.sendComment(token, hb_id, content, listener, mFragment, null);
    }

    @Override
    public void shareOpen(final String token, final String grab_id, final String shareType, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_SHARE_OPEN_RED, response);
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
//                params.put("methodName", Constants.SHARE_OPEN_RED_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initShareOpenDataToJson(token, grab_id, shareType));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mFragment);
        mManager.shareOpen(token, grab_id, shareType, listener, mFragment, null);
    }

    @Override
    public void justOpen(final String token, final String grab_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_JUST_OPEN_RED, response);
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
//                params.put("methodName", Constants.JUST_OPEN_RED_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initShareOpenDataToJson(token, grab_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mFragment);
        mManager.justOpen(token, grab_id, listener, mFragment, null);
    }
}
