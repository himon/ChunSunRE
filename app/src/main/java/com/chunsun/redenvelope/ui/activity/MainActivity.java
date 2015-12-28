package com.chunsun.redenvelope.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.TitlePopupItemEntity;
import com.chunsun.redenvelope.event.BaiduMapLocationEvent;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.scanlibrary.CaptureActivity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.ad.CreateCircleActivity;
import com.chunsun.redenvelope.ui.activity.red.SearchCircleActivity;
import com.chunsun.redenvelope.ui.activity.red.TaskListActivity;
import com.chunsun.redenvelope.ui.activity.scan.ScanChunsunCodeResultActivity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.tab.AdFragment;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.ui.fragment.tab.InteractiveFragment;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.ui.view.IMainView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.widget.ChangeColorIconWithText;
import com.chunsun.redenvelope.widget.popupwindow.TitlePopup;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.root)
    RelativeLayout mRoot;
    @Bind(R.id.toolsbar)
    RelativeLayout mToolsBar;
    @Bind(R.id.indicator_home)
    ChangeColorIconWithText mHome;
    @Bind(R.id.indicator_ad)
    ChangeColorIconWithText mAd;
    @Bind(R.id.indicator_near)
    ChangeColorIconWithText mForward;
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
    private HomeFragment mCircleFragment;
    private InteractiveFragment mInteractiveFragment;
    private NewMeFragment mMeFragment;

    //选中Tab页图标的颜色
    private int mSelectedColor;
    private int mUnSelectedColor;

    //定义标题栏弹窗按钮
    private TitlePopup mTitlePopup;
    //圈子排序弹窗按钮
    private TitlePopup mOrderPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
        initXinGe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult click = XGPushManager.onActivityStarted(this);
        if (click != null) { // 判断是否来自信鸽的打开方式
            Toast.makeText(this, "通知被点击:" + click.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
    }

    /**
     * 初始化信鸽推送
     */
    private void initXinGe() {
        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(this, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        Context context = getApplicationContext();
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int i) {
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });

        // 2.36（不包括）之前的版本需要调用以下2行代码
        Intent service = new Intent(context, XGPushService.class);
        context.startService(service);
    }

    @Override
    protected void initView() {

        initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_CIRCLE);

        mTabIndicators.add(mHome);
        mTabIndicators.add(mAd);
        mTabIndicators.add(mForward);
        mTabIndicators.add(mMe);

        mHomeFragment = new HomeFragment();
        mAdFragment = new AdFragment();
        mCircleFragment = new HomeFragment();
        mInteractiveFragment = new InteractiveFragment();
        mMeFragment = new NewMeFragment();

        Bundle homeBundle = new Bundle();
        homeBundle.putString(Constants.EXTRA_KEY, Constants.RED_AD_TYPE);
        mHomeFragment.setArguments(homeBundle);

        Bundle circleBundle = new Bundle();
        circleBundle.putString(Constants.EXTRA_KEY, Constants.SCROLL_AD_TYPE);
        mCircleFragment.setArguments(circleBundle);

        Bundle forwardBundle = new Bundle();
        forwardBundle.putString(Constants.EXTRA_KEY, Constants.TASK_AD_TYPE);
        mInteractiveFragment.setArguments(forwardBundle);

        mTabs.add(mHomeFragment);
        mTabs.add(mAdFragment);
        mTabs.add(mCircleFragment);
        mTabs.add(mInteractiveFragment);
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
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setCurrentItem(2);

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
                    case 2:
                        toTaskList();
                        break;
                }
            }
        });

        mOrderPopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mOrderPopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(TitlePopupItemEntity item, int position) {
                switch (position) {
                    case 0:
                        mCircleFragment.orderRefresh(0);
                        mTvOrder.setText("最新");
                        break;
                    case 1:
                        mCircleFragment.orderRefresh(1);
                        mTvOrder.setText("最热");
                        break;
                    case 2:
                        mCircleFragment.orderRefresh(2);
                        mTvOrder.setText("精华");
                        break;
                    case 3:
                        mCircleFragment.orderRefresh(3);
                        mTvOrder.setText("随机");
                        break;
                    case 4:
                        mCircleFragment.orderRefresh(4);
                        mTvOrder.setText("市区");
                        break;
                    case 5:
                        mCircleFragment.orderRefresh(5);
                        mTvOrder.setText("我的");
                        break;
                    case 6:
                        mCircleFragment.orderRefresh(6);
                        mTvOrder.setText("收藏");
                        break;
                    case 7:
                        mCircleFragment.orderRefresh(7);
                        mTvOrder.setText("附近");
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
        mRlCreate.setOnClickListener(this);
        mIvCreate.setOnClickListener(this);
        mRlSearch.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mLLOrder.setOnClickListener(this);

        mHome.setOnClickListener(this);
        mAd.setOnClickListener(this);
        mForward.setOnClickListener(this);
        mMe.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);
        mIvInteractive.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        mSelectedColor = getResources().getColor(R.color.global_red);
        mUnSelectedColor = getResources().getColor(R.color.font_gray);

        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_red));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_ad));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_interaction));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_me));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_red_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_ad_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_interaction_selected));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.img_icon_me_selected));

        //给标题栏弹窗添加子类
        mTitlePopup.addAction(new TitlePopupItemEntity(this, "互动平台", R.drawable.img_dialog_platform));
        mTitlePopup.addAction(new TitlePopupItemEntity(this, "扫一扫", R.drawable.img_dialog_scan));
        mTitlePopup.addAction(new TitlePopupItemEntity(this, "任务", R.drawable.img_dialog_scan));

        //给圈子排序弹窗添加子类
        mOrderPopup.addAction(new TitlePopupItemEntity("最新"));
        mOrderPopup.addAction(new TitlePopupItemEntity("最热"));
        mOrderPopup.addAction(new TitlePopupItemEntity("精华"));
        mOrderPopup.addAction(new TitlePopupItemEntity("随机"));
        mOrderPopup.addAction(new TitlePopupItemEntity("市区"));
        mOrderPopup.addAction(new TitlePopupItemEntity("我的"));
        mOrderPopup.addAction(new TitlePopupItemEntity("收藏"));
        mOrderPopup.addAction(new TitlePopupItemEntity("附近"));
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
                ShowToast.Short("重新获取位置");
                break;
            case R.id.ib_nav_right:
                mTitlePopup.show(mToolsBar);
                break;
            case R.id.rl_create:
            case R.id.iv_create:
                toCreateCircle();
                break;
            case R.id.rl_search:
            case R.id.iv_search:
                toSearchCircle();
                break;
            case R.id.ll_order:
                mOrderPopup.show(mToolsBar);
                break;
            default:
                clickTab(v);
        }
    }

    private void toSearchCircle() {
        Intent intent = new Intent(this, SearchCircleActivity.class);
        startActivity(intent);
    }

    private void toCreateCircle() {
        Intent intent = new Intent(this, CreateCircleActivity.class);
        startActivity(intent);
    }

    private void clickTab(View v) {

        switch (v.getId()) {
            case R.id.indicator_home:
                mViewPager.setCurrentItem(0, false);
                showTitleBar(v.getId());
                break;
            case R.id.indicator_ad:
                if (isLogin(Constants.FROM_AD)) {
                    mViewPager.setCurrentItem(1, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.indicator_near:
                if (isLogin(Constants.FROM_TAB3)) {
                    mViewPager.setCurrentItem(3, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.indicator_me:
                if (isLogin(Constants.FROM_ME)) {
                    mViewPager.setCurrentItem(4, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.iv_toInteractive://圈子
                mViewPager.setCurrentItem(2, false);
                showTitleBar(v.getId());
                break;
        }
    }

    /**
     * 显示title bar样式
     *
     * @param type
     */
    private void showTitleBar(int type) {
        mToolsBar.setVisibility(View.VISIBLE);
        switch (type) {
            case R.id.indicator_home:
                initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_HOME);
                break;
            case R.id.indicator_near:
                initTitleBar("互动平台", "", "", Constants.TITLE_TYPE_NONE);
                break;
            case R.id.indicator_ad:
                mToolsBar.setVisibility(View.GONE);
                break;
            case R.id.indicator_me:
                initTitleBar("个人中心", "", "", Constants.TITLE_TYPE_NONE);
                break;
            case R.id.iv_toInteractive:
                initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_CIRCLE);
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
        mIvInteractive.setImageResource(R.drawable.img_circle_icon);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        resetOtherTabs();
        if (position != 2) {
            if (position > 2) {
                position--;
            }
            mTabIndicators.get(position).setmIcon(bitmaps.get(position + 4), mSelectedColor);
        } else {
            mIvInteractive.setImageResource(R.drawable.img_circle_icon_selected);
        }
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

    /**
     * 互动平台
     */
    @Override
    public void toInteract() {
        if (isLogin(Constants.FROM_LOGIN_BACK)) {
            Intent intent = new Intent(this, InteractivePlatformActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 二维码扫描
     */
    @Override
    public void toScan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转任务列表
     */
    @Override
    public void toTaskList() {
        Intent intent = new Intent(this, TaskListActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.TASK_AD_TYPE + "");
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    /**
     * 页面跳转Event
     *
     * @param event x
     */
    public void onEvent(MainEvent event) {
        if (Constants.FROM_LOGIN_BACK.equals(event.getMsg())) {
            mViewPager.setCurrentItem(2, false);
            showTitleBar(R.id.iv_toInteractive);
        } else if (Constants.FROM_TAB1.equals(event.getMsg())) {
            mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        } else if (Constants.FROM_AD.equals(event.getMsg())) {
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(1, false);
            //刷新Fragment页面
            mMeFragment.getData();
            mInteractiveFragment.getAllData();
            showTitleBar(R.id.indicator_ad);
        } else if (Constants.FROM_TAB3.equals(event.getMsg())) {
            mTabIndicators.get(2).setmIcon(bitmaps.get(6), mSelectedColor);
            mViewPager.setCurrentItem(3, false);
            //刷新MeFragment页面
            mMeFragment.getData();
            mInteractiveFragment.getAllData();
            showTitleBar(R.id.indicator_near);
        } else if (Constants.FROM_ME.equals(event.getMsg())) {
            mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
            mViewPager.setCurrentItem(4, false);
            //刷新MeFragment页面
            mMeFragment.getData();
            mInteractiveFragment.getAllData();
            showTitleBar(R.id.indicator_me);
        } else if (Constants.USER_INFO_PASS_FROM_ME.equals(event.getMsg())) {
            toLogin(Constants.FROM_ME);
        } else if (Constants.SUPERADDITION_AD.equals(event.getMsg())) {//追加
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(1, false);
        }
    }

    /**
     * 地图Event
     *
     * @param event
     */
    public void onEvent(BaiduMapLocationEvent event) {
        mNavLeft.setText(MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 88) {
            Intent intent = new Intent(this, ScanChunsunCodeResultActivity.class);
            String result = data.getStringExtra("message_extra");
            intent.putExtra(Constants.EXTRA_KEY, result);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ShowToast.Short("再按一次退出程序！");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
