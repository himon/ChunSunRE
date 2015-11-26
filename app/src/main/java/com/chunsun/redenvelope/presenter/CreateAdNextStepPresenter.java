package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.CreateAdNextStepMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.impl.CreateAdNextStepModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextStepActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdNextStepView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/24.
 */
public class CreateAdNextStepPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private ICreateAdNextStepView mICreateAdNextStepView;
    private CreateAdNextStepMode mCreateAdNextStepMode;
    /**
     * 最小单价
     */
    private float mMinPrice;
    /**
     * 最小总价
     */
    private float mMinAmount;

    public CreateAdNextStepPresenter(ICreateAdNextStepView iCreateAdNextStepView) {
        this.mICreateAdNextStepView = iCreateAdNextStepView;
        this.mCreateAdNextStepMode = new CreateAdNextStepModeImpl((CreateAdNextStepActivity) iCreateAdNextStepView);
    }

    /**
     * 获取广告延时时间
     */
    public void getAdDelaySecondsRate() {
        mCreateAdNextStepMode.getAdDelaySecondsRate(this);
    }

    public void getDelaySecondsSuccess(AdDelaySecondsRateEntity response) {
        AdDelaySecondsRateEntity.ResultEntity result = response.getResult();
        mMinPrice = Float.parseFloat(result.getHb_min_price());
        mMinAmount = Float.parseFloat(result.getHb_min_amount());
        mICreateAdNextStepView.setDelaySecondsRateData(result.getDelay_seconds_rate());
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CREATE_AD_DELAY_SECONDS:
                getDelaySecondsSuccess((AdDelaySecondsRateEntity) data);
                break;
        }
    }

    /**
     * 验证价格是否符合要求
     *
     * @param mAdEntity
     */
    public void toValidatePrice(AdEntity mAdEntity) {

        if ("5".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁止发送广告，有什么疑问请联系客服!");
            return;
        }

        if (TextUtils.isEmpty(mAdEntity.getPrice()) || mAdEntity.getPrice().equals(".")) {
            ShowToast.Short("单价不能为空或不合法的价格！");
            return;
        }

        float price = Float.valueOf(mAdEntity.getPrice());
        float amount = Float.valueOf(mAdEntity.getAdPrice());
        if (price < mMinPrice) {
            ShowToast.Short("广告单价小于规定最低单价" + mMinPrice + "元！");
            return;
        }
        if (amount < mMinAmount) {
            ShowToast.Short("广告总价小于规定最低总价格" + mMinAmount + "元！");
            return;
        }

        if (TextUtils.isEmpty(mAdEntity.getNum()) || "0".equals(mAdEntity.getNum())) {
            ShowToast.Short("数量不能为空！");
            return;
        }

        if (TextUtils.isEmpty(mAdEntity.getStartTime())) {
            ShowToast.Short("显示时间不能为零, 请重新进入此页面加载数据");
            return;
        }

        mICreateAdNextStepView.toNextStep();
    }
}
