package com.chunsun.redenvelope.constants;


/**
 * Created by Administrator on 2015/7/28.
 */
public interface Constants {

    /**
     * 每页显示的数据条数
     */
    int PAGE_NUM = 15;

    /**
     * 红包类型
     */
    //生活、企业
    String RED_DETIAL_TYLE_SAMPLE = "-1";
    //生活
    String RED_DETIAL_TYPE_LEFT = "1";
    //企业
    String RED_DETIAL_TYPE_COMPANY = "2";
    //附近
    String RED_DETIAL_TYLE_NEAR = "3";
    //链接
    String RED_DETAIL_TYPE_LINK = "4";

    /**
     * 服务器地址
     */
    //String HOST_URL = "http://sv.chunsunkeji.com";
    String HOST_URL = "https://1.193.162.20:899";// 外网
    //String IMG_HOST_URL = "http://admin.chunsunkeji.com";
    String IMG_HOST_URL = "https://1.193.162.20:899";

    /**
     * 正式发布时需要修正-----web service地址
     */
    String NEW_URL = HOST_URL + "/service.asmx/";

    /**
     * title bar样式类型
     */
    int TITLE_TYPE_SAMPLE = 0x1000;
    int TITLE_TYPE_HOME = 0x1001;
    int TITLE_TYPE_NONE = 0x1002;
    int TITLE_TYPE_AD = 0x1003;

    /**
     * web service地址接口
     */
    String WEB_SERVICE_URL = NEW_URL + "CaseMethad";
    //登录
    String LOGIN_JSON_REQUEST_URL = "login_bypassword_cs";
    //注销登录
    String LOGOUT_JSON_REQUEST_URL = "log_out_cs";
    //注册时获取验证码
    String REGISTER_GET_VALIDATA_CODE_JSON_REQUEST_URL = "sms_verify_code_get_register_bymobile";
    //注册时验证验证码
    String REGISTER_NEXT_STEP_CODE_JSON_REQUEST_URL = "tool_check_v_code_sms";
    //注册时验证邀请码
    String REGISTER_VALIDATA_INVITE_CODE_JSON_REQUEST_URL = "user_register_check_invitation_code_is_exist";
    //注册
    String REGISTER_JSON_REQUEST_URL = "user_register_v_1_2";
    //获取用户信息
    String GET_USERINFO_JSON_REQUEST_URL = "user_info_get_bytoken";
    //快捷登录获取验证码
    String LOGIN_GET_VALIDATA_CODE_JSON_REQUEST_URL = "sms_get_verify_bymobile";
    //快捷登录
    String QUICK_LOGIN_CODE_JSON_REQUEST_URL = "login_by_mobile_verifycode";
    //找回密码
    String FIND_PWD_JSON_REQUEST_URL = "user_find_password_bymobile";
    //获取红包列表数据
    String HB_POOL_JSON_REQUEST_URL = "hb_get_pool_list_v1_2";
    //获取红包列表广告
    String HB_AD_JSON_REQUEST_URL = "advert_index_by_province_city_type";
    //获取红包详情
    String HB_DETAIL_JSON_REQUEST_URL = "hb_get_detail_v1_2";
    //获取红包详情评论列表
    String HB_DETAIL_COMMENT_LIST_JSON_REQUEST_URL = "hb_this_comment_list_v1_2";
    //获取红包详情领取记录列表
    String HB_DETAIL_RED_RECORD_LIST_JSON_REQUEST_URL = "hb_this_grab_records_list";
    //收藏红包
    String HB_DETAIL_SET_FAVORITE_JSON_REQUEST_URL = "hb_user_favorite";
    //举报红包
    String HB_DETAIL_REPORT_JSON_REQUEST_URL = "hb_report";
    //评论
    String HB_DETAIL_COMMENT_JSON_REQUEST_URL = "hb_user_comment";
    //抢红包
    String HB_GRAB_JSON_REQUEST_URL = "hb_user_grab_bytoken";
    //未领取红包列表
    String HB_UNRECEIVE_JSON_REQUEST_URL = "user_unreceive_hb_list_bytoken";
    //红包收藏列表
    String HB_FAVORITE_JSON_REQUEST_URL = "user_fav_hb_list";
    //红包发送记录分类
    String HB_SEND_RECORD_CLASSIFY_JSON_REQUEST_URL = "user_send_hongbao_list_v_1_2";
    //红包发送记录分类列表
    String HB_SEND_RECORD_CLASSIFY_LIST_JSON_REQUEST_URL = "user_get_hongbao_log_bytype";
    //用户账户信息
    String USER_AMOUNT_JSON_REQUEST_URL = "user_get_available_amount";
    //账户额度记录
    String HB_BALANCE_AMOUNT_LIST_JSON_REQUEST_URL = "user_get_amount_log_list_v_1_2";
    //支付宝提现
    String RECHARGE_ALIPAY_JSON_REQUEST_URL = "user_zfb_cash";
    //获取手机号的运营商信息
    String CARRIER_OPERATOR_JSON_REQUEST_URL = "http://chongzhi.jd.com/json/order/search_searchPhone.action";
    //手机话费充值
    String RECHARGE_MOBILE_JSON_REQUEST_URL = "user_mobile_cz";
    //用户账号充值，调用该接口生成充值订单
    String USER_RECHARGE_BALANCE_JSON_REQUEST_URL = "user_recharge_to_own_amount";
    //获取用户下级用户，包含通过代理提成和好友助力获取的金额
    String USER_INVITE_RECORD_LIST_JSON_REQUEST_URL = "user_get_children_list";
    //修改密码
    String USER_UPDATE_PWD_JSON_REQUEST_URL = "user_mod_password_now";
    //没有旧密码，修改密码
    String USER_UPDATE_PWD_NOT_HAS_OLD_PWD_JSON_REQUEST_URL = "user_reset_password_bytoken";
    //修改用户基本信息
    String USER_UPDATE_INFO_JSON_REQUEST_URL = "user_info_edit_byuser";
    //获取广告延时时间
    String GET_AD_DELAY_SECONDS_RATE_JSON_REQUEST_URL = "send_delay_seconds_to_rate";

