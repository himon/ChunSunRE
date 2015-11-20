package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.PhoneRechargeConfirmMode;
import com.chunsun.redenvelope.ui.activity.personal.PhoneRechargeConfirmActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PhoneRechargeConfirmModeImpl implements PhoneRechargeConfirmMode {

    private PhoneRechargeConfirmActivity mActivity;
    private HttpManager mManager;

    public PhoneRechargeConfirmModeImpl(PhoneRechargeConfirmActivity phoneRechargeConfirmActivity) {
        this.mActivity = phoneRechargeConfirmActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCarrierOperator(final String mobile, final BaseMultiLoadedListener listener) {

//        GsonRequest<CarrierOperatorEntity> request = new GsonRequest<CarrierOperatorEntity>(Request.Method.POST, StringUtil.preUrl(Constants.CARRIER_OPERATOR_JSON_REQUEST_URL),
//                CarrierOperatorEntity.class, null, new Response.Listener<CarrierOperatorEntity>() {
//
//            @Override
//            public void onResponse(CarrierOperatorEntity response) {
//                if (response != null) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CARRIER_OPERATOR, response);
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
//                params.put("mobile", mobile);
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getCarrierOperator(mobile, listener, mActivity);
    }

    @Override
    public void rechargeMobile(final String token, final String mobile, final String yunyingshang, final int cz_poundage_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_RECHARGE, response);
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
//                params.put("methodName", Constants.RECHARGE_MOBILE_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataMobileRechargeToJson(token, mobile, yunyingshang, cz_poundage_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.rechargeMobile(token, mobile, yunyingshang, cz_poundage_id, listener, mActivity);
    }
}
