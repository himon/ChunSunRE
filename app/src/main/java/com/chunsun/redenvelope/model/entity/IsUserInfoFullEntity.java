package com.chunsun.redenvelope.model.entity;

/**
 * 判断用户是否填写了必要的信息的实体
 */
public class IsUserInfoFullEntity {

    private boolean qq;
    private boolean weixin;
    private boolean telphone;
    private boolean mobile;
    private boolean birthday;
    private boolean has_send_amount;
    private boolean sex;
    private boolean nick_name;
    private boolean job;

    public boolean isQq() {
        return qq;
    }

    public void setQq(boolean qq) {
        this.qq = qq;
    }

    public boolean isWeixin() {
        return weixin;
    }

    public void setWeixin(boolean weixin) {
        this.weixin = weixin;
    }

    public boolean isTelphone() {
        return telphone;
    }

    public void setTelphone(boolean telphone) {
        this.telphone = telphone;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public boolean isBirthday() {
        return birthday;
    }

    public void setBirthday(boolean birthday) {
        this.birthday = birthday;
    }

    public boolean isHas_send_amount() {
        return has_send_amount;
    }

    public void setHas_send_amount(boolean has_send_amount) {
        this.has_send_amount = has_send_amount;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isNick_name() {
        return nick_name;
    }

    public void setNick_name(boolean nick_name) {
        this.nick_name = nick_name;
    }

    public boolean isJob() {
        return job;
    }

    public void setJob(boolean job) {
        this.job = job;
    }
}
