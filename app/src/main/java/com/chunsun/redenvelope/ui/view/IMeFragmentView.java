package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;

/**
 * Created by Administrator on 2015/8/3.
 */
public interface IMeFragmentView {

    /**
     * 个人信息
     */
    void toMeInfomation();

    /**
     * 我的邀请码
     */
    void toMineInviteCode();

    /**
     * 余额
     */
    void toBalance();

    /**
     * 发广告记录
     */
    void toAdRecord();

    /**
     * 未领取红包
     */
    void toNotReceivingRed();

    /**
     * 充值
     */
    void toRecharge();

    /**
     * 收藏
     */
    void toCollect();

    /**
     * 设置
     */
    void toSetting();

    /**
     * 刷新
     */
    void refresh(UserInfoEntity entity);

    /**
     * 跳转登录界面
     */
    void toLogin();
}
