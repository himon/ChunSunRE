package com.chunsun.redenvelope.model.entity.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/8/11.
 */
public class RedDetailEntity extends BaseEntity {


    /**
     * result : {"detail":{"img_url":"","remark":"","is_v":"False","is_open":"False","is_danbao":"False","favorite_status":"False","type":"1","telphone":"","nick_name":"推广达人","send_time":"2015/9/8 12:43:35","has_open_count":"1742","age":"17","type_title":"生活","current_u_amount":"0.28","hb_type":"1","total_amount":"33.48","u_img_url":"/upload/201509/08/201509081455371752.jpeg","status":"3","hb_status":"True","job":"文化/广告/传媒","content":"【惠信宝】可以获得自己的链接啦！  惠信宝9月9日10亿活动开启，为了避免服务器压力过大而注册不上，大家可以先通过惠卡获取自己的链接  注册惠卡，成为惠粉，账号与惠信宝通用  注册地址： http://api.huikamall.com/Register/potal/MTY3NTU4?14416861756796221                           注册后随时咨询本人qq(1130361364)","invitation_code":"182946688","price":"0.01","pre_load_seconds":"6","hb_link_num":"0","mobile":"18759756341","sex":"男","range":"全国","grab_status":"True","has_send_amount":"266.96","is_repeat":"False","enable_share":"True","id":"8933","current_u_id":"615","title":"九月九号【信惠宝】砸10亿推广！","weixin":"","can_open_linknum":"8","user_id":"18294","status_title":"审核通过","must_share":"False","qq":"1130361364","share_host":"http://ad.chunsunkeji.com/","cover_img_url":"/upload/201509/08/201509081242147891.jpeg","is_allow_repeat":"True","delay_seconds":"8","can_trans":"True","hg_id":"3351647","total_count":"3100","private_json":"{\"nick_name\":\"true\",\"weixin\":\"false\",\"has_send_amount\":\"true\",\"telphone\":\"true\",\"mobile\":\"false\",\"qq\":\"true\",\"sex\":\"true\",\"birthday\":\"true\",\"job\":\"true\"}","enable_reward":"True"}}
     */
    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * detail : {"img_url":"","remark":"","is_v":"False","is_open":"False","is_danbao":"False","favorite_status":"False","type":"1","telphone":"","nick_name":"推广达人","send_time":"2015/9/8 12:43:35","has_open_count":"1742","age":"17","type_title":"生活","current_u_amount":"0.28","hb_type":"1","total_amount":"33.48","u_img_url":"/upload/201509/08/201509081455371752.jpeg","status":"3","hb_status":"True","job":"文化/广告/传媒","content":"【惠信宝】可以获得自己的链接啦！  惠信宝9月9日10亿活动开启，为了避免服务器压力过大而注册不上，大家可以先通过惠卡获取自己的链接  注册惠卡，成为惠粉，账号与惠信宝通用  注册地址： http://api.huikamall.com/Register/potal/MTY3NTU4?14416861756796221                           注册后随时咨询本人qq(1130361364)","invitation_code":"182946688","price":"0.01","pre_load_seconds":"6","hb_link_num":"0","mobile":"18759756341","sex":"男","range":"全国","grab_status":"True","has_send_amount":"266.96","is_repeat":"False","enable_share":"True","id":"8933","current_u_id":"615","title":"九月九号【信惠宝】砸10亿推广！","weixin":"","can_open_linknum":"8","user_id":"18294","status_title":"审核通过","must_share":"False","qq":"1130361364","share_host":"http://ad.chunsunkeji.com/","cover_img_url":"/upload/201509/08/201509081242147891.jpeg","is_allow_repeat":"True","delay_seconds":"8","can_trans":"True","hg_id":"3351647","total_count":"3100","private_json":"{\"nick_name\":\"true\",\"weixin\":\"false\",\"has_send_amount\":\"true\",\"telphone\":\"true\",\"mobile\":\"false\",\"qq\":\"true\",\"sex\":\"true\",\"birthday\":\"true\",\"job\":\"true\"}","enable_reward":"True"}
         */
        private DetailEntity detail;

        public void setDetail(DetailEntity detail) {
            this.detail = detail;
        }

        public DetailEntity getDetail() {
            return detail;
        }

