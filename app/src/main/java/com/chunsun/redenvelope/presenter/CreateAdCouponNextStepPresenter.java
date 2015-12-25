package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.CreateAdCouponNextStepMode;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.impl.CreateAdCouponNextStepModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdCouponNextStepActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdCouponNextStepView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/4 12:15
 * @des
 */
public class CreateAdCouponNextStepPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private ICreateAdCouponNextStepView mICreateAdCouponNextStepView;
    private CreateAdCouponNextStepMode mCreateAdCouponNextStepMode;
    /**
     * 最小单价
     */
    private float mMinPrice;
    /**
     * 最小总价
     */
    private float mMinAmount;

    public CreateAdCouponNextStepPresenter(ICreateAdCouponNextStepView iCreateAdCouponNextStepView) {
        mICreateAdCouponNextStepView = iCreateAdCouponNextStepView;
        mCreateAdCouponNextStepMode = new CreateAdCouponNextStepModeImpl((CreateAdCouponNextStepActivity) iCreateAdCouponNextStepView);
    }

    /**
     * 获取广告延时时间
     */
    public void getAdDelaySecondsRate() {
        mCreateAdCouponNextStepMode.getAdDelaySecondsRate(this);
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

        mICreateAdCouponNextStepView.toNextStep();
    }


    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_CREATE_AD_DELAY_SECONDS:
                getDelaySecondsSuccess((AdDelaySecondsRateEntity) data);
                break;
        }
    }

    public void getDelaySecondsSuccess(AdDelaySecondsRateEntity response) {
        AdDelaySecondsRateEntity.ResultEntity result = response.getResult();
        mMinPrice = Float.parseFloat(result.getHb_min_price());
        mMinAmount = Float.parseFloat(result.getHb_min_amount());
        mICreateAdCouponNextStepView.setDelaySecondsRateData(result.getDelay_seconds_rate());
    }
}
