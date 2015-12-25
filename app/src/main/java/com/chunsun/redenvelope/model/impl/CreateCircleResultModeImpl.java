package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.CreateCircleResultMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateCircleResultActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/24 15:52
 * @des
 */
public class CreateCircleResultModeImpl implements CreateCircleResultMode{

    private HttpManager mManager;
    private CreateCircleResultActivity mActivity;

    public CreateCircleResultModeImpl(CreateCircleResultActivity activity) {
        this.mActivity = activity;
        this.mManager = new HttpManager();
    }

    @Override
    public void userOperateCircle(String token, String province, String city, String longitude, String latitude, int operate_type, String hb_id, BaseSingleLoadedListener listener) {
        mManager.userOperateCircle(token, province, city, longitude, latitude, operate_type, hb_id, listener, mActivity);
    }
}
