package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.InteractivePlatformMode;
import com.chunsun.redenvelope.model.impl.InteractivePlatformModeImpl;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.fragment.tab.InteractiveFragment;
import com.chunsun.redenvelope.ui.view.IInteractivePlatformView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractivePlatformPresenter extends UserLosePresenter<IInteractivePlatformView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IInteractivePlatformView mIInteractivePlatformView;
    private InteractivePlatformMode mInteractivePlatformMode;
    private Gson mGson;

    public InteractivePlatformPresenter(IInteractivePlatformView iInteractivePlatformView) {
        mIInteractivePlatformView = iInteractivePlatformView;
        if (iInteractivePlatformView instanceof InteractivePlatformActivity) {
            mInteractivePlatformMode = new InteractivePlatformModeImpl((InteractivePlatformActivity) iInteractivePlatformView, null);
        } else {
            mInteractivePlatformMode = new InteractivePlatformModeImpl(null, (InteractiveFragment) iInteractivePlatformView);
        }
        mGson = new Gson();
    }

    public void getCountryList(String token, int page_index) {
        mInteractivePlatformMode.getCountryCommentList(token, page_index, this);
    }

    public void getLocalList(String token, int page_index) {
        mInteractivePlatformMode.getLocalCommentList(token, page_index, this);
    }

    /**
     * 评论
     *
     * @param currentCheckType
     * @param comment
     * @param at
     */
    public void sendComment(String token, int currentCheckType, String comment, String at) {

        if(MainApplication.getContext().getUserEntity() == null){
            mIInteractivePlatformView.UserIsEmpty();
            return;
        }
        if ("4".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        mIInteractivePlatformView.showLoading();
        switch (currentCheckType) {
            case 0:
                mInteractivePlatformMode.sendComment(token, comment, "全国", "全国", at, this);
                break;
            case 1:
                mInteractivePlatformMode.sendComment(token, comment, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), at, this);
                break;
        }
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_INTERACTIVE_COUNTRY:
                mIInteractivePlatformView.setCountryList((InteractiveEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_INTERACTIVE_LOCAL:
                mIInteractivePlatformView.setLocalList((InteractiveEntity) data);
                break;
            case Constants.LISTENER_TYPE_COMMENT:
                mIInteractivePlatformView.hideLoading();
                mIInteractivePlatformView.commentSuccess();
                break;
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onError(int event_tag, String msg) {
        ShowToast.Short(msg);
        mIInteractivePlatformView.hideLoading();
    }

    public void setCountryCash(String country) {
        InteractiveEntity entity = mGson.fromJson(country, InteractiveEntity.class);
        if (entity.isSuccess()) {
            mIInteractivePlatformView.setCountryList(entity);
        }
    }

    public void setLocalCash(String local) {
        InteractiveEntity entity = mGson.fromJson(local, InteractiveEntity.class);
        if (entity.isSuccess()) {
            mIInteractivePlatformView.setLocalList(entity);
        }
    }
}
