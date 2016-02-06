package com.chunsun.redenvelope.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chunsun.redenvelope.clip.BitmapClipUtils;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.CreateAdResultEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdContentMode;
import com.chunsun.redenvelope.model.impl.CreateAdContentModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.ICreateAdContentView;
import com.chunsun.redenvelope.utils.Base64Utils;
import com.chunsun.redenvelope.utils.ImageUtils;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

import me.iwf.photopicker.entity.Photo;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdContentPresenter extends UserLosePresenter<ICreateAdContentView> implements UserLoseMultiLoadedListener<BaseEntity> {

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

        CreateAdResultEntity entity = null;
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COMMIT_AD:
                entity = (CreateAdResultEntity) data;
                mICreateAdContentView.toAdPayActivity(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_COMMIT_CIRCLE:
                entity = (CreateAdResultEntity) data;
                mICreateAdContentView.toCreateCircleSuccess(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_COMMIT_LUCK:
                entity = (CreateAdResultEntity) data;
                mICreateAdContentView.toLuckAdPayActivity(entity.getResult());
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

    }

    @Override
    public void onError(int event_tag, String msg) {
        mICreateAdContentView.hideLoading();
        switch (event_tag) {
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
    public void commit(String token, AdEntity mAdEntity, String title, String content, ArrayList<Photo> mPhotos) {

        if (validateBaseInfo(mAdEntity, title, content)) {
            mICreateAdContentView.hideLoading();
            return;
        }

        convertBitmap(mAdEntity, mPhotos);
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
    public void superadditionCommit(final String token, final AdEntity adEntity, final String title, final String content, final ArrayList<String> selectedPhotos, Context context) {

        if (validateBaseInfo(adEntity, title, content)) {
            mICreateAdContentView.hideLoading();
            return;
        }
        mBitmapList = new ArrayList<>();
        selectedPhotos.add(0, adEntity.getCoverImagePath());
        final int length = selectedPhotos.size();
        for (int i = 0; i < length; i++) {
            downloadBitmap(token, adEntity, title, content, selectedPhotos, context, length, i);
        }
    }

    private void downloadBitmap(final String token, final AdEntity adEntity, final String title, final String content, ArrayList<String> selectedPhotos, Context context, final int length, final int i) {
        String path = selectedPhotos.get(i);
        Glide.with(context)
                .load(path)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(800, 480) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        mBitmapList.add(i, bitmap);
                        toBitmapCount++;
                        if (toBitmapCount == length) {
                            bitmapToString(token, adEntity, title, content);
                            toBitmapCount = 0;
                            mBitmapList.clear();
                        }
                    }
                });
    }

    /**
     * 拼手气红包创建
     *
     * @param mToken
     * @param mAdEntity
     * @param title
     * @param content
     * @param mPhotos
     */
    public void commitLuck(String mToken, AdEntity mAdEntity, String title, String content, ArrayList<Photo> mPhotos) {
        if (validateBaseInfo(mAdEntity, title, content)) {
            mICreateAdContentView.hideLoading();
            return;
        }

        convertBitmap(mAdEntity, mPhotos);
        mCreateAdContentMode.commitLuckCreate(mToken, mAdEntity, title, content, this);
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
        if (validateBaseInfo(mAdEntity, title, content)) {
            mICreateAdContentView.hideLoading();
            return;
        }

        convertBitmap(mAdEntity, mPhotos);
        mCreateAdContentMode.commitCircleCreate(mToken, mAdEntity, title, content, this);
    }

    /**
     * bitmap转换成base64格式
     *
     * @param mAdEntity
     * @param mPhotos
     */
    private void convertBitmap(AdEntity mAdEntity, ArrayList<Photo> mPhotos) {

        for (int i = 0; i < mPhotos.size(); i++) {

            Photo item = mPhotos.get(i);

            /**
             * 计算图片角度
             */
            int degree = ImageUtils.readPictureDegree(item.getPath());

            Bitmap bitmap = ImageUtils.rotaingImageView(degree,
                    BitmapClipUtils
                            .createImageThumbnailScale(item.getPath(), 800));

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
        if (adEntity.getType().getKey().equals(("" + Constants.RED_DETAIL_TYPE_lUCK)) || adEntity.getType().getKey().equals(("" + Constants.RED_DETAIL_TYPE_lUCK_LINK))) {
            mCreateAdContentMode.commitLuckCreate(token, adEntity, title, content, this);
        } else {
            mCreateAdContentMode.commitAdCreate(token, adEntity, title, content, this);
        }
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

        if (mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_LINK + "") || mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_CIRCLE_LINK + "") || mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_lUCK_LINK + "")) {
            if (TextUtils.isEmpty(content)) {
                ShowToast.Short("请输入链接网址！");
                return true;
            }
            if (!content.startsWith("http") && !content.startsWith("https")) {
                ShowToast.Short("请输入已http或https开头的网址！");
                return true;
            }
        }

        if (!mAdEntity.isAgreement()) {
            ShowToast.Short("请同意春笋红包发广告协议！");
            return true;
        }

        return false;
    }


}
