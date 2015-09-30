package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
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
public class MeFragmentPresenter implements BaseSingleLoadedListener<UserEntity> {

    private IMeFragmentView meFragmentView;
    private MeFragmentMode meFragmentMode;

    public MeFragmentPresenter(IMeFragmentView meFragmentView) {
        this.meFragmentView = meFragmentView;
        meFragmentMode = new MeFragmentModeImpl((NewMeFragment) meFragmentView);
    }

    public UserInfoEntity getData(String token) {
        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        if (userEntity == null) {
            meFragmentMode.getUserInfomation(token, this);
            return null;
        } else {
            return userEntity;
        }
    }

    @Override
    public void onSuccess(UserEntity entity) {
        MainApplication.getContext().setmUserEntity(entity.getResult());
        meFragmentView.refresh(entity.getResult());
    }

    @Override
    public void onError(String msg) {
        if ("会员信息不存在".equals(msg)) {
            EventBus.getDefault().post(new MainEvent(Constants.USER_INFO_PASS_FROM_ME));
        }
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
