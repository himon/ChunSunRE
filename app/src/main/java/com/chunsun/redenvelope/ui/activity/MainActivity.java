package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.AdFragment;
import com.chunsun.redenvelope.ui.fragment.HomeFragment;
import com.chunsun.redenvelope.ui.fragment.MeFragment;
import com.chunsun.redenvelope.ui.fragment.NearFragment;
import com.chunsun.redenvelope.ui.view.IMainView;
import com.chunsun.redenvelope.widget.ChangeColorIconWithText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.indicator_home)
    ChangeColorIconWithText mHome;
    @Bind(R.id.indicator_ad)
    ChangeColorIconWithText mAd;
    @Bind(R.id.indicator_near)
    ChangeColorIconWithText mNear;
    @Bind(R.id.indicator_me)
    ChangeColorIconWithText mMe;
    @Bind(R.id.vp_viewpager)
    ViewPager mViewPager;


    private ArrayList<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();
    private ArrayList<Bitmap> bitmaps;
    private ArrayList<Fragment> mTabs = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    private HomeFragment mHomeFragment;
    private AdFragment mAdFragment;
    private MeFragment mMeFragment;
    private NearFragment mNearFragment;
    //选中Tab页图标的颜色
    private int mSelectedColor;
    private int mUnSelectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        initTitleBar("春笋红包", "不限", "", Constants.TITLE_TYPE_HOME);

        mTabIndicators.add(mHome);
        mTabIndicators.add(mAd);
        mTabIndicators.add(mNear);
        mTabIndicators.add(mMe);

        mHomeFragment = new HomeFragment();
        mAdFragment = new AdFragment();
        mNearFragment = new NearFragment();
        mMeFragment = new MeFragment();

        mTabs.add(mHomeFragment);
        mTabs.add(mAdFragment);
        mTabs.add(mNearFragment);
        mTabs.add(mMeFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);

        initEvent();
    }

    private void initEvent() {
        mHome.setOnClickListener(this);
        mAd.setOnClickListener(this);
        mNear.setOnClickListener(this);
        mMe.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    protected void initData() {

        mSelectedColor = getResources().getColor(R.color.global_red);
        mUnSelectedColor = getResources().getColor(R.color.font_gray);

        bitmaps = new ArrayList<Bitmap>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_home));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_ad));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_near));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_me));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_home_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_ad_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_near_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_me_selected));
    }


    @Override
    public void onClick(View v) {

        clickTab(v);
    }

    private void clickTab(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.indicator_home:
                mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
                mViewPager.setCurrentItem(0, false);
                showTitleBar(v.getId());
                break;
            case R.id.indicator_ad:
                if (isLogin()) {
                    mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
                    mViewPager.setCurrentItem(1, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.indicator_near:
                mTabIndicators.get(2).setmIcon(bitmaps.get(6), mSelectedColor);
                mViewPager.setCurrentItem(2, false);
                showTitleBar(v.getId());
                break;
            case R.id.indicator_me:
                if (isLogin()) {
                    mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
                    mViewPager.setCurrentItem(3, false);
                    showTitleBar(v.getId());
                }
                break;
        }
    }

    /**
     * 显示title bar样式
     *
     * @param type
     */
    private void showTitleBar(int type) {
        switch (type) {
            case R.id.indicator_home:
            case R.id.indicator_near:
                initTitleBar("春笋红包", "不限", "", Constants.TITLE_TYPE_HOME);
                break;
            case R.id.indicator_ad:
                initTitleBar("发广告", "", "说明", Constants.TITLE_TYPE_AD);
                break;
            case R.id.indicator_me:
                initTitleBar("个人中心", "", "", Constants.TITLE_TYPE_NONE);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {

        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setmIcon(bitmaps.get(i), mUnSelectedColor);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        resetOtherTabs();
        mTabIndicators.get(position).setmIcon(bitmaps.get(position + 4), mSelectedColor);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean isLogin() {
        if (TextUtils.isEmpty(new Preferences(MainApplication.getContext()).getToken())) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_AD);
            startActivity(intent);
            return false;
        }
        return true;
    }

    public void onEvent(MainEvent event) {
        if (Constants.FROM_LOGIN_BACK.equals(event.getMsg())) {
            mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
        } else if (Constants.FROM_AD.equals(event.getMsg())) {
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        } else if (Constants.FROM_ME.equals(event.getMsg())) {
            mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
