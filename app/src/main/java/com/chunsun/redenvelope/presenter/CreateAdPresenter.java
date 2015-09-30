package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.model.impl.CreateAdModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.chunsun.redenvelope.utils.ListUtils;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/9/29 10:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CreateAdPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private ICreateAdView mICreateAdView;
    private CreateAdMode mCreateAdMode;

    private ArrayList<SampleEntity> mTypeList;
    private ArrayList<SampleEntity> mDistanceList;
    private AdEntity mAdEntity;
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;

    public CreateAdPresenter(ICreateAdView iCreateAdView) {
        mICreateAdView = iCreateAdView;
        mCreateAdMode = new CreateAdModeImpl((CreateAdActivity) iCreateAdView);
    }

    /**
     * 获取初始数据
     */
    public void getData(AdEntity adEntity, RedSuperadditionEntity.ResultEntity superadditionEntity) {

        this.mAdEntity = adEntity;
        if (superadditionEntity != null) {
            this.mSuperadditionEntity = superadditionEntity;
        }

        mTypeList = new ArrayList<>();
        initType(mTypeList);
        mDistanceList = new ArrayList<>();
        initDistance(mDistanceList);
        mCreateAdMode.initProvinceAndCity(this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_PROVINCE_AND_CITY:
                DistrictEntity entity = (DistrictEntity) data;
                setProAndCity(entity);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
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

        item = new SampleEntity();
        item.setKey(Constants.AD_REPEAT_TYPE);
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("转发类");
        list.add(item);
    }

    public void setProAndCity(DistrictEntity data) {
        ArrayList<DistrictEntity.AreaEntity> list = (ArrayList<DistrictEntity.AreaEntity>) data.getArea();

        setUnlimited(list);

        String province = "";
        String city = "";

        if (mSuperadditionEntity != null) {
            for (SampleEntity item : mTypeList) {
                if (mSuperadditionEntity.getType().equals(item.getKey())) {
                    item.setCheck(true);
                    this.mAdEntity.setType(item);
                }
            }
            for (SampleEntity item : mDistanceList) {
                if (mSuperadditionEntity.getRange().equals(item.getKey())) {
                    item.setCheck(true);
                    this.mAdEntity.setDistance(item);
                }
            }
            province = mSuperadditionEntity.getProvince();
            city = mSuperadditionEntity.getCity();
        } else {
            mTypeList.get(0).setCheck(true);
            this.mAdEntity.setType(mTypeList.get(0));
            mDistanceList.get(0).setCheck(true);
            this.mAdEntity.setDistance(mDistanceList.get(0));

            province = MainApplication.getContext().getProvince();
            city = MainApplication.getContext().getCity();
        }

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
        mICreateAdView.setInitData(mTypeList, mDistanceList, list, mAdEntity);
    }

    /**
     * 添加不限选项
     * @param list
     */
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


}
