package com.chunsun.redenvelope.presenter.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ListView;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.CreateAdNextPageMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.impl.CreateAdNextPageModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextPageActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdNextPageView;
import com.chunsun.redenvelope.utils.Base64Util;
import com.chunsun.redenvelope.utils.ListUtils;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.entity.Photo;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdNextPagePresenter implements BaseMultiLoadedListener<BaseEntity> {

    private ICreateAdNextPageView mICreateAdNextPageView;
    private CreateAdNextPageMode mCreateAdNextPageMode;

    private ArrayList<SampleEntity> mTypeList;
    private ArrayList<SampleEntity> mDistanceList;
    private AdEntity mAdEntity;

    public CreateAdNextPagePresenter(ICreateAdNextPageView iCreateAdNextPageView) {
        this.mICreateAdNextPageView = iCreateAdNextPageView;
        mCreateAdNextPageMode = new CreateAdNextPageModeImpl((CreateAdNextPageActivity) iCreateAdNextPageView);
    }

    public void initData(AdEntity adEntity) {
        this.mAdEntity = adEntity;

        mTypeList = new ArrayList<>();
        initType(mTypeList);

        mDistanceList = new ArrayList<>();
        initDistance(mDistanceList);

        mCreateAdNextPageMode.initProvinceAndCity(this);
    }

    /**
     * 初始化距离数据
     *
     * @param list
     */
    private void initDistance(List<SampleEntity> list) {
        SampleEntity distance = new SampleEntity();
        distance.setKey("0");
        distance.setValue("100米");
        distance.setCount("0.1");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("1");
        distance.setValue("200米");
        distance.setCount("0.2");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("2");
        distance.setValue("500米");
        distance.setCount("0.5");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("3");
        distance.setValue("1千米");
        distance.setCount("1");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("4");
        distance.setValue("2千米");
        distance.setCount("2");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("5");
        distance.setValue("5千米");
        distance.setCount("5");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("6");
        distance.setValue("10千米");
        distance.setCount("10");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("7");
        distance.setValue("20千米");
        distance.setCount("20");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("8");
        distance.setValue("50千米");
        distance.setCount("50");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);
    }

    /**
     * 初始化类型
     *
     * @param list
     */
    private void initType(List<SampleEntity> list) {
        SampleEntity item = new SampleEntity();
        item.setKey(Constants.AD_LEFT_TYPE);
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("生活类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.AD_COMPANY_TYPE);
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("企业类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.AD_NEAR_TYPE);
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("附近类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.AD_LINK_TYPE);
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("链接类");
        list.add(item);
    }

    public void setProAndCity(DistrictEntity data) {
        ArrayList<DistrictEntity.AreaEntity> list = (ArrayList<DistrictEntity.AreaEntity>) data.getArea();

        setUnlimited(list);

        mTypeList.get(0).setCheck(true);
        this.mAdEntity.setType(mTypeList.get(0));
        mDistanceList.get(0).setCheck(true);
        this.mAdEntity.setDistance(mDistanceList.get(0));

        String province = MainApplication.getContext().getProvince();
        String city = MainApplication.getContext().getCity();
        if (TextUtils.isEmpty(province)) {
            list.get(0).setCheck(true);
            DistrictEntity.AreaEntity entityProvince = list.get(0);
            this.mAdEntity.setProvince(entityProvince);
            entityProvince.getCc().get(0).setCheck(true);
            this.mAdEntity.setCity(entityProvince.getCc().get(0));
            this.mAdEntity.getCity().setCt(null);
            this.mAdEntity.getProvince().setCc(null);
        } else {
            List<DistrictEntity.AreaEntity.CcEntity> cc = null;
            for (DistrictEntity.AreaEntity item : list) {
                if (province.equals(item.getP())) {
                    this.mAdEntity.setProvince(item);
                    cc = this.mAdEntity.getProvince().getCc();
                    break;
                }
            }
            if (!ListUtils.isEmpty(cc)) {
                for (DistrictEntity.AreaEntity.CcEntity item : cc) {
                    if (city.equals(item.getC())) {
                        this.mAdEntity.setCity(item);
                        break;
                    }
                }
            }
        }
        mICreateAdNextPageView.setInitData(mTypeList, mDistanceList, list, mAdEntity);
    }

    private void setUnlimited(ArrayList<DistrictEntity.AreaEntity> list) {
        DistrictEntity.AreaEntity provinceEntity = new DistrictEntity.AreaEntity();
        provinceEntity.setP("不限");
        DistrictEntity.AreaEntity.CcEntity cityEntity = new DistrictEntity.AreaEntity.CcEntity();
        cityEntity.setC("不限");
        cityEntity.setCt(null);
        ArrayList<DistrictEntity.AreaEntity.CcEntity> citys = new ArrayList<>();
        provinceEntity.setCc(citys);
        list.add(0, provinceEntity);

        for (DistrictEntity.AreaEntity item : list) {
            item.getCc().add(0, cityEntity);
        }
    }


    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_PROVINCE_AND_CITY:
                DistrictEntity entity = (DistrictEntity) data;
                setProAndCity(entity);
                break;
            case Constants.LISTENER_TYPE_COMMIT_AD:
                CreateAdResultEntity entity1 = (CreateAdResultEntity) data;
                mICreateAdNextPageView.toAdPayActivity(entity1.getResult());
                mICreateAdNextPageView.hideLoading();
                break;
        }
    }

    @Override
    public void onError(String msg) {
        mICreateAdNextPageView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        mICreateAdNextPageView.hideLoading();
        ShowToast.Short(msg);
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

        if (TextUtils.isEmpty(title)) {
            ShowToast.Short("请输入广告标题！");
            return;
        }

        if (TextUtils.isEmpty(mAdEntity.getCoverImagePath())) {
            ShowToast.Short("请选择要上传的封面图片！");
            return;
        }

        if (mAdEntity.getType().getKey().equals(Constants.AD_LINK_TYPE)) {
            if (TextUtils.isEmpty(content)) {
                ShowToast.Short("请输入链接网址！");
                return;
            }
            if (!content.startsWith("http") && !content.startsWith("https")) {
                ShowToast.Short("请输入已http或https开头的网址！");
                return;
            }
        }

        mICreateAdNextPageView.showLoading();

        for (int i = 0; i < mPhotos.size() + 1; i++) {

            Photo item = null;

            if (i == 0) {
                item = new Photo();
                item.setPath(mAdEntity.getCoverImagePath());
            } else {
                item = mPhotos.get(i - 1);
            }

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
                    mAdEntity.setCoverImagePath(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 1:
                    mAdEntity.setImagePath(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 2:
                    mAdEntity.setImagePath2(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 3:
                    mAdEntity.setImagePath3(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 4:
                    mAdEntity.setImagePath4(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 5:
                    mAdEntity.setImagePath5(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 6:
                    mAdEntity.setImagePath6(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 7:
                    mAdEntity.setImagePath7(Base64Util.bitmapToBase64(bitmap));
                    break;
                case 8:
                    mAdEntity.setImagePath8(Base64Util.bitmapToBase64(bitmap));
                    break;
            }
        }
        mCreateAdNextPageMode.commit(token, mAdEntity, title, content, this);
    }
}
