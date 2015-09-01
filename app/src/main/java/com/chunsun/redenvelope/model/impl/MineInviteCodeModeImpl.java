package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.MineInviteCodeMode;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.OnGetInviteRecordListener;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MineInviteCodeModeImpl implements MineInviteCodeMode {

    private MineInviteCodeActivity mActivity;

    public MineInviteCodeModeImpl(MineInviteCodeActivity mineInviteCodeActivity) {
        this.mActivity = mineInviteCodeActivity;
    }

    @Override
    public void getInviteRecord(final String token, final BaseSingleLoadedListener listener) {
        GsonRequest<InviteRecordEntity> request = new GsonRequest<InviteRecordEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InviteRecordEntity.class, null, new Response.Listener<InviteRecordEntity>() {

            @Override
            public void onResponse(InviteRecordEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(response);
                } else {
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
                params.put("methodName", Constants.USER_INVITE_RECORD_LIST_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
