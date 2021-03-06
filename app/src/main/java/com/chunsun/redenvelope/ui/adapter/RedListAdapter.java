package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.ui.base.adapter.CommonAdapter;

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
    private final SimpleDateFormat timeFormat;
    private final SimpleDateFormat dateFormat;

    public RedListAdapter(Context context, List<RedListDetailEntity.ResultEntity.PoolEntity> datas, int layoutId) {
        super(context, datas, layoutId);
        this.mContext = context;
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
    }

    @Override
    public void convert(ViewHolder holder, RedListDetailEntity.ResultEntity.PoolEntity poolEntity) {

        Date date = new Date(poolEntity.getBegin_time());
        Date curDate = new Date();

        String curDateStr = dateFormat.format(curDate);
        String dateStr = dateFormat.format(date);
        String time = "";
        if (curDateStr.equals(dateStr)) {
            time = timeFormat.format(date);
        } else {
            time = dateStr;
        }

        TextView type = holder.getView(R.id.tv_range);
        type.setText(poolEntity.getTypeName());
        ImageView icon = holder.getView(R.id.iv_quan_icon);

        if (Constants.RED_DETAIL_TYPE_LINK == poolEntity.getType() || Constants.RED_DETAIL_TYPE_CIRCLE_LINK == poolEntity.getType()) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_link);
            icon.setVisibility(View.GONE);
        } else if (Constants.RED_DETAIL_TYPE_LEFT == poolEntity.getType() || Constants.RED_DETAIL_TYPE_COMPANY == poolEntity.getType() || Constants.RED_DETAIL_TYPE_CIRCLE == poolEntity.getType()) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_type);
            icon.setVisibility(View.GONE);
        } else if (Constants.RED_DETAIL_TYPE_NEAR == poolEntity.getType()) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_range);
            type.setText(poolEntity.getRangeString());
            icon.setVisibility(View.GONE);
        } else if (Constants.RED_DETAIL_TYPE_REPEAT == poolEntity.getType()) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_repeat);
            icon.setVisibility(View.GONE);
        } else if (Constants.RED_DETAIL_TYPE_COUPON == poolEntity.getType()) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_juan);
            type.setText(poolEntity.getRangeString());
            icon.setVisibility(View.VISIBLE);
        }else if(Constants.RED_DETAIL_TYPE_lUCK == poolEntity.getType() || Constants.RED_DETAIL_TYPE_lUCK_LINK == poolEntity.getType()){
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_luck);
            icon.setVisibility(View.GONE);
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

        if (poolEntity.isHb_status()) {
            TextView textView = holder.getView(R.id.tv_title);
            textView.setTextColor(mContext.getResources().getColor(R.color.font_black));
        } else {
            TextView textView = holder.getView(R.id.tv_title);
            textView.setTextColor(mContext.getResources().getColor(R.color.font_gray2));
        }

        holder.setText(R.id.tv_title, poolEntity.getTitle()).setText(R.id.tv_time, time).setText(R.id.tv_author, "来自：" + poolEntity.getNick_name()).setText(R.id.tv_reply, poolEntity.getComment_count() + "").setText(R.id.tv_price, "￥" + poolEntity.getPayable_amount());
        holder.setImageResource(R.id.iv_icon, poolEntity.getCover_img_url());
    }
}
