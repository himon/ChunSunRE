package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.ui.base.SelectListBase;

import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SelectListInfoAdaper extends CommonAdapter<SelectListBase> {

    public SelectListInfoAdaper(Context context, List<SelectListBase> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, SelectListBase entity) {

        if (entity instanceof AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) {
            AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySecondsRateEntity = (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) entity;
            holder.setText(R.id.tv_content, delaySecondsRateEntity.getDelay_seconds() + "秒");
        }else if(entity instanceof SampleEntity){
            SampleEntity sampleEntity = (SampleEntity) entity;
            holder.setText(R.id.tv_content, sampleEntity.getValue());
        }else if(entity instanceof DistrictEntity.AreaEntity){
            DistrictEntity.AreaEntity areaEntity = (DistrictEntity.AreaEntity) entity;
            holder.setText(R.id.tv_content, areaEntity.getP());
        }else if(entity instanceof DistrictEntity.AreaEntity.CcEntity){
            DistrictEntity.AreaEntity.CcEntity ccEntity = (DistrictEntity.AreaEntity.CcEntity) entity;
            holder.setText(R.id.tv_content, ccEntity.getC());
        }

        if (entity.isCheck()) {
            holder.getView(R.id.iv_check).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.iv_check).setVisibility(View.INVISIBLE);
        }
    }
}
