package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.NearFragmentMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.model.impl.NearFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.NearFragment;
import com.chunsun.redenvelope.ui.view.INearFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/10.
 */
public class NearFragmentPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private INearFragmentView mNearFragmentView;
    private NearFragmentMode mNearFragmentMode;
    private String mCurrentRedId;

    public NearFragmentPresenter(INearFragmentView nearFragmentView) {
        this.mNearFragmentView = nearFragmentView;
        mNearFragmentMode = new NearFragmentModeImpl((NearFragment) nearFragmentView);
    }

    public void loadData(String token, String type, int page_index) {
        mNearFragmentMode.loadData(token, type, page_index, this);
    }

    public void getAdData(String type) {
        mNearFragmentMode.getAdData(type, this);
    }

    public void grabRedEnvelope(String mToken, String id) {
        mCurrentRedId = id;
        mNearFragmentMode.grabRedEnvelope(mToken, id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_RED_ENVELOPE_LIST:
                RedListDetailEntity entity = (RedListDetailEntity) data;
                mNearFragmentView.setData(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_AD:
                RedAutoAdEntity entity2 = (RedAutoAdEntity) data;
                mNearFragmentView.setAdData(entity2.getResult().getAdvert());
                break;
            case Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE:
                mNearFragmentView.gradRedEnvelopeSuccess(mCurrentRedId);
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
