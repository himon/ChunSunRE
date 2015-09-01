package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.ui.view.IInviteRecordListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/22.
 */
public class InviteRecordListPresenter {

    private IInviteRecordListView mIInviteRecordListView;

    public InviteRecordListPresenter(IInviteRecordListView iInviteRecordListView) {
        this.mIInviteRecordListView = iInviteRecordListView;
    }

    public void initData(InviteRecordEntity.ResultEntity resultEntity) {

        List<InviteRecordEntity.ResultEntity.TichengEntity> ticheng = resultEntity.getTicheng();
        Map<Integer, InviteRecordEntity.ResultEntity.TichengEntity> maps = new HashMap<Integer, InviteRecordEntity.ResultEntity.TichengEntity>();

        for (int i = 0; i < ticheng.size(); i++) {
            InviteRecordEntity.ResultEntity.TichengEntity entity = ticheng.get(i);
            if (maps.containsKey(entity.getId())) {
                InviteRecordEntity.ResultEntity.TichengEntity item = maps.get(entity.getId());
                if (entity.getRemark().startsWith("好友")) {
                    double m = Double.parseDouble(item.getPrice()
                            .substring(1, item.getPrice().length()));
                    double p = Double.parseDouble(entity.getValue()
                            .substring(1, entity.getValue().length()));
                    DecimalFormat myformat = new DecimalFormat("0.00");
                    String str = myformat.format(m + p);
                    item.setPrice("+" + str);
                } else {
                    double m = Double.parseDouble(item.getValue()
                            .substring(1, item.getValue().length()));
                    double p = Double.parseDouble(entity.getValue()
                            .substring(1, entity.getValue().length()));
                    DecimalFormat myformat = new DecimalFormat("0.00");
                    String str = myformat.format(m + p);
                    item.setValue("+" + str);
                }
            } else {
                if (entity.getRemark().startsWith("好友")) {
                    double p = Double.parseDouble(entity.getValue()
                            .substring(1, entity.getValue().length()));
                    DecimalFormat myformat = new DecimalFormat("0.00");
                    String str = myformat.format(0.00 + p);
                    entity.setPrice("+" + str);
                    entity.setValue("+0.00");
                } else {
                    entity.setPrice("+0.00");
                }
                maps.put(entity.getId(), entity);
            }
        }

        resultEntity.getTicheng().clear();

        for (Map.Entry<Integer, InviteRecordEntity.ResultEntity.TichengEntity> entrySet : maps.entrySet()) {
            resultEntity.getTicheng().add(entrySet.getValue());
        }

        List<InviteRecordEntity.ResultEntity.BaseEntity> mList = new ArrayList<InviteRecordEntity.ResultEntity.BaseEntity>();

        InviteRecordEntity.ResultEntity.BaseEntity entity = new InviteRecordEntity.ResultEntity.BaseEntity();
        entity.setNick_name("获取奖励");
        entity.setPrice("￥"
                + (TextUtils.isEmpty(resultEntity.getAmount()) ? "0" : resultEntity
                .getAmount()));
        entity.setType(1);
        mList.add(entity);

        entity = new InviteRecordEntity.ResultEntity.BaseEntity();
        entity.setNick_name("代理用户");
        entity.setType(2);
        mList.add(entity);

        mList.addAll(resultEntity.getTicheng());

        entity = new InviteRecordEntity.ResultEntity.BaseEntity();
        entity.setNick_name("注册用户");
        entity.setType(2);
        mList.add(entity);

        mList.addAll(resultEntity.getZhuli());

        mIInviteRecordListView.setData(mList);
    }
}
