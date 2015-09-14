package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.ui.view.IUserInfoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/14.
 */
public class UserInfoPresenter {

    private IUserInfoView mIUserInfoView;

    public UserInfoPresenter(IUserInfoView iUserInfoView) {
        mIUserInfoView = iUserInfoView;
    }

    public void initData() {
        List<SampleEntity> sexList = initSexList();
        List<SampleEntity> jobList = initJobList();
        mIUserInfoView.setData(sexList, jobList);
    }

    private List<SampleEntity> initSexList() {
        List<SampleEntity> sexList = new ArrayList<SampleEntity>();
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("男");
        sexList.add(item);

        item = new SampleEntity();
        item.setKey("2");
        item.setValue("女");
        sexList.add(item);

        return sexList;
    }

    private List<SampleEntity> initJobList() {
        List<SampleEntity> jobList = new ArrayList<SampleEntity>();
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("计算机/互联网/通信");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("2");
        item.setValue("生产/工艺/制造");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("3");
        item.setValue("医疗/护理/制药");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("4");
        item.setValue("金融/银行/投资/保险");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("5");
        item.setValue("商业/服务业/个体经营");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("6");
        item.setValue("文化/广告/传媒");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("7");
        item.setValue("娱乐/艺术/表演");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("8");
        item.setValue("律师/法务");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("9");
        item.setValue("教育/培训");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("10");
        item.setValue("公务员/行政/事业单位");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("11");
        item.setValue("模特");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("12");
        item.setValue("空姐");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("13");
        item.setValue("学生");
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("14");
        item.setValue("其他职业");
        jobList.add(item);

        return jobList;
    }
}
