package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.CreateCircleResultMode;
import com.chunsun.redenvelope.model.impl.CreateCircleResultModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateCircleResultActivity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/24 15:49
 * @des
 */
public class CreateCircleResultPresenter implements BaseSingleLoadedListener<BaseEntity>{

    private CreateCircleResultMode mCreateCircleResultMode;
    private CreateCircleResultActivity mActivity;

    public CreateCircleResultPresenter(CreateCircleResultActivity activity) {
        this.mActivity = activity;
        this.mCreateCircleResultMode = new CreateCircleResultModeImpl(activity);
    }

    public void userOperateCircle(String token, String province,
                                  String city, String longitude, String latitude, int operate_type,
                                  String hb_id) {
        this.mCreateCircleResultMode.userOperateCircle(token, province, city, longitude, latitude, operate_type, hb_id, this);
    }

    @Override
    public void onSuccess(BaseEntity data) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onException(String msg) {

    }
}
