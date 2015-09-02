package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.model.CreateAdNextPageMode;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextPageActivity;
import com.chunsun.redenvelope.utils.AssetsUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdNextPageModeImpl implements CreateAdNextPageMode {

    private CreateAdNextPageActivity mActivity;

    public CreateAdNextPageModeImpl(CreateAdNextPageActivity createAdNextPageActivity) {
        this.mActivity = createAdNextPageActivity;
    }

    @Override
    public void initProvinceAndCity() {
        String json = AssetsUtils.getFromAssets("address_data_json.txt", mActivity);
        Gson gson = new Gson();
        DistrictEntity districtEntity = gson.fromJson(json, DistrictEntity.class);
        List<DistrictEntity.AreaEntity> area = districtEntity.getArea();
    }
}
