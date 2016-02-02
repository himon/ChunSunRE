package com.chunsun.redenvelope.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WithdrawCashBankMode;
import com.chunsun.redenvelope.model.impl.WithdrawCashBankModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IWithdrawCashBankView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 16:56
 * @des
 */
public class WithdrawCashBankPresenter extends UserLosePresenter<IWithdrawCashBankView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IWithdrawCashBankView mIWithdrawCashBankView;
    private WithdrawCashBankMode mWithdrawCashBankMode;

    public WithdrawCashBankPresenter(IWithdrawCashBankView view) {
        this.mIWithdrawCashBankView = view;
        this.mWithdrawCashBankMode = new WithdrawCashBankModeImpl((Activity) view);
    }

    public void userCashInfo(String token) {
        this.mWithdrawCashBankMode.userCashInfo(token, this);
    }

    /**
     * 获取省市信息
     */
    public void initProvinceAndCity() {
        mIWithdrawCashBankView.showLoading();
        this.mWithdrawCashBankMode.initProvinceAndCity(this);
    }


    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_PROVINCE_AND_CITY:
                DistrictEntity entity = (DistrictEntity) data;
                mIWithdrawCashBankView.setAddressInfo(entity);
                break;
        }
    }

    /**
     * 获取银行信息
     *
     * @return
     */
    public ArrayList<SampleEntity> buildBankList() {
        ArrayList<SampleEntity> bankList = new ArrayList<>();

        // 农业银行，工商银行，建设银行，交通银行，中信银行，民生银行,招商银行，浦发银行
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("工商银行");
        item.setCheck(true);
        item.setType(Constants.EDIT_TYPE_BANK);
        bankList.add(item);

        return bankList;
    }

    /**
     * 验证提交的数据
     *
     * @param name
     * @param bankCardNum
     * @param bankName
     * @param bank
     * @param province
     * @param city
     */
    public void validate(String name, String bankCardNum, String bankName, SampleEntity bank, DistrictEntity.AreaEntity province, DistrictEntity.AreaEntity.CcEntity city) {
        if (TextUtils.isEmpty(name)) {
            ShowToast.Short("姓名不能为空！");
            return;
        }

        if (TextUtils.isEmpty(bankCardNum)) {
            ShowToast.Short("卡号不能为空！");
            return;
        }

        if (TextUtils.isEmpty(bankName)) {
            ShowToast.Short("开户行不能为空！");
            return;
        }

        if (bank == null) {
            ShowToast.Short("银行不能为空！");
            return;
        }

        if (province == null) {
            ShowToast.Short("省份不能为空！");
            return;
        }

        if (city == null) {
            ShowToast.Short("城市不能为空！");
            return;
        }

        String[] data = new String[]{name, bankCardNum, bankName, bank.getValue(), province.getP(), city.getC()};
        mIWithdrawCashBankView.toConfirm(data);
    }
}
