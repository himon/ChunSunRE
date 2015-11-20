package com.chunsun.redenvelope.constants;


import android.os.Environment;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface Constants {

    /**
     * 每页显示的数据条数
     */
    int PAGE_NUM = 30;

    /**
     * 红包类型
     */
    //生活、企业
    String RED_DETAIL_TYLE_SAMPLE = "-1";
    //生活
    int RED_DETAIL_TYPE_LEFT = 1;
    //企业
    int RED_DETAIL_TYPE_COMPANY = 2;
    //附近
    int RED_DETAIL_TYPE_NEAR = 3;
    //链接
    int RED_DETAIL_TYPE_LINK = 4;
    //转发
    int RED_DETAIL_TYPE_REPEAT = 5;
    //券
    int RED_DETAIL_TYPE_COUPON = 6;


    /**
     * 服务器地址
     */
    String HOST_URL = "http://cssv.chunsunkeji.com";
//    String HOST_URL = "http://192.168.1.109:9101";// 外网
    //    String HOST_URL = "http://192.168.1.195:9000";//内网

    String IMG_HOST_URL = "http://cssv.chunsunkeji.com";
//    String IMG_HOST_URL = "http://192.168.1.109:9101";//外网
    //    String IMG_HOST_URL = "http://192.168.1.195:9000";//内网

    /**
     * 系统用户id，用于在互相奖励时做判断
     */
    String SYSTEM_USER_ID = "22292";
//    String SYSTEM_USER_ID = "6778";// 测试环境

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
    String REGISTER_JSON_REQUEST_URL = "user_register_bymobile_v_1_2_3";
    //获取用户信息
    String GET_USERINFO_JSON_REQUEST_URL = "user_info_get_bytoken";
    //快捷登录获取验证码
    String LOGIN_GET_VALIDATA_CODE_JSON_REQUEST_URL = "sms_get_verify_bymobile";
    //快捷登录
    String QUICK_LOGIN_CODE_JSON_REQUEST_URL = "login_by_mobile_verifycode";
    //找回密码
    String FIND_PWD_JSON_REQUEST_URL = "user_find_password_bymobile";
    //获取红包列表数据
    String HB_POOL_JSON_REQUEST_URL = "hb_get_pool_list_v1_7";
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
    //修改用户隐私设置
    String USER_UPDATE_INFO_JSON_REQUEST_URL = "user_info_edit_byuser";
    //获取广告延时时间
    String GET_AD_DELAY_SECONDS_RATE_JSON_REQUEST_URL = "send_delay_seconds_to_rate";
    //创建广告
    String CREATE_AD_JSON_REQUEST_URL = NEW_URL + "hb_create_v1_2_9";
    //获取红包支付明细
    String GET_AD_AMOUNT_DETAIL = "hb_send_amount_bill_detail";
    //余额支付广告费用
    String AD_PAY_BY_BALANCE = "hb_user_zhifu_by_balance";
    //控制每天分享次数
    String HB_SHARE_LIMIT_JSON_REQUEST_URL = "user_share_limit_condition";
    //获取互动评论列表
    String INTERACTION_COMMENT_LIST = "interaction_comment_list";
    //互动平台评论
    String INTERACTION_COMMENT_LIST_COMMENT = "interaction_comment_add";
    //修改用户基本信息
    String USER_INFO_EDIT_JSON_REQUEST_URL = "user_info_edit_byuser";
    //获取用户公开信息
    String USER_PUBLIC_INFO_JSON_REQUEST_URL = "user_get_info_byuserid_cs_v_1_2_3";
    //用户向他人转账
    String USER_REWARD_PAY_JSON_REQUEST_URL = "user_trans_to_user";
    //拆红包
    String SHARE_OPEN_RED_JSON_REQUEST_URL = "hb_user_open_take_money_v1_1";
    //直接领钱
    String JUST_OPEN_RED_JSON_REQUEST_URL = "hb_user_open_take_money";
    //红包追加
    String RED_ENVELOPE_SUPERADDITION_JSON_REQUEST_URL = "hb_user_superaddition_new";
    //转发类分享host
    String REPEAT_HB_DETAIL_GET_HOST_JSON_REQUEST_URL = "hb_forward_by_user";
    //获取转发类套餐
    String CEATE_AD_GET_REPEAT_MEAL_JSON_REQUEST_URL = "get_hb_forwarding_packages";
    //红包记录列表删除
    String HB_RECORD_LIST_DETAIL_JSON_REQUEST_URL = "user_hongbao_delete";
    //获取春笋券信息
    String GET_CHUNSUN_COUPON_INFO = "get_ticket_info_for_seller";
    //使用春笋券
    String USE_CHUNSUN_COUPON = "use_chunsun_ticket";

    /**
     * 正式发布时需要修正-----市场渠道地址
     */
    String CHANNEL_PACKAGE_NAME = "test";

    /**
     * 推送通知的url
     */
    String SYSTEM_NOTICE_URL = Constants.HOST_URL
            + "/pages/notice/index.aspx?id=";

    /**
     * 传递打开WebView的URL
     */
    String INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL = "intent_bundle_key_common_web_view_url";

    /**
     * 传递打开WebView的title
     */
    String INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE = "intent_bundle_key_common_web_view_title";

    /**
     * 推送通知的内容
     */
    String INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_CONTENT = "intent_bundle_key_common_web_view_content";

    /**
     * 注册协议
     */
    String REGISTER_SERVICE_PROTOCOL = "http://www.chunsunkeji.com/registe/RegistrationAgreement.html";

    /**
     * 券类广告统计链接
     */
    String RED_AGREEMENT_STATISTICS_URL = "pages/ticket/dateCount.html?";

    /**
     * 新手指引WebView
     */
    String NOVICE_GUIDELINES_URL = "http://www.chunsunkeji.com/Help/Nav";

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
    String EXTRA_LIST_KEY = "extra_list_key";
    String EXTRA_KEY_LINES = "extra_key_lines";

    /**
     * EditInfoActivity显示类型
     */
    //举报
    int EDIT_TYPE_COMPLAINT = 0x1001;
    int EDIT_TYPE_CHUNSUN_ACCOUNT = 0x1002;
    int EDIT_TYPE_NICK_NAME = 0x1003;
    int EDIT_TYPE_PHONE = 0x1004;
    int EDIT_TYPE_TEL = 0x1005;
    int EDIT_TYPE_WECHAT = 0x1006;
    int EDIT_TYPE_QQ = 0x1007;
    int EDIT_TYPE_ALIPAY = 0x1008;
    int EDIT_TYPE_ID_CARD = 0x1009;
    int EDIT_TYPE_DESC = 0x1010;
    int EDIT_TYPE_SEX = 0x1011;
    int EDIT_TYPE_JOB = 0x1012;

    /**
     * 标示从发广告跳转到登录
     */
    String FROM_AD = "from_ad";

    /**
     * 标示从我跳转到登录
     */
    String FROM_ME = "from_me";

    /**
     * 标示从tab1的fragment跳转到登录
     */
    String FROM_TAB1 = "from_tab1";

    /**
     * 标示从tab3的fragment跳转到登录
     */
    String FROM_TAB3 = "from_tab3";

    /**
     * 标示登录页点击了返回
     */
    String FROM_LOGIN_BACK = "from_login_back";

    /**
     * token过期
     */
    String USER_INFO_PASS_FROM_ME = "user_info_pass_from_me";

    /**
     * 追加广告
     */
    String SUPERADDITION_AD = "superaddition_ad";

    /**
     * WebView的url
     */
    // 担保交易WebView
    String SEND_GUARANTEE_URL = "http://www.chunsunkeji.com/danbao/db.html";
    // 调用银联充值余额
    String BACK_RECHARGE_URL = HOST_URL + "/api/payment/unionpay/notify_url.aspx?order_no=";
    // 调用银联支付的url
    String BACK_PAY_URL = HOST_URL + "/api/payment/unionpay/index.aspx?order_no=";
    // 发广告的说明WebView
    String SEND_RED_INSTRUCTION_URL = "http://chunsunkeji.com/hbexplain/hbshuoming.html";
    // 转发类发广告的说明WebView
    String REPEAT_SEND_RED_INSTRUCTION_URL = "http://www.chunsunkeji.com/hbexplain/forwardshuoming.html";
    // 发广告的价格说明WebView
    String SEND_PRICE_EXPLAIN_URL = "http://chunsunkeji.com/help/price/dj.html";

    /**
     * 分享url
     */
    String SHARE_RED_ENVELOPE_URL = "pages/share/index.aspx?grab_id=";

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
    String RED_DETAIL_STATUS_YDJ = "ydj_count";

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
    //转发红包收入
    String BALANCE_TYPE_FORWARD = "forward_amount";

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
    int LISTENER_TYPE_GET_PROVINCE_AND_CITY = 0x2017;
    int LISTENER_TYPE_COMMIT_AD = 0x2018;
    int LISTENER_TYPE_GET_AD_AMOUNT_DETAIL = 0x2019;
    int LISTENER_TYPE_PAY_BY_BANLANCE = 0x2020;
    int LISTENER_TYPE_GET_RED_ENVELOPE_LIMIT = 0x2021;
    int LISTENER_TYPE_GET_INTERACTIVE_COUNTRY = 0x2022;
    int LISTENER_TYPE_GET_INTERACTIVE_LOCAL = 0x2023;
    int LISTENER_TYPE_COMLAINT_RED_ENVELOPE = 0x2024;
    int LISTENER_TYPE_EDIT_USER_INFO = 0x2025;
    int LISTENER_TYPE_GET_USER_AMOUNT = 0x2026;
    int LISTENER_TYPE_USER_REWARD_PAY = 0x2027;
    int LISTENER_TYPE_SHARE_OPEN_RED = 0x2028;
    int LISTENER_TYPE_JUST_OPEN_RED = 0x2029;
    int LISTENER_TYPE_RED_ENVELOPE_SUPERADDITION = 0x2030;
    int LISTENER_TYPE_REPEAT_GET_HOST = 0x2031;
    int LISTENER_TYPE_GET_CREATE_AD_DELAY_SECONDS = 0x2032;
    int LISTENER_TYPE_GET_REPEATE_MEAL = 0x2033;
    int LISTENER_TYPE_GET_SEND_RED_ENVELOPE_RECORD = 0x2034;
    int LISTENER_TYPE_DETAIL_SEND_RED_ENVELOPE_RECORD = 0x2035;
    int LISTENER_GET_CHUNSUN_COUPON_INFO = 0x2036;
    int LISTENER_USE_CHUNSUN_COUPON = 0x2037;
    int LISTENER_TYPE_GET_USER_INVITE_INFO = 0x2038;

    /**
     * 发广告默认数据
     */
    String AD_DEFAULT_PRICE = "0.03";
    String AD_DEFAULT_NUM = "2000";
    String AD_DEFAULT_DAYS = "1";

    /**
     * 发广告 选择列表类型
     */
    int AD_SELECT_LIST_TYPE = 1;
    int AD_SELECT_LIST_DISTANCE = 2;
    int AD_SELECT_LIST_PROVINCE = 3;
    int AD_SELECT_LIST_CITY = 4;

    /**
     * startActivityForResult
     */
    int REQUEST_CODE = 1;

    /**
     * 支付宝
     */
    // 商户PID
    String PARTNER = "2088911439210941";
    // 商户收款账号
    String SELLER = "576806999@qq.com";
    // 商户私钥，pkcs8格式
    String RSA_PRIVATE = "MIICXgIBAAKBgQC497m3M57yDN0wYDr+f1YFsuCMFLfJZvX6WPuke4TQf5SfjUFTI8CPMioKlXeymFzryp4Fk099+M8Z60P3DUIGcxy59a4RzeTkwOczll5HJFLt+LivApz5PpVij+x1MgTrtZyoUnVNL4Ol2NTPDo3WrVDYMLy4FZB3ttneVCq2xwIDAQABAoGBAI9ALDDaZcsIc3W30XiwnaqkMovKr9vnRbGxoJJupxni9PPsrh75nXRJYY343E8Q+UYUEfY3dGUXgS2Nq4F2Xx7WPcHo1k7J47aIX4MdnAzsXLx4mshfI9oCaYpYgq8mAE1OyRcFqvBwu2l4NDQpdRA3yqxwEmfBWa0YdpaWqQKBAkEA41+E/auMyOYKB3vrEfamF7RJunA1PjvGcV1Hlbk3IizO578HHIGxDqhz5CKQOSG6gMMv7h96549z6xIuSBUpNwJBANBBbswbqNQ9ftjcjEylOwrqqBcGwspuWMJthlu4f+3+TqQSQc9FeB4iI6yFowOd5LVyLYWpUxxxzPhJnoJFZvECQQC8+VWqE5uGGZM6VyavnmS7DM++UaYe3EV5UQK/ENoe4Ejy2ZUKf0vuF9mCQavGoB7HB/LdIXLf5B1+wXSP2m6PAkAPN8jV52uF3tyHEk66RxSyboVL8XWIf1nDE2fPCgNnK78pZCAk+kmVwh7jO3y3BfGxhJ9o9f+Zw4Mb3Z1UnaexAkEA4R+XMs7hw0J0jdtbOmcUG69EzqEH0R3sXx6CxLgGh35bDTSjwGIDW765BUrOChLwiZfcZdpoFG1BQ3rTDXWFlQ==";
    // 支付宝公钥
    String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    //异步支付结果获取接口
    String ZHIFU_NOTIFY_URL = "http://admin.chunsunkeji.com/api/payment/alipay/notify_url_mobile.aspx";


    /**
     * ImageLoader 缓存路径
     */
    String IMAGE_LOADER_CACHE_PATH = "/SimplifyReader/Images/";

    /**
     * sd卡路径
     */
    String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 互动平台 奖励
     */
    //全国
    String INTERACTIVE_PLATFORM_COUNTRY = "0";
    //地区
    String INTERACTIVE_PLATFORM_LOCAL = "1";

    /**
     * 标示从哪跳转到SharePopupWindow
     */
    int SHARE_FROM_WEB_RED = 0x9001;
    int SHARE_FROM_RED = 0x9002;

    /**
     * startActivityForResult
     */
    int REQUEST_CODE_IMAGE_CUT = 0x9001;
}
