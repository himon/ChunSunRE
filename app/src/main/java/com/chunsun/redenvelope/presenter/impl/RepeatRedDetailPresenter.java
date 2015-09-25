package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RepeatRedDetailMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.impl.RepeatRedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.view.IRepeatRedDetailView;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 15:54
 * @des 转发类红包的Presenter
 */
public class RepeatRedDetailPresenter implements BaseMultiLoadedListener<BaseEntity> {

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
}
