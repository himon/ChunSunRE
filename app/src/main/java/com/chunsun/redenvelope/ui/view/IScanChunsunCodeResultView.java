package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.ScanCouponResultEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/5 16:34
 * @des
 */
public interface IScanChunsunCodeResultView {

    /**
     * 显示错误界面
     *
     * @param code
     */
    void showErrorPage(String code);

    /**
     * 显示成功界面
     * @param result
     */
    void showSuccessPage(ScanCouponResultEntity.ResultEntity result);

    /**
     * 券类使用成功
     * @param msg
     */
    void useCouponSuccess(String msg);
}
