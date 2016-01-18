package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
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
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.MyCircleListDetailPresenter;
import com.chunsun.redenvelope.ui.adapter.RedDetailFragmentAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IMyCircleListDetailView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyCircleListDetailActivity extends BaseActivity implements IMyCircleListDetailView {

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
    TextView mTvContent;
    LinearLayout mLLCollect;
    ImageView mIvCollect;
    LinearLayout mLLComplaint;
    RelativeLayout mLLGuarantee;
    RelativeLayout mRlRedEnvelope;
    RadioButton mRbCommentRecord;
    RadioButton mRbGetRedRecord;

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
    //评论列表总数
    private int mTotalComment = 0;
    //标示当前列表显示的是评论还是领取记录， 0 ： 评论， 1 ： 领取记录
    private int mCurrentCheckType = 0;
    //评论列表
    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment = new ArrayList<>();
    //领取记录列表
    private List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> mListRedRecord = new ArrayList<>();
    private String mToken;
    /**
     * 红包帮助类
     */
    RedDetailHelper mRedDetailHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle_list_detail);
        ButterKnife.bind(this);
        mPresenter = new MyCircleListDetailPresenter(this);
        mRedDetailHelper = new RedDetailHelper(this);
        initView();
        initData();
    }

    private void initTitle() {
        mNavIcon.setVisibility(View.VISIBLE);
        mNavIcon.setImageResource(R.drawable.img_back);
        mNavLeft.setText("返回");
        mNavLeft.setVisibility(View.VISIBLE);
        mNavTitle.setText("春笋圈子");
    }

    @Override
    protected void initView() {
        initTitle();

        View view = LayoutInflater.from(this).inflate(R.layout.layout_red_detail_item, null);
        mIvHead = (ImageView) view.findViewById(R.id.iv_head_logo);
        mTvTitle = (TextView) view.findViewById(R.id.tv_red_title);
        mTvUserName = (TextView) view.findViewById(R.id.tv_red_name);
        mIvCompanyIcon = (ImageView) view.findViewById(R.id.iv_company_v);
        mTvTime = (TextView) view.findViewById(R.id.tv_red_time);
        mViewPager = (GuideGallery) view.findViewById(R.id.vp_pictures);
        mTvContent = (TextView) view.findViewById(R.id.tv_red_content);
        mLLCollect = (LinearLayout) view.findViewById(R.id.ll_collect_container);
        mIvCollect = (ImageView) view.findViewById(R.id.iv_collect);
        mLLComplaint = (LinearLayout) view.findViewById(R.id.ll_red_complaint);
        mLLGuarantee = (RelativeLayout) view.findViewById(R.id.ll_guarantee);
        mRlRedEnvelope = (RelativeLayout) view.findViewById(R.id.rl_red_envelope);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mRbGetRedRecord = (RadioButton) view.findViewById(R.id.rb_get_red_record);

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
        mDataAdapter = new RedDetailFragmentAdapter(this, mListComment, mListRedRecord, mCurrentCheckType, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head_logo:
                        Object tag = v.getTag();
                        //toUserRewardActivity(tag.toString());
                        break;
                }
            }
        });
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
        mLLCollect.setOnClickListener(this);
        mLLComplaint.setOnClickListener(this);
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
    }

    public void getData() {
        if (mCurrentCommentPage * Constants.PAGE_NUM > mTotalComment + Constants.PAGE_NUM) {
            mListView.getMoreComplete();
            mPtr.refreshComplete();
            return;
        }
        mPresenter.getCommentList(mRedDetailId, mCurrentCommentPage);
    }

    @Override
    public void setData(ArrayList<String> urls, RedDetailEntity.ResultEntity.DetailEntity detail) {
        mUrls = urls;
        mDetail = detail;
        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(mDetail.getNick_name());
        mTvTime.setText(mDetail.getSend_time());
        mTvContent.setText(mDetail.getContent());
        ImageLoader.getInstance().displayImage(mDetail.getU_img_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(mUrls, this);
        mViewPager.setAdapter(mAdapter);
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
        mCurrentCommentPage++;
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
        mRedDetailHelper.setFavoriteSuccess(entity, mIvCollect, false);
    }

    @Override
    public void commentSuccess() {
        mEtComment.setText("");
        mCurrentCommentPage = 1;
        mListComment.clear();
        mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);
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
                mPresenter.sendComment(StringUtil.textview2String(mEtComment), mToken, mDetail.getId());
                break;
        }
    }
}
