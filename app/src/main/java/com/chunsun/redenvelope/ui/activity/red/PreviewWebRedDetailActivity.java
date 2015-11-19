package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.event.WebRedDetailEvent;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.WebRedDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 链接类预览Activity
 */
public class PreviewWebRedDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private AdEntity mAdEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_web_red_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);

        mFragments = new ArrayList<Fragment>();

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mAdEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
            initFragment();
            super.showLoading();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
        }
    }

    /**
     * 初始化有多少fragment
     */
    private void initFragment() {

        String[] urls = mAdEntity.getContent().split(",");

        boolean flag = false;
        for (int i = 0; i < urls.length; i++) {
            String url = urls[i];
            if (i == 0) {
                flag = true;
            }
            WebRedDetailFragment fragment = new WebRedDetailFragment();
            Bundle data = new Bundle();
            data.putString(Constants.EXTRA_KEY, url);
            data.putBoolean(Constants.EXTRA_KEY2, flag);
            fragment.setArguments(data);
            mFragments.add(fragment);
            flag = false;
        }
        mViewPager.setAdapter(mAdapter);
        // 设置预加载数
        mViewPager.setOffscreenPageLimit(mFragments.size());
    }

    public void onEventMainThread(WebRedDetailEvent event) {
        if ("hideLoading".equals(event.getMsg())) {//第一个page加载完成后hide loading
            super.hideLoading();
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