        public static class DetailEntity implements Parcelable {
            /**
             * img_url :
             * remark :
             * is_v : False
             * is_open : False
             * is_danbao : False
             * favorite_status : False
             * type : 1
             * telphone :
             * nick_name : 推广达人
             * send_time : 2015/9/8 12:43:35
             * has_open_count : 1742
             * age : 17
             * type_title : 生活
             * current_u_amount : 0.28
             * hb_type : 1
             * total_amount : 33.48
             * u_img_url : /upload/201509/08/201509081455371752.jpeg
             * status : 3
             * hb_status : True
             * job : 文化/广告/传媒
             * content : 【惠信宝】可以获得自己的链接啦！  惠信宝9月9日10亿活动开启，为了避免服务器压力过大而注册不上，大家可以先通过惠卡获取自己的链接  注册惠卡，成为惠粉，账号与惠信宝通用  注册地址： http://api.huikamall.com/Register/potal/MTY3NTU4?14416861756796221                           注册后随时咨询本人qq(1130361364)
             * invitation_code : 182946688
             * price : 0.01
             * pre_load_seconds : 6
             * hb_link_num : 0
             * mobile : 18759756341
             * sex : 男
             * range : 全国
             * grab_status : True
             * has_send_amount : 266.96
             * is_repeat : False
             * enable_share : True
             * id : 8933
             * current_u_id : 615
             * title : 九月九号【信惠宝】砸10亿推广！
             * weixin :
             * can_open_linknum : 8
             * user_id : 18294
             * status_title : 审核通过
             * must_share : False
             * qq : 1130361364
             * share_host : http://ad.chunsunkeji.com/
             * cover_img_url : /upload/201509/08/201509081242147891.jpeg
             * is_allow_repeat : True
             * delay_seconds : 8
             * can_trans : True
             * hg_id : 3351647
             * total_count : 3100
             * private_json : {"nick_name":"true","weixin":"false","has_send_amount":"true","telphone":"true","mobile":"false","qq":"true","sex":"true","birthday":"true","job":"true"}
             * enable_reward : True
             */
            private String img_url;
            private String remark;
            private boolean is_v;
            /**
             * 是否领取红包 true 领取，false 未领取
             */
            private boolean is_open;
            /**
             * 是否显示担保
             */
            private boolean is_danbao;
            /**
             * 该红包是否被收藏
             */
            private boolean favorite_status;
            /**
             * 账号类型
             */
            private String type;
            private String telphone;
            private String nick_name;
            private String send_time;
            private String has_open_count;
            private String age;
            private String type_title;
            private String current_u_amount;
            /**
             * 红包类型
             */
            private String hb_type;
            private String total_amount;
            private String u_img_url;
            private String status;
            /**
             * 红包是否领完 true 未领完， false 领完
             */
            private boolean hb_status;
            private String job;
            private String content;
            private String invitation_code;
            /**
             * 需要转发的最低金额
             */
            private String price;
            /**
             * 前置加载时间（秒）
             */
            private int pre_load_seconds;
            /**
             * 链接类广告的链接数
             */
            private int hb_link_num;
            private String mobile;
            private String sex;
            private String range;
            /**
             * 是否抢过红包 true 抢过，false 未抢过
             */
            private boolean grab_status;
            private String has_send_amount;
            /**
             * 链接是否是同一平台链接
             */
            private boolean is_repeat;
            /**
             * 该红包是否可以分享
             */
            private boolean enable_share;
            private String id;
            private String current_u_id;
            private String title;
            private String weixin;
            /**
             * 可以打开的链接数
             */
            private int can_open_linknum;
            private String user_id;
            private String status_title;
            /**
             * 是否必须分享
             */
            private boolean must_share;
            private String qq;
            /**
             * 分享时的域名
             */
            private String share_host;
            private String cover_img_url;
            /**
             * 是否允许同平台的链接打开
             */
            private boolean is_allow_repeat;
            private int delay_seconds;
            private String can_trans;
            /**
             * 抢到的红包的记录ID，为了拆红包时候使用
             */
            private String hg_id;
            private String total_count;
            private String private_json;
            /**
             * 是否开启互相奖励
             */
            private boolean enable_reward;

