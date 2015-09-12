package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.InteractivePlatformMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.InteractiveEntity;
import com.chunsun.redenvelope.model.impl.InteractivePlatformModeImpl;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.ui.view.IInteractivePlatformView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractivePlatformPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IInteractivePlatformView mIInteractivePlatformView;
    private InteractivePlatformMode mInteractivePlatformMode;

    public InteractivePlatformPresenter(IInteractivePlatformView iInteractivePlatformView) {
        mIInteractivePlatformView = iInteractivePlatformView;
        mInteractivePlatformMode = new InteractivePlatformModeImpl((InteractivePlatformActivity) iInteractivePlatformView);
    }

    public void getCountryList(String token, int page_index) {
        mInteractivePlatformMode.getCountryList(token, page_index, this);
    }

    public void getLocalList(String token, int page_index) {
        mInteractivePlatformMode.getLocalList(token, page_index, this);
    }

    /**
     * 评论
     *
     * @param currentCheckType
     * @param comment
     */
    public void sendComment(String token, int currentCheckType, String comment) {
        if ("4".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        mIInteractivePlatformView.showLoading();
        switch (currentCheckType) {
            case 0:
                mInteractivePlatformMode.sendComment(token, comment, "全国", "全国", this);
                break;
            case 1:
                mInteractivePlatformMode.sendComment(token, comment, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), this);
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
        mIInteractivePlatformView.hideLoading();
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
