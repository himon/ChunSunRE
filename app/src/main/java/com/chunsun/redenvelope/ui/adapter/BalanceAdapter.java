package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.utils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalanceAdapter extends CommonAdapter<SampleEntity> {

    public BalanceAdapter(Context context, List<SampleEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, SampleEntity sampleEntity) {
        holder.setText(R.id.tv_text, sampleEntity.getKey()).setText(R.id.tv_code, sampleEntity.getValue());
    }
}