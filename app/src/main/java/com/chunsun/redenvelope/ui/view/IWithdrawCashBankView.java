package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 16:56
 * @des
 */
public interface IWithdrawCashBankView extends LoadingView{

    void setAddressInfo(DistrictEntity entity);

    void toConfirm(String[] data);
}
