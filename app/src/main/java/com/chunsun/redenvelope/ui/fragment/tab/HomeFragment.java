package com.chunsun.redenvelope.ui.fragment.tab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.context.LoginContext;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.HomeFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.adapter.RedListAdapter;
import com.chunsun.redenvelope.ui.base.fragment.BaseFragment;
import com.chunsun.redenvelope.ui.base.view.LoadingView;
import com.chunsun.redenvelope.ui.view.IHomeFragmentView;
import com.chunsun.redenvelope.utils.DensityUtils;
import com.chunsun.redenvelope.utils.helper.RedEvenlopeListHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.AdImageAdapter;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 广告列表Fragment
 */
public class HomeFragment extends BaseFragment implements IHomeFragmentView, LoadingView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.iv_to_top)
    ImageView mIvTop;
    @Bind(R.id.ll_sys_notice_container)
    LinearLayout mLLNotice;
    @Bind(R.id.tv_title)
    TextView mTvNotice;

    private GuideGallery mViewPager;
    private HomeFragmentPresenter mPresenter;
    private RedListAdapter mAdapter;
    private AdImageAdapter imageAdapter;

    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedListDetailEntity.ResultEntity.PoolEntity> mList = new ArrayList<RedListDetailEntity.ResultEntity.PoolEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //轮播广告是否刷新
    private boolean isAdRefresh;
    //红包列表总数
    private int mTotal = 0;
    //选中的Item的详情
    private RedListDetailEntity.ResultEntity.PoolEntity mEntity;
    /**
     * 滚动广告类型
     */
    private int mScrollAdType;
    /**
     * 列表显示广告类型
     */
    private int mShowAdType;
    /**
     * 排序方式
     */
    private int mOrderType = Constants.CIRCLE_ORDER_TYPE_FLASHBACK;
    /**
     * 帮助类
     */
    RedEvenlopeListHelper mRedEvenlopeListHelper;

    private boolean isCheck;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new HomeFragmentPresenter(this);
        mRedEvenlopeListHelper = new RedEvenlopeListHelper(getActivity());
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        Bundle bundle = getArguments();
        mScrollAdType = bundle.getInt(Constants.EXTRA_KEY);
        if (Constants.RED_AD_TYPE == mScrollAdType) {
            mShowAdType = Constants.RED_DETAIL_TYLE_SAMPLE;
        } else if (Constants.TASK_AD_TYPE == mScrollAdType) {
            mShowAdType = Constants.RED_DETAIL_TYPE_REPEAT;
        } else if (Constants.SCROLL_AD_TYPE == mScrollAdType) {
            mShowAdType = Constants.RED_DETAIL_TYPE_CIRCLE;
        }

        /**
         * 轮播图
         */
        int density = (int) (120 * DensityUtils.getDensity(getActivity()));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, density);
        mViewPager = new GuideGallery(getActivity());
        //解决滑动切换时有声音
        //android:soundEffectsEnabled="false"
        mViewPager.setSoundEffectsEnabled(false);
        mViewPager.setLayoutParams(params);

        /**
         * List列表
         */
        mListView.addHeaderView(mViewPager);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                isAdRefresh = false;
                getData();
            }
        });

        mAdapter = new RedListAdapter(getActivity(), mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isCheck && (Constants.SCROLL_AD_TYPE == mScrollAdType || LoginContext.getLoginContext().forward(getActivity(), Constants.FROM_TAB1))) {
                    isCheck = true;
                    showLoading();
                    mEntity = (RedListDetailEntity.ResultEntity.PoolEntity) parent.getAdapter().getItem(position);
                    toJump();
                }
            }
        });

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                isRefresh = true;
                isAdRefresh = true;
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mListView.doOnScrollStateChanged(view, scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mListView.doOnScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                if ((firstVisibleItem + visibleItemCount) - visibleItemCount >= 3) {
                    mIvTop.setVisibility(View.VISIBLE);
                } else {
                    mIvTop.setVisibility(View.GONE);
                }
            }
        });

        initEvent();
    }

    /**
     * 刷新列表
     */
    public void refresh() {
        isRefresh = true;
        getData();
    }


    private void initEvent() {
        mIvTop.setOnClickListener(this);
        mLLNotice.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        switch (mShowAdType) {
            case Constants.RED_DETAIL_TYLE_SAMPLE:
                String redListCash = new Preferences(getActivity()).getRedDetailListData();
                String redAdCash = new Preferences(getActivity()).getRedDetailListAdsData();
                if (!TextUtils.isEmpty(redListCash)) {
                    mPresenter.loadCash(redListCash);
                }
                if (!TextUtils.isEmpty(redAdCash)) {
                    mPresenter.loadAdCash(redAdCash);
                }
                break;
            case Constants.RED_DETAIL_TYPE_CIRCLE:
                String circleListCash = new Preferences(getActivity()).getCircleListData();
                String circleAdCash = new Preferences(getActivity()).getCircleListAdsData();
                if (!TextUtils.isEmpty(circleListCash)) {
                    mPresenter.loadCash(circleListCash);
                }
                if (!TextUtils.isEmpty(circleAdCash)) {
                    mPresenter.loadAdCash(circleAdCash);
                }
                break;
        }
    }

    public void getData() {
        if (isRefresh) {
            mCurrentPage = 1;
            if (isAdRefresh) {
                mPresenter.getAdData(mScrollAdType);
            }
        }
        mPresenter.loadData(new Preferences(getActivity()).getToken(), mShowAdType, mCurrentPage, mOrderType, "");
    }

    @Override
    public void setData(RedListDetailEntity.ResultEntity entity) {
        List<RedListDetailEntity.ResultEntity.PoolEntity> list = entity.getPool();

        mTotal = Integer.parseInt(entity.getTotal_count());

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMoreWithToast();
        } else {
            mListView.setHasMore();
        }

        mCurrentPage++;

        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(list);

        mAdapter.setmDatas(mList);
        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }

    @Override
    public void setAdData(RedAutoAdEntity.ResultEntity advert) {
        List<RedAutoAdEntity.ResultEntity.AdvertEntity> adList = advert.getAdvert();
        imageAdapter = new AdImageAdapter(adList, getActivity());
        if (adList.size() == 0) {
            mListView.removeHeaderView(mViewPager);
        } else {
            mViewPager.setSize(adList.size());
            mViewPager.setAdapter(imageAdapter);
            mViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    RedAutoAdEntity.ResultEntity.AdvertEntity entity = (RedAutoAdEntity.ResultEntity.AdvertEntity) parent.getAdapter().getItem(position);
                    toAdWebView(entity.getTitle(), entity.getTarget_url());
                }
            });
            mViewPager.startAutoScroll();
        }
        if (advert.getNotice() != null && advert.getNotice().size() > 0) {
            RedAutoAdEntity.ResultEntity.NoticeEntity noticeEntity = advert.getNotice().get(0);
            mTvNotice.setText(noticeEntity.getTitle());
            mLLNotice.setTag(noticeEntity);
            mLLNotice.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLLNotice.setVisibility(View.GONE);
                }
            }, 5000);
        }
    }

    @Override
    public void toRedDetail(String id) {
        hideLoading();
        mRedEvenlopeListHelper.toRedDetail(id, mShowAdType);
    }

    @Override
    public void toWebRedDetail(String id) {
        hideLoading();
        mRedEvenlopeListHelper.toWebRedDetail(id, mEntity.getType());
    }

    @Override
    public void toRepeatRedDetail(String id) {
        hideLoading();
        mRedEvenlopeListHelper.toRepeatRedDetail(id);
    }

    @Override
    public void toAdWebView(String title, String url) {
        mRedEvenlopeListHelper.toAdWebView(title, url);
    }

    private void toJump() {
        if (mEntity != null) {
            switch (mEntity.getType()) {
                case 1:
                case 2:
                case 3:
                case 6:
                case 7:
                case 9:
                    toRedDetail(mEntity.getId());
                    break;
                case 4:
                case 8:
                case 10:
                    toWebRedDetail(mEntity.getId());
                    break;
                case 5:
                    toRepeatRedDetail(mEntity.getId());
                    break;
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isCheck = false;
            }
        }, 1000);

    }


    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
    }

    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideLoading();
    }

    /**
     * 按排序刷新
     *
     * @param order
     */
    public void orderRefresh(int order) {
        showLoading();
        switch (order) {
            case 0:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_FLASHBACK;
                break;
            case 1:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_HOT;
                break;
            case 2:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_ESSENCE;
                break;
            case 3:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_RANDOM;
                break;
            case 4:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_LOCAL;
                break;
            case 5:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_MY_CIRCLE;
                break;
            case 6:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_COLLECTION;
                break;
            case 7:
                mOrderType = Constants.CIRCLE_ORDER_TYPE_RANGE;
                break;
        }
        isRefresh = true;
        isAdRefresh = false;
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_to_top:
                mListView.setSelection(0);
                break;
            case R.id.ll_sys_notice_container:
                RedAutoAdEntity.ResultEntity.NoticeEntity entity = (RedAutoAdEntity.ResultEntity.NoticeEntity) v.getTag();
                Intent intentWeb = new Intent(v.getContext(),
                        CommonWebActivity.class);
                intentWeb.putExtra(
                        Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                        Constants.SYSTEM_NOTICE_URL + entity.getId());
                intentWeb.putExtra(
                        Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                        entity.getTitle());
                v.getContext().startActivity(intentWeb);
                break;
        }
    }
}
