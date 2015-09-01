package com.chunsun.redenvelope.model.entity.json;


import com.chunsun.redenvelope.model.entity.BaseEntity;

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
