package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.BalanceEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.utils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/8/21.
 */
public class PhoneRechargeAdapter extends CommonAdapter<BalanceEntity.ResultEntity.CzPoundageEntity>{

    public PhoneRechargeAdapter(Context context, List<BalanceEntity.ResultEntity.CzPoundageEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, BalanceEntity.ResultEntity.CzPoundageEntity czPoundageEntity) {
        holder.setText(R.id.tv_box_price, czPoundageEntity.getAmount() + "").setText(R.id.tv_title, czPoundageEntity.getAmount() + "元话费充值卡").setText(R.id.tv_recharge_price, "￥：" + czPoundageEntity.getReal_amount());
    }
}