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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.clip.PicClipActivity;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.CreateAdResultEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CreateAdContentPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.red.preview.PreviewRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.preview.PreviewRepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.preview.PreviewWebRedDetailActivity;
import com.chunsun.redenvelope.ui.adapter.PhotoAdapter;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
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

/**
 * 广告添加图片、文本Activity
 */
public class CreateAdContentActivity extends MBaseActivity<ICreateAdContentView, CreateAdContentPresenter> implements ICreateAdContentView {

    @Bind(R.id.main_nav)
    RelativeLayout mToolsBar;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.tv_cover_pic)
    TextView mTvCoverPicTitle;
    @Bind(R.id.iv_add_title_img)
    ImageView mIvCover;
    @Bind(R.id.tv_content_pic)
    TextView mTvContentPicTitle;
    @Bind(R.id.ll_image_container)
    LinearLayout mLLImages;
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.recycler_view)
    RecyclerView mRvPic;
    @Bind(R.id.tv_max_count)
    TextView mTvMaxCount;
    @Bind(R.id.ll_agreement)
    LinearLayout mLLAgreement;
    @Bind(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @Bind(R.id.tv_agreement)
    TextView mTvAgreement;
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
    private ArrayList<Photo> mPhotos = new ArrayList<>();

    //
    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;
    //封面图片url
    private String mCoverPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad_next_page);
        ButterKnife.bind(this);
        mPresenter = (CreateAdContentPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new CreateAdContentPresenter(this);
    }

    @Override
    protected void initView() {
        initTitle();
        selectedPhotos.add("");

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mIvCover.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mCbAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAdEntity.setAgreement(true);
                } else {
                    mAdEntity.setAgreement(false);
                }
            }
        });
    }

    private void initTitle() {
        initTitleBar("编辑广告", "", "预览", Constants.TITLE_TYPE_SAMPLE);
        mNavRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        //重新获取位置（创建红包和圈子的时候使用）
        MainApplication.getContext().getLocationClient().start();
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY2);

            photoAdapter = new PhotoAdapter(this, selectedPhotos, mPhotos, mAdEntity.getPrice());
            mRvPic.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
            mRvPic.setAdapter(photoAdapter);
            mAdEntity.setAgreement(true);

            if ("0.01".equals(mAdEntity.getPrice())) {
                mTvMaxCount.setText("（选填：最多3张）");
            } else if ("0.02".equals(mAdEntity.getPrice())) {
                mTvMaxCount.setText("（选填：最多5张）");
            }

            if ((Constants.RED_DETAIL_TYPE_LINK + "").equals(mAdEntity.getType().getKey()) || (Constants.RED_DETAIL_TYPE_lUCK_LINK + "").equals(mAdEntity.getType().getKey())) {
                mTvContentPicTitle.setVisibility(View.GONE);
                mLLImages.setVisibility(View.GONE);
                mEtContent
                        .setHint("请输入您的链接：http:// \n只能输入有效网址，以http开头，输入格式不合法广告将出错！自己承担填写错误影响广告效果责任。 \n多个网址请使用中文逗号“，”分割 \n本平台将备案发广告的信息和发广告人的信息，如有违法、虚假等广告，将依法追究法律责任。");
                mTvTitle.setText("链接标题");
                mTvCoverPicTitle.setText("链接封面图");
                mTvContent.setText("链接地址");
            } else if ((Constants.RED_DETAIL_TYPE_COUPON + "").equals(mAdEntity.getType().getKey())) {
                mTvTitle.setText("券名称");
                mTvCoverPicTitle.setText("券封面");
                mTvContentPicTitle.setText("券内容图片");
                mTvContent.setText("券使用规则定义");
            } else if ((Constants.RED_DETAIL_TYPE_CIRCLE + "").equals(mAdEntity.getType().getKey())) {
                mNavRight.setVisibility(View.GONE);
                mLLAgreement.setVisibility(View.GONE);
                mNavTitle.setText("春笋圈子");
                mEtTitle.setHint("写个主题吧");
                mEtContent.setHint("写点什么吧...");
                mTvTitle.setText("标题");
                mTvCoverPicTitle.setText("封面图片");
                mTvContentPicTitle.setText("内容图片");
                mTvContent.setText("正文");
                mBtnNextStep.setText("发 布");
            } else if ((Constants.RED_DETAIL_TYPE_CIRCLE_LINK + "").equals(mAdEntity.getType().getKey())) {
                mTvContentPicTitle.setVisibility(View.GONE);
                mLLImages.setVisibility(View.GONE);
                mNavRight.setVisibility(View.GONE);
                mLLAgreement.setVisibility(View.GONE);
                mNavTitle.setText("春笋圈子");
                mEtTitle.setHint("写个主题吧");
                mEtContent.setHint("http://(必选：只能输入http://开头的有效网址)");
                mTvTitle.setText("标题");
                mTvCoverPicTitle.setText("封面图片");
                mTvContent.setText("链接地址");
                mBtnNextStep.setText("发 布");
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
        selectedPhotos.clear();

        mEtTitle.setText(mSuperadditionEntity.getTitle());
        mEtContent.setText(mSuperadditionEntity.getContent());
        setCoverImage(Constants.IMG_HOST_URL + mSuperadditionEntity.getCover_img_url());

        String[] split = mSuperadditionEntity.getImg_url().split(",");
        if (split != null && !TextUtils.isEmpty(split[0])) {
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
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
                toPreview();
                break;
            case R.id.iv_add_title_img://添加封面图片
                selectCoverImage();
                break;
            case R.id.btn_next_step://提交
                showLoading();
                if (mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_CIRCLE + "") || mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_CIRCLE_LINK + "")) {
                    mPresenter.commitCircle(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), mPhotos);
                } else if (mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_lUCK + "") || mAdEntity.getType().getKey().equals(Constants.RED_DETAIL_TYPE_lUCK_LINK + "")) {
                    if (mSuperadditionEntity != null) {
                        mPresenter.superadditionCommit(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), selectedPhotos, this);
                    } else {
                        mPresenter.commitLuck(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), mPhotos);
                    }
                } else {
                    if (mSuperadditionEntity != null) {
                        mPresenter.superadditionCommit(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), selectedPhotos, this);
                    } else {
                        mPresenter.commit(mToken, mAdEntity, StringUtil.textview2String(mEtTitle), StringUtil.textview2String(mEtContent), mPhotos);
                    }
                }
                break;
            case R.id.tv_agreement:
                showAgreement();
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

    /**
     * 拼手气支付界面
     *
     * @param result
     */
    @Override
    public void toLuckAdPayActivity(CreateAdResultEntity.ResultEntity result) {
        Intent intent = new Intent(this, LuckAdPayActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, result.getId());
        startActivity(intent);
    }

    @Override
    public void toCreateCircleSuccess(CreateAdResultEntity.ResultEntity result) {

        RedDetailEntity.ResultEntity.DetailEntity detail = new RedDetailEntity.ResultEntity.DetailEntity();
        detail.setId(result.getId());
        detail.setShare_host(result.getShare_host());
        detail.setTitle(mAdEntity.getTitle());
        detail.setContent(mAdEntity.getContent());
        detail.setCover_img_url(mCoverPath);
        detail.setHb_type(Integer.parseInt(mAdEntity.getType().getKey()));

        Intent intent = new Intent(this, CreateCircleResultActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, detail);
        startActivity(intent);
    }

    @Override
    public void toPreview() {
        mAdEntity.setTitle(mEtTitle.getText().toString().trim());
        mAdEntity.setContent(mEtContent.getText().toString().trim());
        mAdEntity.setCoverImagePath(mCoverPath);

        Intent intent = null;
        String type = mAdEntity.getType().getKey();
        if ((Constants.RED_DETAIL_TYPE_LEFT + "").equals(type) || (Constants.RED_DETAIL_TYPE_NEAR + "").equals(type) || (Constants.RED_DETAIL_TYPE_COUPON + "").equals(type)) {
            intent = new Intent(this, PreviewRedDetailActivity.class);
        } else if ((Constants.RED_DETAIL_TYPE_LINK + "").equals(type)) {
            intent = new Intent(this, PreviewWebRedDetailActivity.class);
        } else if ((Constants.RED_DETAIL_TYPE_REPEAT + "").equals(type)) {
            intent = new Intent(this, PreviewRepeatRedDetailActivity.class);
        }
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        intent.putParcelableArrayListExtra(Constants.EXTRA_KEY2, mPhotos);
        startActivity(intent);
    }

    @Override
    public void toCreateError() {
        Intent intent = new Intent(this, CreateCircleResultActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    /**
     * 跳转春笋红包发广告协议
     */
    private void showAgreement() {
        Intent intentWeb = new Intent(this, CommonWebActivity.class);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                Constants.SEND_RED_AGREEMENT_URL);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                "春笋红包发广告协议");
        startActivity(intentWeb);
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
                    mCoverPath = photos.get(0).getPath();
                    toImageCutActivity(mCoverPath);
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

//    private void downloadBitmap(String path, final int index) {
//        Glide.with(this)
//                .load(path)
//                .asBitmap()
//                .toBytes()
//                .centerCrop()
//                .into(new SimpleTarget<byte[]>(800, 480) {
//                    @Override
//                    public void onResourceReady(byte[] data, GlideAnimation anim) {
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                        String base64 = Base64Utils.bitmapToBase64(bitmap);
//                        switch (index) {
//                            case 0:
//                                mAdEntity.setImagePath(base64);
//                                break;
//                            case 1:
//                                mAdEntity.setImagePath2(base64);
//                                break;
//                            case 2:
//                                mAdEntity.setImagePath3(base64);
//                                break;
//                            case 3:
//                                mAdEntity.setImagePath4(base64);
//                                break;
//                            case 4:
//                                mAdEntity.setImagePath5(base64);
//                                break;
//                            case 5:
//                                mAdEntity.setImagePath6(base64);
//                                break;
//                            case 6:
//                                mAdEntity.setImagePath7(base64);
//                                break;
//                            case 7:
//                                mAdEntity.setImagePath8(base64);
//                                break;
//                            default:
//                                mAdEntity.setCoverImagePath(base64);
//                                break;
//                        }
//                    }
//                });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
