package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.ui.base.view.LoadingView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/14.
 */
public interface IUserInfoView extends LoadingView {

    void setData(ArrayList<SampleEntity> setList, ArrayList<SampleEntity> jobList);

    void editUserBirthdaySuccess(String birthday);

    void editUserHeadLogoSuccess(SampleResponseEntity path);
}
