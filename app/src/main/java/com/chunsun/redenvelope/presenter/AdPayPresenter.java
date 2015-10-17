package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.AdPayMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.AdPayAmountDetailEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.AdPayModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.AdPayActivity;
import com.chunsun.redenvelope.ui.view.IAdPayView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/9/8.
 */
public class AdPayPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IAdPayView mIAdPayView;
    private AdPayMode mAdPayMode;

    public AdPayPresenter(IAdPayView iAdPayView) {
        this.mIAdPayView = iAdPayView;
        mAdPayMode = new AdPayModeImpl((AdPayActivity) iAdPayView);
    }

    /**
     * 获取红包支付明细
     *
     * @param mToken
     * @param hb_id
     */
    public void getAdAmountDetail(String mToken, String hb_id) {
        mAdPayMode.getAdAmountDetail(mToken, hb_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_AD_AMOUNT_DETAIL:
                AdPayAmountDetailEntity entity = (AdPayAmountDetailEntity) data;
                AdPayAmountDetailEntity.ResultEntity result = entity.getResult();
                mIAdPayView.setData(result);
                break;
            case Constants.LISTENER_TYPE_PAY_BY_BANLANCE:
                mIAdPayView.hideLoading();
                SampleResponseEntity entity1 = (SampleResponseEntity) data;
                mIAdPayView.paySuccess(entity1.getMsg());
                break;
        }
    }

    @Override
    public void onError(String msg) {
        mIAdPayView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {
        mIAdPayView.hideLoading();
        switch (event_tag) {
            case Constants.LISTENER_TYPE_PAY_BY_BANLANCE:
                mIAdPayView.payError(msg);
                break;
        }
    }

    @Override
    public void onException(String msg) {
        mIAdPayView.hideLoading();
        ShowToast.Short(msg);
    }

    /**
     * 余额付款
     *
     * @param token
     * @param hb_id
     */
    public void payByBalance(String token, String hb_id) {
        mIAdPayView.showLoading();
        mAdPayMode.payByBalance(token, hb_id, this);
    }


}
