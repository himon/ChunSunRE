package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.CollectRedEnvelopeListMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.impl.CollectRedEnvelopeListModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.ui.view.ICollectRedEnvelopeListView;

/**
 * Created by Administrator on 2015/8/15.
 */
public class CollectRedEnvelopeListPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

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

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST:
                RedDetailUnReceiveAndCollectEntity entity = (RedDetailUnReceiveAndCollectEntity) data;
                mICollectRedEnvelopeListView.setData(entity.getResult());
                break;
        }
    }
}
