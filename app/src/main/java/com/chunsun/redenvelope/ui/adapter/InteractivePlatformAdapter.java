package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractivePlatformAdapter extends BaseAdapter {

    private List<InteractiveEntity.ResultEntity.ListEntity> mListCountry;
    private List<InteractiveEntity.ResultEntity.ListEntity> mListLocal;
    private int mCurrentCheckType = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private DisplayImageOptions mOptions;
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;

    public void setData(List<InteractiveEntity.ResultEntity.ListEntity> listCountry, List<InteractiveEntity.ResultEntity.ListEntity> listLocal, int currentCheckType) {
        mListCountry = listCountry;
        mListLocal = listLocal;
        mCurrentCheckType = currentCheckType;
    }

    public InteractivePlatformAdapter(Context context, List<InteractiveEntity.ResultEntity.ListEntity> listCountry, List<InteractiveEntity.ResultEntity.ListEntity> listLocal, int currentCheckType, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        mListCountry = listCountry;
        mListLocal = listLocal;
        mCurrentCheckType = currentCheckType;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions(8);
        this.mOnClickListener = onClickListener;
        this.mOnLongClickListener = onLongClickListener;
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

        InteractiveEntity.ResultEntity.ListEntity entity = null;

        switch (mCurrentCheckType) {
            case 0:
                entity = mListCountry.get(position);
                break;
            case 1:
                entity = mListLocal.get(position);
                break;
        }

        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = mInflater.inflate(R.layout.adapter_red_detail_comment_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(entity.getNick_name());
        holder.tvContent.setText(entity.getContent());
        holder.tvTime.setText(entity.getAdd_time());
        holder.tvFloor.setText(entity.getFloor() + "楼");
        if(TextUtils.isEmpty(entity.getAt_user_name())){
            holder.tvAt.setVisibility(View.GONE);
            holder.tvAtUser.setVisibility(View.GONE);
        }else{
            holder.tvAt.setVisibility(View.VISIBLE);
            holder.tvAtUser.setVisibility(View.VISIBLE);
            holder.tvAt.setText("@了");
            holder.tvAtUser.setText(entity.getAt_user_name());
        }

        /**
         * 判断是否是系统用户
         */
        if (Constants.SYSTEM_USER_ID.equals(entity.getId() + "")) {
            holder.tvContent.setTextColor(mContext.getResources().getColor(R.color.global_red));
            holder.ivLogo.setOnClickListener(null);
        } else {
            holder.tvContent.setTextColor(mContext.getResources().getColor(R.color.red_detail_comment_font_gray));
            holder.ivLogo.setOnClickListener(mOnClickListener);
            holder.ivLogo.setOnLongClickListener(mOnLongClickListener);
            holder.ivLogo.setTag(R.id.tag_first, entity.getId() + "");
            holder.ivLogo.setTag(R.id.tag_second, entity.getNick_name());
        }
        ImageLoader.getInstance().displayImage(entity.getThumb_img_url(), holder.ivLogo, mOptions);

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
        @Bind(R.id.tv_at)
        TextView tvAt;
        @Bind(R.id.tv_at_user)
        TextView tvAtUser;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
