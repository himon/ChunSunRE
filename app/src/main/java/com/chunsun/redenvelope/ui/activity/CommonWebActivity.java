package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 普通WebView显示Activity
 */
public class CommonWebActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.lay_common_webview_load_error)
    LinearLayout mLayLoadError;
    @Bind(R.id.btn_common_webview_load_error)
    Button mButtonLoadError;

    private boolean mIsSuccessLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        showCircleLoading();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                mIsSuccessLoad = false;
                mWebView.setVisibility(View.GONE);
                mLayLoadError.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideCircleLoading();
                if (mIsSuccessLoad) {
                    mWebView.setVisibility(View.VISIBLE);
                } else {
                    mWebView.setVisibility(View.GONE);
                }
            }
        });

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mButtonLoadError.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL);
            String title = intent.getStringExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE);
            initTitleBar(title, "", "", Constants.TITLE_TYPE_SAMPLE_WEB);
            mWebView.loadUrl(url);
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_common_webview_load_error:
                mIsSuccessLoad = true;
                mLayLoadError.setVisibility(View.GONE);
                initData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            back();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.loadData("<a></a>", "text/html", "utf-8");
    }
}
