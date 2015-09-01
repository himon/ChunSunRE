package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceRechargeMode;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceRechargeEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.presenter.OnRechargeBalanceListener;
import com.chunsun.redenvelope.ui.activity.personal.BalanceRechargeActivity;
import com.chunsun.redenvelope.utils.JSONUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/21.
 */
public class BalanceRechargeModeImpl implements BalanceRechargeMode {

    private BalanceRechargeActivity mActivity;

    public BalanceRechargeModeImpl(BalanceRechargeActivity balanceRechargeActivity) {
        this.mActivity = balanceRechargeActivity;
    }

    @Override
    public void recharge(final String token, final String type, final String amount, final BaseSingleLoadedListener listener) {
        GsonRequest<BalanceRechargeEntity> request = new GsonRequest<BalanceRechargeEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                BalanceRechargeEntity.class, null, new Response.Listener<BalanceRechargeEntity>() {

            @Override
            public void onResponse(BalanceRechargeEntity response) {
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
                params.put("methodName", Constants.USER_RECHARGE_BALANCE_JSON_REQUEST_URL);
                params.put("parames", JSONUtils.initDataRechargeToJson(token, type, amount));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
