package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;
import com.chunsun.redenvelope.model.event.WebRedDetailEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.WebRedDetailPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.WebRedDetailFragment;
import com.chunsun.redenvelope.ui.view.IWebRedDetailView;
import com.chunsun.redenvelope.utils.DensityUtils;
import com.chunsun.redenvelope.widget.popupwindow.ShareRedEnvelopePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 链接型红包详情Activity
 */
public class WebRedDetailActivity extends BaseActivity implements IWebRedDetailView, View.OnClickListener {

    @Bind(R.id.main_nav)
    RelativeLayout mToolsBar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.ib_button)
    ImageButton mIbInfo;
    @Bind(R.id.rl_get_red)
    RelativeLayout mRlGetRed;
    @Bind(R.id.iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_yuan)
    TextView mTvYuan;
    @Bind(R.id.main)
    LinearLayout mLLMain;

    private String mRedDetailId;
    private RedDetailEntity.ResultEntity.DetailEntity mRed;
    //红包倒计时秒数
    private int mDelaySeconds;

    private WebRedDetailPresenter mPresenter;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    //button移动用到的属性
    private int lastX, lastY;
    private int downX, downY;
    //四边的边界
    private int mLeftSide;
    private int mRightSide;
    private int mTopSide;
    private int mBottomSide;


    private String mToken;
    private ShareLimitEntity.ResultEntity mShareLimit;
    //当前显示的webview是第几个
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_red_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new WebRedDetailPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);
        mNavRightIcon.setBackgroundResource(R.drawable.img_share_icon);
        mNavRightIcon.setVisibility(View.VISIBLE);

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
        mNavRightIcon.setOnClickListener(this);
        mIbInfo.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mCurrentPage = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        mIbInfo.setOnTouchListener(new View.OnTouchListener() {

            /**
             * 这有一点要说明的是 ****小理解点******* 1. event.getX : 是以 widget（控件） 的左上角 为 原点的
             * X坐标 event.getRawX() : 是以 屏幕左上角 为原点的 X坐标 2. View.layout(left, top,
             * right, bottom); left ： 控件左端以 父 控件的 左上角为原点的X坐标 top ： 控件顶端以 父 控件的
             * 左上角为原点的Y坐标 right ： 控件右端以 父 控件的 左上角为原点的X坐标 bottom ： 控件底端以 父 控件的
             * 左上角为原点的Y坐标 这个地方如果不明白会在博客上图说明。
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /**
                 * 这个地方的逻辑是： 在 down 的时候记录一下距离屏幕左上角的距离 然后move的时候来再来计算一下距离 2着的差值就是分别
                 * x轴和y轴移动的距离
                 */
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 按下的时候距离屏幕左上角的距离
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        downX = lastX;
                        downY = lastY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 移动的时候距离屏幕左上角的距离
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();

                        if ((nowX >= mLeftSide && nowX <= mRightSide)
                                && (nowY >= mTopSide && nowY <= mBottomSide)) {

                            // X轴和Y轴移动的距离
                            int moveX = nowX - lastX;
                            int moveY = nowY - lastY;
                            // 分别计算距离
                            int top = v.getTop() + moveY;
                            int bottom = v.getBottom() + moveY;
                            int left = v.getLeft() + moveX;
                            int right = v.getRight() + moveX;

                            v.layout(left, top, right, bottom);

                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        int upX = (int) event.getRawX();
                        int upY = (int) event.getRawY();
                        if (Math.abs(upX - downX) > 5 || Math.abs(upY - downY) > 5) {
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mRedDetailId = intent.getStringExtra(Constants.EXTRA_KEY);
        }
        mPresenter.getData(new Preferences(this).getToken(), mRedDetailId);
        mPresenter.getShareLimit(mToken);
        showLoading();
    }

    @Override
    public void loadUrl(RedDetailEntity.ResultEntity.DetailEntity entity) {
        mRed = entity;
        initFragment();
        setRedStatus();
        initButtonMoveSide();
    }

    @Override
    public void getShareLimit(ShareLimitEntity.ResultEntity result) {
        mShareLimit = result;
    }

    @Override
    public void shareSuccess() {
        back();
    }

    /**
     * 初始化Button移动的范围
     */
    private void initButtonMoveSide() {
        int statusBarH = DensityUtils.getStatusBarH(this);
        int toolBarH = mToolsBar.getHeight();
        int recordButtonW = mIbInfo.getWidth();
        int recordButtonH = mIbInfo.getHeight();
        int getRedRectH = mRlGetRed.getHeight();

        mLeftSide = recordButtonW;
        mRightSide = DensityUtils.getDisplayWidth(this) - recordButtonW;
        mTopSide = statusBarH + toolBarH + recordButtonH;
        mBottomSide = DensityUtils.getDisplayHeight(this) - getRedRectH - recordButtonH;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                back();
                break;
            case R.id.ib_nav_right:
                ShareRedEnvelopePopupWindow noRewardMenuWindow = new ShareRedEnvelopePopupWindow(this, mRed);
                noRewardMenuWindow.showAtLocation(mLLMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.rl_get_red:
                String path = mRed.getContent().split("，")[mCurrentPage];
                mRed.setContent(path);
                ShareRedEnvelopePopupWindow menuWindow = new ShareRedEnvelopePopupWindow(this, mRed, mShareLimit, Constants.SHARE_FROM_WEB_RED);
                menuWindow.showAtLocation(mLLMain, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.ib_button:
                Intent intent = new Intent(this, WebRedDetailCommentActivity.class);
                intent.putExtra(Constants.EXTRA_KEY, mRed.getId());
                startActivity(intent);
                break;
        }
    }

    private void countDown() {

        if (mRed.is_open()) {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("您已经领取红包" + mRed.getPrice() + "元");
            mRlGetRed.setBackgroundColor(getResources().getColor(
                    R.color.global_red_tran));
        } else if (!mRed.isHb_status()) {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("来晚了，红包已抢空");
            mTvContent
                    .setTextColor(getResources().getColor(R.color.font_web_red_detail));
            mRlGetRed.setBackgroundColor(getResources().getColor(
                    R.color.bg_web_red_detail));
        } else {
            mPresenter.countDown(mRed.getPre_load_seconds());
        }
    }

    private void refreshDelaySeconds() {
        if (mDelaySeconds > 0) {
            mTvContent.setText(mDelaySeconds + "秒后可领取");
            mDelaySeconds--;
            EventBus.getDefault().post(new WebRedDetailEvent("louis"));
        } else {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("点击领取");
            mTvPrice.setText(mRed.getPrice());
            mTvPrice.setVisibility(View.VISIBLE);
            mTvYuan.setVisibility(View.VISIBLE);
            mRlGetRed.setOnClickListener(this);
        }
    }

    /**
     * 初始化有多少fragment
     */
    private void initFragment() {
        boolean repeat = mRed.is_repeat();
        boolean allowRepeat = mRed.is_allow_repeat();

        int linknum = mRed.getCan_open_linknum();
        if (repeat && !allowRepeat) {
            linknum = 1;
        }

        String[] urls = mRed.getContent().split("，");
        if (urls.length < linknum) {
            linknum = urls.length;
        }

        boolean flag = false;
        for (int i = 0; i < linknum; i++) {
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

    /**
     * 设置红包状态
     */
    private void setRedStatus() {
        if (mRed.isGrab_status()) {
            if (mRed.is_open()) {
                mIvIcon.setVisibility(View.GONE);
                mTvContent.setText("您已经领取红包" + mRed.getPrice() + "元");
                mRlGetRed.setBackgroundColor(getResources().getColor(R.color.global_red_tran));
            } else {
                countDown();
            }
        } else if (mRed.isHb_status()) {
            mIvIcon.setVisibility(View.GONE);
            mTvContent.setText("来晚了，红包已抢空");
            mTvContent.setTextColor(getResources().getColor(R.color.font_web_red_detail));
            mRlGetRed.setBackgroundColor(getResources().getColor(R.color.bg_web_red_detail));
        } else {
            countDown();
        }
    }

    public void onEventMainThread(WebRedDetailEvent event) {
        if ("".equals(event.getMsg())) {
            mDelaySeconds = mRed.getDelay_seconds();
            refreshDelaySeconds();
        } else if ("hideLoading".equals(event.getMsg())) {//第一个page加载完成后hide loading
            hideLoading();
        } else if ("share".equals(event.getMsg())) {//分享后
            mPresenter.shareOpen(mToken, mRed.getHg_id(), event.getContent());
        } else if ("no_share".equals(event.getMsg())) {//不分享，直接领钱
            mPresenter.justOpen(mToken, mRed.getHg_id());

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshDelaySeconds();
                }
            }, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }
}
