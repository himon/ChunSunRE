package com.chunsun.redenvelope.ui.activity.red.web;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.context.LoginContext;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.event.RewardEvent;
import com.chunsun.redenvelope.event.WebRedDetailEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.WebRedDetailCommentPresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.ui.adapter.WebRedDetailCommentAdapter;
import com.chunsun.redenvelope.ui.base.activity.at.BaseAtActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IWebRedDetailCommentView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 链接红包评论界面
 */
public class WebRedDetailCommentActivity extends BaseAtActivity<IWebRedDetailCommentView, WebRedDetailCommentPresenter> implements IWebRedDetailCommentView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.et_comment)
    EditText mEtComment;
    @Bind(R.id.btn_send_comment)
    Button mBtnSendComment;
    @Bind(R.id.ll_comment_input_container)
    LinearLayout mLLInputComment;
    RadioButton mRbCommentRecord;
    RadioButton mRbGetRedRecord;

    private WebRedDetailCommentPresenter mPresenter;
    private WebRedDetailCommentAdapter mDataAdapter;
    //评论列表
    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment = new ArrayList<RedDetailCommentEntity.ResultEntity.ListEntity>();
    //领取记录列表
    private List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> mListRedRecord = new ArrayList<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity>();
    //是否是下拉刷新
    private boolean isRefresh;
    //当前评论显示页数
    private int mCurrentCommentPage = 1;
    //当前领取记录显示页数
    private int mCurrentGetPage = 1;
    //评论列表总数
    private int mTotalComment = 0;
    //领取记录列表总数
    private int mTotalGetRedRecord = 0;
    //标示当前列表显示的是评论还是领取记录， 0 ： 评论， 1 ： 领取记录
    private int mCurrentCheckType = 0;

    private String mToken;
    private String mRedId;

    private boolean isCommentFinished;
    private boolean isRecordtFinished;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_red_detail_comment);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = (WebRedDetailCommentPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new WebRedDetailCommentPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_web_red_detail_comment, null);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mRbGetRedRecord = (RadioButton) view.findViewById(R.id.rb_get_red_record);

        mListView.addHeaderView(view);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
                isCommentFinished = false;
                isRecordtFinished = false;
                getAllData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        initEvent();
    }

    private void initEvent() {
        deleteAt();
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mRbCommentRecord.setOnClickListener(this);
        mRbGetRedRecord.setOnClickListener(this);

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
            mRedId = intent.getStringExtra(Constants.EXTRA_KEY);
            mType = intent.getIntExtra(Constants.EXTRA_KEY2, Constants.RED_DETAIL_TYPE_LINK);
            BigDecimal shareMinAmount = (BigDecimal) intent.getSerializableExtra(Constants.EXTRA_KEY3);
            if (Constants.RED_DETAIL_TYPE_CIRCLE_LINK == mType) {
                mRbGetRedRecord.setVisibility(View.GONE);
            }
            mDataAdapter = new WebRedDetailCommentAdapter(this, mType, shareMinAmount, mListComment, mListRedRecord, mCurrentCheckType, mHeadPortraitOnClickListener, mHeadPortraitOnLongClickListener);
            mListView.setAdapter(mDataAdapter);
        }
    }

    private void getAllData() {
        if (isRefresh) {
            mCurrentCommentPage = 1;
            mListComment.clear();
            mCurrentGetPage = 1;
            mListRedRecord.clear();
            //mCurrentCheckType = 0;
        }
        mPresenter.getCommentList(mRedId, mCurrentCommentPage);
        mPresenter.getRedRecordList(mRedId, mCurrentGetPage);
    }

    public void getData() {
        if (mCurrentCheckType == 0) {
            if (mCurrentCommentPage * Constants.PAGE_NUM > mTotalComment + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getCommentList(mRedId, mCurrentCommentPage);
        } else {
            if (mCurrentGetPage * Constants.PAGE_NUM > mTotalGetRedRecord + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getRedRecordList(mRedId, mCurrentGetPage);
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.rb_comment_record:
                mCurrentCheckType = 0;
                mLLInputComment.setVisibility(View.VISIBLE);
                if (isCommentFinished) {
                    mListView.setNoMore();
                } else {
                    mListView.setHasMore();
                }
                changerDataList();
                break;
            case R.id.rb_get_red_record:
                mCurrentCheckType = 1;
                mLLInputComment.setVisibility(View.GONE);
                if (isRecordtFinished) {
                    mListView.setNoMore();
                } else {
                    mListView.setHasMore();
                }
                changerDataList();
                break;
            case R.id.btn_send_comment:
                if (LoginContext.getLoginContext().comment(this, Constants.FROM_COMMENT)) {
                    mPresenter.sendComment(StringUtil.textview2String(mEtComment), mToken, mRedId, at);
                }
                break;
        }
    }

    /**
     * 切换列表
     */
    public void changerDataList() {
        mDataAdapter.setData(mListComment, mListRedRecord, mCurrentCheckType);
        mDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCommentData(RedDetailCommentEntity.ResultEntity result) {
        List<RedDetailCommentEntity.ResultEntity.ListEntity> list = result.getList();
        mTotalComment = Integer.parseInt(result.getTotal_count());
        mRbCommentRecord.setText("评论（" + mTotalComment + "）");
        if (mCurrentCheckType == 0 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            if (mCurrentCheckType == 0) {
                mListView.setNoMore();
            }
            isCommentFinished = true;
        }
        mCurrentCommentPage++;
        mListComment.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();

        //更新web页面的评论数量
        WebRedDetailEvent add = new WebRedDetailEvent("add");
        add.setContent(result.getTotal_count());
        EventBus.getDefault().post(add);
    }

    @Override
    public void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result) {
        List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> list = result.getRecords();
        mTotalGetRedRecord = result.getTotal_count();

        if (mCurrentCheckType == 1 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            if (mCurrentCheckType == 1) {
                mListView.setNoMore();
            }
            isRecordtFinished = true;
        }

        mCurrentGetPage++;
        mListRedRecord.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
    }

    @Override
    public void commentSuccess() {
        mEtComment.setText("");
        mCurrentCommentPage = 1;
        mListComment.clear();
        mPresenter.getCommentList(mRedId, mCurrentCommentPage);
        clearAt();
    }

    @Override
    public void userNoEmpty() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_WELCOME);
        startActivity(intent);
    }

    @Override
    protected void toUserRewardActivity(String id) {
        Intent intent = new Intent(this, UserRewardActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        intent.putExtra(Constants.EXTRA_KEY2, mRedId);
        startActivity(intent);
    }

    /**
     * 刷新评论列表
     */
    private void refreshCommentList() {
        mCurrentCommentPage = 1;
        mListComment.clear();
        mPresenter.getCommentList(mRedId, mCurrentCommentPage);
    }

    public void onEvent(RewardEvent event) {
        refreshCommentList();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
