package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.entities.json.InviteRecordEntity;
import com.chunsun.redenvelope.ui.base.adapter.SampleBaseAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/22.
 */
public class InviteRecordListAdapter extends SampleBaseAdapter<InviteRecordEntity.ResultEntity.BaseEntity, ListView> {

    private LayoutInflater mInflater;

    public InviteRecordListAdapter(Context context, List<InviteRecordEntity.ResultEntity.BaseEntity> list) {
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
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InviteRecordEntity.ResultEntity.BaseEntity entity = list.get(position);
        int type = getItemViewType(position);

        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;

        if (convertView == null) {

            switch (type) {
                case 0:
                    convertView = mInflater.inflate(R.layout.adapter_invite_record_list_item, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                    break;
                case 1:
                    convertView = mInflater.inflate(
                            R.layout.adapter_invite_record_list_amount, null);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
                case 2:
                    convertView = mInflater.inflate(
                            R.layout.adapter_invite_record_list_title, null);
                    holder3 = new ViewHolder3(convertView);
                    convertView.setTag(holder3);
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
                case 2:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case 0:
                if (entity instanceof InviteRecordEntity.ResultEntity.TichengEntity) {//非代理
                    holder.tv_ad_award.setText(entity.getValue());
                    holder.tv_award.setText(entity.getPrice());
                    holder.tv_ad_award.setVisibility(View.VISIBLE);
                    holder.tv_ad_remark.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_award.setText(entity.getValue());
                    holder.tv_ad_award.setVisibility(View.GONE);
                    holder.tv_ad_remark.setVisibility(View.GONE);
                }
                holder.tv_name.setText(entity.getNick_name());
                break;
            case 1:
                holder2.tv_total.setText(entity.getPrice());
                break;
            case 2:
                holder3.tv_title.setText(entity.getNick_name());
                break;
        }
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_award)
        TextView tv_award;
        @Bind(R.id.tv_ad_award)
        TextView tv_ad_award;
        @Bind(R.id.tv_ad_remark)
        TextView tv_ad_remark;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 {
        TextView tv_total;

        public ViewHolder2(View view) {
            tv_total = (TextView) view.findViewById(R.id.tv_total);
        }
    }

    static class ViewHolder3 {
        TextView tv_title;

        public ViewHolder3(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
