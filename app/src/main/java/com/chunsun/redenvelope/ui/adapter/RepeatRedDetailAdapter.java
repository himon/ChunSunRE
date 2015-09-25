package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.ui.base.CommonAdapter;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:55
 * @des ${TODO}
 */
public class RepeatRedDetailAdapter extends CommonAdapter<RedDetailCommentEntity.ResultEntity.ListEntity> {

    public RepeatRedDetailAdapter(Context context, List<RedDetailCommentEntity.ResultEntity.ListEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, RedDetailCommentEntity.ResultEntity.ListEntity listEntity) {

        holder.setText(R.id.tv_name, listEntity.getNick_name());
    }
}