            private String total_left_count;
            /**
             * 券类开始时间
             */
            private String start_time;
            /**
             * 券类结束时间
             */
            private String end_time;


            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public boolean is_v() {
                return is_v;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public boolean is_open() {
                return is_open;
            }

            public void setIs_open(boolean is_open) {
                this.is_open = is_open;
            }

            public boolean is_danbao() {
                return is_danbao;
            }

            public void setIs_danbao(boolean is_danbao) {
                this.is_danbao = is_danbao;
            }

            public boolean isFavorite_status() {
                return favorite_status;
            }

            public void setFavorite_status(boolean favorite_status) {
                this.favorite_status = favorite_status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getHas_open_count() {
                return has_open_count;
            }

            public void setHas_open_count(String has_open_count) {
                this.has_open_count = has_open_count;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getType_title() {
                return type_title;
            }

            public void setType_title(String type_title) {
                this.type_title = type_title;
            }

            public String getCurrent_u_amount() {
                return current_u_amount;
            }

            public void setCurrent_u_amount(String current_u_amount) {
                this.current_u_amount = current_u_amount;
            }

            public String getHb_type() {
                return hb_type;
            }

            public void setHb_type(String hb_type) {
                this.hb_type = hb_type;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getU_img_url() {
                return u_img_url;
            }

            public void setU_img_url(String u_img_url) {
                this.u_img_url = u_img_url;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public boolean isHb_status() {
                return hb_status;
            }

            public void setHb_status(boolean hb_status) {
                this.hb_status = hb_status;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getInvitation_code() {
                return invitation_code;
            }

            public void setInvitation_code(String invitation_code) {
                this.invitation_code = invitation_code;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getPre_load_seconds() {
                return pre_load_seconds;
            }

            public void setPre_load_seconds(int pre_load_seconds) {
                this.pre_load_seconds = pre_load_seconds;
            }

            public int getHb_link_num() {
                return hb_link_num;
            }

            public void setHb_link_num(int hb_link_num) {
                this.hb_link_num = hb_link_num;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public boolean isGrab_status() {
                return grab_status;
            }

            public void setGrab_status(boolean grab_status) {
                this.grab_status = grab_status;
            }

            public String getHas_send_amount() {
                return has_send_amount;
            }

            public void setHas_send_amount(String has_send_amount) {
                this.has_send_amount = has_send_amount;
            }

            public boolean is_repeat() {
                return is_repeat;
            }

            public void setIs_repeat(boolean is_repeat) {
                this.is_repeat = is_repeat;
            }

            public boolean isEnable_share() {
                return enable_share;
            }

            public void setEnable_share(boolean enable_share) {
                this.enable_share = enable_share;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCurrent_u_id() {
                return current_u_id;
            }

            public void setCurrent_u_id(String current_u_id) {
                this.current_u_id = current_u_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public int getCan_open_linknum() {
                return can_open_linknum;
            }

            public void setCan_open_linknum(int can_open_linknum) {
                this.can_open_linknum = can_open_linknum;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getStatus_title() {
                return status_title;
            }

            public void setStatus_title(String status_title) {
                this.status_title = status_title;
            }

            public boolean isMust_share() {
                return must_share;
            }

            public void setMust_share(boolean must_share) {
                this.must_share = must_share;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getShare_host() {
                return share_host;
            }

            public void setShare_host(String share_host) {
                this.share_host = share_host;
            }

            public String getCover_img_url() {
                return cover_img_url;
            }

            public void setCover_img_url(String cover_img_url) {
                this.cover_img_url = cover_img_url;
            }

            public boolean is_allow_repeat() {
                return is_allow_repeat;
            }

            public void setIs_allow_repeat(boolean is_allow_repeat) {
                this.is_allow_repeat = is_allow_repeat;
            }

            public int getDelay_seconds() {
                return delay_seconds;
            }

            public void setDelay_seconds(int delay_seconds) {
                this.delay_seconds = delay_seconds;
            }

            public String getCan_trans() {
                return can_trans;
            }

            public void setCan_trans(String can_trans) {
                this.can_trans = can_trans;
            }

            public String getHg_id() {
                return hg_id;
            }

            public void setHg_id(String hg_id) {
                this.hg_id = hg_id;
            }

            public String getTotal_count() {
                return total_count;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public String getPrivate_json() {
                return private_json;
            }

            public void setPrivate_json(String private_json) {
                this.private_json = private_json;
            }

            public boolean isEnable_reward() {
                return enable_reward;
            }

            public void setEnable_reward(boolean enable_reward) {
                this.enable_reward = enable_reward;
            }

            public String getTotal_left_count() {
                return total_left_count;
            }

            public void setTotal_left_count(String total_left_count) {
                this.total_left_count = total_left_count;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.img_url);
                dest.writeString(this.remark);
                dest.writeByte(is_v ? (byte) 1 : (byte) 0);
                dest.writeByte(is_open ? (byte) 1 : (byte) 0);
                dest.writeByte(is_danbao ? (byte) 1 : (byte) 0);
                dest.writeByte(favorite_status ? (byte) 1 : (byte) 0);
                dest.writeString(this.type);
                dest.writeString(this.telphone);
                dest.writeString(this.nick_name);
                dest.writeString(this.send_time);
                dest.writeString(this.has_open_count);
                dest.writeString(this.age);
                dest.writeString(this.type_title);
                dest.writeString(this.current_u_amount);
                dest.writeString(this.hb_type);
                dest.writeString(this.total_amount);
                dest.writeString(this.u_img_url);
                dest.writeString(this.status);
                dest.writeByte(hb_status ? (byte) 1 : (byte) 0);
                dest.writeString(this.job);
                dest.writeString(this.content);
                dest.writeString(this.invitation_code);
                dest.writeString(this.price);
                dest.writeInt(this.pre_load_seconds);
                dest.writeInt(this.hb_link_num);
                dest.writeString(this.mobile);
                dest.writeString(this.sex);
                dest.writeString(this.range);
                dest.writeByte(grab_status ? (byte) 1 : (byte) 0);
                dest.writeString(this.has_send_amount);
                dest.writeByte(is_repeat ? (byte) 1 : (byte) 0);
                dest.writeByte(enable_share ? (byte) 1 : (byte) 0);
                dest.writeString(this.id);
                dest.writeString(this.current_u_id);
                dest.writeString(this.title);
                dest.writeString(this.weixin);
                dest.writeInt(this.can_open_linknum);
                dest.writeString(this.user_id);
                dest.writeString(this.status_title);
                dest.writeByte(must_share ? (byte) 1 : (byte) 0);
                dest.writeString(this.qq);
                dest.writeString(this.share_host);
                dest.writeString(this.cover_img_url);
                dest.writeByte(is_allow_repeat ? (byte) 1 : (byte) 0);
                dest.writeInt(this.delay_seconds);
                dest.writeString(this.can_trans);
                dest.writeString(this.hg_id);
                dest.writeString(this.total_count);
                dest.writeString(this.private_json);
                dest.writeByte(enable_reward ? (byte) 1 : (byte) 0);
                dest.writeString(this.total_left_count);
                dest.writeString(this.start_time);
                dest.writeString(this.end_time);
            }

            public DetailEntity() {
            }

            protected DetailEntity(Parcel in) {
                this.img_url = in.readString();
                this.remark = in.readString();
                this.is_v = in.readByte() != 0;
                this.is_open = in.readByte() != 0;
                this.is_danbao = in.readByte() != 0;
                this.favorite_status = in.readByte() != 0;
                this.type = in.readString();
                this.telphone = in.readString();
                this.nick_name = in.readString();
                this.send_time = in.readString();
                this.has_open_count = in.readString();
                this.age = in.readString();
                this.type_title = in.readString();
                this.current_u_amount = in.readString();
                this.hb_type = in.readString();
                this.total_amount = in.readString();
                this.u_img_url = in.readString();
                this.status = in.readString();
                this.hb_status = in.readByte() != 0;
                this.job = in.readString();
                this.content = in.readString();
                this.invitation_code = in.readString();
                this.price = in.readString();
                this.pre_load_seconds = in.readInt();
                this.hb_link_num = in.readInt();
                this.mobile = in.readString();
                this.sex = in.readString();
                this.range = in.readString();
                this.grab_status = in.readByte() != 0;
                this.has_send_amount = in.readString();
                this.is_repeat = in.readByte() != 0;
                this.enable_share = in.readByte() != 0;
                this.id = in.readString();
                this.current_u_id = in.readString();
                this.title = in.readString();
                this.weixin = in.readString();
                this.can_open_linknum = in.readInt();
                this.user_id = in.readString();
                this.status_title = in.readString();
                this.must_share = in.readByte() != 0;
                this.qq = in.readString();
                this.share_host = in.readString();
                this.cover_img_url = in.readString();
                this.is_allow_repeat = in.readByte() != 0;
                this.delay_seconds = in.readInt();
                this.can_trans = in.readString();
                this.hg_id = in.readString();
                this.total_count = in.readString();
                this.private_json = in.readString();
                this.enable_reward = in.readByte() != 0;
                this.total_left_count = in.readString();
                this.start_time = in.readString();
                this.end_time = in.readString();
            }

            public static final Parcelable.Creator<DetailEntity> CREATOR = new Parcelable.Creator<DetailEntity>() {
                public DetailEntity createFromParcel(Parcel source) {
                    return new DetailEntity(source);
                }

                public DetailEntity[] newArray(int size) {
                    return new DetailEntity[size];
                }
            };
        }
    }
}
