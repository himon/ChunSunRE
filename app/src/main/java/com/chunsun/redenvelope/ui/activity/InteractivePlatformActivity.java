package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.InteractivePlatformPresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.adapter.InteractivePlatformAdapter;
import com.chunsun.redenvelope.ui.base.activity.at.BaseAtActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IInteractivePlatformView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.InteractiveHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 互动平台
 */
public class InteractivePlatformActivity extends BaseAtActivity<IInteractivePlatformView, InteractivePlatformPresenter> implements IInteractivePlatformView {

    @Bind(R.id.ll_record_type)
    LinearLayout mLLRecordType;
    @Bind(R.id.rb_main_comment_country)
    RadioButton mRbCommentCountry;
    @Bind(R.id.rb_main_comment_local)
    RadioButton mRbCommentLocal;
    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.et_comment)
    EditText mEtComment;
    @Bind(R.id.btn_send_comment)
    Button mBtnSendComment;

    TextView mTvTitle;
    TextView mTvContent;
    TextView mTvTime;
    RadioGroup mRgType;
    RadioButton mRbCountry;
    RadioButton mRbLocal;

    private InteractivePlatformPresenter mPresenter;
    private InteractivePlatformAdapter mDataAdapter;
    //全国列表
    private List<InteractiveEntity.ResultEntity.ListEntity> mListCountry = new ArrayList<>();
    //本地列表
    private List<InteractiveEntity.ResultEntity.ListEntity> mListLocal = new ArrayList<>();
    //是否是下拉刷新
    private boolean isRefresh;
    //当前全国显示页数
    private int mCurrentCountryPage = 1;
    //当前本地显示页数
    private int mCurrentLocalPage = 1;
    //全国列表总数
    private int mTotalCountry = 0;
    //本地列表总数
    private int mTotalLocal = 0;
    //标示当前列表显示的是全国还是本地， 0 ： 全国， 1 ： 本地
    private int mCurrentCheckType = 0;

    //本地评论是否已滑动到底
    private boolean isLocalFinished = false;

    private String mToken;
    /**
     * 回复@用户的id
     */
    private String at = "0";
    /**
     * 互动平台帮助类
     */
    private InteractiveHelper mInteractiveHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_platform);
        ButterKnife.bind(this);
        mPresenter = (InteractivePlatformPresenter) mMPresenter;
        mInteractiveHelper = new InteractiveHelper(this);
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new InteractivePlatformPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("互动平台", "", "", Constants.TITLE_TYPE_SAMPLE);
        View view = LayoutInflater.from(this).inflate(R.layout.adapter_interactive_platform_top_item_view, null);
        mTvTitle = (TextView) view.findViewById(R.id.tv_system_title);
        mTvContent = (TextView) view.findViewById(R.id.tv_system_content);
        mTvTime = (TextView) view.findViewById(R.id.tv_system_content_time);
        mRgType = (RadioGroup) view.findViewById(R.id.rg_record_type);
        mRbCountry = (RadioButton) view.findViewById(R.id.rb_comment_country);
        mRbLocal = (RadioButton) view.findViewById(R.id.rb_comment_local);

        /**
         * ListView
         */
        mListView.addHeaderView(view);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mDataAdapter = new InteractivePlatformAdapter(this, mListCountry, mListLocal, mCurrentCheckType, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head_logo:
                        Object tag = v.getTag(R.id.tag_first);
                        toUserRewardActivity(tag.toString());
                        break;
                }
            }
        }, new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                at = v.getTag(R.id.tag_first).toString();
                String tag = v.getTag(R.id.tag_second).toString();
                mEtComment.setHint("@" + tag + "：");
                return true;
            }
        });
        mListView.setAdapter(mDataAdapter);

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
                isLocalFinished = false;
                getAllData();
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
                if (firstVisibleItem >= 1) {
                    mLLRecordType.setVisibility(View.VISIBLE);
                } else {
                    mLLRecordType.setVisibility(View.GONE);
                }
            }
        });

        initEvent();
    }

    private void initEvent() {
        deleteAt();
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);

        mRbCountry.setOnClickListener(this);
        mRbCommentCountry.setOnClickListener(this);
        mRbLocal.setOnClickListener(this);
        mRbCommentLocal.setOnClickListener(this);
        mBtnSendComment.setOnClickListener(this);

        //设置发送按钮不可点击
        mBtnSendComment.setEnabled(false);
        mEtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(StringUtil.textview2String(mEtComment))) {
                    mBtnSendComment.setTextColor(getResources().getColor(
                            R.color.font_hint_gray));
                    mBtnSendComment
                            .setBackgroundResource(R.drawable.shape_radius_gray);
                    mBtnSendComment.setEnabled(false);
                } else {
                    mBtnSendComment.setTextColor(getResources()
                            .getColor(R.color.white));
                    mBtnSendComment.setBackgroundResource(R.drawable.shape_radius_red);
                    mBtnSendComment.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            String data = intent.getStringExtra(Constants.EXTRA_KEY);
            if (!TextUtils.isEmpty(data)) {
                mCurrentCheckType = 1;
            }
        }
    }


    private void getAllData() {
        if (isRefresh) {
            mCurrentCountryPage = 1;
            mListCountry.clear();
            mCurrentLocalPage = 1;
            mListLocal.clear();
            mCurrentCheckType = 0;
        }
        mPresenter.getCountryList(mToken, mCurrentCountryPage);
        mPresenter.getLocalList(mToken, mCurrentLocalPage);
    }

    /**
     * 跳转用户奖励页面
     */
    //@Override
    public void toUserRewardActivity(String id) {
        mInteractiveHelper.toUserRewardActivity(id, mCurrentCountryPage);
    }

    /**
     * 设置系统公告内容
     *
     * @param notice
     */
    @Override
    public void setNoticeBoard(List<InteractiveEntity.ResultEntity.NoticeEntity> notice) {
        InteractiveEntity.ResultEntity.NoticeEntity noticeEntity = notice.get(0);
        mInteractiveHelper.setNoticeBoard(noticeEntity, mTvTitle, mTvContent, mTvTime);
    }

    @Override
    public void UserIsEmpty() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_INTERACTIVE);
        startActivity(intent);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.rb_comment_country:
            case R.id.rb_main_comment_country:
                mRbCommentCountry.setChecked(true);
                mRbCountry.setChecked(true);
                mCurrentCheckType = 0;
                mListView.setHasMore();
                changerDataList();
                break;
            case R.id.rb_comment_local:
            case R.id.rb_main_comment_local:
                mRbCommentLocal.setChecked(true);
                mRbLocal.setChecked(true);
                mCurrentCheckType = 1;
                if (isLocalFinished) {
                    mListView.setNoMore();
                } else {
                    mListView.setHasMore();
                }
                changerDataList();
                break;
            case R.id.btn_send_comment://评论
                mPresenter.sendComment(mToken, mCurrentCheckType, StringUtil.textview2String(mEtComment), at);

                break;
        }
    }

    @Override
    public void setCountryList(InteractiveEntity entity) {
        List<InteractiveEntity.ResultEntity.ListEntity> list = entity.getResult().getList();
        mTotalCountry = Integer.parseInt(entity.getResult().getTotal_count());
        if (mCurrentCheckType == 0 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        }
        mCurrentCountryPage++;
        mListCountry.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        if (isRefresh) {
            setNoticeBoard(entity.getResult().getNotice());
        }
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void setLocalList(InteractiveEntity entity) {
        List<InteractiveEntity.ResultEntity.ListEntity> list = entity.getResult().getList();
        mTotalLocal = Integer.parseInt(entity.getResult().getTotal_count());
        mRbLocal.setText(MainApplication.getContext().getCity());
        if (mCurrentCheckType == 1 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
            isLocalFinished = true;
        }
        mCurrentLocalPage++;
        mListLocal.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void commentSuccess() {
        switch (mCurrentCheckType) {
            case 0:
                mCurrentCountryPage = 1;
                mListCountry.clear();
                mPresenter.getCountryList(mToken, mCurrentCountryPage);
                break;
            case 1:
                mCurrentLocalPage = 1;
                mListLocal.clear();
                mPresenter.getLocalList(mToken, mCurrentLocalPage);
                break;
        }
        clearAt();
        if (isLocalFinished) {
            isLocalFinished = false;
            mListView.setHasMore();
        }
    }

    /**
     * 切换列表
     */
    public void changerDataList() {
        mDataAdapter.setData(mListCountry, mListLocal, mCurrentCheckType);
        mDataAdapter.notifyDataSetChanged();
    }

    public void getData() {
        if (mCurrentCheckType == 0) {
            if (mCurrentCountryPage * Constants.PAGE_NUM > mTotalCountry + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getCountryList(mToken, mCurrentCountryPage);
        } else {
            if (mCurrentLocalPage * Constants.PAGE_NUM > mTotalLocal + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getLocalList(mToken, mCurrentLocalPage);
        }
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }
}
