package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.model.event.SelectAdNextPageEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.CreateAdNextPagePresenter;
import com.chunsun.redenvelope.ui.activity.SelectListInfoActivity;
import com.chunsun.redenvelope.ui.adapter.PhotoAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdNextPageView;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

public class CreateAdNextPageActivity extends BaseActivity implements ICreateAdNextPageView, View.OnClickListener {

    @Bind(R.id.ll_type_container)
    LinearLayout mLLTypeContainer;
    @Bind(R.id.tv_type)
    TextView mTvType;
    @Bind(R.id.ll_province_container)
    LinearLayout mLLProvince;
    @Bind(R.id.tv_province)
    TextView mTvProvince;
    @Bind(R.id.ll_city_container)
    LinearLayout mLLCity;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.ll_distance_container)
    LinearLayout mLLDistance;
    @Bind(R.id.tv_distance)
    TextView mTvDistance;
    @Bind(R.id.et_title)
    EditText mEtTitle;
    @Bind(R.id.iv_add_title_img)
    ImageView mIvCover;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;

    private CreateAdNextPagePresenter mPresenter;
    private ArrayList<SampleEntity> mDistanceList;
    private ArrayList<SampleEntity> mTypeList;
    private ArrayList<DistrictEntity.AreaEntity> mDistrictList;
    private AdEntity mAdEntity;
    private String mToken;

    /**
     * 图片相关
     */
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerView;
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
        EventBus.getDefault().register(this);
        mPresenter = new CreateAdNextPagePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("发广告", "", "预览", Constants.TITLE_TYPE_SAMPLE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        selectedPhotos.add("");
        photoAdapter = new PhotoAdapter(this, selectedPhotos, mPhotos);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mLLTypeContainer.setOnClickListener(this);
        mLLProvince.setOnClickListener(this);
        mLLCity.setOnClickListener(this);
        mLLDistance.setOnClickListener(this);
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

            if (mSuperadditionEntity != null) {
                initSuperaddition();
            }
        }
        mPresenter.initData(mAdEntity, mSuperadditionEntity);
    }

    /**
     * 初始化追加
     */
    private void initSuperaddition() {
        mEtTitle.setText(mSuperadditionEntity.getTitle());
        mEtContent.setText(mSuperadditionEntity.getContent());
        setCoverImage(mSuperadditionEntity.getCover_img_url());
        mAdEntity.setCoverImagePath(mSuperadditionEntity.getCover_img_url());

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
            case R.id.ll_type_container:
                toSelectType();
                break;
            case R.id.ll_distance_container:
                toSelectDistance();
                break;
            case R.id.ll_city_container:
                toSelectCity();
                break;
            case R.id.ll_province_container:
                toSelectProvince();
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

    @Override
    public void setInitData(ArrayList<SampleEntity> typeList, ArrayList<SampleEntity> distanceList, ArrayList<DistrictEntity.AreaEntity> districtList, AdEntity adEntity) {
        this.mTypeList = typeList;
        this.mDistanceList = distanceList;
        this.mDistrictList = districtList;
        this.mAdEntity = adEntity;

        mTvType.setText(this.mAdEntity.getType().getValue());
        mTvDistance.setText(this.mAdEntity.getDistance().getValue());
        mTvProvince.setText(this.mAdEntity.getProvince().getP());
        mTvCity.setText(this.mAdEntity.getCity().getC());

        refreshChoose();
    }

    private void refreshChoose() {
        if (Constants.AD_LEFT_TYPE.equals(this.mAdEntity.getType().getKey()) || Constants.AD_COMPANY_TYPE.equals(this.mAdEntity.getType().getKey()) || Constants.AD_LINK_TYPE.equals(this.mAdEntity.getType().getKey())) {
            mLLProvince.setVisibility(View.VISIBLE);
            mLLCity.setVisibility(View.VISIBLE);
            mLLDistance.setVisibility(View.GONE);
        } else if (Constants.AD_NEAR_TYPE.equals(this.mAdEntity.getType().getKey())) {
            mLLProvince.setVisibility(View.GONE);
            mLLCity.setVisibility(View.GONE);
            mLLDistance.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void toSelectType() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "选择类型");
        intent.putParcelableArrayListExtra(Constants.EXTRA_LIST_KEY, mTypeList);
        startActivity(intent);
    }

    @Override
    public void toSelectProvince() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "省");
        intent.putParcelableArrayListExtra(Constants.EXTRA_LIST_KEY, mDistrictList);
        startActivity(intent);
    }

    @Override
    public void toSelectCity() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "市");
        intent.putParcelableArrayListExtra(Constants.EXTRA_LIST_KEY, mAdEntity.getProvince().getCc());
        startActivity(intent);
    }

    @Override
    public void toSelectDistance() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "距离");
        intent.putParcelableArrayListExtra(Constants.EXTRA_LIST_KEY, mDistanceList);
        startActivity(intent);
    }

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

    public void onEvent(SelectAdNextPageEvent event) {
        SampleEntity msg = event.getMsg();
        if (Constants.AD_SELECT_LIST_TYPE == msg.getType()) {
            for (SampleEntity item : mTypeList) {
                if (item.getKey().equals(msg.getKey())) {
                    mTvType.setText(item.getValue());
                    item.setCheck(true);
                    mAdEntity.setType(item);
                } else {
                    item.setCheck(false);
                }
            }
        } else if (Constants.AD_SELECT_LIST_DISTANCE == msg.getType()) {
            for (SampleEntity item : mDistanceList) {
                if (item.getKey().equals(msg.getKey())) {
                    mTvDistance.setText(item.getValue());
                    item.setCheck(true);
                    mAdEntity.setDistance(item);
                } else {
                    item.setCheck(false);
                }
            }
        } else if (Constants.AD_SELECT_LIST_PROVINCE == msg.getType()) {
            for (DistrictEntity.AreaEntity item : mDistrictList) {
                if (item.getP().equals(msg.getKey())) {
                    mTvProvince.setText(item.getP());
                    item.setCheck(true);
                    mAdEntity.setProvince(item);
                } else {
                    item.setCheck(false);
                }
            }
        } else if (Constants.AD_SELECT_LIST_CITY == msg.getType()) {
            for (DistrictEntity.AreaEntity.CcEntity item : mAdEntity.getProvince().getCc()) {
                if (item.getC().equals(msg.getKey())) {
                    mTvCity.setText(item.getC());
                    item.setCheck(true);
                    mAdEntity.setCity(item);
                } else {
                    item.setCheck(false);
                }
            }
        }
        refreshChoose();
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
                    setCoverImage(photos.get(0).getPath());
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
        }
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
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
