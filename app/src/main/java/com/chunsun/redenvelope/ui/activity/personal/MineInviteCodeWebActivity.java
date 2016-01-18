package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.InviteRecordEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.UserEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.ShareRedEnvelopeEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.MineInviteCodePresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IMineInviteCodeView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.widget.popupwindow.ShareRedEnvelopePopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MineInviteCodeWebActivity extends BaseActivity implements IMineInviteCodeView {

    @Bind(R.id.main)
    LinearLayout mMain;
    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.view_bg)
    View mViewBg;
    private String mToken;
    private MineInviteCodePresenter mPresenter;
    private InviteRecordEntity.ResultEntity mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_invite_code_web);
        ButterKnife.bind(this);
        mPresenter = new MineInviteCodePresenter(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("我的邀请码", "", "", Constants.TITLE_TYPE_SAMPLE_WEB);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                ShowToast.Short("页面打开失败，请稍候再试");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String type = url.substring(url.length() - 1, url.length());
                if ("1".equals(type)) {
                    mPresenter.getUserInfo(mToken);
                } else {
                    toInviteRecordList();
                }
                return true;//true阻止页面跳转
            }
        });

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        mPresenter.getInviteRecord(mToken);
    }

    @Override
    protected void click(View v) {

    }

    @Override
    public void setData(InviteRecordEntity.ResultEntity result) {
        mResult = result;
        mWebView.loadUrl(MainApplication.getContext().getUserEntity().getShare_host() + "pages/myInvitationCode/index.html?token=" + mToken);
    }

    @Override
    public void toInviteRecordList() {
        Intent intent = new Intent(this, InviteRecordListActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mResult);
        startActivity(intent);
    }

    @Override
    public void getShareUrlSuccess(UserEntity entity) {
        UserInfoEntity result = entity.getResult();

        mViewBg.setVisibility(View.VISIBLE);
        RedDetailEntity.ResultEntity.DetailEntity detail = new RedDetailEntity.ResultEntity.DetailEntity();
        detail.setTitle("我已经领取了红包，你也来试试吧");
        detail.setContent("注册填写邀请码" + result.getInvitation_code() + "，送现金，代理加盟电话：037980859669");
        if (!TextUtils.isEmpty(result.getImg_url())) {
            detail.setCover_img_url(result.getImg_url());
        }
        detail.setShare_host(result.getShare_host());
        detail.setHb_type("-1");//设置红包类型为-1，表示是分享邀请码
        ShareRedEnvelopePopupWindow shareRedEnvelopePopupWindow = new ShareRedEnvelopePopupWindow(this, detail);
        shareRedEnvelopePopupWindow.showAtLocation(mMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public void onEvent(ShareRedEnvelopeEvent event) {
        mViewBg.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
