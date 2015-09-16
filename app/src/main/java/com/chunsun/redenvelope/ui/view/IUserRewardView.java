package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.UserPublicInfoEntity;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface IUserRewardView {

    void setData(UserPublicInfoEntity.ResultEntity entity);

    /**
     * 显示确定Dialog
     *
     * @param isEnough
     */
    void showTextButtonDialog(boolean isEnough);

    /**
     * 去充值
     */
    void toRechargeActivity();

    void paySuccess();
}
