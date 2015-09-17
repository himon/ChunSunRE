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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.TitlePopupItemEntity;
import com.chunsun.redenvelope.model.event.BaiduMapLocationEvent;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.scanlibrary.CaptureActivity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.tab.AdFragment;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.ui.fragment.tab.MeFragment;
import com.chunsun.redenvelope.ui.fragment.tab.NearFragment;
import com.chunsun.redenvelope.ui.view.IMainView;
import com.chunsun.redenvelope.widget.ChangeColorIconWithText;
import com.chunsun.redenvelope.widget.popupwindow.TitlePopup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.toolsbar)
    RelativeLayout mToolsBar;
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
    @Bind(R.id.iv_toInteractive)
    ImageView mIvInteractive;


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

    //定义标题栏弹窗按钮
    private TitlePopup mTitlePopup;

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

        initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_HOME);

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

        //实例化标题栏弹窗
        mTitlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(TitlePopupItemEntity item, int position) {
                switch (position) {
                    case 0:
                        toInteract();
                        break;
                    case 1:
                        toScan();
                        break;
                }
            }
        });
        initEvent();
    }


    private void initEvent() {

        mNavRight.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRightIcon.setOnClickListener(this);

        mHome.setOnClickListener(this);
        mAd.setOnClickListener(this);
        mNear.setOnClickListener(this);
        mMe.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);
        mIvInteractive.setOnClickListener(this);
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

        //给标题栏弹窗添加子类
        mTitlePopup.addAction(new TitlePopupItemEntity(this, "互动平台", R.drawable.img_dialog_platform));
        mTitlePopup.addAction(new TitlePopupItemEntity(this, "扫一扫", R.drawable.img_dialog_scan));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_right:
                toAdExplain();
                break;
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                MainApplication.getContext().getLocationClient().start();
                break;
            case R.id.ib_nav_right:
                mTitlePopup.show(mToolsBar);
                break;
            default:
                clickTab(v);
        }
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
                if (isLogin(Constants.FROM_AD)) {
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
                if (isLogin(Constants.FROM_ME)) {
                    mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
                    mViewPager.setCurrentItem(3, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.iv_toInteractive:
                toInteract();
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
                initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_HOME);
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
    public boolean isLogin(String from) {
        if (TextUtils.isEmpty(new Preferences(MainApplication.getContext()).getToken())) {
            toLogin(from);
            return false;
        }
        return true;
    }

    public void toLogin(String from) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, from);
        startActivity(intent);
    }

    @Override
    public void toAdExplain() {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "说明");
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.SEND_RED_INSTRUCTION_URL);
        startActivity(intent);
    }

    @Override
    public void toInteract() {
        if (isLogin(Constants.FROM_LOGIN_BACK)) {
            Intent intent = new Intent(this, InteractivePlatformActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void toScan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }


    public void onEvent(MainEvent event) {
        if (Constants.FROM_LOGIN_BACK.equals(event.getMsg())) {
            mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
        } else if (Constants.FROM_AD.equals(event.getMsg())) {
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(1, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        } else if (Constants.FROM_ME.equals(event.getMsg())) {
            mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
            mViewPager.setCurrentItem(3, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        } else if (Constants.USER_INFO_PASS_FROM_ME.equals(event.getMsg())) {
            toLogin(Constants.FROM_ME);
        }
    }

    public void onEvent(BaiduMapLocationEvent event) {
        mNavLeft.setText(MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity());
    }


    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
