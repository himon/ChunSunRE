package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceDetailListMode;
import com.chunsun.redenvelope.entities.json.BalanceListEntity;
import com.chunsun.redenvelope.model.impl.BalanceDetailListModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.BalanceDetailListActivity;
import com.chunsun.redenvelope.ui.view.IBalanceDetailListView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/8/20.
 */
public class BalanceDetailListPresenter implements BaseSingleLoadedListener<BalanceListEntity> {

    private IBalanceDetailListView mIBalanceDetailListView;
    private BalanceDetailListMode mBalanceDetailListMode;

    public BalanceDetailListPresenter(IBalanceDetailListView iBalanceDetailListView) {
        this.mIBalanceDetailListView = iBalanceDetailListView;
        this.mBalanceDetailListMode = new BalanceDetailListModeImpl((BalanceDetailListActivity) iBalanceDetailListView);
    }

    public void loadData(String token, String type, int page_index) {
        mBalanceDetailListMode.loadBalanceDetailLitsData(token, type, page_index, this);
    }

    private void initData(List<BalanceListEntity.ResultEntity.LogsEntity> list) {

        List<BalanceListEntity.ResultEntity.LogsEntity> entities = new ArrayList<BalanceListEntity.ResultEntity.LogsEntity>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String temp = "";
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            BalanceListEntity.ResultEntity.LogsEntity entity = list.get(i);
            String date = entity.getAdd_time().split(" ")[0];
            if (!temp.equals(date)) {
                BalanceListEntity.ResultEntity.LogsEntity newEntity = new BalanceListEntity.ResultEntity.LogsEntity();
                Date d = new Date(date);
                newEntity.setAdd_time(dateFormat.format(d));
                //标示添加到list的哪个位置
                newEntity.setStatus(i + count);
                newEntity.setType(0);
                entities.add(newEntity);
                temp = date;
                count++;
            }
        }

        /**
         * 把日期 + 时间 转化成 时间
         */
        for (BalanceListEntity.ResultEntity.LogsEntity item : list) {
            item.setType(1);
            Date time = new Date(item.getAdd_time());
            item.setAdd_time(timeFormat.format(time));
        }

        for (BalanceListEntity.ResultEntity.LogsEntity item : entities) {
            list.add(item.getStatus(), item);
        }
    }

    @Override
    public void onSuccess(BalanceListEntity response) {
        BalanceListEntity.ResultEntity result = response.getResult();
        List<BalanceListEntity.ResultEntity.LogsEntity> list = result.getLogs();
        initData(list);
        mIBalanceDetailListView.setData(result);
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {

    }
}
