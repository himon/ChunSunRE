package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordListMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.model.impl.SendRedEnvelopeRecordListModeImpl;
import com.chunsun.redenvelope.presenter.OnGetSendRedEnvelopeRecordListListener;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordListActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordListView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SendRedEnvelopeRecordListPresenter implements BaseSingleLoadedListener<RedDetailSendRecordListEntity> {

    private ISendRedEnvelopeRecordListView mISendRedEnvelopeRecordListView;
    private SendRedEnvelopeRecordListMode mSendRedEnvelopeRecordListMode;

    public SendRedEnvelopeRecordListPresenter(ISendRedEnvelopeRecordListView sendRedEnvelopeRecordListView) {
        this.mISendRedEnvelopeRecordListView = sendRedEnvelopeRecordListView;
        mSendRedEnvelopeRecordListMode = new SendRedEnvelopeRecordListModeImpl((SendRedEnvelopeRecordListActivity) sendRedEnvelopeRecordListView);
    }

    public void loadData(String token, String type, int page_index) {
        mSendRedEnvelopeRecordListMode.loadData(token, type, page_index, this);
    }

    private void initData(List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> list) {

        List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> entities = new ArrayList<RedDetailSendRecordListEntity.ResultEntity.LogsEntity>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String temp = "";
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            RedDetailSendRecordListEntity.ResultEntity.LogsEntity entity = list.get(i);
            String date = entity.getAdd_time().split(" ")[0];
            if (!temp.equals(date)) {
                RedDetailSendRecordListEntity.ResultEntity.LogsEntity newEntity = new RedDetailSendRecordListEntity.ResultEntity.LogsEntity();
                Date d = new Date(date);
                newEntity.setAdd_time(dateFormat.format(d));
                newEntity.setId(i + count);
                newEntity.setType(0);
                entities.add(newEntity);
                temp = date;
                count++;
            }
        }

        for (RedDetailSendRecordListEntity.ResultEntity.LogsEntity item : list) {
            item.setType(1);
            Date time = new Date(item.getAdd_time());
            item.setAdd_time(timeFormat.format(time));
        }

        for (RedDetailSendRecordListEntity.ResultEntity.LogsEntity item : entities) {
            list.add(item.getId(), item);
        }
    }

    @Override
    public void onSuccess(RedDetailSendRecordListEntity entity) {
        RedDetailSendRecordListEntity.ResultEntity result = entity.getResult();
        List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> list = entity.getResult().getLogs();
        initData(list);
        mISendRedEnvelopeRecordListView.setData(result);
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
