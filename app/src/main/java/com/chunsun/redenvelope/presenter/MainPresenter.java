package com.chunsun.redenvelope.presenter;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.UserNoReadCountEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.MainMode;
import com.chunsun.redenvelope.model.impl.MainModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IMainView;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/21 16:39
 * @des
 */
public class MainPresenter extends UserLosePresenter<IMainView> implements UserLoseMultiLoadedListener<BaseEntity>{

    private IMainView mIMainView;
    private MainMode mMainMode;

    public MainPresenter(IMainView view) {
        this.mIMainView = view;
        mMainMode = new MainModeImpl((Activity) view);
    }

    /**
     * 获取用户未读信息数量
     *
     * @param token
     */
    public void getUserNoReadCount(String token) {
        mMainMode.getUserNoReadCount(token, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_NO_READ_COUNT:
                UserNoReadCountEntity entity = (UserNoReadCountEntity) data;
                mIMainView.setUserNoReadCount(entity.getResult());
                break;
        }
    }
}
