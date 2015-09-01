package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceMode;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.model.impl.BalanceModeImpl;
import com.chunsun.redenvelope.presenter.OnGetUserAmountListener;
import com.chunsun.redenvelope.ui.activity.personal.BalanceActivity;
import com.chunsun.redenvelope.ui.view.IBalanceView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalancePresenter implements BaseSingleLoadedListener<BalanceEntity> {

    private IBalanceView mIBalanceView;
    private BalanceMode mBalanceMode;

    public BalancePresenter(IBalanceView iBalanceView) {
        this.mIBalanceView = iBalanceView;
        mBalanceMode = new BalanceModeImpl((BalanceActivity) iBalanceView);
    }

    public void loadData(String token) {
        mBalanceMode.loadData(token, this);
    }

    @Override
    public void onSuccess(BalanceEntity response) {
        BalanceEntity.ResultEntity result = response.getResult();

        List<SampleEntity> list = new ArrayList<SampleEntity>();

        SampleEntity open = new SampleEntity();
        open.setKey("领红包收入");
        open.setValue("￥" + result.getOpenhb_amount());
        open.setCount(Constants.BALANCE_TYPE_OPEN_HB);
        list.add(open);

        SampleEntity send = new SampleEntity();
        send.setKey("发红包支出");
        send.setValue("￥" + result.getSendhb_amount());
        send.setCount(Constants.BALANCE_TYPE_SEND_HB);
        list.add(send);

        SampleEntity cash = new SampleEntity();
        cash.setKey("已提现");
        cash.setValue("￥" + result.getCash_amount());
        cash.setCount(Constants.BALANCE_TYPE_CASH_AMOUNT);
        list.add(cash);

        SampleEntity cz = new SampleEntity();
        cz.setKey("已充话费");
        cz.setValue("￥" + result.getCz_amount());
        cz.setCount(Constants.BALANCE_TYPE_CZ_AMOUNT);
        list.add(cz);

        SampleEntity other = new SampleEntity();
        other.setKey("其他");
        other.setValue("￥" + result.getOther_amount());
        other.setCount(Constants.BALANCE_TYPE_OTHER);
        list.add(other);

        mIBalanceView.setData(result, list);
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
