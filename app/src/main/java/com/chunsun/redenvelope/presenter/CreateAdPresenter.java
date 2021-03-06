package com.chunsun.redenvelope.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdMode;
import com.chunsun.redenvelope.model.impl.CreateAdModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.chunsun.redenvelope.utils.ListUtils;

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
public class CreateAdPresenter extends UserLosePresenter<ICreateAdView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private ICreateAdView mICreateAdView;
    private CreateAdMode mCreateAdMode;

    private ArrayList<SampleEntity> mTypeList;
    private ArrayList<SampleEntity> mDistanceList;
    private AdEntity mAdEntity;
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;

    public CreateAdPresenter(ICreateAdView iCreateAdView) {
        mICreateAdView = iCreateAdView;
        mCreateAdMode = new CreateAdModeImpl((Activity) iCreateAdView);
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
        if (mAdEntity.getType() != null && (("" + Constants.RED_DETAIL_TYPE_CIRCLE).equals(mAdEntity.getType().getKey()) || ("" + Constants.RED_DETAIL_TYPE_lUCK).equals(mAdEntity.getType().getKey()))) {
            initCircleType(mTypeList);
        } else {
            initType(mTypeList);
        }
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

    /**
     * 初始化距离数据
     *
     * @param list
     */
    private void initDistance(List<SampleEntity> list) {
        SampleEntity distance = new SampleEntity();
        distance.setKey("0");
        distance.setValue("不限");
        distance.setCount("0");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("1");
        distance.setValue("距离当前位置100米可见");
        distance.setCount("0.10");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("2");
        distance.setValue("距离当前位置200米可见");
        distance.setCount("0.20");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("3");
        distance.setValue("距离当前位置500米可见");
        distance.setCount("0.50");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("4");
        distance.setValue("距离当前位置1千米可见");
        distance.setCount("1.00");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("5");
        distance.setValue("距离当前位置2千米可见");
        distance.setCount("2.00");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("6");
        distance.setValue("距离当前位置5千米可见");
        distance.setCount("5.00");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("7");
        distance.setValue("距离当前位置10千米可见");
        distance.setCount("10.00");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("8");
        distance.setValue("距离当前位置20千米可见");
        distance.setCount("20.00");
        distance.setType(Constants.AD_SELECT_LIST_DISTANCE);
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("9");
        distance.setValue("距离当前位置50千米可见");
        distance.setCount("50.00");
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
        item.setKey(Constants.RED_DETAIL_TYPE_LEFT + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("生活类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_COMPANY + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("企业类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_NEAR + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("附近类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_LINK + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("链接类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_REPEAT + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("转发类");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_COUPON + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("券类");
        list.add(item);
    }

    /**
     * 圈子类别
     *
     * @param list
     */
    private void initCircleType(List<SampleEntity> list) {
        SampleEntity item = new SampleEntity();
        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_CIRCLE + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("图文");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_CIRCLE_LINK + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("链接");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_lUCK + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("图文");
        list.add(item);

        item = new SampleEntity();
        item.setKey(Constants.RED_DETAIL_TYPE_lUCK_LINK + "");
        item.setType(Constants.AD_SELECT_LIST_TYPE);
        item.setValue("链接");
        list.add(item);
    }

    public void setProAndCity(DistrictEntity data) {
        ArrayList<DistrictEntity.AreaEntity> list = (ArrayList<DistrictEntity.AreaEntity>) data.getArea();

        setUnlimited(list);

        String province = "";
        String city = "";

        if (mSuperadditionEntity != null) {
            //红包类型
            for (SampleEntity item : mTypeList) {
                if (mSuperadditionEntity.getType().equals(item.getKey())) {
                    item.setCheck(true);
                    this.mAdEntity.setType(item);
                }
            }
            if (mSuperadditionEntity.getType().equals(Constants.RED_DETAIL_TYPE_NEAR) || !"0.00".equals(mSuperadditionEntity.getRange())) {
                //范围
                for (SampleEntity item : mDistanceList) {
                    if (mSuperadditionEntity.getRange().equals(item.getCount())) {
                        item.setCheck(true);
                        this.mAdEntity.setDistance(item);
                    }
                }
            } else {
                mDistanceList.get(0).setCheck(true);
                this.mAdEntity.setDistance(mDistanceList.get(0));
            }
            //省市
            province = mSuperadditionEntity.getProvince();
            city = mSuperadditionEntity.getCity();
        } else {
            mTypeList.get(0).setCheck(true);
            //圈子和拼手气红包，mAdEntity有值
            if(mAdEntity.getType() == null || !("" + Constants.RED_DETAIL_TYPE_lUCK).equals(mAdEntity.getType().getKey())){
                this.mAdEntity.setType(mTypeList.get(0));
            }
            mDistanceList.get(0).setCheck(true);
            this.mAdEntity.setDistance(mDistanceList.get(0));

            province = MainApplication.getContext().getProvince();
            city = MainApplication.getContext().getCity();
        }

        DistrictEntity.AreaEntity currentProvince = null;
        DistrictEntity.AreaEntity.CcEntity currentCity = null;
        DistrictEntity.AreaEntity defaultProvince = null;
        DistrictEntity.AreaEntity.CcEntity defaultCity = null;

        if (!TextUtils.isEmpty(province)) {
            List<DistrictEntity.AreaEntity.CcEntity> cc = null;
            for (DistrictEntity.AreaEntity item : list) {
                if (province.equals(item.getP())) {
                    currentProvince = item;
                    cc = item.getCc();
                    break;
                }
            }
            if (!ListUtils.isEmpty(cc)) {
                for (DistrictEntity.AreaEntity.CcEntity item : cc) {
                    if (city.equals(item.getC())) {
                        currentCity = item;
                        break;
                    }
                }
            }
        }
        list.get(0).setCheck(true);
        defaultProvince = list.get(0);
        defaultProvince.getCc().get(0).setCheck(true);
        defaultCity = defaultProvince.getCc().get(0);

        if (currentProvince == null) {
            currentProvince = defaultProvince;
        }
        if (currentCity == null) {
            currentCity = defaultCity;
        }

        if (mSuperadditionEntity != null) {
            this.mAdEntity.setProvince(currentProvince);
            this.mAdEntity.setCity(currentCity);
        } else {
            this.mAdEntity.setProvince(defaultProvince);
            this.mAdEntity.setCity(defaultCity);
        }
        //this.mAdEntity.getCity().setCt(null);
        //this.mAdEntity.getProvince().setCc(null);

        mICreateAdView.setInitData(mDistanceList, mTypeList, list, currentProvince, currentCity, defaultProvince, defaultCity, mAdEntity);
    }

    /**
     * 添加不限选项
     *
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
