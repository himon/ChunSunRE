package com.chunsun.redenvelope.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.preference.Preferences;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/29.
 * 自定义GsonRequest
 */
public class GsonRequest<T> extends Request<T> {

    private Class<T> mClass;
    private Gson mGson;
    private Listener<T> mListener;
    private Map<String, String> mHeaders;
    private int mType = 0;
    private Preferences mPreferences;

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mGson = new Gson();
        mHeaders = headers;
    }

    public GsonRequest(int method, String url, Class<T> clazz, int type, Map<String, String> headers, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mGson = new Gson();
        mType = type;
        mHeaders = headers;
        mPreferences = new Preferences(MainApplication.getContext());
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            switch (mType) {
                case -1:
                    mPreferences.setRedDetailListData(json);
                    break;
                case 1://圈子的滚动广告
                    mPreferences.setCircleListAdsData(json);
                    break;
                case 2://任务的滚动广告
                    mPreferences.setTaskAdsData(json);
                    break;
                case 3://红包的滚动广告
                    mPreferences.setRedDetailListAdsData(json);
                    break;
                case 5://任务列表
                    mPreferences.setTaskData(json);
                    break;
                case 7://圈子列表
                    mPreferences.setCircleListData(json);
                    break;
                case 8://全国评论
                    mPreferences.setInteractivePlatformCountryData(json);
                    break;
                case 9://本地评论
                    mPreferences.setInteractivePlatformLocalData(json);
                    break;
            }
            return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
