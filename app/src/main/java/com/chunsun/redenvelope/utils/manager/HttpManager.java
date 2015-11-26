package com.chunsun.redenvelope.utils.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.entity.json.AdPayAmountDetailEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceListEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceRechargeEntity;
import com.chunsun.redenvelope.model.entity.json.CarrierOperatorEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.InteractiveEntity;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordClassifyEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.model.entity.json.RepeatMealEntity;
import com.chunsun.redenvelope.model.entity.json.RepeatRedEnvelopeGetHostEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseObjectEntity;
import com.chunsun.redenvelope.model.entity.json.ScanCouponResultEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.model.entity.json.UserPublicInfoEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/19 16:57
 * @des
 */
public class HttpManager {

    /**
     * tab页获取列表数据
     *
     * @param token      token值
     * @param type       -1（领红包），3（任务）
     * @param page_index 加载页码
     * @param listener   回调监听
     */
    public void loadData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener, final Fragment fragment) {
        GsonRequest<RedListDetailEntity> request = new GsonRequest<RedListDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedListDetailEntity.class, null, new Response.Listener<RedListDetailEntity>() {

            @Override
            public void onResponse(RedListDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_RED_ENVELOPE_LIST, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_POOL_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopePoolToJson(token, type, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), MainApplication.getContext().getLongitude() + "", MainApplication.getContext().getLatitude() + "", page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, fragment);
    }

    /**
     * tab页获取轮播广告数据
     *
     * @param type     1（领红包），3（任务）
     * @param listener 回调监听
     */
    public void getAdData(final String type, final BaseMultiLoadedListener listener, Fragment fragment) {
        GsonRequest<RedAutoAdEntity> request = new GsonRequest<RedAutoAdEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedAutoAdEntity.class, null, new Response.Listener<RedAutoAdEntity>() {

            @Override
            public void onResponse(RedAutoAdEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_AD, response);
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
                params.put("methodName", Constants.HB_AD_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initAdDataToJson(type, "", ""));
                return params;
            }
        };
        RequestManager.addRequest(request, fragment);
    }

    /**
     * 获取红包领取名额
     *
     * @param token    token值
     * @param hb_id    红包id
     * @param listener 回调监听
     */
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess() || "-8".equals(response.getCode())) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_GRAB_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, fragment);
        } else {
            RequestManager.addRequest(request, activity);
        }
    }

    /**
     * 支付广告金额时获取广告金额信息
     *
     * @param token
     * @param hb_id
     * @param listener
     * @param activity
     */
    public void getAdAmountDetail(final String token, final String hb_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<AdPayAmountDetailEntity> request = new GsonRequest<AdPayAmountDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                AdPayAmountDetailEntity.class, null, new Response.Listener<AdPayAmountDetailEntity>() {

            @Override
            public void onResponse(AdPayAmountDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_AD_AMOUNT_DETAIL, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.GET_AD_AMOUNT_DETAIL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 余额支付广告费用
     *
     * @param token
     * @param hb_id
     * @param listener
     * @param activity
     */
    public void payByBalance(final String token, final String hb_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_PAY_BY_BANLANCE, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(Constants.LISTENER_TYPE_PAY_BY_BANLANCE, response.getMsg());
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                byte[] data = error.networkResponse.data;
                String msg = new String(data);
                listener.onException(msg);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.AD_PAY_BY_BALANCE);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 加载账户明细数据列表（钱包）
     *
     * @param token      token值
     * @param type       明细类型
     * @param page_index 当前页数
     * @param listener   回调监听
     * @param activity   来自的Activity
     */
    public void loadBalanceDetailLitsData(final String token, final String type, final int page_index, final BaseSingleLoadedListener listener, Activity activity) {
        GsonRequest<BalanceListEntity> request = new GsonRequest<BalanceListEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                BalanceListEntity.class, null, new Response.Listener<BalanceListEntity>() {

            @Override
            public void onResponse(BalanceListEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(response);
                } else {
                    listener.onException(response.getMsg());
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
                params.put("methodName", Constants.HB_BALANCE_AMOUNT_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataAccountLogToJson(token, type, page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取钱包页面显示的数据（获取用户账户金额信息）
     *
     * @param token    token值
     * @param listener 回调监听
     * @param activity 来自的Activity
     */
    public void loadWalletData(final String token, final BaseMultiLoadedListenerImpl listener, final Activity activity) {
        GsonRequest<BalanceEntity> request = new GsonRequest<BalanceEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                BalanceEntity.class, null, new Response.Listener<BalanceEntity>() {

            @Override
            public void onResponse(BalanceEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_AMOUNT, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.USER_AMOUNT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 余额充值
     *
     * @param token
     * @param type
     * @param amount
     * @param listener
     * @param activity
     */
    public void balanceRecharge(final String token, final String type, final String amount, final BaseSingleLoadedListener listener, Activity activity) {
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
                params.put("parames", JsonManager.initDataRechargeToJson(token, type, amount));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取收藏、未领取红包列表
     *
     * @param token
     * @param listener
     * @param activity
     */
    public void loadData(final String token, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<RedDetailUnReceiveAndCollectEntity> request = new GsonRequest<RedDetailUnReceiveAndCollectEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailUnReceiveAndCollectEntity.class, null, new Response.Listener<RedDetailUnReceiveAndCollectEntity>() {

            @Override
            public void onResponse(RedDetailUnReceiveAndCollectEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_FAVORITE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取红包评论列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     * @param fragment
     * @param activity
     */
    public void getCommentList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<RedDetailCommentEntity> request = new GsonRequest<RedDetailCommentEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailCommentEntity.class, null, new Response.Listener<RedDetailCommentEntity>() {

            @Override
            public void onResponse(RedDetailCommentEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_COMMENT_LIST, response);
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
                params.put("methodName", Constants.HB_DETAIL_COMMENT_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initRecordToJson(hb_id, page_index));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 获取红包领取记录列表
     *
     * @param hb_id
     * @param page_index
     * @param listener
     * @param fragment
     */
    public void getRedRecordList(final String hb_id, final int page_index, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<RedDetailGetRedRecordEntity> request = new GsonRequest<RedDetailGetRedRecordEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailGetRedRecordEntity.class, null, new Response.Listener<RedDetailGetRedRecordEntity>() {

            @Override
            public void onResponse(RedDetailGetRedRecordEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RECORD_LIST, response);
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
                params.put("methodName", Constants.HB_DETAIL_RED_RECORD_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initRecordToJson(hb_id, page_index));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 红包设置收藏
     *
     * @param token
     * @param hb_id
     * @param listener
     * @param fragment
     */
    public void setFavorite(final String token, final String hb_id, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_FAVORITE, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_DETAIL_SET_FAVORITE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 红包详情发送评论
     *
     * @param token
     * @param hb_id
     * @param content
     * @param listener
     * @param fragment
     */
    public void sendComment(final String token, final String hb_id, final String content, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMMENT, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_DETAIL_COMMENT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailCommentToJson(token, hb_id, content));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 有奖励分享红包
     *
     * @param token
     * @param grab_id
     * @param shareType
     * @param listener
     * @param fragment
     */
    public void shareOpen(final String token, final String grab_id, final String shareType, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_SHARE_OPEN_RED, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.SHARE_OPEN_RED_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initShareOpenDataToJson(token, grab_id, shareType));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 无奖励红包分享
     *
     * @param token
     * @param grab_id
     * @param listener
     * @param fragment
     */
    public void justOpen(final String token, final String grab_id, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_JUST_OPEN_RED, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getActivity(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.JUST_OPEN_RED_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initShareOpenDataToJson(token, grab_id));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 广告创建提交
     *
     * @param token
     * @param adEntity
     * @param title
     * @param content
     * @param listener
     * @param activity
     */
    public void commitAdCreate(final String token, final AdEntity adEntity, final String title, final String content, final BaseMultiLoadedListener listener, final Activity activity) {

        GsonRequest<CreateAdResultEntity> request = new GsonRequest<CreateAdResultEntity>(Request.Method.POST, StringUtil.preUrl(Constants.CREATE_AD_JSON_REQUEST_URL),
                CreateAdResultEntity.class, null, new Response.Listener<CreateAdResultEntity>() {

            @Override
            public void onResponse(CreateAdResultEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMMIT_AD, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //byte[] data = error.networkResponse.data;
                //String msg = new String(data);
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                params.put("type", adEntity.getType().getKey());
                params.put("title", title);
                params.put("content", content);
                params.put("cover_img_byte_str", adEntity.getCoverImagePath());
                params.put("img_byte_str1", adEntity.getImagePath());
                params.put("img_byte_str2", adEntity.getImagePath2());
                params.put("img_byte_str3", adEntity.getImagePath3());
                params.put("img_byte_str4", adEntity.getImagePath4());
                params.put("img_byte_str5", adEntity.getImagePath5());
                params.put("img_byte_str6", adEntity.getImagePath6());
                params.put("img_byte_str7", adEntity.getImagePath7());
                params.put("img_byte_str8", adEntity.getImagePath8());
                params.put("province", adEntity.getProvince().getP());
                params.put("city", adEntity.getCity().getC());
                params.put("range", adEntity.getDistance().getKey());
                params.put("longitude", "");
                params.put("latitude", "");
                params.put("price", adEntity.getPrice());
                params.put("everyday_count", adEntity.getNum());
                params.put("day_count", adEntity.getDays());
                params.put("delay_seconds_rate_id", adEntity.getDelaySeconds().getId() + "");
                params.put("time", adEntity.getStartTime());
                params.put("is_need_receipt", adEntity.getIsReceipt());
                params.put("receipt_title", "");
                params.put("bank_name", "");
                params.put("bank_no", "");
                params.put("tax_no", "");
                params.put("forwarding_packages_id", adEntity.getMeal().getId() + "");
                params.put("formula_multiple", adEntity.getFormula_multiple());
                params.put("start_time", adEntity.getCouponStartTime());
                params.put("end_time", adEntity.getCouponEndTime());
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取红包延迟时间数据
     *
     * @param listener
     * @param activity
     */
    public void getAdDelaySecondsRate(final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<AdDelaySecondsRateEntity> request = new GsonRequest<AdDelaySecondsRateEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                AdDelaySecondsRateEntity.class, null, new Response.Listener<AdDelaySecondsRateEntity>() {

            @Override
            public void onResponse(AdDelaySecondsRateEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CREATE_AD_DELAY_SECONDS, response);
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
                params.put("methodName", Constants.GET_AD_DELAY_SECONDS_RATE_JSON_REQUEST_URL);
                params.put("parames", "");
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 发广告，获取转发类套餐
     *
     * @param listener
     * @param activity
     */
    public void getRepeatMeal(final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<RepeatMealEntity> request = new GsonRequest<RepeatMealEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RepeatMealEntity.class, null, new Response.Listener<RepeatMealEntity>() {

            @Override
            public void onResponse(RepeatMealEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_REPEATE_MEAL, response);
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
                params.put("methodName", Constants.CEATE_AD_GET_REPEAT_MEAL_JSON_REQUEST_URL);
                params.put("parames", "");
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 举报红包
     *
     * @param token
     * @param hb_id
     * @param reason
     * @param listener
     * @param activity
     */
    public void complaintRedEnvelope(final String token, final String hb_id, final String reason, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMLAINT_RED_ENVELOPE, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_DETAIL_REPORT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailReportToJson(token, hb_id, reason));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 修改用户基本信息
     *
     * @param token
     * @param field_name
     * @param field_value
     * @param listener
     * @param activity
     */
    public void editUserInfo(final String token, final String field_name, final String field_value, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_EDIT_USER_INFO, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.USER_INFO_EDIT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataUpdateInfoToJson(token, field_name, field_value));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取验证码
     *
     * @param mobile
     * @param listener
     * @param activity
     */
    public void getCode(final String mobile, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CODE, response);
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
                params.put("methodName", Constants.LOGIN_GET_VALIDATA_CODE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataSmsToJson(mobile));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 注册、找回密码的下一步
     *
     * @param mobile
     * @param verify_code
     * @param listener
     * @param activity
     */
    public void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_NEXT_STEP, response);
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
                params.put("methodName", Constants.REGISTER_NEXT_STEP_CODE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataVerifyToJson(mobile, verify_code));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 找回密码提交
     *
     * @param mobile
     * @param verify_code
     * @param new_pwd
     * @param confirm_pwd
     * @param listener
     * @param activity
     */
    public void findPwdSubmit(final String mobile, final String verify_code,
                              final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                byte[] htmlBodyBytes = error.networkResponse.data;
                Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.FIND_PWD_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataFindPasswordToJson(mobile, verify_code, new_pwd, confirm_pwd));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 互动平台获取全国评论
     *
     * @param token
     * @param page_index
     * @param listener
     * @param activity
     */
    public void getCountryCommentList(final String token, final int page_index, final BaseMultiLoadedListener listener, final Activity activity) {
        final GsonRequest<InteractiveEntity> request = new GsonRequest<InteractiveEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InteractiveEntity.class, null, new Response.Listener<InteractiveEntity>() {

            @Override
            public void onResponse(InteractiveEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INTERACTIVE_COUNTRY, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST);
                params.put("parames", JsonManager.initInterractiveDataToJson(token, "全国", "全国", page_index + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 互动平台获取本地评论
     *
     * @param token
     * @param page_index
     * @param listener
     * @param activity
     */
    public void getLocalCommentList(final String token, final int page_index, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<InteractiveEntity> request = new GsonRequest<InteractiveEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InteractiveEntity.class, null, new Response.Listener<InteractiveEntity>() {

            @Override
            public void onResponse(InteractiveEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INTERACTIVE_LOCAL, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST);
                params.put("parames", JsonManager.initInterractiveDataToJson(token, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), page_index + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 互动平台发送评论
     *
     * @param token
     * @param comment
     * @param province
     * @param city
     * @param listener
     * @param activity
     */
    public void sendComment(final String token, final String comment, final String province, final String city, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMMENT, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.INTERACTION_COMMENT_LIST_COMMENT);
                params.put("parames", JsonManager.initInterractiveCommentDataToJson(token, province, city, comment));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 用户登录
     *
     * @param mobile_or_chunsun
     * @param password
     * @param push_device_token
     * @param json_str
     * @param listener
     * @param activity
     */
    public void login(final String mobile_or_chunsun, final String password, final String push_device_token, final String json_str, final BaseSingleLoadedListener listener, Activity activity) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.LOGIN_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataLoginToJson(mobile_or_chunsun, password, push_device_token, json_str));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @param listener
     * @param fragment
     */
    public void getUserInfomation(final String token, final BaseMultiLoadedListener listener, final Fragment fragment, final Activity activity) {
        GsonRequest<UserEntity> request = new GsonRequest<UserEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                UserEntity.class, null, new Response.Listener<UserEntity>() {

            @Override
            public void onResponse(UserEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        if (fragment != null) {
                            listener.onError(response.getMsg(), fragment.getContext(), Constants.FROM_TAB1);
                        } else {
                            listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                        }
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.GET_USERINFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        if (fragment == null) {
            RequestManager.addRequest(request, activity);
        } else {
            RequestManager.addRequest(request, fragment);
        }
    }

    /**
     * 取用户下级用户，包含通过代理提成和好友助力获取的金额
     *
     * @param token
     * @param listener
     * @param activity
     */
    public void getInviteRecord(final String token, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<InviteRecordEntity> request = new GsonRequest<InviteRecordEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                InviteRecordEntity.class, null, new Response.Listener<InviteRecordEntity>() {

            @Override
            public void onResponse(InviteRecordEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INVITE_INFO, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取手机号的运营商
     *
     * @param mobile
     * @param listener
     * @param activity
     */
    public void getCarrierOperator(final String mobile, final BaseMultiLoadedListener listener, Activity activity) {

        GsonRequest<CarrierOperatorEntity> request = new GsonRequest<CarrierOperatorEntity>(Request.Method.POST, StringUtil.preUrl(Constants.CARRIER_OPERATOR_JSON_REQUEST_URL),
                CarrierOperatorEntity.class, null, new Response.Listener<CarrierOperatorEntity>() {

            @Override
            public void onResponse(CarrierOperatorEntity response) {
                if (response != null) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CARRIER_OPERATOR, response);
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
                params.put("mobile", mobile);
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 手机话费充值
     *
     * @param token
     * @param mobile
     * @param yunyingshang
     * @param cz_poundage_id
     * @param listener
     * @param activity
     */
    public void rechargeMobile(final String token, final String mobile, final String yunyingshang, final int cz_poundage_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_RECHARGE, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.RECHARGE_MOBILE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataMobileRechargeToJson(token, mobile, yunyingshang, cz_poundage_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 快捷登录
     *
     * @param phone
     * @param code
     * @param phoneInfo
     * @param pushDeviceToken
     * @param listener
     * @param activity
     */
    public void quickLogin(final String phone, final String code,
                           final String phoneInfo, final String pushDeviceToken, final BaseMultiLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_LOGIN, response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //byte[] htmlBodyBytes = error.networkResponse.data;
                //Log.e("LOGIN - ERROR", new String(htmlBodyBytes), error);
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.QUICK_LOGIN_CODE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataQuickLoginToJson(phone, code, phoneInfo, pushDeviceToken));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取红包详情
     *
     * @param token
     * @param hb_id
     * @param listener
     * @param activity
     */
    public void getRedData(final String token, final String hb_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<RedDetailEntity> request = new GsonRequest<RedDetailEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailEntity.class, null, new Response.Listener<RedDetailEntity>() {

            @Override
            public void onResponse(RedDetailEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取每天分享次数
     *
     * @param token
     * @param listener
     * @param activity
     */
    public void getShareLimit(final String token, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<ShareLimitEntity> request = new GsonRequest<ShareLimitEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                ShareLimitEntity.class, null, new Response.Listener<ShareLimitEntity>() {

            @Override
            public void onResponse(ShareLimitEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_RED_ENVELOPE_LIMIT, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_SHARE_LIMIT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 注册时获取验证码
     *
     * @param mobile
     * @param listener
     * @param activity
     */
    public void registerGetCode(final String mobile, final BaseMultiLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_CODE, response);
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
                params.put("methodName", Constants.REGISTER_GET_VALIDATA_CODE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataSmsToJson(mobile));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 验证邀请码
     *
     * @param invitation_code
     * @param listener
     * @param activity
     */
    public void hasInviteCodeNextStep(final String invitation_code, final BaseMultiLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_INVITE_CODE, response);
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
                params.put("methodName", Constants.REGISTER_VALIDATA_INVITE_CODE_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataInviteToJson(invitation_code));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 注册
     *
     * @param type
     * @param mobile
     * @param verify_code
     * @param password
     * @param confirm_pwd
     * @param push_device_token
     * @param json_str
     * @param invitation_code
     * @param listener
     * @param activity
     */
    public void register(final String type, final String mobile, final String verify_code, final String password, final String confirm_pwd, final String push_device_token, final String json_str, final String invitation_code, final BaseMultiLoadedListener listener, Activity activity) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_REGISTER, response);
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
                params.put("methodName", Constants.REGISTER_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRegisterToJson(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取转发类分享host
     *
     * @param token
     * @param hb_id
     * @param platform
     * @param is_valid
     * @param listener
     * @param activity
     */
    public void getHost(final String token, final String hb_id, final String platform, final boolean is_valid, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<RepeatRedEnvelopeGetHostEntity> request = new GsonRequest<RepeatRedEnvelopeGetHostEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RepeatRedEnvelopeGetHostEntity.class, null, new Response.Listener<RepeatRedEnvelopeGetHostEntity>() {

            @Override
            public void onResponse(RepeatRedEnvelopeGetHostEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_REPEAT_GET_HOST, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.REPEAT_HB_DETAIL_GET_HOST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRepeatRedEnvelopeDetailGetHostToJson(token, hb_id, platform, is_valid));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取春笋券信息
     *
     * @param sellerToken
     * @param code
     * @param listener
     * @param activity
     */
    public void validateCoupon(final String sellerToken, final String code, final BaseMultiLoadedListener listener, Activity activity) {
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
        RequestManager.addRequest(request, activity);
    }

    /**
     * 使用春笋券
     *
     * @param sellerToken
     * @param code
     * @param listener
     * @param activity
     */
    public void using(final String sellerToken, final String code, final BaseMultiLoadedListener listener, Activity activity) {
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
        RequestManager.addRequest(request, activity);
    }

    /**
     * 红包发送记录分类
     *
     * @param token
     * @param listener
     * @param activity
     */
    public void loadRedEnvelopeSendRecordClassifyData(final String token, final BaseSingleLoadedListener listener, Activity activity) {
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
        RequestManager.addRequest(request, activity);
    }

    /**
     * 红包追加
     *
     * @param hb_id
     * @param listener
     * @param activity
     */
    public void superaddition(final String hb_id, final BaseMultiLoadedListener listener, Activity activity) {
        GsonRequest<RedSuperadditionEntity> request = new GsonRequest<RedSuperadditionEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedSuperadditionEntity.class, null, new Response.Listener<RedSuperadditionEntity>() {

            @Override
            public void onResponse(RedSuperadditionEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_RED_ENVELOPE_SUPERADDITION, response);
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
                params.put("methodName", Constants.RED_ENVELOPE_SUPERADDITION_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initSuperadditionDataToJson(hb_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 红包发送记录分类列表
     *
     * @param token
     * @param type
     * @param page_index
     * @param listener
     * @param activity
     */
    public void loadRedEnvelopeSendRecordListData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<RedDetailSendRecordListEntity> request = new GsonRequest<RedDetailSendRecordListEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RedDetailSendRecordListEntity.class, null, new Response.Listener<RedDetailSendRecordListEntity>() {

            @Override
            public void onResponse(RedDetailSendRecordListEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_SEND_RED_ENVELOPE_RECORD, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_SEND_RECORD_CLASSIFY_LIST_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataAccountLogToJson(token, type, page_index));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 红包发送记录列表删除
     *
     * @param token
     * @param hb_id
     * @param listener
     * @param activity
     */
    public void delRedEnvelope(final String token, final int hb_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_DETAIL_SEND_RED_ENVELOPE_RECORD, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.HB_RECORD_LIST_DETAIL_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataRedEnvelopeDetailToJson(token, hb_id + ""));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 退出登录
     *
     * @param token
     * @param listener
     * @param activity
     */
    public void logout(final String token, final BaseSingleLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.LOGOUT_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataTokenToJson(token));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 修改密码
     *
     * @param token
     * @param old_pwd
     * @param new_pwd
     * @param confirm_pwd
     * @param listener
     * @param activity
     */
    public void updatePassword(final String token, final String old_pwd, final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.USER_UPDATE_PWD_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initPasswordDataToJson(token, old_pwd, new_pwd, confirm_pwd));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 没有旧密码，修改密码
     *
     * @param token
     * @param new_pwd
     * @param confirm_pwd
     * @param listener
     * @param activity
     */
    public void updatePasswordNotOldPwd(final String token, final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener, Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.USER_UPDATE_PWD_NOT_HAS_OLD_PWD_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataPasswordToJson(token, new_pwd, confirm_pwd));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 修改用户隐私设置
     *
     * @param token
     * @param field_value
     * @param listener
     * @param activity
     */
    public void updateUserPrivacySetting(final String token, final String field_value, final BaseSingleLoadedListener listener, Activity activity) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.USER_UPDATE_INFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataUpdateInfoToJson(token, "private_json", field_value));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 获取用户公开信息
     *
     * @param token
     * @param user_id
     * @param listener
     * @param activity
     */
    public void getUserPublicData(final String token, final String user_id, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<UserPublicInfoEntity> request = new GsonRequest<UserPublicInfoEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                UserPublicInfoEntity.class, null, new Response.Listener<UserPublicInfoEntity>() {

            @Override
            public void onResponse(UserPublicInfoEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_USER_INFO, response);
                } else {
                    if (Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())) {
                        listener.onError(response.getMsg(), activity, Constants.FROM_ME);
                    } else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.USER_PUBLIC_INFO_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initUserPublicInfoDataToJson(token, user_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 用户转账奖励
     *
     * @param token
     * @param user_id
     * @param amount
     * @param msg
     * @param hb_id
     * @param province
     * @param city
     * @param listener
     * @param activity
     */
    public void transfer(final String token, final String user_id, final String amount, final String msg, final String hb_id, final String province, final String city, final BaseMultiLoadedListener listener, final Activity activity) {
        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_USER_REWARD_PAY, response);
                } else {
                    if(Constants.UN_LOGIN_MESSAGE.equals(response.getMsg())){
                        listener.onError(response.getMsg(), activity, Constants.FROM_TAB1);
                    }else {
                        listener.onError(response.getMsg());
                    }
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
                params.put("methodName", Constants.USER_REWARD_PAY_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initUserRewardDataToJson(token, user_id, amount, msg, hb_id, province, city));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }

    /**
     * 支付宝提现
     *
     * @param token
     * @param zfb_no
     * @param zfb_name
     * @param zfb_poundage_id
     * @param listener
     * @param activity
     */
    public void rechargeByAlipay(final String token, final String zfb_no, final String zfb_name, final int zfb_poundage_id, final BaseSingleLoadedListener listener, Activity activity) {

        GsonRequest<SampleResponseEntity> request = new GsonRequest<SampleResponseEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                SampleResponseEntity.class, null, new Response.Listener<SampleResponseEntity>() {

            @Override
            public void onResponse(SampleResponseEntity response) {
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
                params.put("methodName", Constants.RECHARGE_ALIPAY_JSON_REQUEST_URL);
                params.put("parames", JsonManager.initDataWithdrawCashAlipayToJson(token, zfb_no, zfb_name, zfb_poundage_id));
                return params;
            }
        };
        RequestManager.addRequest(request, activity);
    }
}
