package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.ui.base.adapter.CommonAdapter;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:55
 * @des ${TODO}
 */
public class RepeatRedDetailAdapter extends CommonAdapter<RedDetailCommentEntity.ResultEntity.ListEntity> {

    private DisplayImageOptions mOptions;
    private Context mContext;
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickList;

    public RepeatRedDetailAdapter(Context context, List<RedDetailCommentEntity.ResultEntity.ListEntity> datas, int layoutId, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        super(context, datas, layoutId);
        this.mContext = context;
        this.mOnClickListener = onClickListener;
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions(8);
        this.mOnLongClickList = onLongClickListener;
    }

    @Override
    public void convert(ViewHolder holder, final RedDetailCommentEntity.ResultEntity.ListEntity listEntity) {

        if (TextUtils.isEmpty(listEntity.getAt_user_name())) {
            holder.getView(R.id.tv_at).setVisibility(View.GONE);
            holder.getView(R.id.tv_at_user).setVisibility(View.GONE);
        } else {
            TextView at = holder.getView(R.id.tv_at);
            at.setVisibility(View.VISIBLE);
            TextView user = holder.getView(R.id.tv_at_user);
            user.setVisibility(View.VISIBLE);
            at.setText("@了");
            user.setText(listEntity.getAt_user_name());
        }

        holder.setText(R.id.tv_name, listEntity.getNick_name()).setText(R.id.tv_content, listEntity.getContent()).setText(R.id.tv_time, listEntity.getAdd_time()).setText(R.id.tv_floor, listEntity.getFloor() + "");
        ImageView ivLogo = holder.getView(R.id.iv_head_logo);
        ivLogo.setTag(R.id.tag_first, listEntity.getId());
        ivLogo.setTag(R.id.tag_second, listEntity.getNick_name());
        ImageLoader.getInstance().displayImage(listEntity.getThumb_img_url(), ivLogo, mOptions);
        ivLogo.setOnClickListener(mOnClickListener);
        ivLogo.setOnLongClickListener(mOnLongClickList);
    }
}
