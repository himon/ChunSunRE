package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.UserEntity;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.model.impl.MeFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentPresenter implements BaseMultiLoadedListener {

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
    public void onSuccess(int event_tag, Object data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                onSuccessGetData((UserEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                ShowToast.Short(msg);
                break;
        }
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
