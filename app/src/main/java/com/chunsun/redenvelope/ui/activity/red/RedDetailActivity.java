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
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;
import com.chunsun.redenvelope.model.event.RedDetailBackEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.RedDetailPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.CouponRedDetailFragment;
import com.chunsun.redenvelope.ui.fragment.RedDetailFragment;
import com.chunsun.redenvelope.ui.fragment.RedDetailPicPreviewFragment;
import com.chunsun.redenvelope.ui.view.IRedDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/10.
 * 红包详情
 */
public class RedDetailActivity extends BaseActivity implements IRedDetailView {

    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.main_nav)
    RelativeLayout mMainNav;

    private RedDetailPresenter mPresenter;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    //红包id
    private String mRedDetailId;
    //生活类红包Fragment
    private RedDetailFragment mRedDetailFragment;
    //券类红包Fragment
    private CouponRedDetailFragment mCouponRedDetailFragment;
    private String mToken;
    private ShareLimitEntity.ResultEntity mShareLimit;
    //红包类型
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new RedDetailPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mMainNav.setVisibility(View.GONE);

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
                    if (mType == Constants.RED_DETAIL_TYPE_LEFT) {
                        mRedDetailFragment.startAutoScroll();
                    } else if (mType == Constants.RED_DETAIL_TYPE_COUPON) {
                        mCouponRedDetailFragment.startAutoScroll();
                    }
                } else {
                    if (mType == Constants.RED_DETAIL_TYPE_LEFT) {
                        mRedDetailFragment.stopAutoScroll();
                    } else if (mType == Constants.RED_DETAIL_TYPE_COUPON) {
                        mCouponRedDetailFragment.stopAutoScroll();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mRedDetailId = intent.getStringExtra(Constants.EXTRA_KEY);
            mType = intent.getIntExtra(Constants.EXTRA_KEY2, -1);
        }
        mFragments = new ArrayList<>();
        mPresenter.getShareLimit(mToken);

    }

    @Override
    public void setData(ArrayList<String> urls, RedDetailEntity.ResultEntity.DetailEntity detail) {

        for (String str : urls) {
            RedDetailPicPreviewFragment fragment = new RedDetailPicPreviewFragment(str);
            mFragments.add(fragment);
        }

        if (mType == Constants.RED_DETAIL_TYPE_LEFT) {
            mRedDetailFragment = new RedDetailFragment();
            Bundle data = new Bundle();
            data.putParcelable(Constants.EXTRA_KEY, detail);
            data.putStringArrayList(Constants.EXTRA_KEY2, urls);
            data.putParcelable(Constants.EXTRA_KEY3, mShareLimit);
            mRedDetailFragment.setArguments(data);
            mFragments.add(mRedDetailFragment);
        } else if (mType == Constants.RED_DETAIL_TYPE_COUPON) {
            mCouponRedDetailFragment = new CouponRedDetailFragment();
            Bundle data = new Bundle();
            data.putParcelable(Constants.EXTRA_KEY, detail);
            data.putStringArrayList(Constants.EXTRA_KEY2, urls);
            data.putParcelable(Constants.EXTRA_KEY3, mShareLimit);
            mCouponRedDetailFragment.setArguments(data);
            mFragments.add(mCouponRedDetailFragment);
        }

        mViewPager.setAdapter(mAdapter);
        //设置预加载数
        mViewPager.setOffscreenPageLimit(urls.size() + 1);
    }

    @Override
    public void getShareLimit(ShareLimitEntity.ResultEntity result) {
        mShareLimit = result;
        mPresenter.getData(mToken, mRedDetailId);
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
