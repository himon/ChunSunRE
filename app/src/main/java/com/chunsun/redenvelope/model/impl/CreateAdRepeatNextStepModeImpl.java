package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdRepeatNextStepMode;
import com.chunsun.redenvelope.model.entity.json.RepeatMealEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdRepeatNextStepActivity;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

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

    public CreateAdRepeatNextStepModeImpl(CreateAdRepeatNextStepActivity activity) {
        mActivity = activity;
    }

    @Override
    public void getRepeatMeal(final BaseMultiLoadedListener listener) {
        GsonRequest<RepeatMealEntity> request = new GsonRequest<RepeatMealEntity>(Request.Method.POST, StringUtil.preUrl(Constants.WEB_SERVICE_URL),
                RepeatMealEntity.class, null, new Response.Listener<RepeatMealEntity>() {

            @Override
            public void onResponse(RepeatMealEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_GET_REPEATE_MEAL, response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onException(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("methodName", Constants.CEATE_AD_GET_REPEAT_MEAL_JSON_REQUEST_URL);
                params.put("parames", "");
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
