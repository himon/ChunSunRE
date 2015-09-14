package com.chunsun.redenvelope.model.entity.json;

/**
 * 用户信息实体
 */
public class UserInfoEntity {


    private String id;
    private String user_name;
    private String nick_name;
    private String img_url;
    private String telphone;
    private String mobile;
    private String status;
    private String token;
    private String push_device_type;
    private String push_device_token;
    private String weixin;
    private String zhifubao;
    /**
     * 身份证号
     */
    private String ID_num;
    /**
     * 用户类型 1 普通用户， 2 企业用户
     */
    private String type;
    /**
     * 营业执照
     */
    private String licence_img_url;
    /**
     * 身份证照
     */
    private String ID_img_url;
    private String company_name;
    private String company_tel;
    private String company_contact;
    private String contact_mobile;
    private String address;
    /**
     * 企业的认证状态 0:未认证；1：审核中；2：认证成功；3：认证驳回
     */
    private String is_v;
    private String v_reason;
    /**
     * 签名或企业介绍
     */
    private String remark;
    private String bank_name;
    private String bank_no;
    private String tax_no;
    private String json;
    private String has_send_amount;
    private boolean has_pwd;
    private String qq;
    private String sex;
    private String birthday;
    private String age;
    private String job;
    private String thumb_img_url;
    private String invitation_code;
    private String is_proxy;
    private String is_danbao;
    private String reward_amount;
    private String share_host;
    private String yaoqing_is_display;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPush_device_type() {
        return push_device_type;
    }

    public void setPush_device_type(String push_device_type) {
        this.push_device_type = push_device_type;
    }

    public String getPush_device_token() {
        return push_device_token;
    }

    public void setPush_device_token(String push_device_token) {
        this.push_device_token = push_device_token;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getZhifubao() {
        return zhifubao;
    }

    public void setZhifubao(String zhifubao) {
        this.zhifubao = zhifubao;
    }

    public String getID_num() {
        return ID_num;
    }

    public void setID_num(String ID_num) {
        this.ID_num = ID_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicence_img_url() {
        return licence_img_url;
    }

    public void setLicence_img_url(String licence_img_url) {
        this.licence_img_url = licence_img_url;
    }

    public String getID_img_url() {
        return ID_img_url;
    }

    public void setID_img_url(String ID_img_url) {
        this.ID_img_url = ID_img_url;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_v() {
        return is_v;
    }

    public void setIs_v(String is_v) {
        this.is_v = is_v;
    }

    public String getV_reason() {
        return v_reason;
    }

    public void setV_reason(String v_reason) {
        this.v_reason = v_reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getTax_no() {
        return tax_no;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getHas_send_amount() {
        return has_send_amount;
    }

    public void setHas_send_amount(String has_send_amount) {
        this.has_send_amount = has_send_amount;
    }

    public boolean isHas_pwd() {
        return has_pwd;
    }

    public void setHas_pwd(boolean has_pwd) {
        this.has_pwd = has_pwd;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getThumb_img_url() {
        return thumb_img_url;
    }

    public void setThumb_img_url(String thumb_img_url) {
        this.thumb_img_url = thumb_img_url;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public String getIs_proxy() {
        return is_proxy;
    }

    public void setIs_proxy(String is_proxy) {
        this.is_proxy = is_proxy;
    }

    public String getIs_danbao() {
        return is_danbao;
    }

    public void setIs_danbao(String is_danbao) {
        this.is_danbao = is_danbao;
    }

    public String getReward_amount() {
        return reward_amount;
    }

    public void setReward_amount(String reward_amount) {
        this.reward_amount = reward_amount;
    }

    public String getShare_host() {
        return share_host;
    }

    public void setShare_host(String share_host) {
        this.share_host = share_host;
    }

    public String getYaoqing_is_display() {
        return yaoqing_is_display;
    }

    public void setYaoqing_is_display(String yaoqing_is_display) {
        this.yaoqing_is_display = yaoqing_is_display;
    }
}
