package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.utils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SelectListInfoAdaper extends CommonAdapter<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> {

    public SelectListInfoAdaper(Context context, List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySecondsRateEntity) {
        holder.setText(R.id.tv_content, delaySecondsRateEntity.getDelay_seconds() + "秒");
        if (delaySecondsRateEntity.isCheck()) {
            holder.getView(R.id.iv_check).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.iv_check).setVisibility(View.INVISIBLE);
        }
    }
}
