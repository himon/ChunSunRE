package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

import java.util.List;

/**
 * Created by Administrator on 2015/9/12.
 */
public interface IInteractivePlatformView extends LoadingView {

    /**
     * 加载全国信息
     *
     * @param entity
     */
    void setCountryList(InteractiveEntity entity);

    /**
     * 加载本地信息
     *
     * @param entity
     */
    void setLocalList(InteractiveEntity entity);

    /**
     * 评论成功
     */
    void commentSuccess();

    /**
     * 跳转用户奖励页面
     */
    void toUserRewardActivity(String id);

    /**
     * 设置系统公告
     * @param notice
     */
    void setNoticeBoard(List<InteractiveEntity.ResultEntity.NoticeEntity> notice);

    void toLogin();
}
