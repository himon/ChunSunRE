package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/11.
 */
public class WebRedDetailModeImpl implements WebRedDetailMode {

    private WebRedDetailActivity mActivity;
    private HttpManager mManager;

    public WebRedDetailModeImpl(WebRedDetailActivity webRedDetailActivity) {
        this.mActivity = webRedDetailActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getRedData(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
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
    public void getShareLimit(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<ShareLimitEntity> request = new GsonRequest<ShareLimitEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                ShareLimitEntity.class, null, new Response.Listener<ShareLimitEntity>() {
//
//            @Override
//            public void onResponse(ShareLimitEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_LIMIT, response);
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
//                params.put("methodName", Constants.HB_SHARE_LIMIT_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getShareLimit(token, listener, mActivity);
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
//        RequestManager.addRequest(request, mActivity);
        mManager.shareOpen(token, grab_id, shareType, listener, null, mActivity);
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
//        RequestManager.addRequest(request, mActivity);
        mManager.justOpen(token, grab_id, listener, null, mActivity);
    }
}
