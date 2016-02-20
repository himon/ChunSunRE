package com.chunsun.redenvelope.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.event.MeFragmentRefreshEvent;
import com.chunsun.redenvelope.event.RedDetailBackEvent;
import com.chunsun.redenvelope.event.RedDetailEvent;
import com.chunsun.redenvelope.event.RewardEvent;
import com.chunsun.redenvelope.event.ShareRedEnvelopeEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.RedDetailFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.adapter.RedDetailFragmentAdapter;
import com.chunsun.redenvelope.ui.base.fragment.BaseAtFragment;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IRedDetailFragmentView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.chunsun.redenvelope.widget.popupwindow.ShareRedEnvelopePopupWindow;
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

/**
 * A simple {@link Fragment} subclass.
 * 红包详情Fragment
 */
public class RedDetailFragment extends BaseAtFragment<IRedDetailFragmentView, RedDetailFragmentPresenter> implements View.OnClickListener, IRedDetailFragmentView {

    @Bind(R.id.main)
    LinearLayout mMain;
    @Bind(R.id.iv_nav_icon)
    ImageView mNavIcon;
    @Bind(R.id.tv_nav_left)
    TextView mNavLeft;
    @Bind(R.id.tv_nav_mid)
    TextView mNavTitle;
    @Bind(R.id.rl_nav_right)
    RelativeLayout mRlNavRight;
    @Bind(R.id.ib_nav_right)
    ImageButton mIbRepeat;
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
    RelativeLayout mLLGuarantee;
    Button mBtnOpenRed;
    TextView mTvRedPrice;
    RadioButton mRbCommentRecord;
    RadioButton mRbGetRedRecord;
    TextView mTvEffectiveDate;

    //轮播图adapter
    private ImageAdapter mAdapter;
    private RedDetailFragmentAdapter mDataAdapter;
    private RedDetailFragmentPresenter mPresenter;
    //activity传递过来的红包信息
    private RedDetailEntity.ResultEntity.DetailEntity mDetail;
    //分享的限制信息
    //private ShareLimitEntity.ResultEntity mShareLimitResult;
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
    private List<RedDetailCommentEntity.ResultEntity.ListEntity> mListComment = new ArrayList<RedDetailCommentEntity.ResultEntity.ListEntity>();
    //领取记录列表
    private List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> mListRedRecord = new ArrayList<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity>();
    private String mToken;
    //是否倒计时
    private boolean isCountDown;

    /**
     * grab信息
     */
    private GrabEntity mGrabEntity;
    /**
     * 红包帮助类
     */
    RedDetailHelper mRedDetailHelper;

