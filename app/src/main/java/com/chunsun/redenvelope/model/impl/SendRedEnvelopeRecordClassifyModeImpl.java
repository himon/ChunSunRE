package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordClassifyMode;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/15.
 */
public class SendRedEnvelopeRecordClassifyModeImpl implements SendRedEnvelopeRecordClassifyMode {

    private SendRedEnvelopeRecordClassifyActivity mActivity;
    private HttpManager mManager;

    public SendRedEnvelopeRecordClassifyModeImpl(SendRedEnvelopeRecordClassifyActivity sendRedEnvelopeRecordClassifyActivity) {
        this.mActivity = sendRedEnvelopeRecordClassifyActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadRedEnvelopeSendRecordClassifyData(final String token, final BaseSingleLoadedListener listener) {
//        GsonRequest<RedDetailSendRecordClassifyEntity> request = new GsonRequest<RedDetailSendRecordClassifyEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RedDetailSendRecordClassifyEntity.class, null, new Response.Listener<RedDetailSendRecordClassifyEntity>() {
//
//            @Override
//            public void onResponse(RedDetailSendRecordClassifyEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onError(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.HB_SEND_RECORD_CLASSIFY_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.loadRedEnvelopeSendRecordClassifyData(token, listener, mActivity);
    }
}