    /**
     * 正式发布时需要修正-----市场渠道地址
     */
    String CHANNEL_PACKAGE_NAME = "test";

    /**
     * 传递打开WebView的URL
     */
    String INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL = "intent_bundle_key_common_web_view_url";

    /**
     * 传递打开WebView的title
     */
    String INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE = "intent_bundle_key_common_web_view_title";


    /**
     * 注册协议
     */
    String REGISTER_SERVICE_PROTOCOL = "http://www.chunsunkeji.com/registe/RegistrationAgreement.html";

    /**
     * 注册类型
     */
    String REGISTER_TYPE_PERSONAL = "1";
    String REGISTER_TYPE_COMPANY = "2";

    /**
     * 验证码倒计时时间
     */
    int COUNT_DONW = 60;

    /**
     * 图片所保存的路径
     */
    String CHUNSUN_IMAGE_FILE_PATH = "/Chunsun/image/";

    /**
     * Activity跳转intent传递参数的key
     */
    String EXTRA_KEY = "extra_key";
    String EXTRA_KEY2 = "extra_key2";
    String EXTRA_KEY3 = "extra_key3";
    String EXTRA_KEY4 = "extra_key4";
    String EXTRA_KEY5 = "extra_key5";
    String EXTRA_KEY_ID = "extra_key_id";
    String EXTRA_KEY_TITLE = "extra_key_title";
    String EXTRA_KEY_TEXT = "extra_key_text";
    String EXTRA_KEY_TYPE = "extra_key_type";

    /**
     * EditInfoActivity显示类型
     */
    //举报
    String EXTRA_KEY_TYPE_COMPLAINT = "extra_key_type_complaint";

