package com.chunsun.redenvelope.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.WelcomeEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.WelcomePresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.adapter.WelcomeAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWelcomeView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.XgManager;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * 启动页+引导页
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView, ViewPager.OnPageChangeListener {

    ViewPager mViewPager;
    LinearLayout mLLPoints;

    private ArrayList<View> mViews = null;
    private ArrayList<View> mPoints = null;
    private int[] imgs = {R.drawable.img_welcom_page1, R.drawable.img_welcom_page2, R.drawable.img_welcom_page3, R.drawable.img_welcom_page4, R.drawable.img_welcom_page5};
    private WelcomePresenter mPresenter;
    private WelcomeAdapter mAdapter;

    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        XgManager.initXinGe(getApplicationContext(), true);
        mPresenter = new WelcomePresenter(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_welcome);
        mLLPoints = (LinearLayout) findViewById(R.id.view_indicator);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    protected void initData() {
        String open = new Preferences(MainApplication.getContext()).getFirstOpen();
        mPresenter.isShowPager(open);
    }

    private static int XG_TOKEN_GET_TIMES = 5;

    private void buildXGToken() {
        XG_TOKEN_GET_TIMES--;

        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(this, false);

        Context context = getApplicationContext();
        if (new Preferences(this).getXGToken().equals("")) {
            XGPushManager.registerPush(context, new XGIOperateCallback() {

                @Override
                public void onSuccess(Object arg0, int arg1) {
                    String token = XGPushConfig
                            .getToken(getApplicationContext());
                    new Preferences(WelcomeActivity.this).setXGToken(token);

                    if (TextUtils.isEmpty(token)
                            && XG_TOKEN_GET_TIMES > 0) {
                        buildXGToken();
                    }

                    ShowToast.Short("XG token：" + token);
                }

                @Override
                public void onFail(Object arg0, int arg1, String arg2) {
                    if (XG_TOKEN_GET_TIMES > 0) {
                        buildXGToken();
                    }
                }
            });
        }
    }

    @Override
    public void initPager() {
        mViews = new ArrayList<>();
        mPoints = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.layout_lead_page_item, null);
            ImageView page = (ImageView) contentView.findViewById(R.id.iv_page);
            ImageView enter = (ImageView) contentView.findViewById(R.id.iv_look);
            ImageView login = (ImageView) contentView.findViewById(R.id.iv_login);
            page.setImageResource(imgs[i]);

            if (i == imgs.length - 1) {
                enter.setVisibility(View.VISIBLE);
                enter.setOnClickListener(this);
                login.setVisibility(View.VISIBLE);
                login.setOnClickListener(this);
            }
            mViews.add(contentView);
        }

        initIndicator();

        mAdapter = new WelcomeAdapter(mViews);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setAdapter(mAdapter);
        mLLPoints.setVisibility(View.VISIBLE);

        new Preferences(MainApplication.getContext()).setFirstOpen("2");
    }

    private void initIndicator() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        int w = (int) (10 * dm.density);
        int h = (int) (10 * dm.density);

        for (int i = 0; i < imgs.length; i++) {
            View view = new View(this);
            if (i == 0) {
                view.setBackgroundResource(R.drawable.shape_dot_focused_white);
            } else {
                view.setBackgroundResource(R.drawable.shape_dot_normal);
            }

            // Set the size and margin of the dot.
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w, h);
            lp.setMargins((int) (2 * dm.density), 0, (int) (2 * dm.density), 0);
            view.setLayoutParams(lp);
            mLLPoints.addView(view);
            mPoints.add(view);
        }
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.iv_look:
                toMainActivity();
                break;
            case R.id.iv_login:
                toLoginActivity();
                break;
        }
    }

    private void toLoginActivity() {
        Intent intent = new Intent(WelcomeActivity.this,
                LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_WELCOME);
        startActivity(intent);
        finish();
    }

    public void onEventMainThread(WelcomeEvent event) {
        if ("1".equals(event.getMsg())) {
            toMainActivity();
        } else {
            initPager();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position + 1;
        for (int i = 0; i < mPoints.size(); i++) {
            View view = mPoints.get(i);
            if (i == position) {
                view.setBackgroundResource(R.drawable.shape_dot_focused_white);
            } else {
                view.setBackgroundResource(R.drawable.shape_dot_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
