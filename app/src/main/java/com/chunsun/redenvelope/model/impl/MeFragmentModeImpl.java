package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentModeImpl implements MeFragmentMode {

    private NewMeFragment meFragment;
    private HttpManager mManager;

    public MeFragmentModeImpl(NewMeFragment meFragment) {
        this.meFragment = meFragment;
        this.mManager = new HttpManager();
    }

    @Override
    public void getUserInfomation(final String token, final BaseMultiLoadedListener listener) {
//        GsonRequest<UserEntity> request = new GsonRequest<UserEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                UserEntity.class, null, new Response.Listener<UserEntity>() {
//
//            @Override
//            public void onResponse(UserEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(response);
//                } else {
//                    listener.onError(response.getMsg());
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onException(error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("methodName", Constants.GET_USERINFO_JSON_REQUEST_URL);
//                params.put("parames", JsonManager.initDataTokenToJson(token));
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, meFragment);
        mManager.getUserInfomation(token, listener, meFragment, null);
    }
}
