package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CollectRedEnvelopeListMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.impl.CollectRedEnvelopeListModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.ui.view.ICollectRedEnvelopeListView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CollectRedEnvelopeListPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private ICollectRedEnvelopeListView mICollectRedEnvelopeListView;
    private CollectRedEnvelopeListMode mCollectRedEnvelopeListMode;
    private RedDetailUnReceiveAndCollectEntity.ResultEntity mCurrentEntity;

    public CollectRedEnvelopeListPresenter(ICollectRedEnvelopeListView iCollectRedEnvelopeListView) {
        this.mICollectRedEnvelopeListView = iCollectRedEnvelopeListView;
        mCollectRedEnvelopeListMode = new CollectRedEnvelopeListModeImpl((CollectRedEnvelopeListActivity) iCollectRedEnvelopeListView);
    }

    /**
     * 获取收藏列表
     *
     * @param token
     */
    public void loadData(String token) {
        mCollectRedEnvelopeListMode.loadData(token, this);
    }

    /**
     * 抢红包
     *
     * @param token
     * @param entity
     */
    public void grabRedEnvelope(String token, RedDetailUnReceiveAndCollectEntity.ResultEntity entity) {
        mCurrentEntity = entity;
        mCollectRedEnvelopeListMode.grabRedEnvelope(token, entity.getId() + "", this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST:
                RedDetailUnReceiveAndCollectEntity entity = (RedDetailUnReceiveAndCollectEntity) data;
                mICollectRedEnvelopeListView.setData(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE:
                mICollectRedEnvelopeListView.grabRedEnvelopeSuccess(mCurrentEntity);
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
}
