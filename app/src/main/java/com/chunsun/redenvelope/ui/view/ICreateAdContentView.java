package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.CreateAdResultEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface ICreateAdContentView extends LoadingView {

    /**
     * 跳转付款Activity
     *
     * @param result
     */
    void toAdPayActivity(CreateAdResultEntity.ResultEntity result);

    /**
     * 跳转拼手气付款Activity
     * @param result
     */
    void toLuckAdPayActivity(CreateAdResultEntity.ResultEntity result);

    /**
     * 成功创建圈子
     *
     * @param result
     */
    void toCreateCircleSuccess(CreateAdResultEntity.ResultEntity result);

    /**
     * 跳转预览界面
     */
    void toPreview();

    /**
     * 创建圈子失败
     */
    void toCreateError();
}
