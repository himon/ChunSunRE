package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.BalanceMode;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.model.impl.BalanceModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.ui.view.IBalanceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalancePresenter extends BaseMultiLoadedListenerImpl<BalanceEntity> {

    private IBalanceView mIBalanceView;
    private BalanceMode mBalanceMode;

    public BalancePresenter(IBalanceView iBalanceView) {
        this.mIBalanceView = iBalanceView;
        mBalanceMode = new BalanceModeImpl((WalletActivity) iBalanceView);
    }

    public void loadData(String token) {
        mBalanceMode.loadWalletData(token, this);
    }

    public void loadAmountSuccess(BalanceEntity response) {
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

        SampleEntity repeat = new SampleEntity();
        repeat.setKey("转发收益");
        repeat.setValue("￥" + result.getForward_amount());
        repeat.setCount(Constants.BALANCE_TYPE_FORWARD);
        list.add(repeat);

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

        SampleEntity jl = new SampleEntity();
        jl.setKey("奖励金额");
        jl.setValue("￥" + result.getReward_amount());
        jl.setCount(Constants.BALANCE_TYPE_REWARD);
        list.add(jl);

        SampleEntity tc = new SampleEntity();
        tc.setKey("提成收益");
        tc.setValue("￥" + result.getCommission_amount());
        tc.setCount(Constants.BALANCE_TYPE_COMMISSION);
        list.add(tc);

        SampleEntity other = new SampleEntity();
        other.setKey("其他");
        other.setValue("￥" + result.getOther_amount());
        other.setCount(Constants.BALANCE_TYPE_OTHER);
        list.add(other);

        mIBalanceView.setData(result, list);
    }

    @Override
    public void onSuccess(int event_tag, BalanceEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_AMOUNT:
                loadAmountSuccess(data);
                break;
        }
    }
}
