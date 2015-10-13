package com.chunsun.redenvelope.model.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdContentMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.json.CreateAdResultEntity;
import com.chunsun.redenvelope.net.GsonRequest;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdContentModeImpl implements CreateAdContentMode {

    private CreateAdContentActivity mActivity;

    public CreateAdContentModeImpl(CreateAdContentActivity createAdNextPageActivity) {
        this.mActivity = createAdNextPageActivity;
    }

    @Override
    public void commit(final String token, final AdEntity adEntity, final String title, final String content, final BaseMultiLoadedListener listener) {

        GsonRequest<CreateAdResultEntity> request = new GsonRequest<CreateAdResultEntity>(Request.Method.POST, StringUtil.preUrl(Constants.CREATE_AD_JSON_REQUEST_URL),
                CreateAdResultEntity.class, null, new Response.Listener<CreateAdResultEntity>() {

            @Override
            public void onResponse(CreateAdResultEntity response) {
                if (response.isSuccess()) {
                    listener.onSuccess(Constants.LISTENER_TYPE_COMMIT_AD, response);
                } else {
                    listener.onError(response.getMsg());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                byte[] data = error.networkResponse.data;
                String msg = new String(data);
                listener.onError(msg);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                params.put("type", adEntity.getType().getKey());
                params.put("title", title);
                params.put("content", content);
                params.put("cover_img_byte_str", adEntity.getCoverImagePath());
                params.put("img_byte_str1", adEntity.getImagePath());
                params.put("img_byte_str2", adEntity.getImagePath2());
                params.put("img_byte_str3", adEntity.getImagePath3());
                params.put("img_byte_str4", adEntity.getImagePath4());
                params.put("img_byte_str5", adEntity.getImagePath5());
                params.put("img_byte_str6", adEntity.getImagePath6());
                params.put("img_byte_str7", adEntity.getImagePath7());
                params.put("img_byte_str8", adEntity.getImagePath8());
                params.put("province", adEntity.getProvince().getP());
                params.put("city", adEntity.getCity().getC());
                params.put("range", adEntity.getDistance().getKey());
                params.put("longitude", "");
                params.put("latitude", "");
                params.put("price", adEntity.getPrice());
                params.put("everyday_count", adEntity.getNum());
                params.put("day_count", adEntity.getDays());
                params.put("delay_seconds_rate_id", adEntity.getDelaySeconds().getId() + "");
                params.put("time", adEntity.getStartTime());
                params.put("is_need_receipt", adEntity.getIsReceipt());
                params.put("receipt_title", "");
                params.put("bank_name", "");
                params.put("bank_no", "");
                params.put("tax_no", "");
                params.put("forwarding_packages_id", adEntity.getMeal().getId() + "");
                params.put("formula_multiple", adEntity.getFormula_multiple());
                return params;
            }
        };
        RequestManager.addRequest(request, mActivity);
    }
}
