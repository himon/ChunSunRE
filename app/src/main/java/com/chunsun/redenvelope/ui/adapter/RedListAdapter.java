package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.utils.ViewHolder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2015/8/5.
 * 红包列表Adapter
 */
public class RedListAdapter extends CommonAdapter<RedListDetailEntity.ResultEntity.PoolEntity> {

    private Context mContext;

    public RedListAdapter(Context context, List<RedListDetailEntity.ResultEntity.PoolEntity> datas, int layoutId) {
        super(context, datas, layoutId);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder holder, RedListDetailEntity.ResultEntity.PoolEntity poolEntity) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        Date date = new Date(poolEntity.getBegin_time());
        Date curDate = new Date();

        String curDateStr = DateFormat.format(curDate);
        String dateStr = DateFormat.format(date);
        String time = "";
        if (curDateStr.equals(dateStr)) {
            time = timeFormat.format(date);
        } else {
            time = dateStr;
        }

        if ("链接".equals(poolEntity.getRange())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_link);
        } else if ("生活".equals(poolEntity.getRange()) || "企业".equals(poolEntity.getRange())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_type);
        } else if ("附近".equals(poolEntity.getRange())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_range);
        }

        //判断总金额是否大于500
        DecimalFormat format = new DecimalFormat("0.00");
        try {
            Number price = format.parse(poolEntity.getPayable_amount());
            TextView textView = holder.getView(R.id.tv_price);
            if (price.doubleValue() >= 500) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (poolEntity.getHb_status()) {
            TextView textView = holder.getView(R.id.tv_title);
            textView.setTextColor(mContext.getResources().getColor(R.color.font_black));
        } else {
            TextView textView = holder.getView(R.id.tv_title);
            textView.setTextColor(mContext.getResources().getColor(R.color.font_gray2));
        }

        holder.setText(R.id.tv_title, poolEntity.getTitle()).setText(R.id.tv_time, time).setText(R.id.tv_author, poolEntity.getNick_name()).setText(R.id.tv_range, poolEntity.getRange()).setText(R.id.tv_reply, poolEntity.getComment_count() + "").setText(R.id.tv_price, "￥" + poolEntity.getPayable_amount());
        holder.setImageResource(R.id.iv_icon, poolEntity.getCover_img_url());
    }
}