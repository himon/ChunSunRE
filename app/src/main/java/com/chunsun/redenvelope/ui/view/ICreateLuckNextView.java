package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.LuckMealsEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/18 11:28
 * @des
 */
public interface ICreateLuckNextView {

    /**
     * 获取拼手气套装
     * @param result
     */
    void setFightPackageList(List<LuckMealsEntity.ResultEntity> result);
}
