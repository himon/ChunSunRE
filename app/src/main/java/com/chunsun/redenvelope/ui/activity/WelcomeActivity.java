package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.model.event.WelcomeEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.WelcomePresenter;
import com.chunsun.redenvelope.ui.adapter.WelcomeAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IWelcomeView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * 启动页+引导页
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView, View.OnClickListener {

    ViewPager mViewPager;

    private ArrayList<View> mViews = null;
    private int[] imgs = {R.drawable.img_welcom_page1, R.drawable.img_welcom_page2, R.drawable.img_welcom_page3};
    private WelcomePresenter mPresenter;
    private WelcomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        mPresenter = new WelcomePresenter(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_welcome);
    }

    @Override
    protected void initData() {
        String open = new Preferences(MainApplication.getContext()).getFirstOpen();
        mPresenter.isShowPager(open);
    }

    @Override
    public void initPager() {
        mViews = new ArrayList<View>();

        for (int i = 0; i < imgs.length; i++) {
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.layout_lead_page_item, null);
            ImageView page = (ImageView) contentView.findViewById(R.id.iv_page);
            ImageView entry = (ImageView) contentView.findViewById(R.id.iv_entry);
            page.setImageResource(imgs[i]);

            if (i == imgs.length - 1) {
                entry.setVisibility(View.VISIBLE);
                entry.setOnClickListener(this);
            }

            mViews.add(contentView);
        }

        mAdapter = new WelcomeAdapter(mViews);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setAdapter(mAdapter);

        new Preferences(MainApplication.getContext()).setFirstOpen("2");
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_entry:
                toMainActivity();
                break;
        }
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
}
