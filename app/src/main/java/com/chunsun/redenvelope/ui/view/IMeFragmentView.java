package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.UserInfoEntity;

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
    void toMyCircle();

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
     * 显示新手引导
     */
    void noviceGuidelines();

    /**
     * 扫描春笋券
     */
    void scanCoupon();

    /**
     * 春笋券列表
     */
    void chunsunCoupon();
}