    /**
     * 标示从发广告跳转到登录
     */
    String FROM_AD = "from_ad";

    /**
     * 标示从我跳转到登录
     */
    String FROM_ME = "from_me";

    /**
     * 标示登录页点击了返回
     */
    String FROM_LOGIN_BACK = "from_login_back";

    /**
     * WebView的url
     */
    // 担保交易WebView
    String SEND_GUARANTEE_URL = "http://www.chunsunkeji.com/danbao/db.html";
    // 银联充值余额
    String BACK_RECHARGE_URL = HOST_URL + "/api/payment/unionpay/notify_url.aspx?order_no=";

    /**
     * MeFragment item type
     */
    int ME_FRAGMENT_TYPE_MINE = 1;
    int ME_FRAGMENT_TYPE_INVITE_CODE = 2;
    int ME_FRAGMENT_TYPE_BALANCE = 3;
    int ME_FRAGMENT_TYPE_RECORD = 4;
    int ME_FRAGMENT_TYPE_NOT_RECEIVING_RED = 5;
    int ME_FRAGMENT_TYPE_COLLECT = 6;
    int ME_FRAGMENT_TYPE_SETTING = 7;

    /**
     * 红包发布后状态
     */
    //待支付
    String RED_DETAIL_STATUS_DZF = "dzf_count";
    //审核中
    String RED_DETAIL_STATUS_SHZ = "shz_count";
    //已发布
    String RED_DETAIL_STATUS_YFB = "yfb_count";
    //已抢完
    String RED_DETAIL_STATUS_YQW = "yqw_count";
    //未通过
    String RED_DETAIL_STATUS_WTG = "wtg_count";
    //已冻结
    String RED_DETAIL_STATUS_YDJ = "YDJ_count";

    /**
     * 余额账户明细分类类型
     */
    //领红包收入
    String BALANCE_TYPE_OPEN_HB = "openhb_amount";
    //发红包支出
    String BALANCE_TYPE_SEND_HB = "sendhb_amount";
    //已提现
    String BALANCE_TYPE_CASH_AMOUNT = "cash_amount";
    //已充话费
    String BALANCE_TYPE_CZ_AMOUNT = "cz_amount";
    //其他
    String BALANCE_TYPE_OTHER = "other_amount";

    /**
     * 用户余额充值方式
     */
    //支付宝
    String BALANCE_RECHARGE_TYPE_ALIPAY = "1";
    //银行
    String BALANCE_RECHARGE_TYPE_BANK = "2";

    /**
     * 账号注册类型 type：1（个人）， 2 （企业）
     */
    String USER_REGISTER_TYPE_PERSONAL = "1";
    String USER_REGISTER_TYPE_ENTERPRISE = "2";

    /**
     * listener返回类型
     */
    int LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST = 0x2000;
    int LISTENER_TYPE_GRAD_RED_ENVELOPE = 0x2001;
    int LISTENER_TYPE_RED_ENVELOPE_LIST = 0x2002;
    int LISTENER_TYPE_AD = 0x2003;
    int LISTENER_TYPE_GET_CARRIER_OPERATOR = 0x2004;
    int LISTENER_TYPE_RECHARGE = 0x2005;
    int LISTENER_TYPE_GET_CODE = 0x2006;
    int LISTENER_TYPE_LOGIN = 0x2007;
    int LISTENER_TYPE_GET_COMMENT_LIST = 0x2008;
    int LISTENER_TYPE_GET_RECORD_LIST = 0x2009;
    int LISTENER_TYPE_FAVORITE = 0x2010;
    int LISTENER_TYPE_COMMENT = 0x2011;
    int LISTENER_TYPE_REGISTER = 0x2012;
    int LISTENER_TYPE_GET_USER_INFO = 0x2013;
    int LISTENER_TYPE_NEXT_STEP = 0x2014;
    int LISTENER_TYPE_GET_INVITE_CODE = 0X2015;
    int LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL = 0x2016;
}
