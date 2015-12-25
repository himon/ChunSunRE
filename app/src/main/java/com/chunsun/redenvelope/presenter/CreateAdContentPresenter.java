package com.chunsun.redenvelope.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.CreateAdContentMode;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.impl.CreateAdContentModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdContentView;
import com.chunsun.redenvelope.utils.Base64Utils;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.entity.Photo;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdContentPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private ICreateAdContentView mICreateAdContentView;
    private CreateAdContentMode mCreateAdContentMode;

    /**
     * 追加用变量
     **/
    private int toBitmapCount = 0;
    private ArrayList<Bitmap> mBitmapList;


    public CreateAdContentPresenter(ICreateAdContentView iCreateAdNextPageView) {
        this.mICreateAdContentView = iCreateAdNextPageView;
        mCreateAdContentMode = new CreateAdContentModeImpl((CreateAdContentActivity) iCreateAdNextPageView);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        mICreateAdContentView.hideLoading();
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COMMIT_AD:
                CreateAdResultEntity entity1 = (CreateAdResultEntity) data;
                mICreateAdContentView.toAdPayActivity(entity1.getResult());
                break;
            case Constants.LISTENER_TYPE_COMMIT_CIRCLE:
                CreateAdResultEntity entity2 = (CreateAdResultEntity) data;
                mICreateAdContentView.toCreateCircleSuccess(entity2.getResult());
                break;
        }
    }

    @Override
    public void onError(String msg) {
        mICreateAdContentView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        mICreateAdContentView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {
        switch (event_tag){
            case Constants.LISTENER_TYPE_COMMIT_CIRCLE:
                mICreateAdContentView.toCreateError();
                break;
        }
    }

    /**
     * 提交广告
     *
     * @param mAdEntity
     * @param title
     * @param content
     * @param mPhotos
     */
    public void commit(String token, AdEntity mAdEntity, String title, String content, List<Photo> mPhotos) {

        if (validateBaseInfo(mAdEntity, title, content))
            return;

        mICreateAdContentView.showLoading();

        for (int i = 0; i < mPhotos.size(); i++) {

            Photo item = mPhotos.get(i);


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(item.getPath(), options);

            // Calculate inSampleSize
            options.inSampleSize = BitmapUtils.calculateInSampleSize(options, 800, 480);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(item.getPath(), options);

            switch (i) {
                case 0:
                    mAdEntity.setImagePath(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 1:
                    mAdEntity.setImagePath2(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 2:
                    mAdEntity.setImagePath3(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 3:
                    mAdEntity.setImagePath4(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 4:
                    mAdEntity.setImagePath5(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 5:
                    mAdEntity.setImagePath6(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 6:
                    mAdEntity.setImagePath7(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 7:
                    mAdEntity.setImagePath8(Base64Utils.bitmapToBase64(bitmap));
                    break;
            }
        }
        mCreateAdContentMode.commitAdCreate(token, mAdEntity, title, content, this);
    }

    /**
     * 追加提交广告
     *
     * @param token
     * @param adEntity
     * @param title
     * @param content
     * @param selectedPhotos
     */
    public void commit(final String token, final AdEntity adEntity, final String title, final String content, final ArrayList<String> selectedPhotos) {

        if (validateBaseInfo(adEntity, title, content)) {
            return;
        }
        mICreateAdContentView.showLoading();

        mBitmapList = new ArrayList<>();
        selectedPhotos.add(0, adEntity.getCoverImagePath());
        final int length = selectedPhotos.size() < 8 ? selectedPhotos.size() - 1 : selectedPhotos.size();
        for (int i = 0; i < length; i++) {
            String path = selectedPhotos.get(i);
            Glide.with((CreateAdContentActivity) mICreateAdContentView)
                    .load(path)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(800, 480) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            mBitmapList.add(bitmap);
                            toBitmapCount++;
                            if (toBitmapCount == length) {
                                bitmapToString(token, adEntity, title, content);
                                toBitmapCount = 0;
                                mBitmapList.clear();
                            }
                        }
                    });
        }
    }

    /**
     * 圈子提交
     *
     * @param mToken
     * @param mAdEntity
     * @param title
     * @param content
     * @param mPhotos
     */
    public void commitCircle(String mToken, AdEntity mAdEntity, String title, String content, ArrayList<Photo> mPhotos) {
        if (validateBaseInfo(mAdEntity, title, content))
            return;

        mICreateAdContentView.showLoading();

        for (int i = 0; i < mPhotos.size(); i++) {

            Photo item = mPhotos.get(i);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(item.getPath(), options);

            // Calculate inSampleSize
            options.inSampleSize = BitmapUtils.calculateInSampleSize(options, 800, 480);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(item.getPath(), options);

            switch (i) {
                case 0:
                    mAdEntity.setImagePath(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 1:
                    mAdEntity.setImagePath2(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 2:
                    mAdEntity.setImagePath3(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 3:
                    mAdEntity.setImagePath4(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 4:
                    mAdEntity.setImagePath5(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 5:
                    mAdEntity.setImagePath6(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 6:
                    mAdEntity.setImagePath7(Base64Utils.bitmapToBase64(bitmap));
                    break;
                case 7:
                    mAdEntity.setImagePath8(Base64Utils.bitmapToBase64(bitmap));
                    break;
            }
        }
        mCreateAdContentMode.commitCircleCreate(mToken, mAdEntity, title, content, this);
    }

    private void bitmapToString(String token, AdEntity adEntity, String title, String content) {
        if (mBitmapList != null && mBitmapList.size() > 0) {
            for (int i = 0; i < mBitmapList.size(); i++) {
                Bitmap bitmap = mBitmapList.get(i);
                switch (i) {
                    case 0:
                        adEntity.setCoverImagePath(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 1:
                        adEntity.setImagePath(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 2:
                        adEntity.setImagePath2(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 3:
                        adEntity.setImagePath3(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 4:
                        adEntity.setImagePath4(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 5:
                        adEntity.setImagePath5(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 6:
                        adEntity.setImagePath6(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 7:
                        adEntity.setImagePath7(Base64Utils.bitmapToBase64(bitmap));
                        break;
                    case 8:
                        adEntity.setImagePath8(Base64Utils.bitmapToBase64(bitmap));
                        break;
                }
            }
        }
        mCreateAdContentMode.commitAdCreate(token, adEntity, title, content, this);
    }

    /**
     * 验证输入信息
     *
     * @param mAdEntity
     * @param title
     * @param content
     * @return
     */
    private boolean validateBaseInfo(AdEntity mAdEntity, String title, String content) {
        if (TextUtils.isEmpty(title)) {
            ShowToast.Short("请输入广告标题！");
            return true;
        }

        if (TextUtils.isEmpty(mAdEntity.getCoverImagePath())) {
            ShowToast.Short("请选择要上传的封面图片！");
            return true;
        }

        if (mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_LINK + "")) {
            if (TextUtils.isEmpty(content)) {
                ShowToast.Short("请输入链接网址！");
                return true;
            }
            if (!content.startsWith("http") && !content.startsWith("https")) {
                ShowToast.Short("请输入已http或https开头的网址！");
                return true;
            }
        }

        return false;
    }
}
