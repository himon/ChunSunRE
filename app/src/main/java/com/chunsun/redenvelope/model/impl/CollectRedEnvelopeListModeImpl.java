package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CollectRedEnvelopeListMode;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CollectRedEnvelopeListModeImpl implements CollectRedEnvelopeListMode {

    private CollectRedEnvelopeListActivity mActivity;
    private HttpManager mManager;

    public CollectRedEnvelopeListModeImpl(CollectRedEnvelopeListActivity collectRedEnvelopeListActivity) {
        this.mActivity = collectRedEnvelopeListActivity;
        mManager = new HttpManager();
    }

    @Override
    public void loadData(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<RedDetailUnReceiveAndCollectEntity> request = new GsonRequest<RedDetailUnReceiveAndCollectEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailUnReceiveAndCollectEntity.class, null, new Response.Listener<RedDetailUnReceiveAndCollectEntity>() {
//
//            @Override
//            public void onResponse(RedDetailUnReceiveAndCollectEntity response) {
//                if(response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST, response);
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
//                params.put("methodName", Constants.HB_FAVORITE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.loadData(token, listener, mActivity);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE, response);
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
//                params.put("methodName", Constants.HB_GRAB_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.grabRedEnvelope(token, hb_id, listener, null, mActivity);
    }
}
