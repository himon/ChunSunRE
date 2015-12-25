package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.ForwardFragmentMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.ForwardFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.ForwardFragment;
import com.chunsun.redenvelope.ui.view.IForwardFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/5.
 */
public class ForwardFragmentPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IForwardFragmentView mForwardFragmentView;
    private ForwardFragmentMode mForwardFragmentMode;

    private RedListDetailEntity.ResultEntity.PoolEntity currentEntity;

    public ForwardFragmentPresenter(IForwardFragmentView forwardFragmentView) {
        this.mForwardFragmentView = forwardFragmentView;
        mForwardFragmentMode = new ForwardFragmentModeImpl((ForwardFragment) forwardFragmentView);
    }

    /**
     * 获取红包列表
     *
     * @param token
     * @param type
     * @param page_index
     */
    public void loadData(String token, String type, int page_index) {
        mForwardFragmentMode.loadData(token, type, page_index, this);
    }

    /**
     * 获取轮播图广告
     *
     * @param type
     */
    public void getAdData(String type) {
        mForwardFragmentMode.getAdData(type, this);
    }

    /**
     * 抢红包
     *
     * @param token
     * @param id
     */
    public void grabRedEnvelope(String token, String id, RedListDetailEntity.ResultEntity.PoolEntity entity) {
        currentEntity = entity;
        mForwardFragmentMode.grabRedEnvelope(token, id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_RED_ENVELOPE_LIST:
                RedListDetailEntity entity = (RedListDetailEntity) data;
                mForwardFragmentView.setData(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_AD:
                RedAutoAdEntity entity2 = (RedAutoAdEntity) data;
                mForwardFragmentView.setAdData(entity2.getResult().getAdvert());
                break;
            case Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE:
                SampleResponseEntity entity3 = (SampleResponseEntity) data;
                mForwardFragmentView.grabRedEnvelopeSuccess(currentEntity);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
        mForwardFragmentView.toLogin();
    }
}
