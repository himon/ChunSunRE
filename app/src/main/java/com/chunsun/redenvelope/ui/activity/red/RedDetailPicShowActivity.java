package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.RedDetailShowPicBackEvent;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.RedDetailPicShowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 红包详情中轮播图片的放大显示
 */
public class RedDetailPicShowActivity extends BaseActivity {

    @Bind(R.id.toolsbar)
    RelativeLayout mToolsBar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;
    private ArrayList<String> mUrls;
    private List<Fragment> mFragments;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_detail_pic_show);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mToolsBar.setVisibility(View.GONE);

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
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mUrls = intent.getStringArrayListExtra(Constants.EXTRA_LIST_KEY);
            mIndex = intent.getIntExtra(Constants.EXTRA_KEY, 0);
        }
        initFragment();
    }

    private void initFragment() {
        for (int i = 0; i < mUrls.size(); i++) {
            String url = mUrls.get(i);
            RedDetailPicShowFragment fragment = new RedDetailPicShowFragment();
            Bundle data = new Bundle();
            data.putString(Constants.EXTRA_KEY, url);
            fragment.setArguments(data);
            mFragments.add(fragment);
        }
        mViewPager.setAdapter(mAdapter);
        // 设置预加载数
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.setCurrentItem(mIndex);
    }

    public void onEvent(RedDetailShowPicBackEvent event) {
        back();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
