package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdRepeatNextStepMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdRepeatNextStepActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/8 12:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CreateAdRepeatNextStepModeImpl implements CreateAdRepeatNextStepMode {

    private CreateAdRepeatNextStepActivity mActivity;
    private HttpManager mManager;

    public CreateAdRepeatNextStepModeImpl(CreateAdRepeatNextStepActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getRepeatMeal(final BaseMultiLoadedListener listener) {
//        GsonRequest<RepeatMealEntity> request = new GsonRequest<RepeatMealEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
//                RepeatMealEntity.class, null, new Response.Listener<RepeatMealEntity>() {
//
//            @Override
//            public void onResponse(RepeatMealEntity response) {
//                if (response.isSuccess()) {
//                    listener.onSuccess(Constants.LISTENER_TYPE_GET_REPEATE_MEAL, response);
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
//                params.put("methodName", Constants.CEATE_AD_GET_REPEAT_MEAL_JSON_REQUEST_URL);
//                params.put("parames", "");
//                return params;
//            }
//        };
//        RequestManager.addRequest(request, mActivity);
        mManager.getRepeatMeal(listener, mActivity);
    }
}
