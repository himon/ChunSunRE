package com.chunsun.redenvelope.presenter;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.AtMessageEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AtMeMode;
import com.chunsun.redenvelope.model.impl.AtMeModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IAtMeView;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/19 17:53
 * @des
 */
public class AtMePresenter extends UserLosePresenter<IAtMeView> implements UserLoseMultiLoadedListener<BaseEntity>{

    private IAtMeView mIAtMeView;
    private AtMeMode mAtMeMode;

    public AtMePresenter(IAtMeView view) {
        super();
        this.mIAtMeView = view;
        this.mAtMeMode = new AtMeModeImpl((Activity) view);
    }

    /**
     * 获取未读消息
     * @param token
     * @param page_index
     */
    public void getUserNoReadMessage(String token, int page_index) {
        this.mAtMeMode.getUserNoReadMessage(token, page_index, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag){
            case Constants.LISTENER_TYPE_GET_USER_NO_READ_MESSAGE:
                List<AtMessageEntity.ResultEntity> list = ((AtMessageEntity) data).getResult();
                mIAtMeView.setData(list);
                break;
        }
    }
}
