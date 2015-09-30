package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.HomeFragmentMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.HomeFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.ui.view.IHomeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/5.
 */
public class HomeFragmentPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IHomeFragmentView mHomeFragmentView;
    private HomeFragmentMode mHomeFragmentMode;

    private RedListDetailEntity.ResultEntity.PoolEntity currentEntity;

    public HomeFragmentPresenter(IHomeFragmentView homeFragmentView) {
        this.mHomeFragmentView = homeFragmentView;
        mHomeFragmentMode = new HomeFragmentModeImpl((HomeFragment) homeFragmentView);
    }

    /**
     * 获取红包列表
     *
     * @param token
     * @param type
     * @param page_index
     */
    public void loadData(String token, String type, int page_index) {
        mHomeFragmentMode.loadData(token, type, page_index, this);
    }

    /**
     * 获取轮播图广告
     *
     * @param type
     */
    public void getAdData(String type) {
        mHomeFragmentMode.getAdData(type, this);
    }

    /**
     * 抢红包
     *
     * @param token
     * @param id
     */
    public void grabRedEnvelope(String token, String id, RedListDetailEntity.ResultEntity.PoolEntity entity) {
        currentEntity = entity;
        mHomeFragmentMode.grabRedEnvelope(token, id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_RED_ENVELOPE_LIST:
                RedListDetailEntity entity = (RedListDetailEntity) data;
                mHomeFragmentView.setData(entity.getResult());
                break;
            case Constants.LISTENER_TYPE_AD:
                RedAutoAdEntity entity2 = (RedAutoAdEntity) data;
                mHomeFragmentView.setAdData(entity2.getResult().getAdvert());
                break;
            case Constants.LISTENER_TYPE_GRAD_RED_ENVELOPE:
                SampleResponseEntity entity3 = (SampleResponseEntity) data;
                mHomeFragmentView.grabRedEnvelopeSuccess(currentEntity);
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
