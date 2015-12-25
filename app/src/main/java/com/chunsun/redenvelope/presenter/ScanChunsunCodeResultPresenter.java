package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.ScanChunsunCodeResultMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseObjectEntity;
import com.chunsun.redenvelope.entities.json.ScanCouponResultEntity;
import com.chunsun.redenvelope.model.impl.ScanChunsunCodeResultModeImpl;
import com.chunsun.redenvelope.ui.activity.scan.ScanChunsunCodeResultActivity;
import com.chunsun.redenvelope.ui.view.IScanChunsunCodeResultView;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/5 16:34
 * @des
 */
public class ScanChunsunCodeResultPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IScanChunsunCodeResultView mIScanChunsunCodeResultView;
    private ScanChunsunCodeResultMode mScanChunsunCodeResultMode;

    public ScanChunsunCodeResultPresenter(IScanChunsunCodeResultView iScanChunsunCodeResultView) {
        mIScanChunsunCodeResultView = iScanChunsunCodeResultView;
        mScanChunsunCodeResultMode = new ScanChunsunCodeResultModeImpl((ScanChunsunCodeResultActivity) iScanChunsunCodeResultView);
    }

    /**
     * 获取券信息
     *
     * @param token
     * @param code
     */
    public void getTicketInfoForSeller(String token, String code) {
        mScanChunsunCodeResultMode.validateCoupon(token, code, this);
    }

    /**
     * 使用春笋券
     *
     * @param token
     * @param code
     */
    public void useChunsunCoupon(String token, String code) {
        mScanChunsunCodeResultMode.using(token, code, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_GET_CHUNSUN_COUPON_INFO:
                getInfoSuccess(data);
                break;
            case Constants.LISTENER_USE_CHUNSUN_COUPON:
                SampleResponseObjectEntity entity = (SampleResponseObjectEntity) data;
                mIScanChunsunCodeResultView.useCouponSuccess(entity.getMsg());
                break;
        }
    }

    private void getInfoSuccess(BaseEntity data) {
        ScanCouponResultEntity entity = (ScanCouponResultEntity) data;
        ScanCouponResultEntity.ResultEntity result = entity.getResult();
        ArrayList<String> list = new ArrayList<>();
        list.add(Constants.IMG_HOST_URL + result.getCoverImgUrl());
        if (!TextUtils.isEmpty(result.getImgUrl())) {
            String[] url = result.getImgUrl().split(",");
            for (String str : url) {
                if (!TextUtils.isEmpty(str)) {
                    list.add(Constants.IMG_HOST_URL + str);
                }
            }
        }
        result.setUrls(list);
        mIScanChunsunCodeResultView.showSuccessPage(entity.getResult());
    }

    @Override
    public void onError(String code) {
        mIScanChunsunCodeResultView.showErrorPage(code);
    }
}
