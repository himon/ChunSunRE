package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.RewardEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.MyCircleListDetailPresenter;
import com.chunsun.redenvelope.ui.adapter.RedDetailFragmentAdapter;
import com.chunsun.redenvelope.ui.base.activity.at.BaseAtActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IMyCircleListDetailView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyCircleListDetailActivity extends BaseAtActivity<IMyCircleListDetailView, MyCircleListDetailPresenter> implements IMyCircleListDetailView {

    @Bind(R.id.iv_nav_icon)
    ImageView mNavIcon;
    @Bind(R.id.tv_nav_left)
    TextView mNavLeft;
    @Bind(R.id.tv_nav_mid)
    TextView mNavTitle;
    @Bind(R.id.ib_nav_right)
    ImageButton mIbRepeat;
    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
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
    RelativeLayout mLLGuarantee;
    RelativeLayout mRlRedEnvelope;
    RadioButton mRbCommentRecord;
    RadioButton mRbGetRedRecord;
    TextView mTvEffectiveDate;

    //轮播图adapter
    private ImageAdapter mAdapter;
    private RedDetailFragmentAdapter mDataAdapter;
    private MyCircleListDetailPresenter mPresenter;

    //红包id
    private String mRedDetailId;
    //红包信息
    private RedDetailEntity.ResultEntity.DetailEntity mDetail;
    //图片的路径
    private ArrayList mUrls;
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
    //评论列表
    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment = new ArrayList<>();
    //领取记录列表
    private List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> mListRedRecord = new ArrayList<>();
    private String mToken;
    /**
     * 回复@用户的id
     */
    private String at = "0";
    /**
     * 红包帮助类
     */
    RedDetailHelper mRedDetailHelper;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle_list_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = (MyCircleListDetailPresenter) mMPresenter;
        mRedDetailHelper = new RedDetailHelper(this);
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MyCircleListDetailPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("春笋圈子", "", "", Constants.TITLE_TYPE_SAMPLE);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_red_detail_item, null);
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
        mLLGuarantee = (RelativeLayout) view.findViewById(R.id.ll_guarantee);
        mRlRedEnvelope = (RelativeLayout) view.findViewById(R.id.rl_red_envelope);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mRbGetRedRecord = (RadioButton) view.findViewById(R.id.rb_get_red_record);
        mTvEffectiveDate = (TextView) view.findViewById(R.id.tv_effective_date);

        mLLGuarantee.setVisibility(View.GONE);
        mRlRedEnvelope.setVisibility(View.GONE);
        mRbGetRedRecord.setVisibility(View.GONE);

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
        mDataAdapter = new RedDetailFragmentAdapter(this, 7, mListComment, mListRedRecord, mCurrentCheckType, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head_logo:
                        Object tag = v.getTag();
                        toUserRewardActivity(tag.toString());
                        break;
                }
            }
        }, mHeadPortraitOnLongClickListener);

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
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mIbRepeat.setOnClickListener(this);
        mIvHead.setOnClickListener(this);
        mRbCommentRecord.setOnClickListener(this);
        mRbGetRedRecord.setOnClickListener(this);
        mLLCollect.setOnClickListener(this);
        mLLComplaint.setOnClickListener(this);
        mBtnSendComment.setOnClickListener(this);
        //设置发送按钮不可点击
        mBtnSendComment.setEnabled(false);
        mIvHead.setOnLongClickListener(mHeadPortraitOnLongClickListener);

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
            mRedDetailId = intent.getStringExtra(Constants.EXTRA_KEY);
            mType = intent.getIntExtra(Constants.EXTRA_KEY2, 0);
            if (mType == Constants.RED_DETAIL_TYPE_LEFT || mType == Constants.RED_DETAIL_TYPE_NEAR || mType == Constants.RED_DETAIL_TYPE_COUPON || mType == Constants.RED_DETAIL_TYPE_lUCK || mType == Constants.RED_DETAIL_TYPE_COUPON) {
                mNavTitle.setText("春笋红包");
                mRbGetRedRecord.setVisibility(View.VISIBLE);
            }
        }
        mPresenter.getData(mToken, mRedDetailId);
    }

    private void getAllData() {
        if (isRefresh) {
            mCurrentCommentPage = 1;
            mListComment.clear();
            mListRedRecord.clear();
            mCurrentCheckType = 0;
        }
        mPresenter.getCommentList(mRedDetailId, mCurrentCommentPage);
        mPresenter.getRedRecordList(mRedDetailId, mCurrentGetPage);
    }

    private void setRedEnvelopeStatus() {
        if (Constants.RED_DETAIL_TYPE_COUPON == mType) {
            mTvEffectiveDate.setVisibility(View.VISIBLE);
            mTvEffectiveDate.setVisibility(View.VISIBLE);
            mTvEffectiveDate.setText("有效期：" + mDetail.getStart_time() + " -- "
                    + mDetail.getEnd_time());
        }
    }

    public void getData() {
        if (mCurrentCheckType == 0) {
            if (mCurrentCommentPage * Constants.PAGE_NUM > mTotalComment + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getCommentList(mDetail.getId(), ++mCurrentCommentPage);
        } else {
            if (mCurrentGetPage * Constants.PAGE_NUM > mTotalGetRedRecord + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getRedRecordList(mDetail.getId(), mCurrentGetPage);
        }
    }

    @Override
    public void setData(ArrayList<String> urls, RedDetailEntity.ResultEntity.DetailEntity detail) {
        mUrls = urls;
        mDetail = detail;
        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(mDetail.getNick_name());
        mTvTime.setText(mDetail.getSend_time());
        mRedDetailHelper.webViewSetText(mWvContent, mDetail.getContent());
        mIvHead.setTag(R.id.tag_first, detail.getUser_id());
        mIvHead.setTag(R.id.tag_second, detail.getNick_name());
        ImageLoader.getInstance().displayImage(mDetail.getU_img_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(mUrls, this);
        mViewPager.setAdapter(mAdapter);
        setRedEnvelopeStatus();
    }

    @Override
    public void setCommentData(RedDetailCommentEntity.ResultEntity result) {
        List<RedDetailCommentEntity.ResultEntity.ListEntity> list = result.getList();
        mTotalComment = Integer.parseInt(result.getTotal_count());
        mRbCommentRecord.setText("评论（" + mTotalComment + "）");
        if (mCurrentCheckType == 0 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        }
        if (mCurrentCommentPage == 1) {
            mListComment.clear();
        }
        mListComment.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void toUserRewardActivity(String id) {
        mRedDetailHelper.toUserRewardActivity(id, mDetail.getId());
    }

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
    public void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result) {
        BigDecimal share_min_amount = result.getShare_min_amount();
        mDataAdapter.setShareMinAmount(share_min_amount);

        List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> list = result.getRecords();
        mTotalGetRedRecord = result.getTotal_count();

        if (mCurrentCheckType == 1 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        } else {
            mListView.setHasMore();
        }

        mCurrentGetPage++;
        mListRedRecord.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
    }

    /**
     * 开启轮播图自动滚动
     */
    public void startAutoScroll() {
        mViewPager.startAutoScroll();
    }

    /**
     * 关闭轮播图自动滚动
     */
    public void stopAutoScroll() {
        mViewPager.stopAutoScroll();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.iv_head_logo:
                toUserRewardActivity(mDetail.getUser_id());
                break;
            case R.id.ll_collect_container://收藏
                mPresenter.favorite(mToken, mDetail.getId());
                break;
            case R.id.ll_red_complaint://举报
                toComplaintActivity();
                break;
            case R.id.btn_send_comment://评论
                mPresenter.sendComment(StringUtil.textview2String(mEtComment), mToken, mDetail.getId(), at);
                break;
            case R.id.rb_comment_record:
                mCurrentCheckType = 0;
                mLLInputComment.setVisibility(View.VISIBLE);
                changerDataList();
                break;
            case R.id.rb_get_red_record:
                mCurrentCheckType = 1;
                mLLInputComment.setVisibility(View.GONE);
                changerDataList();
                break;
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

    /**
     * 切换列表
     */
    public void changerDataList() {
        mDataAdapter.setData(mListComment, mListRedRecord, mCurrentCheckType);
        mDataAdapter.notifyDataSetChanged();
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
