package com.chunsun.redenvelope.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
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

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mGson = new Gson();
        mHeaders = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
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
