package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.AdFragmentMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.impl.AdFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.AdFragment;
import com.chunsun.redenvelope.ui.view.IAdFragment;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/24.
 */
public class AdFragmentPresenter implements BaseSingleLoadedListener<AdDelaySecondsRateEntity> {

    private IAdFragment mIAdFragment;
    private AdFragmentMode mAdFragmentMode;
    /**
     * 最小单价
     */
    private float mMinPrice;
    /**
     * 最小总价
     */
    private float mMinAmount;

    public AdFragmentPresenter(IAdFragment iAdFragment) {
        this.mIAdFragment = iAdFragment;
        this.mAdFragmentMode = new AdFragmentModeImpl((AdFragment) iAdFragment);
    }

    /**
     * 获取广告延时时间
     */
    public void getAdDelaySecondsRate() {
        mAdFragmentMode.getAdDelaySecondsRate(this);
    }

    @Override
    public void onSuccess(AdDelaySecondsRateEntity response) {
        AdDelaySecondsRateEntity.ResultEntity result = response.getResult();
        mMinPrice = Float.parseFloat(result.getHb_min_price());
        mMinAmount = Float.parseFloat(result.getHb_min_amount());
        mIAdFragment.setDelaySecondsRateData(result.getDelay_seconds_rate());
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }


    /**
     * 验证价格是否符合要求
     *
     * @param mAdEntity
     */
    public void toValidatePrice(AdEntity mAdEntity) {
        float price = Float.valueOf(mAdEntity.getPrice());
        float amount = Float.valueOf(mAdEntity.getAdPrice());
        if (price < mMinPrice) {
            ShowToast.Short("广告单价小于规定最低单价！");
            return;
        }
        if (amount < mMinAmount) {
            ShowToast.Short("广告总价小于规定最低总价格！");
            return;
        }
        mIAdFragment.toNextStep();
    }
}
