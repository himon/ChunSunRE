package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.SampleEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/14.
 */
public interface IUserInfoView {

    void setData(ArrayList<SampleEntity> setList, ArrayList<SampleEntity> jobList);

    void editUserBirthdaySuccess(String birthday);
}
