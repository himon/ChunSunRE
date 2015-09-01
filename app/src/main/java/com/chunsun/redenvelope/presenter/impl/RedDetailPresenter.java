package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.RedDetailMode;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.impl.RedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.RedDetailActivity;
import com.chunsun.redenvelope.ui.view.IRedDetailView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/10.
 */
public class RedDetailPresenter implements BaseSingleLoadedListener<RedDetailEntity> {
    private IRedDetailView redDetailView;
    private RedDetailMode redDetailMode;

    public RedDetailPresenter(IRedDetailView redDetailView) {
        this.redDetailView = redDetailView;
        redDetailMode = new RedDetailModeImpl((RedDetailActivity) redDetailView);
    }

    public void getData(String token, String id) {
        redDetailMode.getRedData(token, id, this);
    }

    @Override
    public void onSuccess(RedDetailEntity entity) {
        RedDetailEntity.ResultEntity.DetailEntity detail = entity.getResult().getDetail();

        ArrayList<String> list = new ArrayList<String>();
        list.add(Constants.HOST_URL + detail.getCover_img_url());

        if (!TextUtils.isEmpty(detail.getImg_url())) {
            String[] url = detail.getImg_url().split(",");
            for (String str : url) {
                if (!TextUtils.isEmpty(str)) {
                    list.add(Constants.HOST_URL + str);
                }
            }
        }
        redDetailView.setData(list, detail);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onException(String msg) {

    }
}
