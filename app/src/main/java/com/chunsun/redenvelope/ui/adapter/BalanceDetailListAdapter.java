package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.entities.json.BalanceListEntity;
import com.chunsun.redenvelope.ui.base.adapter.SampleBaseAdapter;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/20.
 */
public class BalanceDetailListAdapter extends SampleBaseAdapter<BalanceListEntity.ResultEntity.LogsEntity, ListView> {

    private LayoutInflater mInflater;
    private String mType;

    public BalanceDetailListAdapter(Context context, List<BalanceListEntity.ResultEntity.LogsEntity> list) {
        super(context, list);
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 返回adapter需要展示的几种类型
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BalanceListEntity.ResultEntity.LogsEntity entity = list.get(position);
        int type = getItemViewType(position);

        ViewHolder holder = null;
        ViewHolder2 holder2 = null;

        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = mInflater.inflate(R.layout.adapter_send_red_record_item_time_view, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.adapter_balance_detail_list_item, null);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
            }
        } else {
            switch (type) {
                case 0:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case 1:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case 0:
                holder.date.setText(entity.getAdd_time());
                break;
            case 1:
                holder2.status.setText(entity.getStatus_title());
                holder2.title.setText(entity.getRemark());
                holder2.price.setText(entity.getValue());
                holder2.time.setText(entity.getAdd_time());
                break;
        }

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.tv_red_date)
        TextView date;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 {

        @Bind(R.id.tv_type_name)
        TextView title;
        @Bind(R.id.tv_time)
        TextView time;
        @Bind(R.id.tv_withdrawal_price)
        TextView price;
        @Bind(R.id.tv_status)
        TextView status;

        public ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
