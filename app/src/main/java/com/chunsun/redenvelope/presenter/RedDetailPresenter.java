package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.ShareLimitEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailMode;
import com.chunsun.redenvelope.model.impl.RedDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IRedDetailView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/10.
 */
public class RedDetailPresenter extends UserLosePresenter<IRedDetailView> implements UserLoseMultiLoadedListener<BaseEntity> {
    private IRedDetailView mIRedDetailView;
    private RedDetailMode mRedDetailMode;

    public RedDetailPresenter(IRedDetailView redDetailView) {
        this.mIRedDetailView = redDetailView;
        mRedDetailMode = new RedDetailModeImpl((RedDetailActivity) redDetailView);
    }

    public void getData(String token, String id) {
        mRedDetailMode.getRedData(token, id, this);
    }

    /**
     * 获取分享次数信息
     *
     * @param token
     */
    public void getShareLimit(String token) {
        mRedDetailMode.getShareLimit(token, this);
    }

    public void getGrabByToken(String token, String id) {
        mRedDetailMode.getGrabByToken(token, id, this);
    }

    public void getDataSuccess(RedDetailEntity entity) {
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
        mIRedDetailView.setData(list, detail);
    }

    private void grabData(GrabEntity entity) {

    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL:
                getDataSuccess((RedDetailEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_LIMIT:
                ShareLimitEntity entity = (ShareLimitEntity) data;
                ShareLimitEntity.ResultEntity result = entity.getResult();
                mIRedDetailView.getShareLimit(result);
                break;
            case Constants.LISTENER_TYPE_GET_USER_GRAB_BY_TOKEN:
                grabData((GrabEntity) data);
                break;
        }
    }


}
