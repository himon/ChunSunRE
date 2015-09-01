package com.chunsun.redenvelope.presenter;

import com.android.volley.VolleyError;
import com.chunsun.redenvelope.model.entity.json.UserEntity;

/**
 * Created by Administrator on 2015/7/31.
 */
public interface OnGetUserInfoListener {

    void onGetUserInfoSuccess(UserEntity entity);

    void onGetUserInfoError(VolleyError error);

}
