package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.ShareEntitiy;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.event.WebRedDetailEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.WebRedDetailPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWebRedDetailView;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 链接型红包详情Activity
 */
public class WebRedDetailActivity extends BaseActivity implements IWebRedDetailView, View.OnClickListener {

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.lay_common_webview_load_error)
    LinearLayout mLLError;
    @Bind(R.id.btn_common_webview_load_error)
    Button mBtnError;
    @Bind(R.id.rl_get_red)
    RelativeLayout mRlGetRed;
    @Bind(R.id.iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_yuan)
    TextView mTvYuan;
    @Bind(R.id.main)
    LinearLayout mLLMain;

    private boolean mIsSuccessLoad = true;
    private String mRedDetailId;
    private RedDetailEntity.ResultEntity.DetailEntity mRed;
    //红包倒计时秒数
    private int mDelaySeconds;

    private WebRedDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_red_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new WebRedDetailPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);

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
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
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
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mRedDetailId = intent.getStringExtra(Constants.EXTRA_KEY);
        }
        mDialog.show();
        mPresenter.getData(new Preferences(this).getToken(), mRedDetailId);
    }

    @Override
    public void loadUrl(RedDetailEntity.ResultEntity.DetailEntity entity) {
        mRed = entity;
        mWebView.loadUrl(entity.getContent());
        countDown();
    }

    @Override
    public void setData(RedDetailEntity.ResultEntity.DetailEntity entity) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_common_webview_load_error:
                mIsSuccessLoad = true;
                mLLError.setVisibility(View.GONE);
                mDialog.show();
                mPresenter.getData(new Preferences(this).getToken(), mRedDetailId);
                break;
            case R.id.rl_get_red:
                ShareEntitiy entity = new ShareEntitiy();
                entity.setContent("我正在看【" + mRed.getContent() + "】分享你一起来看");
                break;
        }
    }

    private void countDown() {

        if (mRed.is_open()) {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("您已经领取红包" + mRed.getPrice() + "元");
            mRlGetRed.setBackgroundColor(getResources().getColor(
                    R.color.global_red_tran));
        } else if (!mRed.isHb_status()) {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("来晚了，红包已抢空");
            mTvContent
                    .setTextColor(getResources().getColor(R.color.font_web_red_detail));
            mRlGetRed.setBackgroundColor(getResources().getColor(
                    R.color.bg_web_red_detail));
        } else {
            mPresenter.countDown(mRed.getPre_load_seconds());
        }
    }

    private void refreshDelaySeconds() {
        if (mDelaySeconds > 0) {
            mTvContent.setText(mDelaySeconds + "秒后可领取");
            mDelaySeconds--;
            EventBus.getDefault().post(new WebRedDetailEvent("louis"));
        } else {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("点击领取");
            mTvPrice.setText(mRed.getPrice());
            mTvPrice.setVisibility(View.VISIBLE);
            mTvYuan.setVisibility(View.VISIBLE);
            mRlGetRed.setOnClickListener(this);
        }
    }

    public void onEventMainThread(WebRedDetailEvent event) {
        if("".equals(event.getMsg())) {
            mDelaySeconds = mRed.getDelay_seconds();
            refreshDelaySeconds();
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshDelaySeconds();
                }
            }, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
