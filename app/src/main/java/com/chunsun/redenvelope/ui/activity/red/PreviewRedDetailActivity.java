package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.event.RedDetailBackEvent;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.RedDetailPicPreviewFragment;
import com.chunsun.redenvelope.ui.fragment.preview.PreviewRedDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.iwf.photopicker.entity.Photo;

public class PreviewRedDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.main_nav)
    RelativeLayout mMainNav;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    //生活类红包Fragment
    private PreviewRedDetailFragment mPreviewRedDetailFragment;
    //红包数据
    private AdEntity mAdEntity;
    //图片Url
    private ArrayList<Photo> mPhotos = new ArrayList<>();
    //红包类型
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_red_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mMainNav.setVisibility(View.GONE);
        initTitleBar("预览", "", "", Constants.TITLE_TYPE_SAMPLE);

        mFragments = new ArrayList<>();

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

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mFragments.size() - 1) {
                    mMainNav.setVisibility(View.VISIBLE);
                    mPreviewRedDetailFragment.startAutoScroll();
                } else {
                    mMainNav.setVisibility(View.GONE);
                    mPreviewRedDetailFragment.stopAutoScroll();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            mPhotos = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY2);
            try {
                mType = Integer.parseInt(mAdEntity.getType().getKey());
            } catch (Exception e) {
                mType = 1;
            }
        }
        setData();
    }

    public void setData() {

        ArrayList<String> urls = initUrls();

        if (urls.size() == 0) {
            mMainNav.setVisibility(View.VISIBLE);
        }

        for (String str : urls) {
            RedDetailPicPreviewFragment fragment = new RedDetailPicPreviewFragment(str);
            mFragments.add(fragment);
        }

        mPreviewRedDetailFragment = new PreviewRedDetailFragment();
        Bundle data = new Bundle();
        data.putParcelable(Constants.EXTRA_KEY, mAdEntity);
        data.putStringArrayList(Constants.EXTRA_KEY2, urls);
        mPreviewRedDetailFragment.setArguments(data);
        mFragments.add(mPreviewRedDetailFragment);

        mViewPager.setAdapter(mAdapter);
        //设置预加载数
        mViewPager.setOffscreenPageLimit(urls.size() + 1);
    }

    private ArrayList<String> initUrls() {

        ArrayList<String> urls = new ArrayList<>();

        if (TextUtils.isEmpty(mAdEntity.getCoverImagePath())) {
            return urls;
        } else {
            urls.add("file://" + mAdEntity.getCoverImagePath());
        }

        for (int i = 0; i < mPhotos.size(); i++) {
            if (TextUtils.isEmpty(mPhotos.get(i).getPath())) {
                return urls;
            }
            urls.add("file://" + mPhotos.get(i).getPath());
        }

        return urls;
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

    public void onEvent(RedDetailBackEvent event) {
        back();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
