package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.ScanChunsunCodeResultMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseObjectEntity;
import com.chunsun.redenvelope.model.entity.json.ScanCouponResultEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.scan.ScanChunsunCodeResultActivity;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.JsonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/5 16:37
 * @des
 */
public class ScanChunsunCodeResultModeImpl implements ScanChunsunCodeResultMode {

    private ScanChunsunCodeResultActivity mActivity;

    public ScanChunsunCodeResultModeImpl(ScanChunsunCodeResultActivity activity) {
        mActivity = activity;
    }


    @Override
    public void validateCoupon(final String sellerToken, final String code, final BaseMultiLoadedListener listener) {
        GsonRequest<ScanCouponResultEntity> request = new GsonRequest<ScanCouponResultEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                ScanCouponResultEntity.class, null, new Response.Listener<ScanCouponResultEntity>() {

            @Override
            public void onResponse(ScanCouponResultEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_GET_CHUNSUN_COUPON_INFO, response);
                } else {
                    listener.onError(response.getCode());
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
                params.put("methodName", Constants.GET_CHUNSUN_COUPON_INFO);
                params.put("parames", JsonManager.initDataCouponDetailToJson(sellerToken, code));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }

    @Override
    public void using(final String sellerToken, final String code, final BaseMultiLoadedListener listener) {
        GsonRequest<SampleResponseObjectEntity> request = new GsonRequest<SampleResponseObjectEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseObjectEntity.class, null, new Response.Listener<SampleResponseObjectEntity>() {

            @Override
            public void onResponse(SampleResponseObjectEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_USE_CHUNSUN_COUPON, response);
                } else {
                    listener.onError(response.getCode());
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
                params.put("methodName", Constants.USE_CHUNSUN_COUPON);
                params.put("parames", JsonManager.initDataCouponDetailToJson(sellerToken, code));
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
