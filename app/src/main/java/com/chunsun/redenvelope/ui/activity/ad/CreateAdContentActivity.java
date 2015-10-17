package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.clip.PicClipActivity;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CreateAdContentPresenter;
import com.chunsun.redenvelope.ui.adapter.PhotoAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdContentView;
import com.chunsun.redenvelope.utils.Base64Utils;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

public class CreateAdContentActivity extends BaseActivity implements ICreateAdContentView, View.OnClickListener {

    @Bind(R.id.main_nav)
    RelativeLayout mToolsBar;
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.iv_add_title_img)
    ImageView mIvCover;
    @Bind(R.id.tv_content_pic)
    TextView mTvContentPic;
    @Bind(R.id.ll_image_container)
    LinearLayout mLLImages;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.recycler_view)
    RecyclerView mRvPic;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private CreateAdContentPresenter mPresenter;
    private AdEntity mAdEntity;
    private String mToken;

    /**
     * 图片相关
     */
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    //标示是否是上传封面图片
    private boolean mIsCover;
    private List<Photo> mPhotos = new ArrayList<>();

    //
    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad_next_page);
        ButterKnife.bind(this);
        mPresenter = new CreateAdContentPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("发广告", "", "", Constants.TITLE_TYPE_SAMPLE);
        mToolsBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        selectedPhotos.add("");
        photoAdapter = new PhotoAdapter(this, selectedPhotos, mPhotos);
        mRvPic.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mRvPic.setAdapter(photoAdapter);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mIvCover.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);

            if ((Constants.RED_DETAIL_TYPE_LINK + "").equals(mAdEntity.getType().getKey())) {
                mTvContentPic.setVisibility(View.GONE);
                mLLImages.setVisibility(View.GONE);
                mEtContent
                        .setHint("请输入您的链接：http:// \n只能输入有效网址，以http开头，输入格式不合法广告将出错！自己承担填写错误影响广告效果责任。 \n多个网址请使用中文逗号“，”分割 \n本平台将备案发广告的信息和发广告人的信息，如有违法、虚假等广告，将依法追究法律责任。");
            }

            if (mSuperadditionEntity != null) {
                initSuperaddition();
            }
        }
    }

    /**
     * 初始化追加
     */
    private void initSuperaddition() {
        mEtTitle.setText(mSuperadditionEntity.getTitle());
        mEtContent.setText(mSuperadditionEntity.getContent());
        setCoverImage(Constants.IMG_HOST_URL + mSuperadditionEntity.getCover_img_url());
        mAdEntity.setCoverImagePath(Constants.IMG_HOST_URL + mSuperadditionEntity.getCover_img_url());

        selectedPhotos.clear();
        String[] split = mSuperadditionEntity.getImg_url().split(",");

        if (split != null && split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                selectedPhotos.add(Constants.IMG_HOST_URL + split[i]);
                switch (i) {
                    case 0:
                        mAdEntity.setImagePath(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 1:
                        mAdEntity.setImagePath2(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 2:
                        mAdEntity.setImagePath3(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 3:
                        mAdEntity.setImagePath4(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 4:
                        mAdEntity.setImagePath5(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 5:
                        mAdEntity.setImagePath6(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 6:
                        mAdEntity.setImagePath7(Constants.IMG_HOST_URL + split[i]);
                        break;
                    case 7:
                        mAdEntity.setImagePath8(Constants.IMG_HOST_URL + split[i]);
                        break;
                }

            }
        }
        if (selectedPhotos.size() < 8) {
            selectedPhotos.add("");
        }
        photoAdapter.setPhotos(mPhotos);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                back();
                break;
            case R.id.tv_nav_right:

                break;
            case R.id.iv_add_title_img://添加封面图片
                selectCoverImage();
                break;
            case R.id.btn_next_step://提交
                if (mSuperadditionEntity != null) {
                    mPresenter.commit(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), selectedPhotos);
                } else {
                    mPresenter.commit(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), mPhotos);
                }
                break;
        }
    }


    /**
     * 选择广告封面图
     */
    private void selectCoverImage() {
        mIsCover = true;
        PhotoPickerIntent intent = new PhotoPickerIntent(this);
        intent.setPhotoCount(1);
        intent.setShowCamera(true);
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    /**
     * 跳转支付界面
     *
     * @param result
     */
    @Override
    public void toAdPayActivity(CreateAdResultEntity.ResultEntity result) {
        Intent intent = new Intent(this, AdPayActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, result.getId());
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void previewPhoto(Intent intent) {
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constants.REQUEST_CODE) {
            if (mIsCover) {
                if (data != null) {
                    List<Photo> photos = data.getParcelableArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                    toImageCutActivity(photos.get(0).getPath());
                    mIsCover = false;
                }

            } else {
                if (data != null) {
                    mPhotos = data.getParcelableArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                }
                selectedPhotos.clear();

                if (mPhotos != null) {
                    for (Photo photo : mPhotos) {
                        selectedPhotos.add(photo.getPath());
                    }
                }

                if (selectedPhotos.size() < 8) {
                    selectedPhotos.add("");
                }
                photoAdapter.setPhotos(mPhotos);
                photoAdapter.notifyDataSetChanged();
            }
        } else if (resultCode == RESULT_CANCELED && requestCode == Constants.REQUEST_CODE) {
            if (data != null) {
                List<String> paths = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                selectedPhotos.clear();
                selectedPhotos.addAll(paths);

                for (int i = 0; i < mPhotos.size(); i++) {
                    Photo item = mPhotos.get(i);
                    int j = 0;
                    for (j = 0; j < selectedPhotos.size(); j++) {
                        String path = selectedPhotos.get(j);
                        if (item.getPath().equals(path)) {
                            break;
                        }
                    }
                    if (j == selectedPhotos.size()) {
                        mPhotos.remove(item);
                    }
                }

                if (selectedPhotos.size() < 8) {
                    selectedPhotos.add("");
                }
                photoAdapter.setPhotos(mPhotos);
                photoAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == Constants.REQUEST_CODE_IMAGE_CUT) {
            if (data != null) {
                byte[] bis = data.getByteArrayExtra(Constants.EXTRA_KEY2);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
                String base64 = Base64Utils.bitmapToBase64(bitmap);
                mAdEntity.setCoverImagePath(base64);
                mIvCover.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 跳转到剪切图片Activity
     *
     * @param path
     */
    private void toImageCutActivity(String path) {
        Intent intent = new Intent(this, PicClipActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, path);
        intent.putExtra(Constants.EXTRA_KEY2, true);
        startActivityForResult(intent, Constants.REQUEST_CODE_IMAGE_CUT);
    }

    /**
     * 设置封面图片
     *
     * @param path
     */
    private void setCoverImage(String path) {
        if (!TextUtils.isEmpty(path)) {
            Glide.with(this)
                    .load(path)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_default_capture)
                    .error(R.drawable.img_default_error)
                    .into(mIvCover);
            mAdEntity.setCoverImagePath(path);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
