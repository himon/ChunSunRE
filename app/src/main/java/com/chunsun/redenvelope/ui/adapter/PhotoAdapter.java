package com.chunsun.redenvelope.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPagerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

/**
 * Created by donglua on 15/5/31.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<String> photoPaths = new ArrayList<>();
    private List<Photo> mPhotos = new ArrayList<>();
    private LayoutInflater inflater;

    private Context mContext;

    public void setPhotos(List<Photo> photos) {
        this.mPhotos = photos;
    }

    public PhotoAdapter(Context mContext, ArrayList<String> photoPaths, List<Photo> photos) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        this.mPhotos = photos;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(me.iwf.photopicker.R.layout.item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {

        if (TextUtils.isEmpty(photoPaths.get(position))) {//加号

            holder.ivPhoto.setImageResource(R.drawable.img_add_img);

            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
                    intent.setPhotoCount(8);
                    intent.setShowCamera(true);
                    intent.setSelectedPhotos((ArrayList<Photo>) mPhotos);
                    ((CreateAdContentActivity) mContext).startActivityForResult(intent, Constants.REQUEST_CODE);
                }
            });

        } else {

            Uri uri;

            if (!photoPaths.get(position).startsWith("http")) {
                uri = Uri.fromFile(new File(photoPaths.get(position)));
            } else {
                uri = Uri.parse(photoPaths.get(position));
            }

            Glide.with(mContext)
                    .load(uri)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_default_capture)
                    .error(R.drawable.img_default_error)
                    .into(holder.ivPhoto);

            holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PhotoPagerActivity.class);
                    intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, position);
                    if (TextUtils.isEmpty(photoPaths.get(photoPaths.size() - 1))) {
                        photoPaths.remove(photoPaths.size() - 1);
                    }
                    intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, photoPaths);
                    if (mContext instanceof CreateAdContentActivity) {
                        ((CreateAdContentActivity) mContext).previewPhoto(intent);
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return photoPaths.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private View vSelected;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(me.iwf.photopicker.R.id.iv_photo);
            vSelected = itemView.findViewById(me.iwf.photopicker.R.id.v_selected);
            vSelected.setVisibility(View.GONE);
        }
    }
}
