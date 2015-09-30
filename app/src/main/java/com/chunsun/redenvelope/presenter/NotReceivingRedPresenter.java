package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.NotReceivingRedMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.impl.NotReceivingRedModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.ui.view.INotReceivingRedView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public class NotReceivingRedPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private INotReceivingRedView mINotReceivingRedView;
    private NotReceivingRedMode mNotReceivingRedMode;
    private RedDetailUnReceiveAndCollectEntity.ResultEntity mCurrentRed;

    public NotReceivingRedPresenter(INotReceivingRedView notReceivingRedView) {
        this.mINotReceivingRedView = notReceivingRedView;
        mNotReceivingRedMode = new NotReceivingRedModeImpl((NotReceivingRedActivity) notReceivingRedView);
    }

    public void loadData(String token) {
        mNotReceivingRedMode.loadData(token, this);
    }

    public void grabRedEnvelope(String token, RedDetailUnReceiveAndCollectEntity.ResultEntity entity) {
        mCurrentRed = entity;
        mNotReceivingRedMode.grabRedEnvelope(token, entity.getId() + "", this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_RED_ENVELOPE_LIST:
                RedDetailUnReceiveAndCollectEntity entity = (RedDetailUnReceiveAndCollectEntity) data;
                List<RedDetailUnReceiveAndCollectEntity.ResultEntity> result = entity.getResult();
                mINotReceivingRedView.setData(result);
                break;
            case Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE:
                mINotReceivingRedView.grabRedEnvelopeSuccess(mCurrentRed);
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
