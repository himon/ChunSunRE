package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.InteractiveEntity;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractivePlatformAdapter extends BaseAdapter{

    private List<InteractiveEntity.ResultEntity.ListEntity> mListCountry;
    private List<InteractiveEntity.ResultEntity.ListEntity> mListLocal;
    private int mCurrentCheckType = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private DisplayImageOptions mOptions;

    public void setData(List<InteractiveEntity.ResultEntity.ListEntity> listCountry, List<InteractiveEntity.ResultEntity.ListEntity> listLocal, int currentCheckType) {
        mListCountry = listCountry;
        mListLocal = listLocal;
        mCurrentCheckType = currentCheckType;
    }

    public InteractivePlatformAdapter(Context context, List<InteractiveEntity.ResultEntity.ListEntity> listCountry, List<InteractiveEntity.ResultEntity.ListEntity> listLocal, int currentCheckType) {
        mListCountry = listCountry;
        mListLocal = listLocal;
        mCurrentCheckType = currentCheckType;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions(8);
    }

    @Override
    public int getCount() {
        if (mCurrentCheckType == 0) {
            return mListCountry.size();
        } else {
            return mListLocal.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mCurrentCheckType == 0) {
            return mListCountry.get(position);
        } else {
            return mListLocal.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;

        switch (mCurrentCheckType) {
            case 0:
                if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
                    convertView = mInflater.inflate(R.layout.adapter_red_detail_comment_item, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                InteractiveEntity.ResultEntity.ListEntity country = mListCountry.get(position);
                holder.tvName.setText(country.getNick_name());
                holder.tvContent.setText(country.getContent());
                holder.tvTime.setText(country.getAdd_time());
                holder.tvFloor.setText(country.getFloor() + "楼");
                ImageLoader.getInstance().displayImage(country.getThumb_img_url(), holder.ivLogo, mOptions);
                break;
            case 1:
                if (convertView == null || !(convertView.getTag() instanceof ViewHolder2)) {
                    convertView = mInflater.inflate(R.layout.adapter_red_detail_comment_item, null);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                InteractiveEntity.ResultEntity.ListEntity local = mListLocal.get(position);
                holder2.tvName.setText(local.getNick_name());
                holder2.tvContent.setText(local.getContent());
                holder2.tvTime.setText(local.getAdd_time());
                holder2.tvFloor.setText(local.getFloor() + "楼");
                ImageLoader.getInstance().displayImage(local.getThumb_img_url(), holder2.ivLogo, mOptions);
                break;
        }
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.iv_head_logo)
        ImageView ivLogo;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_floor)
        TextView tvFloor;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 {

        @Bind(R.id.iv_head_logo)
        ImageView ivLogo;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_floor)
        TextView tvFloor;

        public ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
