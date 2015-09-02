package com.chunsun.redenvelope.presenter.impl;

import android.widget.ListView;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.CreateAdNextPageMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.impl.CreateAdNextPageModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextPageActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdNextPageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdNextPagePresenter implements BaseSingleLoadedListener<DistrictEntity>{

    private ICreateAdNextPageView mICreateAdNextPageView;
    private CreateAdNextPageMode mCreateAdNextPageMode;

    public CreateAdNextPagePresenter(ICreateAdNextPageView iCreateAdNextPageView) {
        this.mICreateAdNextPageView = iCreateAdNextPageView;
        mCreateAdNextPageMode = new CreateAdNextPageModeImpl((CreateAdNextPageActivity) iCreateAdNextPageView);
    }

    public void initData() {
        List<SampleEntity> typeList = new ArrayList<>();
        initType(typeList);

        List<SampleEntity> distanceList = new ArrayList<>();
        initDistance(distanceList);

        mCreateAdNextPageMode.initProvinceAndCity();

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
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("1");
        distance.setValue("200米");
        distance.setCount("0.2");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("2");
        distance.setValue("500米");
        distance.setCount("0.5");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("3");
        distance.setValue("1千米");
        distance.setCount("1");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("4");
        distance.setValue("2千米");
        distance.setCount("2");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("5");
        distance.setValue("5千米");
        distance.setCount("5");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("6");
        distance.setValue("10千米");
        distance.setCount("10");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("7");
        distance.setValue("20千米");
        distance.setCount("20");
        list.add(distance);

        distance = new SampleEntity();
        distance.setKey("8");
        distance.setValue("50千米");
        distance.setCount("50");
        list.add(distance);
    }

    /**
     * 初始化类型
     *
     * @param list
     */
    private void initType(List<SampleEntity> list) {
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("生活类");
        list.add(item);

        item = new SampleEntity();
        item.setKey("2");
        item.setValue("企业类");
        list.add(item);

        item = new SampleEntity();
        item.setKey("3");
        item.setValue("附近类");
        list.add(item);

        item = new SampleEntity();
        item.setKey("4");
        item.setValue("链接类");
        list.add(item);
    }

    @Override
    public void onSuccess(DistrictEntity data) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onException(String msg) {

    }
}
