package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdMode;
import com.chunsun.redenvelope.utils.AssetsUtils;
import com.google.gson.Gson;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/9/29 10:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CreateAdModeImpl implements CreateAdMode {

    private Activity mActivity;

    public CreateAdModeImpl(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void initProvinceAndCity(BaseMultiLoadedListener listener) {
        String json = AssetsUtils.getFromAssets("address_data_json.txt", mActivity);
        Gson gson = new Gson();
        DistrictEntity districtEntity = gson.fromJson(json, DistrictEntity.class);
        if (districtEntity == null) {
            listener.onError("省、市信息获取失败！");
        } else {
            listener.onSuccess(Constants.LISTENER_TYPE_GET_PROVINCE_AND_CITY, districtEntity);
        }
    }
}
