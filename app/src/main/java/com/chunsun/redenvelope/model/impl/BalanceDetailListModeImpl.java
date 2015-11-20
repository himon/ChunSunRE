package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceDetailListMode;
import com.chunsun.redenvelope.ui.activity.personal.BalanceDetailListActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/20.
 */
public class BalanceDetailListModeImpl implements BalanceDetailListMode {

    private BalanceDetailListActivity mActivity;
    private HttpManager mManager;

    public BalanceDetailListModeImpl(BalanceDetailListActivity balanceDetailListActivity) {
        this.mActivity = balanceDetailListActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadBalanceDetailLitsData(final String token, final String type, final int page_index, final BaseSingleLoadedListener listener) {
//        GsonRequest<BalanceListEntity> request = new GsonRequest<BalanceListEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                BalanceListEntity.class, null, new Response.Listener<BalanceListEntity>() {
//
//            @Override
//            public void onResponse(BalanceListEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(response);
//                } else {
//                    listener.onException(response.getMsg());
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
//                params.put("methodName", Constants.HB_BALANCE_AMOUNT_LIST_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataAccountLogToJson(token, type, page_index));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.loadBalanceDetailLitsData(token, type, page_index, listener, mActivity);
    }
}
