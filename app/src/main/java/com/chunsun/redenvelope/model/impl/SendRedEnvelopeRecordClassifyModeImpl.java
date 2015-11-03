package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordClassifyMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordClassifyEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
public class SendRedEnvelopeRecordClassifyModeImpl implements SendRedEnvelopeRecordClassifyMode {

    private SendRedEnvelopeRecordClassifyActivity mActivity;

    public SendRedEnvelopeRecordClassifyModeImpl(SendRedEnvelopeRecordClassifyActivity sendRedEnvelopeRecordClassifyActivity) {
        this.mActivity = sendRedEnvelopeRecordClassifyActivity;
    }

    @Override
    public void loadData(final String token, final BaseSingleLoadedListener listener) {
        GsonRequest<RedDetailSendRecordClassifyEntity> request = new GsonRequest<RedDetailSendRecordClassifyEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailSendRecordClassifyEntity.class, null, new Response.Listener<RedDetailSendRecordClassifyEntity>() {

            @Override
            public void onResponse(RedDetailSendRecordClassifyEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.HB_SEND_RECORD_CLASSIFY_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
