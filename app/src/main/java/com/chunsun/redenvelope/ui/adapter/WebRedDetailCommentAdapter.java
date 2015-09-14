package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/14.
 */
public class WebRedDetailCommentAdapter extends BaseAdapter {

    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment;
    private List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> mListRedRecord;
    private int mCurrentCheckType = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private DisplayImageOptions mOptions;

    public WebRedDetailCommentAdapter(Context context, List<RedDetailCommentEntity.ResultEntity.ListEntity> listComment, List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> listRedRecord, int currentCheckType) {
        this.mListComment = listComment;
        this.mListRedRecord = listRedRecord;
        this.mCurrentCheckType = currentCheckType;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions(20);
    }

    public void setData(List<RedDetailCommentEntity.ResultEntity.ListEntity> listComment, List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> listRedRecord, int currentCheckType) {
        this.mListComment = listComment;
        this.mListRedRecord = listRedRecord;
        this.mCurrentCheckType = currentCheckType;
    }

    @Override
    public int getCount() {
        if (mCurrentCheckType == 0) {
            return mListComment.size();
        } else {
            return mListRedRecord.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mCurrentCheckType == 0) {
            return mListComment.get(position);
        } else {
            return mListRedRecord.get(position);
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
                RedDetailCommentEntity.ResultEntity.ListEntity comment = mListComment.get(position);
                holder.tvName.setText(comment.getNick_name());
                holder.tvContent.setText(comment.getContent());
                holder.tvTime.setText(comment.getAdd_time());
                holder.tvFloor.setText(comment.getFloor() + "楼");
                ImageLoader.getInstance().displayImage(comment.getThumb_img_url(), holder.ivLogo, mOptions);
                break;
            case 1:
                if (convertView == null || !(convertView.getTag() instanceof ViewHolder2)) {
                    convertView = mInflater.inflate(R.layout.adapter_red_detail_red_record_item, null);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity record = mListRedRecord.get(position);
                holder2.tvName.setText(record.getNick_name());
                holder2.tvPrice.setText(record.getPrice() + "");
                holder2.tvTime.setText(record.getGrab_time());
                ImageLoader.getInstance().displayImage(record.getThumb_img_url(), holder2.ivLogo, mOptions);
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
        @Bind(R.id.tv_red_name)
        TextView tvName;
        @Bind(R.id.tv_red_time)
        TextView tvTime;
        @Bind(R.id.tv_red_price)
        TextView tvPrice;

        public ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
