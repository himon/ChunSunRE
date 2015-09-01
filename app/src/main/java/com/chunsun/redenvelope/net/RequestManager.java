package com.chunsun.redenvelope.net;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chunsun.redenvelope.app.MainApplication;

/**
 * Created by Administrator on 2015/7/23.
 */
public class RequestManager {

    public static RequestQueue mRequestQueue = Volley.newRequestQueue(MainApplication.getContext());

    private RequestManager() {
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);

//		Logger.d("addRequest = " + request.getUrl());

    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
