package com.chunsun.redenvelope.model.entity.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/8/11.
 */
public class RedDetailEntity extends BaseEntity {


    /**
     * result : {"detail":{"img_url":"","sex":"男","grab_status":"False","range":"全国","is_v":"False","remark":"","has_send_amount":"1339.00","is_open":"False","favorite_status":"False","is_danbao":"False","type":"1","telphone":"","id":"1728","nick_name":"悄悄话","current_u_id":"3110","send_time":"2015/8/7 15:14:19","title":"哈哈","weixin":"","has_open_count":"2","age":"0","type_title":"生活","user_id":"6775","status_title":"审核通过","current_u_amount":"85759.56","hb_type":"1","qq":"","total_amount":"41.20","cover_img_url":"/upload/201508/07/201508071513186769.jpeg","u_img_url":"","can_trans":"True","delay_seconds":"10","hb_status":"True","status":"3","job":"","hg_id":"0","total_count":"2000","content":"哈哈","invitation_code":"6775XLF8","private_json":"{\"nick_name\":\"true\",\"weixin\":\"false\",\"has_send_amount\":\"true\",\"telphone\":\"true\",\"mobile\":\"false\",\"qq\":\"true\",\"sex\":\"true\",\"birthday\":\"true\",\"job\":\"true\"}","price":"0.02","pre_load_seconds":"3","enable_reward":"True","mobile":"13377777777"}}
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
         * detail : {"img_url":"","sex":"男","grab_status":"False","range":"全国","is_v":"False","remark":"","has_send_amount":"1339.00","is_open":"False","favorite_status":"False","is_danbao":"False","type":"1","telphone":"","id":"1728","nick_name":"悄悄话","current_u_id":"3110","send_time":"2015/8/7 15:14:19","title":"哈哈","weixin":"","has_open_count":"2","age":"0","type_title":"生活","user_id":"6775","status_title":"审核通过","current_u_amount":"85759.56","hb_type":"1","qq":"","total_amount":"41.20","cover_img_url":"/upload/201508/07/201508071513186769.jpeg","u_img_url":"","can_trans":"True","delay_seconds":"10","hb_status":"True","status":"3","job":"","hg_id":"0","total_count":"2000","content":"哈哈","invitation_code":"6775XLF8","private_json":"{\"nick_name\":\"true\",\"weixin\":\"false\",\"has_send_amount\":\"true\",\"telphone\":\"true\",\"mobile\":\"false\",\"qq\":\"true\",\"sex\":\"true\",\"birthday\":\"true\",\"job\":\"true\"}","price":"0.02","pre_load_seconds":"3","enable_reward":"True","mobile":"13377777777"}
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
             * sex : 男
             * grab_status : False
             * range : 全国
             * is_v : False
             * remark :
             * has_send_amount : 1339.00
             * is_open : False
             * favorite_status : False
             * is_danbao : False
             * type : 1
             * telphone :
             * id : 1728
             * nick_name : 悄悄话
             * current_u_id : 3110
             * send_time : 2015/8/7 15:14:19
             * title : 哈哈
             * weixin :
             * has_open_count : 2
             * age : 0
             * type_title : 生活
             * user_id : 6775
             * status_title : 审核通过
             * current_u_amount : 85759.56
             * hb_type : 1
             * qq :
             * total_amount : 41.20
             * cover_img_url : /upload/201508/07/201508071513186769.jpeg
             * u_img_url :
             * can_trans : True
             * delay_seconds : 10
             * hb_status : True
             * status : 3
             * job :
             * hg_id : 0
             * total_count : 2000
             * content : 哈哈
             * invitation_code : 6775XLF8
             * private_json : {"nick_name":"true","weixin":"false","has_send_amount":"true","telphone":"true","mobile":"false","qq":"true","sex":"true","birthday":"true","job":"true"}
             * price : 0.02
             * pre_load_seconds : 3
             * enable_reward : True
             * mobile : 13377777777
             */
            private String img_url;
            private String sex;
            /**
             * 是否抢过红包 true 抢过，false 未抢过
             */
            private boolean grab_status;
            private String range;
            private boolean is_v;
            private String remark;
            private String has_send_amount;
            /**
             * 是否领取红包 true 领取，false 未领取
             */
            private boolean is_open;
            /**
             * 该红包是否被收藏
             */
            private boolean favorite_status;
            private boolean is_danbao;
            private String type;
            private String telphone;
            private String id;
            private String nick_name;
            private String current_u_id;
            private String send_time;
            private String title;
            private String weixin;
            private String has_open_count;
            private String age;
            private String type_title;
            private String user_id;
            private String status_title;
            private String current_u_amount;
            private String hb_type;
            private String qq;
            private String total_amount;
            private String cover_img_url;
            private String u_img_url;
            private String can_trans;
            private int delay_seconds;
            /**
             * 红包是否领完 true 未领完， false 领完
             */
            private boolean hb_status;
            private String status;
            private String job;
            private String hg_id;
            private String total_count;
            private String content;
            private String invitation_code;
            private String private_json;
            private String price;
            private int pre_load_seconds;
            private String enable_reward;
            private String mobile;

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setGrab_status(boolean grab_status) {
                this.grab_status = grab_status;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setHas_send_amount(String has_send_amount) {
                this.has_send_amount = has_send_amount;
            }

            public void setIs_open(boolean is_open) {
                this.is_open = is_open;
            }

            public void setFavorite_status(boolean favorite_status) {
                this.favorite_status = favorite_status;
            }

            public void setIs_danbao(boolean is_danbao) {
                this.is_danbao = is_danbao;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setCurrent_u_id(String current_u_id) {
                this.current_u_id = current_u_id;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public void setHas_open_count(String has_open_count) {
                this.has_open_count = has_open_count;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public void setType_title(String type_title) {
                this.type_title = type_title;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setStatus_title(String status_title) {
                this.status_title = status_title;
            }

            public void setCurrent_u_amount(String current_u_amount) {
                this.current_u_amount = current_u_amount;
            }

            public void setHb_type(String hb_type) {
                this.hb_type = hb_type;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public void setCover_img_url(String cover_img_url) {
                this.cover_img_url = cover_img_url;
            }

            public void setU_img_url(String u_img_url) {
                this.u_img_url = u_img_url;
            }

            public void setCan_trans(String can_trans) {
                this.can_trans = can_trans;
            }

            public void setDelay_seconds(int delay_seconds) {
                this.delay_seconds = delay_seconds;
            }

            public void setHb_status(boolean hb_status) {
                this.hb_status = hb_status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public void setHg_id(String hg_id) {
                this.hg_id = hg_id;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setInvitation_code(String invitation_code) {
                this.invitation_code = invitation_code;
            }

            public void setPrivate_json(String private_json) {
                this.private_json = private_json;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setPre_load_seconds(int pre_load_seconds) {
                this.pre_load_seconds = pre_load_seconds;
            }

            public void setEnable_reward(String enable_reward) {
                this.enable_reward = enable_reward;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getSex() {
                return sex;
            }

            public boolean getGrab_status() {
                return grab_status;
            }

            public String getRange() {
                return range;
            }

            public boolean getIs_v() {
                return is_v;
            }

            public String getRemark() {
                return remark;
            }

            public String getHas_send_amount() {
                return has_send_amount;
            }

            public boolean getIs_open() {
                return is_open;
            }

            public boolean getFavorite_status() {
                return favorite_status;
            }

            public boolean getIs_danbao() {
                return is_danbao;
            }

            public String getType() {
                return type;
            }

            public String getTelphone() {
                return telphone;
            }

            public String getId() {
                return id;
            }

            public String getNick_name() {
                return nick_name;
            }

            public String getCurrent_u_id() {
                return current_u_id;
            }

            public String getSend_time() {
                return send_time;
            }

            public String getTitle() {
                return title;
            }

            public String getWeixin() {
                return weixin;
            }

            public String getHas_open_count() {
                return has_open_count;
            }

            public String getAge() {
                return age;
            }

            public String getType_title() {
                return type_title;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getStatus_title() {
                return status_title;
            }

            public String getCurrent_u_amount() {
                return current_u_amount;
            }

            public String getHb_type() {
                return hb_type;
            }

            public String getQq() {
                return qq;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public String getCover_img_url() {
                return cover_img_url;
            }

            public String getU_img_url() {
                return Constants.IMG_HOST_URL + u_img_url;
            }

            public String getCan_trans() {
                return can_trans;
            }

            public int getDelay_seconds() {
                return delay_seconds;
            }

            public boolean getHb_status() {
                return hb_status;
            }

            public String getStatus() {
                return status;
            }

            public String getJob() {
                return job;
            }

            public String getHg_id() {
                return hg_id;
            }

            public String getTotal_count() {
                return total_count;
            }

            public String getContent() {
                return content;
            }

            public String getInvitation_code() {
                return invitation_code;
            }

            public String getPrivate_json() {
                return private_json;
            }

            public String getPrice() {
                return price;
            }

            public int getPre_load_seconds() {
                return pre_load_seconds;
            }

            public String getEnable_reward() {
                return enable_reward;
            }

            public String getMobile() {
                return mobile;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.img_url);
                dest.writeString(this.sex);
                dest.writeByte(grab_status ? (byte) 1 : (byte) 0);
                dest.writeString(this.range);
                dest.writeByte(is_v ? (byte) 1 : (byte) 0);
                dest.writeString(this.remark);
                dest.writeString(this.has_send_amount);
                dest.writeByte(is_open ? (byte) 1 : (byte) 0);
                dest.writeByte(favorite_status ? (byte) 1 : (byte) 0);
                dest.writeByte(is_danbao ? (byte) 1 : (byte) 0);
                dest.writeString(this.type);
                dest.writeString(this.telphone);
                dest.writeString(this.id);
                dest.writeString(this.nick_name);
                dest.writeString(this.current_u_id);
                dest.writeString(this.send_time);
                dest.writeString(this.title);
                dest.writeString(this.weixin);
                dest.writeString(this.has_open_count);
                dest.writeString(this.age);
                dest.writeString(this.type_title);
                dest.writeString(this.user_id);
                dest.writeString(this.status_title);
                dest.writeString(this.current_u_amount);
                dest.writeString(this.hb_type);
                dest.writeString(this.qq);
                dest.writeString(this.total_amount);
                dest.writeString(this.cover_img_url);
                dest.writeString(this.u_img_url);
                dest.writeString(this.can_trans);
                dest.writeInt(this.delay_seconds);
                dest.writeByte(hb_status ? (byte) 1 : (byte) 0);
                dest.writeString(this.status);
                dest.writeString(this.job);
                dest.writeString(this.hg_id);
                dest.writeString(this.total_count);
                dest.writeString(this.content);
                dest.writeString(this.invitation_code);
                dest.writeString(this.private_json);
                dest.writeString(this.price);
                dest.writeInt(this.pre_load_seconds);
                dest.writeString(this.enable_reward);
                dest.writeString(this.mobile);
            }

            public DetailEntity() {
            }

            protected DetailEntity(Parcel in) {
                this.img_url = in.readString();
                this.sex = in.readString();
                this.grab_status = in.readByte() != 0;
                this.range = in.readString();
                this.is_v = in.readByte() != 0;
                this.remark = in.readString();
                this.has_send_amount = in.readString();
                this.is_open = in.readByte() != 0;
                this.favorite_status = in.readByte() != 0;
                this.is_danbao = in.readByte() != 0;
                this.type = in.readString();
                this.telphone = in.readString();
                this.id = in.readString();
                this.nick_name = in.readString();
                this.current_u_id = in.readString();
                this.send_time = in.readString();
                this.title = in.readString();
                this.weixin = in.readString();
                this.has_open_count = in.readString();
                this.age = in.readString();
                this.type_title = in.readString();
                this.user_id = in.readString();
                this.status_title = in.readString();
                this.current_u_amount = in.readString();
                this.hb_type = in.readString();
                this.qq = in.readString();
                this.total_amount = in.readString();
                this.cover_img_url = in.readString();
                this.u_img_url = in.readString();
                this.can_trans = in.readString();
                this.delay_seconds = in.readInt();
                this.hb_status = in.readByte() != 0;
                this.status = in.readString();
                this.job = in.readString();
                this.hg_id = in.readString();
                this.total_count = in.readString();
                this.content = in.readString();
                this.invitation_code = in.readString();
                this.private_json = in.readString();
                this.price = in.readString();
                this.pre_load_seconds = in.readInt();
                this.enable_reward = in.readString();
                this.mobile = in.readString();
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
