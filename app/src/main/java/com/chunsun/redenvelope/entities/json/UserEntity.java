package com.chunsun.redenvelope.entities.json;


import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * 用户信息实体
 */
public class UserEntity extends BaseEntity {

    private UserInfoEntity result;

    public UserInfoEntity getResult() {
        return result;
    }

    public void setResult(UserInfoEntity result) {
        this.result = result;
    }
}
