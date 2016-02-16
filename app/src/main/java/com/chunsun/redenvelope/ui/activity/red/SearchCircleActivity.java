package com.chunsun.redenvelope.ui.activity.red;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedAutoAdEntity;
import com.chunsun.redenvelope.entities.json.RedListDetailEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.HomeFragmentPresenter;
import com.chunsun.redenvelope.ui.adapter.RedListAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IHomeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.helper.RedEvenlopeListHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 查询圈子
 */
public class SearchCircleActivity extends BaseActivity implements IHomeFragmentView {

    @Bind(R.id.main_nav)
    RelativeLayout mToolsBar;
    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.ll_clear)
    LinearLayout mLlClear;
    @Bind(R.id.iv_clear)
    ImageView mIvClear;
    @Bind(R.id.btn_search)
    Button mBtnSearch;
    @Bind(R.id.iv_to_top)
    ImageView mIvTop;
    @Bind(R.id.fl_list)
    FrameLayout mFlList;


    private HomeFragmentPresenter mPresenter;
    private RedListAdapter mAdapter;
    //当前显示页数
    private int mCurrentPage = 1;
    private List<RedListDetailEntity.ResultEntity.PoolEntity> mList = new ArrayList<RedListDetailEntity.ResultEntity.PoolEntity>();
    //选中的Item的详情
    private RedListDetailEntity.ResultEntity.PoolEntity mEntity;
    //是否是下拉刷新
    private boolean isRefresh;
    //红包列表总数
    private int mTotal = 0;
    private String mToken;
    /**
     * 帮助类
     */
    RedEvenlopeListHelper mRedEvenlopeListHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_circle);
        ButterKnife.bind(this);
        mPresenter = new HomeFragmentPresenter(this);
        mRedEvenlopeListHelper = new RedEvenlopeListHelper(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        mToolsBar.setVisibility(View.GONE);

        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mAdapter = new RedListAdapter(this, mList, R.layout.adapter_home_adapter);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEntity = (RedListDetailEntity.ResultEntity.PoolEntity) parent.getAdapter().getItem(position);
                toRedDetail(mEntity.getId());
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

    private void initEvent() {
        mBtnSearch.setOnClickListener(this);
        mLlClear.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mIvTop.setOnClickListener(this);
        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (editTextIsEmpty()) {
                    mBtnSearch.setText("取消");
                    mLlClear.setVisibility(View.INVISIBLE);
                } else {
                    mBtnSearch.setText("搜索");
                    mLlClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
    }

    public void getData() {
        if (mCurrentPage * Constants.PAGE_NUM > mTotal + Constants.PAGE_NUM) {
            mPtr.refreshComplete();
            hideLoading();
            return;
        }
        if (isRefresh) {
            mCurrentPage = 1;
            mList.clear();
        }
        mPresenter.loadData(mToken, Constants.RED_DETAIL_TYPE_CIRCLE, mCurrentPage, Constants.CIRCLE_ORDER_TYPE_FLASHBACK, mEtSearch.getText().toString().trim());
    }

    @Override
    public void setData(RedListDetailEntity.ResultEntity entity) {
        List<RedListDetailEntity.ResultEntity.PoolEntity> list = entity.getPool();

        mTotal = Integer.parseInt(entity.getTotal_count());

        if(mTotal == 0){
            ShowToast.Short("暂无相关内容");
            mCurrentPage = 1;
            mList.clear();
        }

        if (list.size() < Constants.PAGE_NUM) {
            mListView.setNoMore();
        }

        mCurrentPage++;

        mList.addAll(list);

        if (mList.size() == 0) {
            mFlList.setVisibility(View.GONE);
        } else {
            mFlList.setVisibility(View.VISIBLE);
        }

        mAdapter.notifyDataSetChanged();

        mListView.getMoreComplete();

        mPtr.refreshComplete();
    }

    @Override
    public void setAdData(RedAutoAdEntity.ResultEntity advert) {

    }

    @Override
    public void toRedDetail(String id) {
        hideLoading();
        mRedEvenlopeListHelper.toRedDetail(id, Constants.RED_DETAIL_TYPE_CIRCLE);
    }

    @Override
    public void toWebRedDetail(String id) {

    }

    @Override
    public void toAdWebView(String title, String url) {

    }

    @Override
    public void toRepeatRedDetail(String id) {

    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (editTextIsEmpty()) {
                    back();
                } else {
                    search();
                }
                break;
            case R.id.ll_clear:
            case R.id.iv_clear:
                mEtSearch.setText("");
                mList.clear();
                mCurrentPage = 1;
                mFlList.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.iv_to_top:
                mListView.setSelection(0);
                break;
        }
    }

    private boolean editTextIsEmpty() {
        String search = mEtSearch.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            return true;
        } else {
            return false;
        }
    }

    private void search() {
        showLoading();
        hideKeyboard();
        mCurrentPage = 1;
        getData();
    }
}
