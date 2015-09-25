package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.MeFragmentEntity;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/24.
 */
public class MeFragmentAdapter extends BaseAdapter {


    private ArrayList<MeFragmentEntity> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    private DisplayImageOptions mOptions;

    public MeFragmentAdapter(Context context, ArrayList<MeFragmentEntity> list) {
        this.mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions(8);
    }

    /**
     * 返回adapter需要展示几种类型
     */
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MeFragmentEntity entity = mList.get(position);
        int type = getItemViewType(position);

        ViewHolder holder = null;
        ViewHolder2 holder2 = null;

        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = mInflater.inflate(R.layout.adapter_me_fragment_top, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.adapter_me_fragment, null);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
                case 2:
                    convertView = mInflater.inflate(R.layout.layout_gray_block, parent, false);
                    break;
                case 3:
                    convertView = mInflater.inflate(R.layout.layout_gray_half_line, parent, false);
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
                ImageLoader.getInstance().displayImage(entity.getImg(), holder.ivIcon, mOptions);
                holder.tvName.setText(entity.getText());
                holder.tvCode.setText(entity.getCode());
                break;
            case 1:
                holder2.ivIcon.setImageResource(entity.getRes());
                holder2.tvText.setText(entity.getText());
                holder2.tvCode.setText(entity.getCode());
                break;
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvCode;

        public ViewHolder(View view) {
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvCode = (TextView) view.findViewById(R.id.tv_code);
        }
    }

    static class ViewHolder2 {
        ImageView ivIcon;
        TextView tvText;
        TextView tvCode;

        public ViewHolder2(View view) {
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.tvText = (TextView) view.findViewById(R.id.tv_text);
            this.tvCode = (TextView) view.findViewById(R.id.tv_code);
        }
    }
}
