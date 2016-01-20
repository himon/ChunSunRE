package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.NotReceivingRedMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedDetailUnReceiveAndCollectEntity;
import com.chunsun.redenvelope.model.impl.NotReceivingRedModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.ui.view.INotReceivingRedView;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public class NotReceivingRedPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private INotReceivingRedView mINotReceivingRedView;
    private NotReceivingRedMode mNotReceivingRedMode;

    public NotReceivingRedPresenter(INotReceivingRedView notReceivingRedView) {
        this.mINotReceivingRedView = notReceivingRedView;
        mNotReceivingRedMode = new NotReceivingRedModeImpl((NotReceivingRedActivity) notReceivingRedView);
    }

    public void loadData(String token) {
        mNotReceivingRedMode.loadData(token, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COLLECT_RED_ENVELOPE_LIST:
                RedDetailUnReceiveAndCollectEntity entity = (RedDetailUnReceiveAndCollectEntity) data;
                List<RedDetailUnReceiveAndCollectEntity.ResultEntity> result = entity.getResult();
                mINotReceivingRedView.setData(result);
                break;
        }
    }
}
