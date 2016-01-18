package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RepeatRedEnvelopeGetHostEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RepeatRedDetailMode;
import com.chunsun.redenvelope.model.impl.RepeatRedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IRepeatRedDetailView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATE} 15:54
 * @des 转发类红包的Presenter
 */
public class RepeatRedDetailPresenter extends UserLosePresenter<IRepeatRedDetailView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IRepeatRedDetailView mIRepeatRedDetailView;
    private RepeatRedDetailMode mRepeatRedDetailMode;

    public RepeatRedDetailPresenter(IRepeatRedDetailView iRepeatRedDetailView) {
        mIRepeatRedDetailView = iRepeatRedDetailView;
        mRepeatRedDetailMode = new RepeatRedDetailModeImpl((RepeatRedDetailActivity) iRepeatRedDetailView);
    }

    public void getRedDetail(String token, String hb_id) {
        mRepeatRedDetailMode.getRedDetail(token, hb_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL:
                mIRepeatRedDetailView.getRedDetailSuccess(((RedDetailEntity) data).getResult().getDetail());
                break;
            case Constants.LISTENER_TYPE_GET_COMMENT_LIST:
                mIRepeatRedDetailView.setCommentData(((RedDetailCommentEntity) data).getResult());
                break;
            case Constants.LISTENER_TYPE_FAVORITE:
                mIRepeatRedDetailView.setFavoriteSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_COMMENT:
                SampleResponseEntity entity = (SampleResponseEntity) data;
                ShowToast.Short(entity.getMsg());
                mIRepeatRedDetailView.commentSuccess();
                break;
            case Constants.LISTENER_TYPE_REPEAT_GET_HOST:
                mIRepeatRedDetailView.setShareHost((RepeatRedEnvelopeGetHostEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {

    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        mRepeatRedDetailMode.getCommentList(hb_id, page_index, this);
    }

    /**
     * 收藏
     *
     * @param token
     * @param id
     */
    public void favorite(String token, String id) {
        mRepeatRedDetailMode.setFavorite(token, id, this);
    }

    /**
     * 评论
     *
     * @param comment
     * @param token
     * @param id
     */
    public void sendComment(String comment, String token, String id) {
        if ("4".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        mRepeatRedDetailMode.sendComment(token, id, comment, this);
    }

    /**
     * 转发类获取host
     * @param token
     * @param hb_id
     * @param platform
     * @param is_valid
     */
    public void getHost(String token, String hb_id, String platform, boolean is_valid) {
        mRepeatRedDetailMode.getHost(token, hb_id, platform, is_valid, this);
    }
}
