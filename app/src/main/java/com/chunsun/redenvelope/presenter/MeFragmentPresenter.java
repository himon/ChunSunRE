package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.model.impl.MeFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentPresenter implements BaseMultiLoadedListener<UserEntity> {

    private IMeFragmentView meFragmentView;
    private MeFragmentMode meFragmentMode;
    private boolean mFlag;

    public MeFragmentPresenter(IMeFragmentView meFragmentView) {
        this.meFragmentView = meFragmentView;
        meFragmentMode = new MeFragmentModeImpl((NewMeFragment) meFragmentView);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @param b     是否是点击click tab请求的
     * @return
     */
    public UserInfoEntity getData(String token, boolean b) {
        this.mFlag = b;
        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        if (userEntity == null) {
            meFragmentMode.getUserInfomation(token, this);
            return null;
        } else {
            meFragmentView.setLoginStatus();
            return userEntity;
        }
    }

    public void onSuccessGetData(UserEntity entity) {
        MainApplication.getContext().setmUserEntity(entity.getResult());
        meFragmentView.refresh(entity.getResult());
        meFragmentView.setLoginStatus();
    }

    @Override
    public void onSuccess(int event_tag, UserEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                onSuccessGetData(data);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        if (mFlag) {
            EventBus.getDefault().post(new MainEvent(Constants.USER_INFO_PASS_FROM_ME));
        } else {
            meFragmentView.loginError();
        }
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
