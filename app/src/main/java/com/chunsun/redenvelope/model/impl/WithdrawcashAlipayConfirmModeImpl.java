package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.WithdrawcashAlipayConfirmMode;
import com.chunsun.redenvelope.ui.activity.personal.WithdrawcashAlipayConfirmActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawcashAlipayConfirmModeImpl implements WithdrawcashAlipayConfirmMode {

    private WithdrawcashAlipayConfirmActivity mActivity;
    private HttpManager mManager;

    public WithdrawcashAlipayConfirmModeImpl(WithdrawcashAlipayConfirmActivity withdrawcashAlipayConfirmActivity) {
        this.mActivity = withdrawcashAlipayConfirmActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void rechargeByAlipay(final String token, final String zfb_no, final String zfb_name, final int zfb_poundage_id, final BaseSingleLoadedListener listener) {

//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
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
//                params.put("methodName", Constants.RECHARGE_ALIPAY_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataWithdrawCashAlipayToJson(token, zfb_no, zfb_name, zfb_poundage_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.rechargeByAlipay(token, zfb_no, zfb_name, zfb_poundage_id, listener, mActivity);
    }
}
