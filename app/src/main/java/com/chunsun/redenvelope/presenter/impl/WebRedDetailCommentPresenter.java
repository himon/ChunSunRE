package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailCommentMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.WebRedDetailCommentModeImpl;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailCommentActivity;
import com.chunsun.redenvelope.ui.view.IWebRedDetailCommentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/9/14.
 */
public class WebRedDetailCommentPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IWebRedDetailCommentView mIWebRedDetailCommentView;
    private WebRedDetailCommentMode mWebRedDetailCommentMode;

    public WebRedDetailCommentPresenter(IWebRedDetailCommentView iWebRedDetailCommentView) {
        mIWebRedDetailCommentView = iWebRedDetailCommentView;
        mWebRedDetailCommentMode = new WebRedDetailCommentModeImpl((WebRedDetailCommentActivity) iWebRedDetailCommentView);
    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        mWebRedDetailCommentMode.getCommentList(hb_id, page_index, this);
    }

    /**
     * 获取领取记录列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getRedRecordList(String hb_id, int page_index) {
        mWebRedDetailCommentMode.getRedRecordList(hb_id, page_index, this);
    }

    /**
     * 发送评论
     *
     * @param comment
     */
    public void sendComment(String comment, String token, String id) {
        if ("4".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        mWebRedDetailCommentMode.sendComment(token, id, comment, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_COMMENT_LIST:
                onGetCommentSuccesss((RedDetailCommentEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_RECORD_LIST:
                onGetRedRecordSuccesss((RedDetailGetRedRecordEntity) data);
                break;
            case Constants.LISTENER_TYPE_COMMENT:
                onCommentSuccess((SampleResponseEntity) data);
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
     * 成功获取评论列表
     *
     * @param response
     */
    public void onGetCommentSuccesss(RedDetailCommentEntity response) {
        RedDetailCommentEntity.ResultEntity result = response.getResult();
        mIWebRedDetailCommentView.setCommentData(result);
    }

    /**
     * 成功获取领取记录列表
     *
     * @param response
     */
    public void onGetRedRecordSuccesss(RedDetailGetRedRecordEntity response) {
        RedDetailGetRedRecordEntity.ResultEntity result = response.getResult();
        mIWebRedDetailCommentView.setGetRedRecord(result);
    }

    /**
     * 评论成功
     */
    public void onCommentSuccess(SampleResponseEntity response) {
        ShowToast.Short(response.getMsg());
        mIWebRedDetailCommentView.commentSuccess();
    }
}
