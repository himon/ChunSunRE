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

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.ForwardFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.WebRedDetailActivity;
import com.chunsun.redenvelope.ui.adapter.RedListAdapter;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.view.IForwardFragmentView;
import com.chunsun.redenvelope.utils.DensityUtils;
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
 * 转发广告Fragment
 */
public class ForwardFragment extends BaseFragment implements IForwardFragmentView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;

    private GuideGallery mViewPager;
    private ForwardFragmentPresenter mPresenter;
    private RedListAdapter mAdapter;
    private AdImageAdapter imageAdapter;

    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedListDetailEntity.ResultEntity.PoolEntity> mList = new ArrayList<RedListDetailEntity.ResultEntity.PoolEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;

    public ForwardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new ForwardFragmentPresenter(this);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        /**
         * 轮播图
         */
        int density = (int) (120 * DensityUtils.getDensity(getActivity()));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, density);
        mViewPager = new GuideGallery(getActivity());
        mViewPager.setLayoutParams(params);

        /**
         * List列表
         */
        mListView.addHeaderView(mViewPager);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mAdapter = new RedListAdapter(getActivity(), mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!((MainActivity) getActivity()).isLogin()) {
                    ((MainActivity) getActivity()).toLogin(Constants.FROM_TAB3);
                    return;
                }
                RedListDetailEntity.ResultEntity.PoolEntity entity = (RedListDetailEntity.ResultEntity.PoolEntity) parent.getAdapter().getItem(position);
                if (Constants.RED_DETAIL_TYPE_REPEAT == entity.getType()) {
                    toRepeatRedDetail(entity.getId());
                } else {
                    mPresenter.grabRedEnvelope(new Preferences(getActivity()).getToken(), entity.getId(), entity);
                }
            }
        });

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);
    }

    @Override
    protected void initData() {

    }

    public void getData() {
        if (mCurrentPage * Constants.PAGE_NUM > mTotal + Constants.PAGE_NUM) {
            return;
        }
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
            mPresenter.getAdData(Constants.RED_DETAIL_TYPE_NEAR + "");
        }
        mPresenter.loadData(new Preferences(getActivity()).getToken(), Constants.RED_DETAIL_TYPE_REPEAT + "", mCurrentPage);
    }

    @Override
    public void setData(RedListDetailEntity.ResultEntity entity) {

        List<RedListDetailEntity.ResultEntity.PoolEntity> list = entity.getPool();

        mTotal = Integer.parseInt(entity.getTotal_count());

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }

        mCurrentPage++;

        mList.addAll(list);

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();

    }

    @Override
    public void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert) {
        imageAdapter = new AdImageAdapter(advert, getActivity());
        mViewPager.setSize(advert.size());
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

    @Override
    public void toRedDetail(String id) {
        Intent intent = new Intent(getActivity(), RedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        startActivity(intent);
    }

    @Override
    public void toWebRedDetail(String id) {
        Intent intent = new Intent(getActivity(), WebRedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        startActivity(intent);
    }

    @Override
    public void toRepeatRedDetail(String id) {
        if (TextUtils.isEmpty(new Preferences(getActivity()).getToken())) {
            toLogin();
        } else {
            Intent intent = new Intent(getActivity(), RepeatRedDetailActivity.class);
            intent.putExtra(Constants.EXTRA_KEY, id);
            startActivity(intent);
        }
    }

    @Override
    public void toAdWebView(String title, String url) {
        Intent intent = new Intent(getActivity(), CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, url);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, title);
        startActivity(intent);
    }

    @Override
    public void grabRedEnvelopeSuccess(RedListDetailEntity.ResultEntity.PoolEntity entity) {
        if (Constants.RED_DETAIL_TYPE_LINK == entity.getType()) {
            toWebRedDetail(entity.getId());
        } else if (Constants.RED_DETAIL_TYPE_REPEAT == entity.getType()) {
            toRepeatRedDetail(entity.getId());
        } else {
            toRedDetail(entity.getId());
        }
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_TAB3);
        startActivity(intent);
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

    /**
     * 从蒙版点击进入第一个Item
     */
    public void mengBanClick() {
        RedListDetailEntity.ResultEntity.PoolEntity entity = mList.get(0);
        if (Constants.RED_DETAIL_TYPE_REPEAT == entity.getType()) {
            toRepeatRedDetail(entity.getId());
        } else {
            mPresenter.grabRedEnvelope(new Preferences(getActivity()).getToken(), entity.getId(), entity);
        }
    }
}
