package com.chunsun.redenvelope.utils.manager;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/26.
 */
public class JsonManager {

    /**
     * 红包详情
     *
     * @param token
     * @param hb_id
     * @return
     */
    public static String initDataRedEnvelopeDetailToJson(String token, String hb_id) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("hb_id", hb_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 举报
     *
     * @param token
     * @param hb_id
     * @return
     */
    public static String initDataRedEnvelopeDetailReportToJson(String token, String hb_id, String reason) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("hb_id", hb_id);
            object.put("reason", reason);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 红包评论
     *
     * @param token
     * @param hb_id
     * @return
     */
    public static String initDataRedEnvelopeDetailCommentToJson(String token, String hb_id, String content) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("hb_id", hb_id);
            object.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 红包列表
     *
     * @param token
     * @param type
     * @param province
     * @param city
     * @param longitude
     * @param latitude
     * @param page_index
     * @return
     */
    public static String initDataRedEnvelopePoolToJson(String token, int type, String province, String city, String longitude, String latitude, int page_index, int order_type, String keywords) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("type", type);
            object.put("province", province);
            object.put("city", city);
            object.put("longitude", longitude);
            object.put("latitude", latitude);
            object.put("page_index", String.valueOf(page_index));
            object.put("page_size", String.valueOf(Constants.PAGE_NUM));
            object.put("order_type", order_type);
            object.put("keywords", keywords);
            object.put("ids_str", order_type == 4 ? "1,2,3,4" : "");
            object.put("app_version", MainApplication.getContext().getmAppVersion());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 手机充值
     *
     * @param token
     * @param mobile
     * @param yunyingshang
     * @param cz_poundage_id
     * @return
     */
    public static String initDataMobileRechargeToJson(String token, String mobile, String yunyingshang, int cz_poundage_id) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("mobile", mobile);
            object.put("yunyingshang", yunyingshang);
            object.put("cz_poundage_id", cz_poundage_id + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 支付宝提现
     *
     * @param token
     * @param zfb_no
     * @param zfb_name
     * @param zfb_poundage_id
     * @return
     */
    public static String initDataWithdrawCashAlipayToJson(String token, String zfb_no, String zfb_name, int zfb_poundage_id) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("zfb_no", zfb_no);
            object.put("zfb_name", zfb_name);
            object.put("zfb_poundage_id", zfb_poundage_id + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 红包记录
     *
     * @param hb_id
     * @param page_index
     * @return
     */
    public static String initRecordToJson(String hb_id, int page_index) {
        JSONObject object = new JSONObject();
        try {
            object.put("hb_id", hb_id);
            object.put("page_index", page_index + "");
            object.put("page_size", Constants.PAGE_NUM + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * token
     *
     * @param token
     * @return
     */
    public static String initDataTokenToJson(String token) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 账户记录
     *
     * @param token
     * @param type
     * @param page_index
     * @return
     */
    public static String initDataAccountLogToJson(String token, String type, int page_index) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("type", type);
            object.put("page_index", page_index + "");
            object.put("page_size", Constants.PAGE_NUM + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 短信
     *
     * @param mobile
     * @return
     */
    public static String initDataSmsToJson(String mobile) {
        JSONObject object = new JSONObject();
        try {
            object.put("mobile", mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 邀请码
     *
     * @param invitation_code
     * @return
     */
    public static String initDataInviteToJson(String invitation_code) {
        JSONObject object = new JSONObject();
        try {
            object.put("invitation_code", invitation_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 提现
     *
     * @param token
     * @param type
     * @param amount
     * @return
     */
    public static String initDataRechargeToJson(String token, String type, String amount) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("type", type);
            object.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 验证验证码
     *
     * @param mobile
     * @param verify_code
     * @return
     */
    public static String initDataVerifyToJson(String mobile, String verify_code) {
        JSONObject object = new JSONObject();
        try {
            object.put("mobile", mobile);
            object.put("verify_code", verify_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
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
     * @return
     */
    public static String initDataRegisterToJson(String type, String mobile, String verify_code, String password, String confirm_pwd, String push_device_token, String json_str, String invitation_code) {
        JSONObject object = new JSONObject();
        try {
            object.put("type", type);
            object.put("mobile", mobile);
            object.put("verify_code", verify_code);
            object.put("password", password);
            object.put("confirm_pwd", confirm_pwd);
            object.put("push_device_type", "3");
            object.put("push_device_token", push_device_token);
            object.put("json_str", json_str);
            object.put("invitation_code", invitation_code);
            object.put("imei", MainApplication.getContext().getmImei());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 登录
     *
     * @param mobile_or_chunsun
     * @param password
     * @param push_device_token
     * @param json_str
     * @return
     */
    public static String initDataLoginToJson(String mobile_or_chunsun, String password, String push_device_token, String json_str) {
        JSONObject object = new JSONObject();
        try {
            object.put("mobile_or_chunsun", mobile_or_chunsun);
            object.put("password", password);
            object.put("push_device_type", "3");
            object.put("push_device_token", push_device_token);
            object.put("json_str", json_str);
            object.put("imei", MainApplication.getContext().getmImei());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 快捷登录
     *
     * @param phone
     * @param code
     * @param phoneInfo
     * @param pushDeviceToken
     * @return
     */
    public static String initDataQuickLoginToJson(String phone, String code, String phoneInfo, String pushDeviceToken) {
        JSONObject object = new JSONObject();
        try {
            object.put("mobile", phone);
            object.put("verify_code", code);
            object.put("push_device_type", "3");
            object.put("push_device_token", pushDeviceToken);
            object.put("json_str", phoneInfo);
            object.put("imei", MainApplication.getContext().getmImei());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 修改信息
     *
     * @param token
     * @param field_value
     * @return
     */
    public static String initDataUpdateInfoToJson(String token, String field_name, String field_value) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("field_name", field_name);
            object.put("field_value", field_value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 修改密码
     *
     * @param token
     * @param old_pwd
     * @param new_pwd
     * @param confirm_pwd
     * @return
     */
    public static String initPasswordDataToJson(String token, String old_pwd, String new_pwd, String confirm_pwd) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("old_pwd", old_pwd);
            object.put("new_pwd", new_pwd);
            object.put("confirm_pwd", confirm_pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 找回密码
     *
     * @param mobile
     * @param verify_code
     * @param new_pwd
     * @param confirm_pwd
     * @return
     */
    public static String initDataFindPasswordToJson(String mobile, String verify_code, String new_pwd, String confirm_pwd) {
        JSONObject object = new JSONObject();
        try {
            object.put("mobile", mobile);
            object.put("verify_code", verify_code);
            object.put("new_pwd", new_pwd);
            object.put("confirm_pwd", confirm_pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 修改密码（没有旧密码）
     *
     * @param token
     * @param new_pwd
     * @param confirm_pwd
     * @return
     */
    public static String initDataPasswordToJson(String token, String new_pwd, String confirm_pwd) {
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("new_pwd", new_pwd);
            object.put("confirm_pwd", confirm_pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 轮播广告
     *
     * @param type
     * @return
     */
    public static String initAdDataToJson(String type, String province, String city) {
        JSONObject object = new JSONObject();
        try {
            object.put("type", type);
            object.put("province", province);
            object.put("city", city);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    /**
     * 互动平台
     *
     * @param token
     * @param province
     * @param city
     * @param page_index
     * @return
     */
    public static String initInterractiveDataToJson(String token, String province, String city, String page_index) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("province", province);
            jsonObject.put("city", city);
            jsonObject.put("page_index", String.valueOf(page_index));
            jsonObject.put("page_size", Constants.PAGE_NUM);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 互动平台评论
     *
     * @param token
     * @param province
     * @param city
     * @param content
     * @return
     */
    public static String initInterractiveCommentDataToJson(String token, String province, String city, String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("province", province);
            jsonObject.put("city", city);
            jsonObject.put("content", content);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 获取用户公开信息
     *
     * @param token
     * @param user_id
     * @return
     */
    public static String initUserPublicInfoDataToJson(String token, String user_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String initUserRewardDataToJson(String token, String user_id, String amount, String msg, String hb_id, String province, String city) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("user_id", user_id);
            jsonObject.put("amount", amount);
            jsonObject.put("msg", msg);
            jsonObject.put("hb_id", hb_id);
            jsonObject.put("province", province);
            jsonObject.put("city", city);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 拆红包
     *
     * @param token
     * @param grab_id
     * @param shareType
     * @return
     */
    public static String initShareOpenDataToJson(String token, String grab_id, String shareType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("grab_id", grab_id);
            jsonObject.put("type", shareType);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     * @return
     */
    public static String initShareOpenDataToJson(String token, String grab_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("grab_id", grab_id);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 红包追加
     *
     * @param hb_id
     * @return
     */
    public static String initSuperadditionDataToJson(String hb_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hb_id", hb_id);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 获取转发类分享host
     *
     * @param token
     * @param hb_id
     * @param platform
     * @param is_valid
     * @return
     */
    public static String initDataRepeatRedEnvelopeDetailGetHostToJson(String token, String hb_id, String platform, boolean is_valid) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("hb_id", hb_id);
            jsonObject.put("platform", platform);
            jsonObject.put("is_valid", is_valid);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static String initDataCouponDetailToJson(String sellerToken, String code) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sellerToken", sellerToken);
            jsonObject.put("code", code);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 圈子记录操作
     *
     * @param token
     * @param province
     * @param city
     * @param longitude
     * @param latitude
     * @param operate_type
     * @param hb_id
     * @return
     */
    public static String initDataCircleOperatorToJson(String token, String province, String city, String longitude, String latitude, int operate_type, String hb_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("province", province);
            jsonObject.put("city", city);
            jsonObject.put("longitude", longitude);
            jsonObject.put("latitude", latitude);
            jsonObject.put("operate_type", operate_type);
            jsonObject.put("hb_id", hb_id);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }
}
