package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 10:47
 * @des
 */
public interface AuthenticationMode {
    /**
     * 企业认证
     *
     * @param token
     * @param cmp_name
     * @param cmp_tel
     * @param address
     * @param contact_mobile
     * @param cmp_contact
     * @param bank_no
     * @param bank_name
     * @param tax_no
     * @param licence_img_byte_str
     * @param ID_img_byte_str
     * @param listener
     */
    void userCmp(String token, String cmp_name, String cmp_tel, String address, String contact_mobile, String cmp_contact, String bank_no, String bank_name, String tax_no, String licence_img_byte_str, String ID_img_byte_str, UserLoseMultiLoadedListener listener);
}
