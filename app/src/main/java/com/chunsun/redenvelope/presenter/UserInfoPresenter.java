package com.chunsun.redenvelope.presenter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.DatePicker;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.UserInfoMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.UserInfoModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.ui.view.IUserInfoView;
import com.chunsun.redenvelope.utils.Base64Utils;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2015/9/14.
 */
public class UserInfoPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IUserInfoView mIUserInfoView;
    private UserInfoMode mUserInfoMode;

    private String mBirthday;

    public UserInfoPresenter(IUserInfoView iUserInfoView) {
        mIUserInfoView = iUserInfoView;
        mUserInfoMode = new UserInfoModeImpl((UserInfoActivity) iUserInfoView);
    }

    /**
     * 上传头像
     *
     * @param token
     * @param data
     */
    public void saveHeadLogo(String token, Intent data) {
        mIUserInfoView.showLoading();
        if (data != null) {
            byte[] bis = data.getByteArrayExtra(Constants.EXTRA_KEY2);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
            String base64 = Base64Utils.bitmapToBase64(bitmap);
            mUserInfoMode.editUserInfo(token, "img_url", base64, this);
        }
    }

    public void initData() {
        ArrayList<SampleEntity> sexList = initSexList();
        ArrayList<SampleEntity> jobList = initJobList();
        mIUserInfoView.setData(sexList, jobList);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_EDIT_USER_INFO:
                if (TextUtils.isEmpty(mBirthday)) {
                    mIUserInfoView.editUserHeadLogoSuccess((SampleResponseEntity) data);
                    mIUserInfoView.hideLoading();
                } else {
                    mIUserInfoView.editUserBirthdaySuccess(mBirthday);
                }
                break;
        }
    }

    @Override
    public void onError(String msg) {
        mIUserInfoView.showLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        mIUserInfoView.showLoading();
    }

    private ArrayList<SampleEntity> initSexList() {
        ArrayList<SampleEntity> sexList = new ArrayList<SampleEntity>();
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("男");
        item.setType(Constants.EDIT_TYPE_SEX);
        sexList.add(item);

        item = new SampleEntity();
        item.setKey("2");
        item.setValue("女");
        item.setType(Constants.EDIT_TYPE_SEX);
        sexList.add(item);

        return sexList;
    }

    private ArrayList<SampleEntity> initJobList() {
        ArrayList<SampleEntity> jobList = new ArrayList<SampleEntity>();
        SampleEntity item = new SampleEntity();
        item.setKey("1");
        item.setValue("计算机/互联网/通信");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("2");
        item.setValue("生产/工艺/制造");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("3");
        item.setValue("医疗/护理/制药");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("4");
        item.setValue("金融/银行/投资/保险");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("5");
        item.setValue("商业/服务业/个体经营");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("6");
        item.setValue("文化/广告/传媒");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("7");
        item.setValue("娱乐/艺术/表演");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("8");
        item.setValue("律师/法务");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("9");
        item.setValue("教育/培训");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("10");
        item.setValue("公务员/行政/事业单位");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("11");
        item.setValue("模特");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("12");
        item.setValue("空姐");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("13");
        item.setValue("学生");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        item = new SampleEntity();
        item.setKey("14");
        item.setValue("其他职业");
        item.setType(Constants.EDIT_TYPE_JOB);
        jobList.add(item);

        return jobList;
    }

    public void editBirthday(final String token) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog((UserInfoActivity) mIUserInfoView,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int month, int day) {
                        // 因为month会比正常值小1
                        month++;
                        String strMonth = month < 10 ? ("0" + month)
                                : ("" + month);
                        String strDay = day < 10 ? ("0" + day) : ("" + day);
                        String date = year + "年" + strMonth + "月" + strDay
                                + "日";
                        mBirthday = date;
                        mUserInfoMode.editUserInfo(token, "birthday", year + "/" + strMonth + "/" + strDay, UserInfoPresenter.this);
                    }
                }, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH)).show();
    }
}
