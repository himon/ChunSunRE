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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.TitlePopupItemEntity;
import com.chunsun.redenvelope.model.event.BaiduMapLocationEvent;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.model.event.MainMengBanEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.scanlibrary.CaptureActivity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdActivity;
import com.chunsun.redenvelope.ui.activity.scan.ScanChunsunCodeResultActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.mengban.MengBanTab3Fragment;
import com.chunsun.redenvelope.ui.fragment.mengban.MengBanTab4Fragment;
import com.chunsun.redenvelope.ui.fragment.mengban.MengBanTab4StepTwoFragment;
import com.chunsun.redenvelope.ui.fragment.mengban.MengbanTab1Fragment;
import com.chunsun.redenvelope.ui.fragment.mengban.MengbanTab1StepThreeFragment;
import com.chunsun.redenvelope.ui.fragment.mengban.MengbanTab1StepTwoFragment;
import com.chunsun.redenvelope.ui.fragment.tab.ForwardFragment;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
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

    private ForwardFragment mForwardFragment;
    //private AdFragment mAdFragment;
    private NewMeFragment mMeFragment;
    private HomeFragment mHomeFragment;
    //选中Tab页图标的颜色
    private int mSelectedColor;
    private int mUnSelectedColor;

    //定义标题栏弹窗按钮
    private TitlePopup mTitlePopup;
    private Preferences mPreferences;

    /**
     * 蒙版Fragment
     */
    private MengbanTab1Fragment mTab1Fragment;
    private MengbanTab1StepTwoFragment mTab1StepTwoFragment;
    private MengbanTab1StepThreeFragment mTab1StepThreeFragment;
    private MengBanTab3Fragment mTab3Fragment;
    private MengBanTab4Fragment mTab4Fragment;
    private MengBanTab4StepTwoFragment mTab4StepTwoFragment;

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

        initTitleBar("春笋红包", MainApplication.getContext().getCity().equals("市辖区") ? MainApplication.getContext().getProvince() : MainApplication.getContext().getCity(), "", Constants.TITLE_TYPE_HOME);

        mTabIndicators.add(mHome);
        mTabIndicators.add(mAd);
        mTabIndicators.add(mForward);
        mTabIndicators.add(mMe);

        mHomeFragment = new HomeFragment();
        //mAdFragment = new AdFragment();
        mForwardFragment = new ForwardFragment();
        mMeFragment = new NewMeFragment();

        mTabs.add(mHomeFragment);
        //mTabs.add(mAdFragment);
        mTabs.add(mForwardFragment);
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


        isMengBanShow();

        initEvent();
    }

    /**
     * 显示蒙版
     */
    private void isMengBanShow() {
        mPreferences = new Preferences(this);
        if (mPreferences.getFirstShowTab1()) {
            mPreferences.setFirstShowTab1(false);
            mTab1Fragment = new MengbanTab1Fragment();
            mTab1StepTwoFragment = new MengbanTab1StepTwoFragment();
            mTab1StepThreeFragment = new MengbanTab1StepThreeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.root, mTab1Fragment).commit();
        }
    }

    /**
     * tab3的蒙版是否显示
     */
    private void isMengBanShowOfTab3() {
        if (mPreferences.getFirstShowTab3()) {
            mPreferences.setFirstShowTab3(false);
            mTab3Fragment = new MengBanTab3Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.root, mTab3Fragment).commit();
        }
    }

    /**
     * 个人中心是否显示蒙版
     */
    public boolean isMengBanShowOfTab4() {
        if (mPreferences.getFirstShowPersonal() && mViewPager.getCurrentItem() == 2) {
            mPreferences.setFirstShowPersonal(false);
            mTab4Fragment = new MengBanTab4Fragment();
            mTab4StepTwoFragment = new MengBanTab4StepTwoFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.root, mTab4Fragment).commit();
            return true;
        }
        return false;
    }

    /**
     * 用户判断MeFragment是否成功获取用户信息
     * <p/>
     * 如果有token，但是没有获取用户信息，说明当前账号在别处登录
     *
     * @return
     */
    public boolean isLogin() {
        return mMeFragment.isLogin();
    }

    private void initEvent() {

        mNavRight.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRightIcon.setOnClickListener(this);

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
                ShowToast.Short("重新获取位置");
                break;
            case R.id.ib_nav_right:
                mTitlePopup.show(mToolsBar);
                break;
            default:
                clickTab(v);
        }
    }

    private void clickTab(View v) {

        switch (v.getId()) {
            case R.id.indicator_home:
                mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
                mViewPager.setCurrentItem(0, false);
                showTitleBar(v.getId());
                break;
            case R.id.indicator_ad:
                if (isLogin(Constants.FROM_AD)) {
                    if (mMeFragment.isLogin()) {
                        //mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
                        //mViewPager.setCurrentItem(1, false);
                        //showTitleBar(v.getId());

                        Intent intent = new Intent(this, CreateAdActivity.class);
                        startActivity(intent);
                    }else{
                        toLogin(Constants.FROM_AD);
                    }
                }
                break;
            case R.id.indicator_near:
                //判断是否显示蒙版
                isMengBanShowOfTab3();
                mTabIndicators.get(2).setmIcon(bitmaps.get(6), mSelectedColor);
                mViewPager.setCurrentItem(1, false);
                showTitleBar(v.getId());
                break;
            case R.id.indicator_me:
                //判断是否显示蒙版
                if (isLogin(Constants.FROM_ME)) {
                    if (isMengBanShowOfTab4() || TextUtils.isEmpty(new Preferences(this).getToken())) {
                        mMeFragment.getData();
                    } else if (!mMeFragment.isLogin()) {
                        mMeFragment.getDataFromClick();
                    }
                    mTabIndicators.get(3).setmIcon(bitmaps.get(7), mSelectedColor);
                    mViewPager.setCurrentItem(2, false);
                    showTitleBar(v.getId());
                }
                break;
            case R.id.iv_toInteractive:
                if (mMeFragment.isLogin()) {
                    toInteract();
                } else {
                    toLogin(Constants.FROM_TAB1);
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
        if (position != 0) {
            position = position + 1;
        }
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

    /**
     * 二维码扫描
     */
    @Override
    public void toScan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }

    /**
     * 页面跳转Event
     *
     * @param event
     */
    public void onEvent(MainEvent event) {
        if (Constants.FROM_LOGIN_BACK.equals(event.getMsg())) {
            mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
        } else if (Constants.FROM_TAB1.equals(event.getMsg())) {
            mTabIndicators.get(0).setmIcon(bitmaps.get(4), mSelectedColor);
            mViewPager.setCurrentItem(0, false);
            //刷新MeFragment页面
            mMeFragment.getData();
        } else if (Constants.FROM_AD.equals(event.getMsg())) {
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(1, false);
        } else if (Constants.FROM_TAB3.equals(event.getMsg())) {
            mTabIndicators.get(2).setmIcon(bitmaps.get(6), mSelectedColor);
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
        } else if (Constants.SUPERADDITION_AD.equals(event.getMsg())) {//老版本追加
            mTabIndicators.get(1).setmIcon(bitmaps.get(5), mSelectedColor);
            mViewPager.setCurrentItem(1, false);
            //mAdFragment.setSuperaddition(event.getEntity());
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

    /**
     * 蒙版Event
     *
     * @param event
     */
    public void onEvent(MainMengBanEvent event) {
        switch (event.getMsg()) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.root, mTab1StepTwoFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.root, mTab1StepThreeFragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().remove(mTab1StepThreeFragment)
                        .commit();
                //进入第一个Item详情
                mHomeFragment.mengBanClick();
                mTab1Fragment = null;
                mTab1StepTwoFragment = null;
                mTab1StepThreeFragment = null;
                break;
            case 3://tab3
                getSupportFragmentManager().beginTransaction().remove(mTab3Fragment).commit();
                //进入tab3的第一个Item详情
                mForwardFragment.mengBanClick();
                mTab3Fragment = null;
                break;
            case 4://tab4
                getSupportFragmentManager().beginTransaction().replace(R.id.root, mTab4StepTwoFragment).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().remove(mTab4StepTwoFragment).commit();
                mTab4Fragment = null;
                mTab4StepTwoFragment = null;
                break;
        }
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
}
