package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserRewardMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.UserPublicInfoEntity;
import com.chunsun.redenvelope.model.impl.UserRewardModeImpl;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.ui.view.IUserRewardView;
import com.chunsun.redenvelope.utils.ShowToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserRewardPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IUserRewardView mIUserRewardView;
    private UserRewardMode mUserRewardMode;

    public UserRewardPresenter(IUserRewardView iUserRewardView) {
        mIUserRewardView = iUserRewardView;
        mUserRewardMode = new UserRewardModeImpl((UserRewardActivity) iUserRewardView);
    }

    public void getData(String token, String user_id) {
        mUserRewardMode.getData(token, user_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                mIUserRewardView.setData(((UserPublicInfoEntity) data).getResult());
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
     * 显示公开数据
     *
     * @param entity
     */
    public void setSecretInfo(UserPublicInfoEntity.ResultEntity entity, TextView name, TextView mobile, TextView wechat, TextView tel, TextView sendTotal, TextView sex, TextView age, TextView job, TextView qq, TextView desc, TextView code, LinearLayout sexLayout, LinearLayout ageLayout, LinearLayout jobLayout) {

        boolean isShowName = true;
        boolean isShowMobile = true;
        boolean isShowWeixin = true;
        boolean isShowTel = true;
        boolean isShowSendTotalMoney = true;

        boolean isShowSex = true;
        boolean isShowAge = true;
        boolean isShowJob = true;
        boolean isShowQQ = true;

        String private_json = entity.getPrivate_json();
        if (!TextUtils.isEmpty(private_json)) {
            try {
                JSONObject obj = new JSONObject(private_json);
                isShowName = obj.getString("nick_name").equalsIgnoreCase("True") ? true : false;
                isShowMobile = obj.getString("mobile").equalsIgnoreCase("True") ? true : false;
                isShowWeixin = obj.getString("weixin").equalsIgnoreCase("True") ? true : false;
                isShowTel = obj.getString("telphone").equalsIgnoreCase("True") ? true : false;
                isShowSendTotalMoney = obj.getString("has_send_amount").equalsIgnoreCase("True") ? true : false;
                isShowSex = obj.getString("sex").equalsIgnoreCase("True") ? true : false;
                isShowAge = obj.getString("birthday").equalsIgnoreCase("True") ? true : false;
                isShowJob = obj.getString("job").equalsIgnoreCase("True") ? true : false;
                isShowQQ = obj.getString("qq").equalsIgnoreCase("True") ? true : false;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (isShowName) {
            name.setText(entity.getNick_name());
        } else {
            name.setText("保密");
        }

        if (isShowMobile) {
            mobile.setText(entity.getMobile());
        } else {
            mobile.setText("保密");
        }

        if (isShowWeixin) {
            wechat.setText(entity.getWeixin());
        } else {
            wechat.setText("保密");
        }

        if (isShowTel) {
            tel.setText(entity.getTelphone());
        } else {
            tel.setText("保密");
        }

        if (isShowSendTotalMoney) {
            sendTotal.setText(entity.getHas_send_amount() + "元");
        } else {
            sendTotal.setText("保密");
        }

        if (isShowSex) {
            sex.setText(entity.getSex());
        } else {
            sex.setText("保密");
        }

        if (isShowAge) {
            age.setText(entity.getAge());
        } else {
            age.setText("保密");
        }

        if (isShowJob) {
            job.setText(entity.getJob());
        } else {
            job.setText("保密");
        }

        if (isShowQQ) {
            qq.setText(entity.getQq());
        } else {
            qq.setText("保密");
        }

        desc.setText(entity.getRemark());
        code.setText(entity.getInvitation_code());

        if (Constants.USER_REGISTER_TYPE_PERSONAL.equals(entity.getType())) {
            // 个人
            sexLayout.setVisibility(View.VISIBLE);
            ageLayout.setVisibility(View.VISIBLE);
            jobLayout.setVisibility(View.VISIBLE);
        } else if (Constants.USER_REGISTER_TYPE_ENTERPRISE.equals(entity.getType())) {
            // 企业
            sexLayout.setVisibility(View.GONE);
            ageLayout.setVisibility(View.GONE);
            jobLayout.setVisibility(View.GONE);
        }
    }
}
