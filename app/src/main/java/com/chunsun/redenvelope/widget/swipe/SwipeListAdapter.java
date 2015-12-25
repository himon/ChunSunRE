package com.chunsun.redenvelope.widget.swipe;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.entities.json.RedDetailSendRecordListEntity;
import com.chunsun.redenvelope.ui.base.adapter.SampleBaseAdapter;

import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SwipeListAdapter extends SampleBaseAdapter<RedDetailSendRecordListEntity.ResultEntity.LogsEntity, ListView> {

    private LayoutInflater mInflater;
    private HashSet<SwipeLayout> mUnClosedLayouts = new HashSet<SwipeLayout>();
    private OnClickListener mOnActionClick;
    private OnClickListener mOnItemClicklistener;

    public SwipeListAdapter(Context context, List<RedDetailSendRecordListEntity.ResultEntity.LogsEntity> list, OnClickListener onClickListener, OnClickListener onItemClicklistener) {
        super(context, list);
        this.mInflater = LayoutInflater.from(context);
        this.mOnActionClick = onClickListener;
        this.mOnItemClicklistener = onItemClicklistener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        ViewHolder2 mHolder2 = null;

        RedDetailSendRecordListEntity.ResultEntity.LogsEntity entity = list.get(position);
        int type = getItemViewType(position);

        if (convertView != null) {
            switch (type) {
                case 0:
                    mHolder = (ViewHolder) convertView.getTag();
                    break;
                case 1:
                    mHolder2 = (ViewHolder2) convertView.getTag();
                    break;
            }
        } else {
            switch (type) {
                case 0:
                    convertView = mInflater.inflate(R.layout.adapter_send_red_record_item_time_view, null);
                    mHolder = new ViewHolder(convertView);
                    convertView.setTag(mHolder);
                    break;
                case 1:
                    convertView = (SwipeLayout) mInflater.inflate(R.layout.adapter_list_item_swipe, null);
                    mHolder2 = new ViewHolder2(convertView);
                    convertView.setTag(mHolder2);
                    break;
            }
        }

        switch (type) {
            case 0:
                mHolder.date.setText(entity.getAdd_time());
                break;
            case 1:
                SwipeLayout view = (SwipeLayout) convertView;

                view.close(false, false);

                view.getFrontView().setTag(entity.getId());
                view.getFrontView().setOnClickListener(mOnItemClicklistener);

                view.setSwipeListener(mSwipeListener);
                if(TextUtils.isEmpty(entity.getStatus_title())){
                    mHolder2.status.setText(entity.getStatus_name());
                }else {
                    mHolder2.status.setText(entity.getStatus_title());
                }
                mHolder2.title.setText(entity.getTitle());
                mHolder2.type.setText(entity.getType_title());
                mHolder2.time.setText(entity.getAdd_time());
                mHolder2.mButtonDel.setTag(entity.getId());
                mHolder2.mButtonDel.setOnClickListener(mOnActionClick);
                break;
        }
        return convertView;
    }

    SwipeLayout.SwipeListener mSwipeListener = new SwipeLayout.SwipeListener() {
        @Override
        public void onOpen(SwipeLayout swipeLayout) {
            mUnClosedLayouts.add(swipeLayout);
        }

        @Override
        public void onClose(SwipeLayout swipeLayout) {
            mUnClosedLayouts.remove(swipeLayout);
        }

        @Override
        public void onStartClose(SwipeLayout swipeLayout) {
        }

        @Override
        public void onStartOpen(SwipeLayout swipeLayout) {
            closeAllLayout();
            mUnClosedLayouts.add(swipeLayout);
        }

    };

    public int getUnClosedCount() {
        return mUnClosedLayouts.size();
    }

    public void closeAllLayout() {
        if (mUnClosedLayouts.size() == 0)
            return;

        for (SwipeLayout l : mUnClosedLayouts) {
            l.close(true, false);
        }
        mUnClosedLayouts.clear();
    }

    static class ViewHolder {

        @Bind(R.id.tv_red_date)
        TextView date;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 {

        @Bind(R.id.tv_red_title)
        TextView title;
        @Bind(R.id.tv_red_time)
        TextView time;
        @Bind(R.id.tv_red_type)
        TextView type;
        @Bind(R.id.tv_red_status)
        TextView status;
        @Bind(R.id.bt_delete)
        Button mButtonDel;

        public ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }

}