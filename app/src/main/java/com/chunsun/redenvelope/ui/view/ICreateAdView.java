package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/9/29 10:34
 * @des
 */
public interface ICreateAdView {

    /**
     * 初始化省市信息
     * @param distanceList
     * @param typeList
     * @param districtList
     * @param currentProvince
     * @param currentCity
     * @param defaultProvince
     * @param defaultCity
     * @param adEntity
     */
    void setInitData(ArrayList<SampleEntity> distanceList, ArrayList<SampleEntity> typeList, ArrayList<DistrictEntity.AreaEntity> districtList, DistrictEntity.AreaEntity currentProvince, DistrictEntity.AreaEntity.CcEntity currentCity, DistrictEntity.AreaEntity defaultProvince, DistrictEntity.AreaEntity.CcEntity defaultCity, AdEntity adEntity);

    void nextCreateActivity();
}
