package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.WebRedDetailEvent;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.model.impl.WebRedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IWebRedDetailView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/11.
 */
public class WebRedDetailPresenter extends UserLosePresenter<IWebRedDetailView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IWebRedDetailView mIWebRedDetailView;
    private WebRedDetailMode mWebRedDetailMode;

    public WebRedDetailPresenter(IWebRedDetailView webRedDetailView) {
        this.mIWebRedDetailView = webRedDetailView;
        mWebRedDetailMode = new WebRedDetailModeImpl((WebRedDetailActivity) webRedDetailView);
    }

    /**
     * 获取红包详情
     *
     * @param token
     * @param id
     */
    public void getData(String token, String id) {
        mWebRedDetailMode.getRedData(token, id, this);
    }

    /**
     * 设置收藏当前红包
     *
     * @param mToken
     * @param id
     */
    public void favorite(String mToken, String id) {
        mWebRedDetailMode.setFavorite(mToken, id, this);
    }

    public void getGrabByToken(String token, String id) {
        mWebRedDetailMode.getGrabByToken(token, id, this);
    }

    /**
     * Pre_load_seconds倒计时
     */
    public void countDown(final int seconds) {
        new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (count <= seconds) {
                    try {
                        Thread.sleep(1000);
                        count++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                EventBus.getDefault().post(new WebRedDetailEvent(""));
            }
        }).start();
    }

    public void getDataSuccess(RedDetailEntity entity) {
        RedDetailEntity.ResultEntity.DetailEntity detail = entity.getResult().getDetail();
        mIWebRedDetailView.loadUrl(detail);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL:
                getDataSuccess((RedDetailEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_USER_GRAB_BY_TOKEN:
                GrabEntity entity = (GrabEntity) data;
                mIWebRedDetailView.setGrab(entity);
                break;
            case Constants.LISTENER_TYPE_JUST_OPEN_RED:
            case Constants.LISTENER_TYPE_SHARE_OPEN_RED:
                ShowToast.Short(((SampleResponseEntity)data).getMsg());
                mIWebRedDetailView.shareSuccess();
                break;
            case Constants.LISTENER_TYPE_FAVORITE:
                mIWebRedDetailView.setFavoriteSuccess((SampleResponseEntity) data);
                break;
        }
    }

    /**
     * 拆红包
     *
     * @param token
     * @param grab_id
     * @param shareType
     */
    public void shareOpen(String token, String grab_id, String shareType) {
        mWebRedDetailMode.shareOpen(token, grab_id, shareType, this);
    }

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     */
    public void justOpen(String token, String grab_id) {
        mWebRedDetailMode.justOpen(token, grab_id, this);
    }
}
