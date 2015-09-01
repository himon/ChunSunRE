package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.AdFragmentMode;
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
        mIAdFragment.setDelaySecondsRateData(result);
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
