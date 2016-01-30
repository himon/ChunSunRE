package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.UserEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.model.impl.MeFragmentModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentPresenter extends UserLosePresenter<IMeFragmentView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IMeFragmentView meFragmentView;
    private MeFragmentMode meFragmentMode;

    public MeFragmentPresenter(IMeFragmentView meFragmentView) {
        this.meFragmentView = meFragmentView;
        meFragmentMode = new MeFragmentModeImpl((NewMeFragment) meFragmentView);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public void getData(String token) {
        meFragmentMode.getUserInfomation(token, this);
    }

    public void onSuccessGetData(UserEntity entity) {
        MainApplication.getContext().setmUserEntity(entity.getResult());
        meFragmentView.refresh(entity.getResult());
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                onSuccessGetData((UserEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {

    }
}
