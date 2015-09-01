package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.UserPrivacyMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.UserPrivacyModeImpl;
import com.chunsun.redenvelope.presenter.OnUpdateUserInfoListener;
import com.chunsun.redenvelope.ui.activity.personal.UserPrivacyActivity;
import com.chunsun.redenvelope.ui.view.IUserPrivacyView;
import com.chunsun.redenvelope.utils.ShowToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/24.
 */
public class UserPrivacyPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private IUserPrivacyView mIUserPrivacyView;
    private UserPrivacyMode mUserPrivacyMode;
    private String mValue;

    public UserPrivacyPresenter(IUserPrivacyView iUserPrivacyView) {
        this.mIUserPrivacyView = iUserPrivacyView;
        this.mUserPrivacyMode = new UserPrivacyModeImpl((UserPrivacyActivity) iUserPrivacyView);
    }

    public void updateUserInfo(String token, String nick_name, String mobile, String weixin, String telphone, String has_send_amount, String sex, String birthday, String job, String qq) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("nick_name", nick_name);
            jsonObj.put("mobile", mobile);
            jsonObj.put("weixin", weixin);
            jsonObj.put("telphone", telphone);
            jsonObj.put("has_send_amount", has_send_amount);
            jsonObj.put("sex", sex);
            jsonObj.put("birthday", birthday);
            jsonObj.put("job", job);
            jsonObj.put("qq", qq);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mValue = jsonObj.toString();
        mUserPrivacyMode.updateUserInfo(token, mValue, this);
    }

    @Override
    public void onSuccess(SampleResponseEntity entity) {
        ShowToast.Short(entity.getMsg());
        mIUserPrivacyView.updateSuccess(mValue);
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
