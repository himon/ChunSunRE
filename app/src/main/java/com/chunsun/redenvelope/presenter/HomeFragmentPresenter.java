package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.HomeFragmentMode;
import com.chunsun.redenvelope.model.impl.HomeFragmentModeImpl;
import com.chunsun.redenvelope.ui.activity.red.SearchCircleActivity;
import com.chunsun.redenvelope.ui.activity.red.TaskListActivity;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.ui.view.IHomeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/10.
 */
public class HomeFragmentPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IHomeFragmentView mHomeFragmentView;
    private HomeFragmentMode mHomeFragmentMode;
    private String mCurrentRedId;

    public HomeFragmentPresenter(IHomeFragmentView homeFragmentView) {
        this.mHomeFragmentView = homeFragmentView;
        if (mHomeFragmentView instanceof HomeFragment) {
            mHomeFragmentMode = new HomeFragmentModeImpl((HomeFragment) homeFragmentView, null);
        } else if (mHomeFragmentView instanceof TaskListActivity) {
            mHomeFragmentMode = new HomeFragmentModeImpl(null, (TaskListActivity) homeFragmentView);
        } else {
            mHomeFragmentMode = new HomeFragmentModeImpl(null, (SearchCircleActivity) homeFragmentView);
        }
    }

    public void loadData(String token, int type, int page_index, int order_type, String keywords) {
        mHomeFragmentMode.loadData(token, type, page_index, order_type, keywords, this);
    }

    public void getAdData(String type) {
        mHomeFragmentMode.getAdData(type, this);
    }

    public void grabRedEnvelope(String mToken, String id) {
        mCurrentRedId = id;
        mHomeFragmentMode.grabRedEnvelope(mToken, id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        mHomeFragmentView.hideLoading();
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
                mHomeFragmentView.gradRedEnvelopeSuccess(mCurrentRedId);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
        mHomeFragmentView.hideLoading();
    }
}
