package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface ICreateAdContentView extends BaseView{

    /**
     * 跳转付款Activity
     * @param result
     */
    void toAdPayActivity(CreateAdResultEntity.ResultEntity result);

}
