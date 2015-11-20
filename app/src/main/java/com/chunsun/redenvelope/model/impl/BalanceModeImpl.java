package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.BalanceMode;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalanceModeImpl implements BalanceMode {

    private WalletActivity mActivity;
    private HttpManager mManager;

    public BalanceModeImpl(WalletActivity balanceActivity) {
        this.mActivity = balanceActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadWalletData(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<BalanceEntity> request = new GsonRequest<BalanceEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                BalanceEntity.class, null, new Response.Listener<BalanceEntity>() {
//
//            @Override
//            public void onResponse(BalanceEntity response) {
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
//                listener.onException(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.USER_AMOUNT_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.loadWalletData(token, listener, mActivity);
    }
}
