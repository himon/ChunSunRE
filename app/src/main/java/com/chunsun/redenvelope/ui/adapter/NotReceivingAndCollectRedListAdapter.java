package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;
import com.chunsun.redenvelope.utils.adapter.ViewHolder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2015/8/15.
 * 未领取红包和收藏红包的adapter
 */
public class NotReceivingAndCollectRedListAdapter extends CommonAdapter<RedDetailUnReceiveAndCollectEntity.ResultEntity> {

    public NotReceivingAndCollectRedListAdapter(Context context, List<RedDetailUnReceiveAndCollectEntity.ResultEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, RedDetailUnReceiveAndCollectEntity.ResultEntity resultEntity) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String datetime;
        if(TextUtils.isEmpty(resultEntity.getGrab_time())){
            datetime = resultEntity.getSend_time();
        }else{
            datetime = resultEntity.getGrab_time();
        }
        Date date = new Date(datetime);
        String time = timeFormat.format(date);

        if (Constants.RED_DETAIL_TYPE_LINK.equals(resultEntity.getType())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_link);
        } else if (Constants.RED_DETIAL_TYPE_LEFT.equals(resultEntity.getType()) || Constants.RED_DETIAL_TYPE_LEFT.equals(resultEntity.getType())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_type);
        } else if (Constants.RED_DETIAL_TYPE_COMPANY.equals(resultEntity.getType())) {
            holder.setImageResource(R.id.iv_range, R.drawable.img_icon_range);
        }

        //判断总金额是否大于500
        DecimalFormat format = new DecimalFormat("0.00");
        try {
            Number price = format.parse(resultEntity.getPayable_amount() + "");
            TextView textView = holder.getView(R.id.tv_price);
            if (price.doubleValue() >= 500) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //设置标题为黑色
        TextView textView = holder.getView(R.id.tv_title);
        textView.setTextColor(mContext.getResources().getColor(R.color.font_black));

        holder.setText(R.id.tv_title, resultEntity.getTitle()).setText(R.id.tv_time, time).setText(R.id.tv_author, resultEntity.getNick_name()).setText(R.id.tv_range, resultEntity.getType_title()).setText(R.id.tv_reply, resultEntity.getComment_count() + "").setText(R.id.tv_price, "￥" + resultEntity.getPayable_amount());
        holder.setImageResource(R.id.iv_icon, resultEntity.getCover_img_url());
    }
}
