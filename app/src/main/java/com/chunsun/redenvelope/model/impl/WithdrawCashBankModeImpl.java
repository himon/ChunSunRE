package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WithdrawCashBankMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;
import com.chunsun.redenvelope.utils.AssetsUtils;
import com.google.gson.Gson;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 17:04
 * @des
 */
public class WithdrawCashBankModeImpl extends BaseModeImpl implements WithdrawCashBankMode{

    private Activity mActivity;

    public WithdrawCashBankModeImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void userCashInfo(String token, UserLoseMultiLoadedListener listener) {
        mManager.userCashInfo(token, listener, mActivity);
    }

    @Override
    public void initProvinceAndCity(UserLoseMultiLoadedListener listener) {
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
