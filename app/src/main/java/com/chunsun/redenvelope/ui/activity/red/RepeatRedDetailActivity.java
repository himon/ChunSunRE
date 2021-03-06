package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.callback.GetRepeatHostCallback;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RepeatRedEnvelopeGetHostEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.RewardEvent;
import com.chunsun.redenvelope.event.ShareRedEnvelopeEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.RepeatRedDetailPresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.adapter.RepeatRedDetailAdapter;
import com.chunsun.redenvelope.ui.base.activity.at.BaseSwipeBackAtActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IRepeatRedDetailView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.chunsun.redenvelope.widget.popupwindow.ShareRedEnvelopePopupWindow;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 转发红包详情
 */
public class RepeatRedDetailActivity extends BaseSwipeBackAtActivity<IRepeatRedDetailView, RepeatRedDetailPresenter> implements IRepeatRedDetailView {

    @Bind(R.id.main)
    LinearLayout mMain;
    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.view_bg)
    View mViewBg;
    @Bind(R.id.ll_comment_input_container)
    LinearLayout mLLInputComment;
    @Bind(R.id.et_comment)
    EditText mEtComment;
    @Bind(R.id.btn_send_comment)
    Button mBtnSendComment;
    ImageView mIvHead;
    TextView mTvTitle;
    TextView mTvUserName;
    ImageView mIvCompanyIcon;
    TextView mTvTime;
    GuideGallery mViewPager;
    WebView mWvContent;
    LinearLayout mLLCollect;
    ImageView mIvCollect;
    LinearLayout mLLComplaint;
    RadioButton mRbCommentRecord;
    LinearLayout mLLRepeat;
    TextView mTvPrice;
    TextView mTvRestNum;

    //是否是下拉刷新
    private boolean isRefresh;
    //轮播图adapter
    private ImageAdapter mAdapter;
    private RepeatRedDetailAdapter mDataAdapter;
    private RepeatRedDetailPresenter mPresenter;
    //评论列表
    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment = new ArrayList<RedDetailCommentEntity.ResultEntity.ListEntity>();
    //当前评论显示页数
    private int mCurrentCommentPage = 1;
    //评论列表总数
    private int mTotalComment = 0;
    private String mToken;
    private String mRedId;

    private RedDetailEntity.ResultEntity.DetailEntity mDetail;
    private ArrayList<String> mUrls;
    private ShareRedEnvelopePopupWindow mMenuWindow;

    /**
     * 红包帮助类
     */
    private RedDetailHelper mRedDetailHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_red_detail);
        ButterKnife.bind(this);
        mPresenter = (RepeatRedDetailPresenter) mMPresenter;
        EventBus.getDefault().register(this);
        mRedDetailHelper = new RedDetailHelper(this);
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new RepeatRedDetailPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_repeat_red_detail_item, null);
        mIvHead = (ImageView) view.findViewById(R.id.iv_head_logo);
        mTvTitle = (TextView) view.findViewById(R.id.tv_red_title);
        mTvUserName = (TextView) view.findViewById(R.id.tv_red_name);
        mIvCompanyIcon = (ImageView) view.findViewById(R.id.iv_company_v);
        mTvTime = (TextView) view.findViewById(R.id.tv_red_time);
        mViewPager = (GuideGallery) view.findViewById(R.id.vp_pictures);
        mWvContent = (WebView) view.findViewById(R.id.wv_red_content);
        mLLCollect = (LinearLayout) view.findViewById(R.id.ll_collect_container);
        mIvCollect = (ImageView) view.findViewById(R.id.iv_collect);
        mLLComplaint = (LinearLayout) view.findViewById(R.id.ll_red_complaint);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mLLRepeat = (LinearLayout) view.findViewById(R.id.ll_repeat);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvRestNum = (TextView) view.findViewById(R.id.tv_rest_num);

        /**
         * ListView
         */
        mListView.addHeaderView(view);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                //getData();
            }
        });
        mDataAdapter = new RepeatRedDetailAdapter(this, mListComment, R.layout.adapter_red_detail_comment_item, mHeadPortraitOnClickListener, mHeadPortraitOnLongClickListener);
        mListView.setAdapter(mDataAdapter);

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
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
        mIvHead.setOnClickListener(this);
        mLLCollect.setOnClickListener(this);
        mLLComplaint.setOnClickListener(this);
        mBtnSendComment.setOnClickListener(this);
        mLLRepeat.setOnClickListener(this);
        mIvHead.setOnLongClickListener(mHeadPortraitOnLongClickListener);

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

        mViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRedDetailHelper.toPicShowActivity(mUrls, position);
            }
        });
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mRedId = intent.getStringExtra(Constants.EXTRA_KEY);
        }
    }

    private void getAllData() {
        if (isRefresh) {
            mCurrentCommentPage = 1;
            mListComment.clear();
        }
        mPresenter.getRedDetail(mToken, mRedId);
    }

    /**
     * 跳转用户奖励页面
     */
    @Override
    public void toUserRewardActivity(String id) {
        mRedDetailHelper.toUserRewardActivity(id, mDetail.getId());
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.ll_collect_container://收藏
                mPresenter.favorite(mToken, mDetail.getId());
                break;
            case R.id.ll_red_complaint://举报
                toComplaintActivity();
                break;
            case R.id.btn_send_comment://评论
                mPresenter.sendComment(StringUtil.textview2String(mEtComment), mToken, mDetail.getId(), at);
                break;
            case R.id.iv_head_logo:
                toUserRewardActivity(mDetail.getUser_id());
                break;
            case R.id.ll_repeat://转发
                mViewBg.setVisibility(View.VISIBLE);
                mMenuWindow = new ShareRedEnvelopePopupWindow(this, mDetail, new GetRepeatHostCallback() {

                    @Override
                    public void getHost(String platform) {
                        mPresenter.getHost(mToken, mDetail.getId(), platform, true);
                    }
                });
                mMenuWindow.showAtLocation(mMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    @Override
    public void getRedDetailSuccess(RedDetailEntity.ResultEntity.DetailEntity entity) {

        mDetail = entity;

        mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);

        mTvPrice.setText("每次点击收益：" + mDetail.getPrice() + "元");
        mTvRestNum.setText("剩余收益次数：" + mDetail.getTotal_left_count());

        if (mDetail.isFavorite_status()) {
            mIvCollect.setImageResource(R.drawable.img_collect_select);
        } else {
            mIvCollect.setImageResource(R.drawable.img_collect_normal);
        }

        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(mDetail.getNick_name());
        //判断是否是代理
        if (mDetail.is_v()) {
            mIvCompanyIcon.setVisibility(View.VISIBLE);
        } else {
            mIvCompanyIcon.setVisibility(View.GONE);
        }
        mTvTime.setText(mDetail.getSend_time());
        mRedDetailHelper.webViewSetText(mWvContent, mDetail.getContent());
        mIvHead.setTag(R.id.tag_first, entity.getUser_id());
        mIvHead.setTag(R.id.tag_second, entity.getNick_name());
        ImageLoader.getInstance().displayImage(mDetail.getU_img_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        ArrayList<String> list = new ArrayList<String>();
        list.add(Constants.HOST_URL + mDetail.getCover_img_url());

        if (!TextUtils.isEmpty(mDetail.getImg_url())) {
            String[] url = mDetail.getImg_url().split(",");
            for (String str : url) {
                if (!TextUtils.isEmpty(str)) {
                    list.add(Constants.HOST_URL + str);
                }
            }
        }

        mUrls = list;

        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(list, this);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void setCommentData(RedDetailCommentEntity.ResultEntity result) {
        List<RedDetailCommentEntity.ResultEntity.ListEntity> list = result.getList();
        mTotalComment = Integer.parseInt(result.getTotal_count());
        mRbCommentRecord.setText("评论（" + mTotalComment + "）");
        if (list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        }
        mCurrentCommentPage++;
        mListComment.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    /**
     * 跳转举报页面
     */
    @Override
    public void toComplaintActivity() {
        mRedDetailHelper.toComplaintActivity(mDetail.getId());
    }

    @Override
    public void setFavoriteSuccess(SampleResponseEntity entity) {
        mRedDetailHelper.setFavoriteSuccess(entity, mIvCollect, true);
    }

    @Override
    public void commentSuccess() {
        mEtComment.setText("");
        mCurrentCommentPage = 1;
        mListComment.clear();
        mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);
        clearAt();
    }

    @Override
    public void userIsEmpty() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_WELCOME);
        startActivity(intent);
    }

    @Override
    public void setShareHost(RepeatRedEnvelopeGetHostEntity entity) {
        if (mMenuWindow != null) {
            mMenuWindow.repeatShare(entity.getResult().getShare_url());
        }
    }

    /**
     * 刷新评论列表
     */
    private void refreshCommentList() {
        mCurrentCommentPage = 1;
        mListComment.clear();
        mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);
    }

    public void onEvent(RewardEvent event) {
        refreshCommentList();
    }

    public void onEvent(ShareRedEnvelopeEvent event) {
        mViewBg.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
