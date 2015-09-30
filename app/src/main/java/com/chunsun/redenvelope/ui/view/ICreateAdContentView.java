package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/2.
 */
public interface ICreateAdContentView extends BaseView{

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
     * 跳转付款Activity
     * @param result
     */
    void toAdPayActivity(CreateAdResultEntity.ResultEntity result);

}
