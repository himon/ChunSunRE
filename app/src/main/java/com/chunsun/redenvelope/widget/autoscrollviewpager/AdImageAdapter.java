package com.chunsun.redenvelope.widget.autoscrollviewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.utils.DensityUtils;
import com.chunsun.redenvelope.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class AdImageAdapter extends BaseAdapter {
    public List<RedAutoAdEntity.ResultEntity.AdvertEntity> imageUrls; // 图片地址list
    private Context context;
    private DisplayImageOptions mOptions;
    public static int size;
    private int height;

    public AdImageAdapter(List<RedAutoAdEntity.ResultEntity.AdvertEntity> imageUrls, Context context) {
        this.imageUrls = imageUrls;
        size = imageUrls.size();
        this.context = context;
        height = (int) (DensityUtils.getDensity(context) * 120);
        mOptions = ImageLoaderHelper.getInstance(context).getDisplayOptions();
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public RedAutoAdEntity.ResultEntity.AdvertEntity getItem(int position) {
        return imageUrls.get(position % size);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (convertView == null) {
            holderView = new HolderView();
            // 实例化convertView
            convertView = holderView.ivPic = new ImageView(context);
            // 设置缩放比例：保持原样
            holderView.ivPic.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holderView.ivPic.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, height));
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(getItem(position).getImg_url(), holderView.ivPic, mOptions);
        return convertView;
    }

    class HolderView {
        private ImageView ivPic;
    }
}
