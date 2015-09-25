package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/14.
 */
public interface IUserInfoView extends BaseView{

    void setData(ArrayList<SampleEntity> setList, ArrayList<SampleEntity> jobList);

    void editUserBirthdaySuccess(String birthday);

    void editUserHeadLogoSuccess(SampleResponseEntity path);
}