    public RedDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_red_detail, container, false);
        ButterKnife.bind(this, view);
        mPresenter = (RedDetailFragmentPresenter) mMPresenter;
        mRedDetailHelper = new RedDetailHelper(getActivity());
        EventBus.getDefault().register(this);
        initView();
        initData();
        return view;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new RedDetailFragmentPresenter(this);
    }

    private void initTitle() {
        mNavIcon.setVisibility(View.VISIBLE);
        mNavIcon.setImageResource(R.drawable.img_back);
        mNavLeft.setText("返回");
        mNavLeft.setVisibility(View.VISIBLE);
        mNavTitle.setText("春笋红包");
        mIbRepeat.setVisibility(View.VISIBLE);
        mIbRepeat.setBackgroundResource(R.drawable.img_share_icon);
    }

    @Override
    protected void initView() {
        initTitle();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_red_detail_item, null);
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
        mBtnOpenRed = (Button) view.findViewById(R.id.btn_open_red);
        mTvRedPrice = (TextView) view.findViewById(R.id.tv_red_price);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mRbGetRedRecord = (RadioButton) view.findViewById(R.id.rb_get_red_record);
        mTvEffectiveDate = (TextView) view.findViewById(R.id.tv_effective_date);

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
        mRlNavRight.setOnClickListener(this);
        mIbRepeat.setOnClickListener(this);
        mIvHead.setOnClickListener(this);
        mRbCommentRecord.setOnClickListener(this);
        mRbGetRedRecord.setOnClickListener(this);
        mLLCollect.setOnClickListener(this);
        mLLComplaint.setOnClickListener(this);
        mLLGuarantee.setOnClickListener(this);
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
        mToken = new Preferences(getActivity()).getToken();

        Bundle bundle = getArguments();
        mDetail = bundle.getParcelable(Constants.EXTRA_KEY);
        mUrls = bundle.getStringArrayList(Constants.EXTRA_KEY2);

        mDataAdapter = new RedDetailFragmentAdapter(getActivity(), mDetail.getHb_type(), mListComment, mListRedRecord, mCurrentCheckType, mHeadPortraitOnClickListener, mHeadPortraitOnLongClickListener);
        mListView.setAdapter(mDataAdapter);

        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(mDetail.getNick_name());
        //判断是否是代理
        if (mDetail.is_v()) {
            mIvCompanyIcon.setVisibility(View.VISIBLE);
        } else {
            mIvCompanyIcon.setVisibility(View.GONE);
        }
        //判断是否是担保交易
        if (mDetail.is_danbao()) {
            mLLGuarantee.setVisibility(View.VISIBLE);
        } else {
            mLLGuarantee.setVisibility(View.GONE);
        }
        mTvTime.setText(mDetail.getSend_time());
        mRedDetailHelper.webViewSetText(mWvContent, mDetail.getContent());
        mIvHead.setTag(R.id.tag_first, mDetail.getUser_id());
        mIvHead.setTag(R.id.tag_second, mDetail.getNick_name());
        ImageLoader.getInstance().displayImage(mDetail.getU_img_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(mUrls, getActivity());
        mViewPager.setAdapter(mAdapter);

        mPresenter.getGrabByToken(mToken, mDetail.getId());
    }

    private void getAllData() {
        if (isRefresh) {
            mCurrentCommentPage = 1;
            mListComment.clear();
            mCurrentGetPage = 1;
            mListRedRecord.clear();
        }
        mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);
        mPresenter.getRedRecordList(mDetail.getId(), mCurrentGetPage);

        setRedEnvelopeStatus();
    }

    /**
     * 开启倒计时
     */
    public void countDown() {
        if (!isCountDown) {
            mPresenter.countDown(mDetail.getDelay_seconds());
            isCountDown = true;
        }
    }

    /**
     * 显示红包状态
     */
    private void setRedEnvelopeStatus() {

        if (mDetail.getHb_type() == Constants.RED_DETAIL_TYPE_COUPON) {
            mTvRedPrice.setText("红包礼券");
        } else {
            mTvRedPrice.setText("现金红包");
        }
        if (mDetail.isGrab_status()) {
            if (mDetail.is_open()) {
                mBtnOpenRed.setText("您已经领取红包");
                mBtnOpenRed.setTextColor(getResources().getColor(R.color.font_white));
                //mTvRedPrice.setText(mDetail.getPrice() + "元");
            } else {
                countDown();
            }
        } else {
            if (!mDetail.isHb_status()) {
                mBtnOpenRed.setText("红包已领空");
                mBtnOpenRed.setTextColor(getResources().getColor(R.color.font_white));
                mBtnOpenRed.setEnabled(false);
                //mTvRedPrice.setText(mDetail.getPrice() + "元");
                mTvRedPrice.setTextColor(getResources().getColor(R.color.font_gray_red_envelope));
            } else {
                countDown();
            }
        }

        if (mDetail.isFavorite_status()) {
            mIvCollect.setImageResource(R.drawable.img_collect_select);
        } else {
            mIvCollect.setImageResource(R.drawable.img_collect_normal);
        }

        if ((Constants.RED_DETAIL_TYPE_COUPON + "").equals(mDetail.getHb_type())) {
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
            mPresenter.getCommentList(mDetail.getId(), mCurrentCommentPage);
        } else {
            if (mCurrentGetPage * Constants.PAGE_NUM > mTotalGetRedRecord + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getRedRecordList(mDetail.getId(), mCurrentGetPage);
        }
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

    /**
     * 切换列表
     */
    public void changerDataList() {
        mDataAdapter.setData(mListComment, mListRedRecord, mCurrentCheckType);
        mDataAdapter.notifyDataSetChanged();
    }


    private void refreshDelaySeconds(int delaySeconds) {
        if (delaySeconds > 0) {
            mBtnOpenRed.setText(delaySeconds + "秒后可领取红包");
        } else {
            mTvRedPrice.setText("点击领取");
            if (mGrabEntity != null && !"0.00".equals(mGrabEntity.getResult().getHb_price())) {
                mBtnOpenRed.setText(mGrabEntity.getResult().getHb_price() + "元");
            } else {
                mBtnOpenRed.setText("祝下次好运！");
                mTvRedPrice.setText("谢谢阅读");
            }
            mBtnOpenRed.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                EventBus.getDefault().post(new RedDetailBackEvent(""));
                break;
            case R.id.ib_nav_right:
            case R.id.rl_nav_right:
                mViewBg.setVisibility(View.VISIBLE);
                ShareRedEnvelopePopupWindow noRewardMenuWindow = new ShareRedEnvelopePopupWindow(getActivity(), mDetail, mGrabEntity);
                noRewardMenuWindow.showAtLocation(mMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.iv_head_logo:
                toUserRewardActivity(mDetail.getUser_id());
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
            case R.id.btn_open_red://打开红包
                if (mGrabEntity != null && !"0.00".equals(mGrabEntity.getResult().getHb_price())) {
                    mViewBg.setVisibility(View.VISIBLE);
                    if ("0".equals(mGrabEntity.getResult().getHb_grab_id())) {
                        return;
                    }
                    ShareRedEnvelopePopupWindow menuWindow = new ShareRedEnvelopePopupWindow(getActivity(), mDetail, mGrabEntity, Constants.SHARE_FROM_RED);
                    menuWindow.showAtLocation(mMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    EventBus.getDefault().post(new RedDetailBackEvent(""));
                }
                break;
            case R.id.ll_collect_container://收藏
                mPresenter.favorite(mToken, mDetail.getId());
                break;
            case R.id.ll_red_complaint://举报
                toComplaintActivity();
                break;
            case R.id.ll_guarantee://担保交易
                toGuaranteeActivity();
                break;
            case R.id.btn_send_comment://评论
                mPresenter.sendComment(StringUtil.textview2String(mEtComment), mToken, mDetail.getId(), at);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
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

    @Override
    public void setFavoriteSuccess(SampleResponseEntity entity) {
        mRedDetailHelper.setFavoriteSuccess(entity, mIvCollect, true);
    }

    @Override
    public void toComplaintActivity() {
        mRedDetailHelper.toComplaintActivity(mDetail.getId());
    }

    @Override
    public void toGuaranteeActivity() {
        mRedDetailHelper.toGuaranteeActivity();
    }

    @Override
    public void commentSuccess() {
        refreshCommentList();
        clearAt();
    }

    @Override
    public void userIsEmpty() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_WELCOME);
        startActivity(intent);
    }

    @Override
    public void shareSuccess() {
        //刷新余额
        EventBus.getDefault().post(new MeFragmentRefreshEvent(""));
        //关闭Activity
        EventBus.getDefault().post(new RedDetailBackEvent(""));
    }

    @Override
    public void setGrab(GrabEntity entity) {
        mGrabEntity = entity;
        if (mGrabEntity != null && mGrabEntity.isSuccess()) {
            if ("0.00".equals(entity.getResult().getHb_price())) {
                if (mDetail.getHb_type() == Constants.RED_DETAIL_TYPE_COUPON) {
                    mPresenter.createChunsunTicket(mToken, mGrabEntity.getResult().getHb_grab_id());
                }
            }
        } else {
            mIbRepeat.setVisibility(View.GONE);
        }
    }

    /**
     * 跳转用户奖励页面
     */
    @Override
    public void toUserRewardActivity(String id) {
        mRedDetailHelper.toUserRewardActivity(id, mDetail.getId());
    }


    public void onEventMainThread(RedDetailEvent event) {
        if (TextUtils.isEmpty(event.getMsg())) {//倒计时
            refreshDelaySeconds(event.getSecond());
        } else if ("share".equals(event.getMsg())) {//分享
            mPresenter.shareOpen(mToken, mGrabEntity.getResult().getHb_grab_id(), event.getContent());
        } else if ("no_share".equals(event.getMsg())) {//直接领钱
            mPresenter.justOpen(mToken, mGrabEntity.getResult().getHb_grab_id());
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

    public void onEvent(ShareRedEnvelopeEvent event) {
        mViewBg.setVisibility(View.GONE);
    }

    public void onEvent(RewardEvent event) {
        refreshCommentList();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mPresenter.setLoop(false);
        super.onDestroy();
    }

}
