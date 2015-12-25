package com.chunsun.redenvelope.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.WebRedDetailEvent;
import com.chunsun.redenvelope.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebRedDetailFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.lay_common_webview_load_error)
    LinearLayout mLLError;
    @Bind(R.id.btn_common_webview_load_error)
    Button mBtnError;

    private boolean mIsSuccessLoad = true;
    private String mUrl;
    private boolean mFlag;

    public WebRedDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_red_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                mIsSuccessLoad = false;
                mWebView.setVisibility(View.GONE);
                mLLError.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mFlag) {
                    EventBus.getDefault().post(new WebRedDetailEvent("hideLoading"));
                }
                if (mIsSuccessLoad) {
                    mWebView.setVisibility(View.VISIBLE);
                } else {
                    mWebView.setVisibility(View.GONE);
                }
            }
        });
        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        initEvent();
    }

    private void initEvent() {
        mBtnError.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Bundle data = getArguments();
        mUrl = data.getString(Constants.EXTRA_KEY);
        mFlag = data.getBoolean(Constants.EXTRA_KEY2);
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_common_webview_load_error:
                mWebView.reload();
                mWebView.setVisibility(View.VISIBLE);
                mLLError.setVisibility(View.GONE);
                break;
        }
    }
}
