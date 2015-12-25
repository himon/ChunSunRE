package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 * 红包列表数据
 */
public class RedListDetailEntity extends BaseEntity {


    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * is_allow_repeat : True
         * pre_load_seconds : 2
         * pool : [{"img_url":"","bank_name":"","begin_time":"2015/10/31 17:33:12","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"匿名","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://events.pingan.com.cn/h5/hfzqfb/index.html?WT.mc_id=sms_ZQFL_YZT20","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13623","title":"2元话费免费领 打开链接填写手机号码收取验证码  ","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"7","cover_img_url":"/upload/201510/28/201510281615176956.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 16:21:09","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"匿名","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"[S]买QQ靓号选号地址http://www.jihaoba.com/u/104406，客服QQ95524473诚信至上","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"4278","title":"易道QQ靓号，专业出售6.7.8.9位情侣，兄弟，连号QQ","area":"","receipt_title":"","poundage":"0","payable_amount":"3","comment_count":"3","cover_img_url":"/upload/201508/24/201508241811169200.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"/upload/201510/27/201510271503070468.jpeg,/upload/201510/27/201510271503071025.jpeg,/upload/201510/27/201510271503071650.jpeg,/upload/201510/27/201510271503072109.jpeg,/upload/201510/27/201510271503072333.jpeg","bank_name":"","begin_time":"2015/10/31 15:31:00","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"钢制办公家具","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"        经营各种钢制办公家具，保险柜。 中国领导品牌，值得信赖。 联系人赵先生  联系电话：13653876830  联系QQ：411785187","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13615","title":"经营各种钢制办公家具，有需要联系13653876830","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"9","cover_img_url":"/upload/201510/27/201510271503070019.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 15:22:25","is_v":"False","remark":"","is_danbao":"False","type":"2","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"听风者","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"企业","day_count":"0","left_day_count":"0","id":"13654","title":"推荐一下我","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"1","cover_img_url":"/upload/201510/29/201510291520559043.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 15:20:34","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13668","title":"测试缩略图地址","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/30/thumb_201510301519254404.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 15:12:08","is_v":"False","remark":"","is_danbao":"False","type":"2","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"小修","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"企业","day_count":"0","left_day_count":"0","id":"13653","title":"企业","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291511054285.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 14:36:56","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"恭喜发财","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13650","title":"Hdjdjd","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291435535317.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"/upload/201510/27/201510271431389046.jpeg,/upload/201510/27/201510271431389710.jpeg,/upload/201510/27/201510271431390413.jpeg,/upload/201510/27/201510271431391156.jpeg,/upload/201510/27/201510271431391790.jpeg","bank_name":"","begin_time":"2015/10/31 14:33:13","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"乐斯福-丹宝利","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"    燕牌酵母\u2014行业的标杆！  发面快，后劲足，适应性强！  全世界每三面包中就有一个是用乐斯福的酵母做成的。  与我们一起开启燕牌之旅  做面包的方法有上千种，  但酵母只有一种！  燕牌，原·本。    \n联系人：黄先生  \n电话：13343819967    \n微信：Astravel1989   \nqq:313724887  \n店面铺货，自己使用都可以，联系说明春笋红包，送货上门哦~~~~","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13614","title":"燕牌酵母\u2014\u2014行业的标杆！  发面快，后劲足，适应性强！","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"13","cover_img_url":"/upload/201509/23/201509232001355912.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"/upload/201510/27/201510271421546032.jpeg,/upload/201510/27/201510271421546745.jpeg,/upload/201510/27/201510271421547419.jpeg,/upload/201510/27/201510271421548044.jpeg,/upload/201510/27/201510271421548444.jpeg","bank_name":"","begin_time":"2015/10/31 14:23:45","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"克丽缇娜","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"        很多明星代言过很多护肤品，但是真正使用自己代言品牌的却没几个，陈聪，杨澜，吕燕，范冰冰，刘嘉玲，伊丽莎白等明星都爱用克丽缇娜，真正的好东西是用心体会出来的，而且公司没给任何代言费，张国立、邓捷，韩红、李娜、也投资开店（克丽缇娜），克丽缇娜是国家唯一获得的中国驰名商标，最受欢迎品牌，也是25周年放心品牌！你还在用日化线护肤产品吗?走进克缇，拥有美丽健康人生！你不仅可以终身免费用，还可以为你赚大钱的大牌美容护肤品[玫瑰]而且是女神范冰冰等明星们都在用的美容护肤品牌－－克丽缇娜（美容业唯一的中国驰名商标，台湾上市公司，营业额全球排名第9 全国招商创富热线：18303683997","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13613","title":"女人敢爱   去爱用你最美的姿态\r\n克丽缇娜  美丽最任性","area":"","receipt_title":"","poundage":"0","payable_amount":"50","comment_count":"10","cover_img_url":"/upload/201510/27/201510271421545407.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 14:06:25","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13647","title":"哈哈哈","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291405220999.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 14:05:31","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"听风者","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13666","title":"封面缩略图","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/30/thumb_201510301404286712.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 14:03:01","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13646","title":"测试生活类","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291401496748.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 13:49:56","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13664","title":"缩略图","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/30/thumb_201510301348529567.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"/upload/201510/30/201510301140203316.jpeg","bank_name":"","begin_time":"2015/10/31 11:45:45","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13662","title":"123321","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/30/201510301140184215.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 11:42:40","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://www.baidu.com","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13641","title":"投放链接类广告","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291141366086.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 11:32:20","is_v":"False","remark":"","is_danbao":"False","type":"2","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"孙涛","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"企业","day_count":"0","left_day_count":"0","id":"13640","title":"测试广告发送","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"/upload/201510/29/201510291131166031.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 11:28:53","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"小修","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13628","title":"生活","area":"","receipt_title":"","poundage":"0","payable_amount":"6","comment_count":"0","cover_img_url":"/upload/201510/29/201510291038313131.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/31 10:57:01","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"听风者","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13658","title":"缩略图，封面","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"0","cover_img_url":"upload/201510/30/thumb_201510301055567452.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 10:41:08","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"听风者","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://mp.weixin.qq.com/s?__biz=MzAwODYzMjQ2OA==&mid=400046564&idx=1&sn=7a07ef64c8949f972d51adf43a3d63a0&scene=0#rd","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13556","title":"收徒也可以赚钱，转发任务，5层收益🉐️，嗨起来！","area":"","receipt_title":"","poundage":"0","payable_amount":"50","comment_count":"18","cover_img_url":"/upload/201510/23/201510231037304077.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"/upload/201510/26/201510261035217444.jpeg","bank_name":"","begin_time":"2015/10/28 10:39:50","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"灵犀","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"如今的时代，网上赚钱以普遍，可为什么有的人几万的赚，成为富翁有了自己的事业，而有的人没赚到钱还一次一次的被骗，是我们需要强大的团队和独特的眼光，2015赚钱是虚拟币和互助平台的时代，在这里我为大家推荐两款稳定的互助平台，希望有梦想有远见的朋友加入。\n第一款 [图片]\nAC一个国际航母级集团平台内排啦！！内排啦！！\n\n资金集中在拆分盘怕平台怕跑路？\n还在参与纯粹的互助盘拿静态利息？\n你已经OUT啦！！\n\n美国AC集团---八月资平台本10月1号火爆启动正式上线匹配！\n现正开展火爆发展、招商加盟！速度抢占市场!\n\n****复合双轨、互助、拆分相结合模式，夺尽两者优势，256位SSL银行级别加密保护！！国外尊享独立服务器！！\n免费注册排队就拿利息，不需要先投资就已经开始盈利的理财方式！！需要一定的智慧与时间去了解的理财方式，想要赚钱又拥有智慧的你准备好了吗？\n\n●●公司视频： http://dwz.cn/23ReVe\n●●制度详解： http://dwz.cn/23RfUO\n●●中国大陆备用网http://cn.acmutualaid.com/\n\n注册链接地址：\n手机左区推广链接 http://cn.acmutualaid.com/Home/Login/reg/id/65631/wz/0/\n电脑左区推广链接: http://www.acmutualaid.com/Home/Login/reg/id/65631/wz/0/\n\n\n温馨提醒一 注册一定要找到推荐老师 要不然后续非常麻烦 专业带团队 欢迎各路高手对接《战狼团队》欢迎你加入携手共进，共创辉煌--6\n--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P\n温馨提示 二 注册成功以后一定要点击免费参与分红选择一个套餐激活，不然没有分红的。详情联系 QQ2821381054\n第二款. 网赚最强消息：\n\n8月19开盘，10月7号华人尊享MMM升级服务器成功！之前有人说跑路的现在可以闭嘴了！互助盘是所有盘子里面最稳定的！全国前3强互助模式华人尊享再次腾飞，本次更换可容纳200万会员高级服务器重磅来袭！！ 你还想失去赚钱的机会吗？ **投资60-5000，排队12天打款，15天解冻，钱离开手三天就能回本+15%的收益！每月可以排6单，一月收益90%，你还在犹豫什么，快加入我们的华人尊享MMM团队吧！财富链接： http://www.chinavip-mmm.com/reg-a6597317123.html\n\n注册好找我QQ:2821381054记得联系我哦，快来开启您的财富之路啊！等你的参与","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13595","title":"互联网的时代，赚钱选对了吗","area":"","receipt_title":"","poundage":"0","payable_amount":"60","comment_count":"22","cover_img_url":"/upload/201510/26/201510261035217024.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 10:05:52","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"萧圣","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"【WenXin263会员系统】平台，正规平台，主要做投票任务，单价0.25元以上一个任务，网速快的两分钟就可搞定一个任务，工资日结，每晚20:00结算工资（大了|元以上才能结算），多劳多得，有充足的单子，就怕你懒，一般一天能赚几十元，高手就上百。\n       1.注册时资料一定要真实有效，假资料一律封号，核对无误后才注册，这个一定要认真填写，或者看一下新手帮助，记住（用户名和密码，这个登陆需要）\n       2.接任务点《进行中的任务》，先看剩余量多的才做，量少的尽力而为，然后点《开始任务》，一定先看要求和样图，做好了任务的截图一定要真实，点《我的任务》查看你刚才做的任务，在点《继续任务》，对不上要求（投票成功图）或样图的，不要上传，不要提交，传错图提交会被投拆，容易封号。注:要下载软件和管理的单子不要接，会被查封的。\n      3.你有单可以在平台联系管理放单，这个平台有介绍哦，自己看一下就好\n       4.愿意做的就注册 http://www.weixin263.com/index/regist/primarykey/46615.html 不会的加我微信13268962357,好教你，以免做错。注册好了，复制这网址在微信中打开，点【WinXin263会员系统】登陆即可，把 个人中心收藏，方便下次进入（进入平台后，点右上角，菜单有个收藏的，点一下收藏，在微信点一下《我的》，在点《收藏》，就可以找到你收藏的文件了），进到平台一定要看一下平台介绍，公告，以免任务时出错，到时无法挽救，谢谢大家的支持。\n     5.本平台不靠任何人脉，自己做，也不用提现，每晚20:00清零结算工资（大于1元以上），自动到账。有不会做的加Q群132440680下载视频教程（本人亲自录制的），你推广时写上这个群号，方便教你的徒弟，也省时省力，在不会加我微信13268962357.谢谢。","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"11401","title":"微信最火爆网赚平台，日赚过百","area":"","receipt_title":"","poundage":"0","payable_amount":"200","comment_count":"258","cover_img_url":"/upload/201509/22/201509221709240718.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 9:27:54","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"佚名","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://www.sharewowo.com:8080//index.php?group=Wap&action=Wootheme&method=wooTheme&topicId=2354&adId=134&money=share&mids=101572&type=share_topic&invitation_code=102559&share_to=WechatMoments&from=timeline&isappinstalled=1&nsukey=9VUTc0uylRPZcKrLR1fKZvtdSa9psRvi19sonkgxcg23%2BnwnXuzPCAC60b5hVM52iAuGrAoInzBaaCdy5cxQ5A%3D%3D，http://wxsy1016010.mtxlj.cn/?520da&__t=WWWP41","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13620","title":"有钱任性，没钱认命","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"6","cover_img_url":"/upload/201510/27/201510272214135801.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 9:27:54","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"几个意思啊","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://www.sharewowo.com:8080//index.php?group=Wap&action=Wootheme&method=wooTheme&topicId=2355&adId=135&money=share&mids=100086&type=share_topic&invitation_code=101078&share_to=WechatMoments&from=timeline&isappinstalled=1， http://www.chuanbolian.net/main/renwuview.php?newsid=500","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13619","title":"满满的都是爱啊！","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"3","cover_img_url":"/upload/201510/27/201510272159087381.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 9:27:53","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"135246","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://m.ymesd.com/index.php?s=/Article/Content/index/content_id/639/uid/15112/time/1445860417/fxapi/1.html&from=timeline&isappinstalled=0","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13601","title":"让孩子变机灵，好玩又益智的筷子游戏！","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"4","cover_img_url":"/upload/201510/26/201510262255110593.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/28 9:19:00","is_v":"False","remark":"","is_danbao":"False","type":"4","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"匿名","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"http://m2.nnwhy.com/page/420?home=y&key=ojx9V","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"链接","day_count":"0","left_day_count":"0","id":"13597","title":"微商城数千款产品等你来代理，躺着都能收钱 ","area":"","receipt_title":"","poundage":"0","payable_amount":"45","comment_count":"8","cover_img_url":"/upload/201510/26/201510262206233431.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/21 23:02:54","is_v":"False","remark":"","is_danbao":"False","type":"2","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"萧圣","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"每获\n1.看广告** 0.05元佣金（可提现）。推荐好友6级30%提成！\n2.商家进驻，可拿消费额的1%提成！收益远超下载及返利网。\n3.零碎时间看广告，无需下载任务，无需消费，不伤手机。\n4.广告每日多批次更新，注册了可能看不到几个广告，一定要加群了解。\n5.商家告别纸质传单，多远用户能看到广告您说了算。单个成本仅0.06。\n注册下载： http://a.app.qq.com/o/simple.jsp?pkgname=com.meihuo.user.phone\n邀请码：13268962357.\n玩法:在首页下拉刷新广告，有几条点几条，不管之前有木有点过，每条都点一下，只要广告里面出现《想要，一般，不想要》，点【想要】就0k了，广告多的话上拉加载，一般新广告都在下面，一般10:00--13:00,晚21:00--1:00这段时间更新比较多的广告，其它时间自己也多上线更新一下，有就点。照方法做就行\n    多加好友， 推广时把玩法写上，并叮嘱下线照方案去做，去推广，下线会玩了，收益自然会提高，下线越多赚的越多。\n       有不懂的加我微信xiaosheng27513.加Q群132440680。在群里相互学习，有新广告相互通知。你现在有十几亿人的市场，只要坚持做下去，你才是老板。哪个项目都要推广，只要坚持做，抢到市场，还有什么不成功的吗，努力一年比打工十年强，这个是最简单的，容易推广，没有哪个项目有这么简单的了。\n          获友把整条广告复制下来，方便使用，把链接，邀请码，联系方式，改成你自己的就可以用了。3Q。照方案去推广，你努力了，自然会有回报。给自己一个奋斗的目标。要向成功的目标奋进，找方法。不为失败找借口，只为成功找方法。简单的事情重复做，重复的事情坚持做，我都敢在这投放，你怕什么。","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"企业","day_count":"0","left_day_count":"0","id":"9982","title":"每获点广告赚米","area":"","receipt_title":"","poundage":"0","payable_amount":"300","comment_count":"340","cover_img_url":"/upload/201509/12/201509122254086709.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/21 15:12:45","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"冯军","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"我早说了，惠众是大平台！很多平台都是惠众的代理！涨钱真的飞！\n惠众分享-转发赚钱，我提现3次都是当天到账！\nhttp://dhtaoci.cn/reg.php?uid=6057","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13484","title":"我又来推广这个，提现快！","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"4","cover_img_url":"/upload/201510/21/201510211509405527.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/21 9:09:39","is_v":"False","remark":"","is_danbao":"False","type":"2","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"hfff","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"0 投资 手把手带你赚钱\r\n制定计划，一个月破万\r\n真心想赚钱的就来\r\n好项目不多做介绍，计划简单，跟得上做30天必破万\r\n规则：\r\n1、不花一分钱\r\n2、会玩微信\r\n3、想赚钱\r\n有兴趣的扫描二维码或者加微信 lked888\r\n","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"企业","day_count":"0","left_day_count":"0","id":"13297","title":"一个月破万","area":"","receipt_title":"","poundage":"0","payable_amount":"100","comment_count":"28","cover_img_url":"/upload/201510/17/201510170908097427.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/20 16:47:30","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"冯军","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"惠锁屏！下载注册并登录，在春笋评论里回复注册手机前三位和后三位（如图）。你将得到我的0.5元春笋红包！上不封顶！\nhttp://www.huisuoping.com/share2.jsp?user_origin=990457649\n邀请码别填错:990457649","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13449","title":"下载注册登录，0.5元！","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"8","cover_img_url":"/upload/201510/20/201510201646130747.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""},{"img_url":"","bank_name":"","begin_time":"2015/10/20 16:37:52","is_v":"False","remark":"","is_danbao":"False","type":"1","is_need_receipt":"","payment_time":"0001/1/1 0:00:00","order_no":"","city":"","nick_name":"冯军","time":"","send_time":"","province":"","RangeString":"0km","longitude":"","points":"","receipt_fee":"0","total_amount":"0","trade_no":"","everyday_count":"0","real_amount":"0","hb_status":"True","status":"0","add_user_id":"0","frozen_time":"0001/1/1 0:00:00","day_left_count":"0","tax_no":"","content":"菜鸟赚--唯一经过腾讯认证的转发平台，注册送1元，一个点击0.08元！5级粉丝提成哦，涨钱刷刷的！10元即可提现！还等啥？快来体验正真的涨钱飞是啥赶脚！\nhttp://cnz.zhentouzhen.com/index.php/login/reg/uid/880.html\n别一有新平台就去，请选好此平台，一直做赚下去！","receipt_express_fee":"0","price":"0","end_time":"","is_top":"False","bank_no":"","total_left_count":"0","latitude":"","is_good":"False","grab_status":"False","range":"","enable_share":"False","TypeName":"生活","day_count":"0","left_day_count":"0","id":"13448","title":"新平台！！！值得广告","area":"","receipt_title":"","poundage":"0","payable_amount":"10","comment_count":"7","cover_img_url":"/upload/201510/20/201510201636039490.jpeg","delay_seconds":"0","report_times":"0","delay_amount":"0","day_send_count":"0","is_receipt_dealed":"False","start_time":"","add_time":"0001/1/1 0:00:00","payment_type":""}]
         * can_open_linknum : 3
         * total_count : 186
         */
        private String is_allow_repeat;
        private String pre_load_seconds;
        private List<PoolEntity> pool;
        private String can_open_linknum;
        private String total_count;

        public void setIs_allow_repeat(String is_allow_repeat) {
            this.is_allow_repeat = is_allow_repeat;
        }

        public void setPre_load_seconds(String pre_load_seconds) {
            this.pre_load_seconds = pre_load_seconds;
        }

        public void setPool(List<PoolEntity> pool) {
            this.pool = pool;
        }

        public void setCan_open_linknum(String can_open_linknum) {
            this.can_open_linknum = can_open_linknum;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public String getIs_allow_repeat() {
            return is_allow_repeat;
        }

        public String getPre_load_seconds() {
            return pre_load_seconds;
        }

        public List<PoolEntity> getPool() {
            return pool;
        }

        public String getCan_open_linknum() {
            return can_open_linknum;
        }

        public String getTotal_count() {
            return total_count;
        }

        public static class PoolEntity {
            /**
             * img_url :
             * bank_name :
             * begin_time : 2015/10/31 17:33:12
             * is_v : False
             * remark :
             * is_danbao : False
             * type : 4
             * is_need_receipt :
             * payment_time : 0001/1/1 0:00:00
             * order_no :
             * city :
             * nick_name : 匿名
             * time :
             * send_time :
             * province :
             * RangeString : 0km
             * longitude :
             * points :
             * receipt_fee : 0
             * total_amount : 0
             * trade_no :
             * everyday_count : 0
             * real_amount : 0
             * hb_status : True
             * status : 0
             * add_user_id : 0
             * frozen_time : 0001/1/1 0:00:00
             * day_left_count : 0
             * tax_no :
             * content : http://events.pingan.com.cn/h5/hfzqfb/index.html?WT.mc_id=sms_ZQFL_YZT20
             * receipt_express_fee : 0
             * price : 0
             * end_time :
             * is_top : False
             * bank_no :
             * total_left_count : 0
             * latitude :
             * is_good : False
             * grab_status : False
             * range :
             * enable_share : False
             * TypeName : 链接
             * day_count : 0
             * left_day_count : 0
             * id : 13623
             * title : 2元话费免费领 打开链接填写手机号码收取验证码
             * area :
             * receipt_title :
             * poundage : 0
             * payable_amount : 10
             * comment_count : 7
             * cover_img_url : /upload/201510/28/201510281615176956.jpeg
             * delay_seconds : 0
             * report_times : 0
             * delay_amount : 0
             * day_send_count : 0
             * is_receipt_dealed : False
             * start_time :
             * add_time : 0001/1/1 0:00:00
             * payment_type :
             */
            private String img_url;
            private String bank_name;
            private String begin_time;
            private String is_v;
            private String remark;
            private boolean is_danbao;
            private int type;
            private String is_need_receipt;
            private String payment_time;
            private String order_no;
            private String city;
            private String nick_name;
            private String time;
            private String send_time;
            private String province;
            /**
             * 附近类红包距离
             */
            private String RangeString;
            private String longitude;
            private String points;
            private String receipt_fee;
            private String total_amount;
            private String trade_no;
            private String everyday_count;
            private String real_amount;
            private boolean hb_status;
            private String status;
            private String add_user_id;
            private String frozen_time;
            private String day_left_count;
            private String tax_no;
            private String content;
            private String receipt_express_fee;
            private String price;
            private String end_time;
            private boolean is_top;
            private String bank_no;
            private String total_left_count;
            private String latitude;
            private boolean is_good;
            private boolean grab_status;
            private String range;
            private boolean enable_share;
            /**
             * 红包类型名称
             */
            private String TypeName;
            private String day_count;
            private String left_day_count;
            private String id;
            private String title;
            private String area;
            private String receipt_title;
            private String poundage;
            private String payable_amount;
            private String comment_count;
            private String cover_img_url;
            private String delay_seconds;
            private String report_times;
            private String delay_amount;
            private String day_send_count;
            private boolean is_receipt_dealed;
            private String start_time;
            private String add_time;
            private String payment_type;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(String begin_time) {
                this.begin_time = begin_time;
            }

            public String getIs_v() {
                return is_v;
            }

            public void setIs_v(String is_v) {
                this.is_v = is_v;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public boolean is_danbao() {
                return is_danbao;
            }

            public void setIs_danbao(boolean is_danbao) {
                this.is_danbao = is_danbao;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getIs_need_receipt() {
                return is_need_receipt;
            }

            public void setIs_need_receipt(String is_need_receipt) {
                this.is_need_receipt = is_need_receipt;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRangeString() {
                return RangeString;
            }

            public void setRangeString(String rangeString) {
                RangeString = rangeString;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getReceipt_fee() {
                return receipt_fee;
            }

            public void setReceipt_fee(String receipt_fee) {
                this.receipt_fee = receipt_fee;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getTrade_no() {
                return trade_no;
            }

            public void setTrade_no(String trade_no) {
                this.trade_no = trade_no;
            }

            public String getEveryday_count() {
                return everyday_count;
            }

            public void setEveryday_count(String everyday_count) {
                this.everyday_count = everyday_count;
            }

            public String getReal_amount() {
                return real_amount;
            }

            public void setReal_amount(String real_amount) {
                this.real_amount = real_amount;
            }

            public boolean isHb_status() {
                return hb_status;
            }

            public void setHb_status(boolean hb_status) {
                this.hb_status = hb_status;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAdd_user_id() {
                return add_user_id;
            }

            public void setAdd_user_id(String add_user_id) {
                this.add_user_id = add_user_id;
            }

            public String getFrozen_time() {
                return frozen_time;
            }

            public void setFrozen_time(String frozen_time) {
                this.frozen_time = frozen_time;
            }

            public String getDay_left_count() {
                return day_left_count;
            }

            public void setDay_left_count(String day_left_count) {
                this.day_left_count = day_left_count;
            }

            public String getTax_no() {
                return tax_no;
            }

            public void setTax_no(String tax_no) {
                this.tax_no = tax_no;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReceipt_express_fee() {
                return receipt_express_fee;
            }

            public void setReceipt_express_fee(String receipt_express_fee) {
                this.receipt_express_fee = receipt_express_fee;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public boolean is_top() {
                return is_top;
            }

            public void setIs_top(boolean is_top) {
                this.is_top = is_top;
            }

            public String getBank_no() {
                return bank_no;
            }

            public void setBank_no(String bank_no) {
                this.bank_no = bank_no;
            }

            public String getTotal_left_count() {
                return total_left_count;
            }

            public void setTotal_left_count(String total_left_count) {
                this.total_left_count = total_left_count;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public boolean is_good() {
                return is_good;
            }

            public void setIs_good(boolean is_good) {
                this.is_good = is_good;
            }

            public boolean isGrab_status() {
                return grab_status;
            }

            public void setGrab_status(boolean grab_status) {
                this.grab_status = grab_status;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public boolean isEnable_share() {
                return enable_share;
            }

            public void setEnable_share(boolean enable_share) {
                this.enable_share = enable_share;
            }

            public String getTypeName() {
                return TypeName;
            }

            public void setTypeName(String typeName) {
                TypeName = typeName;
            }

            public String getDay_count() {
                return day_count;
            }

            public void setDay_count(String day_count) {
                this.day_count = day_count;
            }

            public String getLeft_day_count() {
                return left_day_count;
            }

            public void setLeft_day_count(String left_day_count) {
                this.left_day_count = left_day_count;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getReceipt_title() {
                return receipt_title;
            }

            public void setReceipt_title(String receipt_title) {
                this.receipt_title = receipt_title;
            }

            public String getPoundage() {
                return poundage;
            }

            public void setPoundage(String poundage) {
                this.poundage = poundage;
            }

            public String getPayable_amount() {
                return payable_amount;
            }

            public void setPayable_amount(String payable_amount) {
                this.payable_amount = payable_amount;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getCover_img_url() {
                return Constants.IMG_HOST_URL + cover_img_url;
            }

            public void setCover_img_url(String cover_img_url) {
                this.cover_img_url = cover_img_url;
            }

            public String getDelay_seconds() {
                return delay_seconds;
            }

            public void setDelay_seconds(String delay_seconds) {
                this.delay_seconds = delay_seconds;
            }

            public String getReport_times() {
                return report_times;
            }

            public void setReport_times(String report_times) {
                this.report_times = report_times;
            }

            public String getDelay_amount() {
                return delay_amount;
            }

            public void setDelay_amount(String delay_amount) {
                this.delay_amount = delay_amount;
            }

            public String getDay_send_count() {
                return day_send_count;
            }

            public void setDay_send_count(String day_send_count) {
                this.day_send_count = day_send_count;
            }

            public boolean is_receipt_dealed() {
                return is_receipt_dealed;
            }

            public void setIs_receipt_dealed(boolean is_receipt_dealed) {
                this.is_receipt_dealed = is_receipt_dealed;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(String payment_type) {
                this.payment_type = payment_type;
            }
        }
    }
}
