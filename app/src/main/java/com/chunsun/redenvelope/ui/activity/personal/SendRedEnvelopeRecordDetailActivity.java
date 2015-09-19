package com.chunsun.redenvelope.ui.activity.personal;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.SendRedEnvelopeRecordDetailPresenter;
import com.chunsun.redenvelope.ui.adapter.RedDetailFragmentAdapter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordDetailView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.GetMoreListView;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
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
 * 发广告记录详情Activity
 */
public class SendRedEnvelopeRecordDetailActivity extends BaseActivity implements ISendRedEnvelopeRecordDetailView, View.OnClickListener {

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

    private ImageView mIvHead;
    private TextView mTvTitle;
    private TextView mTvUserName;
    private ImageView mIvCompanyIcon;
    private TextView mTvTime;
    private GuideGallery mViewPager;
    private TextView mTvContent;
    private RadioGroup mRgRecord;
    private RadioButton mRbCommentRecord;
    private RadioButton mRbGetRedRecord;
    private LinearLayout mLLButtonPay;
    private Button mBtnPay;
    private TextView mTvRange;
    private TextView mTvType;
    private TextView mTvTotalNum;
    private TextView mTvTotalMoney;
    private TextView mTvNotReceiveNum;
    private TextView mTvReceiveNum;
    private View mRedLine;
    private RelativeLayout mRlClassifyStatus;
    private TextView mTvClassifyStatus;

    private RedDetailFragmentAdapter mDataAdapter;
    //轮播图adapter
    private ImageAdapter mAdapter;
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
    //红包id
    private String mRedEnvelopeId;
    //红包状态
    private String mClassify;
    private String mClassifyValue;

    private SendRedEnvelopeRecordDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_red_envelope_record_detail);
        ButterKnife.bind(this);
        mPresenter = new SendRedEnvelopeRecordDetailPresenter(this);
        initView();
        initData();
    }

    private void initTitle() {
        mNavIcon.setVisibility(View.VISIBLE);
        mNavIcon.setImageResource(R.drawable.img_back);
        mNavLeft.setText("返回");
        mNavLeft.setVisibility(View.VISIBLE);
        mNavTitle.setText("春笋红包");
    }

    @Override
    protected void initView() {
        initTitle();

        View view = LayoutInflater.from(this).inflate(R.layout.layout_send_record_red_detail_item, null);
        mIvHead = (ImageView) view.findViewById(R.id.iv_head_logo);
        mTvTitle = (TextView) view.findViewById(R.id.tv_red_title);
        mTvUserName = (TextView) view.findViewById(R.id.tv_red_name);
        mIvCompanyIcon = (ImageView) view.findViewById(R.id.iv_company_v);
        mTvTime = (TextView) view.findViewById(R.id.tv_red_time);
        mViewPager = (GuideGallery) view.findViewById(R.id.vp_pictures);
        mTvContent = (TextView) view.findViewById(R.id.tv_red_content);
        mRgRecord = (RadioGroup) view.findViewById(R.id.rg_record_type);
        mRbCommentRecord = (RadioButton) view.findViewById(R.id.rb_comment_record);
        mRbGetRedRecord = (RadioButton) view.findViewById(R.id.rb_get_red_record);
        mLLButtonPay = (LinearLayout) view.findViewById(R.id.ll_pay_red);
        mBtnPay = (Button) view.findViewById(R.id.btn_pay_red);
        mTvRange = (TextView) view.findViewById(R.id.tv_range);
        mTvType = (TextView) view.findViewById(R.id.tv_type);
        mTvTotalNum = (TextView) view.findViewById(R.id.tv_total_num);
        mTvTotalMoney = (TextView) view.findViewById(R.id.tv_total_money);
        mTvNotReceiveNum = (TextView) view.findViewById(R.id.tv_not_receive_num);
        mTvReceiveNum = (TextView) view.findViewById(R.id.tv_receive_num);
        mRedLine = view.findViewById(R.id.red_line);
        mRlClassifyStatus = (RelativeLayout) view.findViewById(R.id.rl_classify_status);
        mTvClassifyStatus = (TextView) view.findViewById(R.id.tv_classify_status);

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
        mRbCommentRecord.setOnClickListener(this);
        mRbGetRedRecord.setOnClickListener(this);
        mBtnSendComment.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
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
            mRedEnvelopeId = intent.getStringExtra(Constants.EXTRA_KEY);
            mClassify = intent.getStringExtra(Constants.EXTRA_KEY2);
            mClassifyValue = intent.getStringExtra(Constants.EXTRA_KEY3);
            initTitleBar(mClassifyValue + "广告", "", "", Constants.TITLE_TYPE_SAMPLE);
        }
    }

    private void getAllData() {
        if (isRefresh) {
            mCurrentCommentPage = 1;
            mListComment.clear();
            mCurrentGetPage = 1;
            mListRedRecord.clear();
            mCurrentCheckType = 0;
        }

        if (Constants.RED_DETAIL_STATUS_DZF.equals(mClassify)) {//未支付
            mPresenter.getRedEnvelopeDetail(mToken, mRedEnvelopeId);
            mRgRecord.setVisibility(View.GONE);
            mRedLine.setVisibility(View.GONE);
            mBtnPay.setText("支 付");
            mTvClassifyStatus.setText(mClassifyValue);
        } else if (Constants.RED_DETAIL_STATUS_YFB.equals(mClassify) || Constants.RED_DETAIL_STATUS_YQW.equals(mClassify)) {//已发布、已抢完
            mPresenter.getRedEnvelopeDetail(mToken, mRedEnvelopeId);
            mPresenter.getCommentList(mRedEnvelopeId, mCurrentCommentPage);
            mPresenter.getRedRecordList(mRedEnvelopeId, mCurrentGetPage);
            mBtnPay.setText("追 加");
            mRlClassifyStatus.setVisibility(View.GONE);
        } else if (Constants.RED_DETAIL_STATUS_YDJ.equals(mClassify) || Constants.RED_DETAIL_STATUS_WTG.equals(mClassify) || Constants.RED_DETAIL_STATUS_SHZ.equals(mClassify)) {
            mPresenter.getRedEnvelopeDetail(mToken, mRedEnvelopeId);
            mTvReceiveNum.setVisibility(View.GONE);
            mTvNotReceiveNum.setVisibility(View.GONE);
            mRgRecord.setVisibility(View.GONE);
            mLLButtonPay.setVisibility(View.GONE);
            mRedLine.setVisibility(View.GONE);
            mTvClassifyStatus.setText(mClassifyValue);
        }
    }

    public void getData() {
        if (mCurrentCheckType == 0) {
            if (mCurrentCommentPage * Constants.PAGE_NUM > mTotalComment + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getCommentList(mRedEnvelopeId, mCurrentCommentPage);
        } else {
            if (mCurrentGetPage * Constants.PAGE_NUM > mTotalGetRedRecord + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getRedRecordList(mRedEnvelopeId, mCurrentCommentPage);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_pay_red:
                if (Constants.RED_DETAIL_STATUS_DZF.equals(mClassify)) {
                    mPresenter.onException("");
                } else if (Constants.RED_DETAIL_STATUS_YFB.equals(mClassify) || Constants.RED_DETAIL_STATUS_YQW.equals(mClassify)) {//已发布、已抢完
                    mPresenter.superaddition(mRedEnvelopeId);
                }
                break;
        }
    }

    @Override
    public void setRedEnvelopeDetail(ArrayList<String> list, RedDetailEntity.ResultEntity.DetailEntity detail) {
        mTvTitle.setText(detail.getTitle());
        mTvUserName.setText(detail.getNick_name());
        mTvTime.setText(detail.getSend_time());
        mTvContent.setText(detail.getContent());
        ImageLoader.getInstance().displayImage(detail.getU_img_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(list, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.startAutoScroll();

        mTvRange.setText(detail.getRange());
        mTvType.setText(detail.getType_title());
        mTvTotalNum.setText(detail.getTotal_count());
        mTvTotalMoney.setText(detail.getTotal_amount());
        String num = detail.getHas_open_count();
        mTvNotReceiveNum.setText(Integer.parseInt(detail.getTotal_count()) - Integer.parseInt(num) + "");
        mTvReceiveNum.setText(num);
        mPtr.refreshComplete();
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
    }

    @Override
    public void setGetRedRecord(RedDetailGetRedRecordEntity.ResultEntity result) {
        List<RedDetailGetRedRecordEntity.ResultEntity.RecordsEntity> list = result.getRecords();
        mTotalGetRedRecord = result.getTotal_count();

        if (mCurrentCheckType == 1 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        }

        mCurrentGetPage++;
        mListRedRecord.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
    }

    @Override
    public void getSuperaddition(RedSuperadditionEntity entity) {
        RedSuperadditionEntity.ResultEntity result = entity.getResult();
        MainEvent mainEvent = new MainEvent(Constants.SUPERADDITION_AD);
        mainEvent.setEntity(result);
        EventBus.getDefault().post(mainEvent);
        back();
        AppManager.getAppManager().finishActivity(SendRedEnvelopeRecordListActivity.class);
        AppManager.getAppManager().finishActivity(SendRedEnvelopeRecordClassifyActivity.class);
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
    }
}
