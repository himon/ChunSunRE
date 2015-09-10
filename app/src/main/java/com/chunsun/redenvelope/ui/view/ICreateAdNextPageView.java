package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface ICreateAdNextPageView {

    /**
     * 初始化省市信息
     *
     * @param distanceList
     * @param typeList
     * @param districtList
     * @param adEntity
     */
    void setInitData(ArrayList<SampleEntity> distanceList, ArrayList<SampleEntity> typeList, ArrayList<DistrictEntity.AreaEntity> districtList, AdEntity adEntity);

    /**
     * 跳转选择类型界面
     */
    void toSelectType();

    /**
     * 跳转选择省份界面
     */
    void toSelectProvince();

    /**
     * 跳转选择城市界面
     */
    void toSelectCity();

    /**
     * 跳转选择距离界面
     */
    void toSelectDistance();

    /**
     * 跳转付款Activity
     * @param result
     */
    void toAdPayActivity(CreateAdResultEntity.ResultEntity result);

    void showLoading();

    void hideLoading();
}
