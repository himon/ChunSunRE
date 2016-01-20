package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.entities.json.AtMessageEntity;
import com.chunsun.redenvelope.ui.base.adapter.CommonAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/20 14:54
 * @des
 */
public class AtMeAdapter extends CommonAdapter<AtMessageEntity.ResultEntity> {

    private Context mContext;
    private final SimpleDateFormat timeFormat;
    private final SimpleDateFormat dateFormat;

    public AtMeAdapter(Context context, List<AtMessageEntity.ResultEntity> datas, int layoutId) {
        super(context, datas, layoutId);
        this.mContext = context;
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
    }

    @Override
    public void convert(ViewHolder holder, AtMessageEntity.ResultEntity entity) {

        Date date = new Date(entity.getAddTime());
        Date curDate = new Date();
        String curDateStr = dateFormat.format(curDate);
        String dateStr = dateFormat.format(date);
        String time = "";
        if (curDateStr.equals(dateStr)) {
            time = timeFormat.format(date);
        } else {
            time = dateStr;
        }

        holder.setText(R.id.tv_name, entity.getNickName()).setText(R.id.tv_date, time).setText(R.id.tv_type, "（来自：" + entity.getTypeName() + "）").setText(R.id.tv_comment, entity.getContent());
        holder.setHeadImageResource(R.id.iv_head_logo, entity.getImgUrl());

        if(entity.getType() == 3){
            holder.setImageResource(R.id.iv_cover_img, R.drawable.img_from_interactive_platform);
        }else {
            holder.setImageResource(R.id.iv_cover_img, entity.getCoverImgThumb());
        }
    }
}
