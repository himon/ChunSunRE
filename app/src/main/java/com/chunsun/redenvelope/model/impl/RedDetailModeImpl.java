package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.RedDetailMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.OnGetRedDetailListener;
import com.chunsun.redenvelope.ui.activity.RedDetailActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2015/8/10.
 */
public class RedDetailModeImpl implements RedDetailMode {

    private RedDetailActivity mActivity;

    public RedDetailModeImpl(RedDetailActivity redDetailActivity) {
        this.mActivity = redDetailActivity;
    }

    @Override
    public void getRedData(final String token, final String hb_id, final BaseSingleLoadedListener listener) {
        GsonRequest<RedDetailEntity> request = new GsonRequest<RedDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailEntity.class, null, new Response.Listener<RedDetailEntity>() {

            @Override
            public void onResponse(RedDetailEntity response) {
                if(response.isSuccess()) {
                    listener.onSuccess(response);
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
                params.put("methodName", Constants.HB_DETAIL_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
