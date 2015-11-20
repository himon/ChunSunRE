package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserRewardMode;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserRewardModeImpl implements UserRewardMode {

    private UserRewardActivity mActivity;
    private HttpManager mManager;

    public UserRewardModeImpl(UserRewardActivity userRewardActivity) {
        mActivity = userRewardActivity;
        mManager = new HttpManager();
    }

    @Override
    public void getUserPublicData(final String token, final String user_id, final BaseMultiLoadedListener listener) {
//        GsonRequest<UserPublicInfoEntity> request = new GsonRequest<UserPublicInfoEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                UserPublicInfoEntity.class, null, new Response.Listener<UserPublicInfoEntity>() {
//
//            @Override
//            public void onResponse(UserPublicInfoEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO, response);
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
//                params.put("methodName", Constants.USER_PUBLIC_INFO_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initUserPublicInfoDataToJson(token, user_id));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getUserPublicData(token, user_id, listener, mActivity);
    }

    @Override
    public void getUserAmount(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<BalanceEntity> request = new GsonRequest<BalanceEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                BalanceEntity.class, null, new Response.Listener<BalanceEntity>() {
//
//            @Override
//            public void onResponse(BalanceEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_AMOUNT, response);
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

    @Override
    public void transfer(final String token, final String user_id, final String amount, final String msg, final String hb_id, final String province, final String city, final BaseMultiLoadedListener listener) {
//        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {
//
//            @Override
//            public void onResponse(SampleResponseEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_USER_REWARD_PAY, response);
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
//                params.put("methodName", Constants.USER_REWARD_PAY_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initUserRewardDataToJson(token, user_id, amount, msg, hb_id, province, city));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.transfer(token, user_id, amount, msg, hb_id, province, city, listener, mActivity);
    }
}
