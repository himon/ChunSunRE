package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/9/15.
 * 用户互相奖励时
 */
public class UserPublicInfoEntity extends BaseEntity {

    /**
     * result : {"birthday":"2015/9/3 0:00:00","u_img_url":"/upload/201506/06/201506060933441659.jpeg","can_trans":"False","sex":"男","remark":"努力追逐，快乐生活！","is_v":"False","has_send_amount":"0.00","job":"其他职业","is_danbao":"False","thumb_img_url":"/upload/201506/06/201506060933441659.jpeg","type":"1","telphone":"03716666888","nick_name":"有缘人","current_u_id":"3110","reward_amount":"0.00","is_proxy":"False","invitation_code":"65222FN","private_json":"{\"nick_name\":\"true\",\"birthday\":\"true\",\"sex\":\"true\",\"weixin\":\"false\",\"has_send_amount\":\"false\",\"job\":\"true\",\"qq\":\"false\",\"telphone\":\"false\",\"mobile\":\"false\"}","weixin":"","age":"0","user_id":"652","enable_reward":"True","current_u_amount":"0.31","qq":"","mobile":"13525587119"}
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
         * birthday : 2015/9/3 0:00:00
         * u_img_url : /upload/201506/06/201506060933441659.jpeg
         * can_trans : False
         * sex : 男
         * remark : 努力追逐，快乐生活！
         * is_v : False
         * has_send_amount : 0.00
         * job : 其他职业
         * is_danbao : False
         * thumb_img_url : /upload/201506/06/201506060933441659.jpeg
         * type : 1
         * telphone : 03716666888
         * nick_name : 有缘人
         * current_u_id : 3110
         * reward_amount : 0.00
         * is_proxy : False
         * invitation_code : 65222FN
         * private_json : {"nick_name":"true","birthday":"true","sex":"true","weixin":"false","has_send_amount":"false","job":"true","qq":"false","telphone":"false","mobile":"false"}
         * weixin :
         * age : 0
         * user_id : 652
         * enable_reward : True
         * current_u_amount : 0.31
         * qq :
         * mobile : 13525587119
         */
        private String birthday;
        private String u_img_url;
        /**
         * 活跃度
         */
        private boolean can_trans;
        private String sex;
        private String remark;
        private boolean is_v;
        /**
         * 发送了多少钱的广告
         */
        private String has_send_amount;
        private String job;
        private boolean is_danbao;
        private String thumb_img_url;
        private String type;
        private String telphone;
        private String nick_name;
        /**
         * token对应用户id
         */
        private String current_u_id;
        private String reward_amount;
        private boolean is_proxy;
        private String invitation_code;
        private String private_json;
        private String weixin;
        private String age;
        /**
         * 目标用户的id
         */
        private String user_id;
        /**
         * 是否开启相互奖励功能
         */
        private boolean enable_reward;
        /**
         * 账户余额
         */
        private String current_u_amount;
        private String qq;
        private String mobile;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getU_img_url() {
            return u_img_url;
        }

        public void setU_img_url(String u_img_url) {
            this.u_img_url = u_img_url;
        }

        public boolean isCan_trans() {
            return can_trans;
        }

        public void setCan_trans(boolean can_trans) {
            this.can_trans = can_trans;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getHas_send_amount() {
            return has_send_amount;
        }

        public void setHas_send_amount(String has_send_amount) {
            this.has_send_amount = has_send_amount;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public boolean is_danbao() {
            return is_danbao;
        }

        public void setIs_danbao(boolean is_danbao) {
            this.is_danbao = is_danbao;
        }

        public String getThumb_img_url() {
            return thumb_img_url;
        }

        public void setThumb_img_url(String thumb_img_url) {
            this.thumb_img_url = thumb_img_url;
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

        public String getCurrent_u_id() {
            return current_u_id;
        }

        public void setCurrent_u_id(String current_u_id) {
            this.current_u_id = current_u_id;
        }

        public String getReward_amount() {
            return reward_amount;
        }

        public void setReward_amount(String reward_amount) {
            this.reward_amount = reward_amount;
        }

        public boolean is_proxy() {
            return is_proxy;
        }

        public void setIs_proxy(boolean is_proxy) {
            this.is_proxy = is_proxy;
        }

        public String getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(String invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getPrivate_json() {
            return private_json;
        }

        public void setPrivate_json(String private_json) {
            this.private_json = private_json;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public boolean isEnable_reward() {
            return enable_reward;
        }

        public void setEnable_reward(boolean enable_reward) {
            this.enable_reward = enable_reward;
        }

        public String getCurrent_u_amount() {
            return current_u_amount;
        }

        public void setCurrent_u_amount(String current_u_amount) {
            this.current_u_amount = current_u_amount;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
