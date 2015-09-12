package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.WebRedDetailMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.event.WebRedDetailEvent;
import com.chunsun.redenvelope.model.impl.WebRedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.view.IWebRedDetailView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/11.
 */
public class WebRedDetailPresenter implements BaseSingleLoadedListener<RedDetailEntity> {

    private IWebRedDetailView webRedDetailView;
    private WebRedDetailMode webRedDetailMode;

    public WebRedDetailPresenter(IWebRedDetailView webRedDetailView) {
        this.webRedDetailView = webRedDetailView;
        webRedDetailMode = new WebRedDetailModeImpl((WebRedDetailActivity) webRedDetailView);
    }

    /**
     * 获取红包详情
     *
     * @param token
     * @param id
     */
    public void getData(String token, String id) {
        webRedDetailMode.getRedData(token, id, this);
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

    @Override
    public void onSuccess(RedDetailEntity entity) {
        RedDetailEntity.ResultEntity.DetailEntity detail = entity.getResult().getDetail();
        webRedDetailView.loadUrl(detail);
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
