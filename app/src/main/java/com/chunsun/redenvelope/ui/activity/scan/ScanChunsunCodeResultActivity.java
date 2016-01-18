package com.chunsun.redenvelope.ui.activity.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.ScanCouponResultEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.ScanChunsunCodeResultPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IScanChunsunCodeResultView;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScanChunsunCodeResultActivity extends BaseActivity implements IScanChunsunCodeResultView {

    @Bind(R.id.rl_not_chunsun_quan)
    RelativeLayout mRlNotChunsunCoupon;
    @Bind(R.id.rl_not_permission)
    RelativeLayout mRlNotPermission;
    @Bind(R.id.rl_chunsun_quan_expired)
    RelativeLayout mRlChunsunCouponExpired;//过期
    @Bind(R.id.rl_chunsun_quan_lose_efficacy)
    RelativeLayout mRlChunsunCouponLoseEfficacy;//失效
    @Bind(R.id.rl_chunsun_quan_used)
    RelativeLayout mRlChunsunCouponUsed;//已使用
    @Bind(R.id.sv_scan_success)
    ScrollView mSvSuccess;
    @Bind(R.id.tv_get_quan_time)
    TextView mTvGetQuanTime;//领券日期
    @Bind(R.id.tv_quan_code)
    TextView mTvQuanCode;//消费码
    @Bind(R.id.tv_get_quan_user)
    TextView mTvGetQuanUser;//领券人
    @Bind(R.id.btn_validate_quan)
    Button mBtnQuan;//验证按钮
    @Bind(R.id.ll_not_using)
    LinearLayout mLLUsing;//验证前显示的布局
    @Bind(R.id.ll_used)
    LinearLayout mLLUsed;//验证后显示的布局
    @Bind(R.id.tv_no)
    TextView mTvNo;//流水号
    @Bind(R.id.tv_quan_user)
    TextView mTvUser;//使用者
    @Bind(R.id.tv_red_title)
    TextView mTvTitle;// 标题
    @Bind(R.id.iv_head_logo)
    ImageView mIvHeadIcon;// 头像
    @Bind(R.id.tv_red_name)
    TextView mTvName; // 昵称
    @Bind(R.id.iv_company_v)
    ImageView mIvCompanyV;// 企业用户
    @Bind(R.id.tv_red_time)
    TextView mTvTime; // 时间
    @Bind(R.id.vp_pictures)
    GuideGallery mViewPager;//轮播图
    @Bind(R.id.tv_effective_date)
    TextView mTvEffectiveDate;// 有效期
    @Bind(R.id.tv_red_content)
    TextView mTvContent;

    private ScanChunsunCodeResultPresenter mPresenter;
    //轮播图adapter
    private ImageAdapter mAdapter;
    private String mToken;
    private String mCode;
    private ScanCouponResultEntity.ResultEntity mDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_chunsun_code_result);
        ButterKnife.bind(this);
        mPresenter = new ScanChunsunCodeResultPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("扫券结果", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnQuan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mCode = intent.getStringExtra(Constants.EXTRA_KEY);
        }
        mPresenter.getTicketInfoForSeller(mToken, mCode);
    }

    @Override
    public void showErrorPage(String code) {
        if ("5001".equals(code)) {// 不是春笋券
            mRlNotChunsunCoupon.setVisibility(View.VISIBLE);
        } else if ("5002".equals(code)) {// 过期
            mRlChunsunCouponExpired.setVisibility(View.VISIBLE);
        } else if ("5003".equals(code)) {// 无权消费
            mRlNotPermission.setVisibility(View.VISIBLE);
        } else if ("5005".equals(code)) {// 已失效
            mRlChunsunCouponLoseEfficacy.setVisibility(View.VISIBLE);
        } else if ("5006".equals(code)) {// 已使用
            mRlChunsunCouponUsed.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSuccessPage(ScanCouponResultEntity.ResultEntity result) {
        mSvSuccess.setVisibility(View.VISIBLE);
        initQuanInfo(result);
    }

    @Override
    public void useCouponSuccess(String msg) {
        mLLUsing.setVisibility(View.GONE);
        mLLUsed.setVisibility(View.VISIBLE);
        mTvNo.setText("流水号：" + msg);
        mTvUser.setText("使用人：" + mDetail.getUserName());
    }

    /**
     * 显示券信息
     *
     * @param detail
     */
    private void initQuanInfo(ScanCouponResultEntity.ResultEntity detail) {

        mDetail = detail;

        mTvTitle.setText(mDetail.getTitle());
        mTvName.setText(mDetail.getSellerName());
        mTvTime.setText(mDetail.getHbAddTimeStr());
        mTvEffectiveDate.setVisibility(View.VISIBLE);
        mTvEffectiveDate.setText("有效期：" + mDetail.getStartTimeStr() + " -- "
                + mDetail.getEndTimeStr());
        mTvContent.setText(mDetail.getContent());
        if (mDetail.isV()) {
            mIvCompanyV.setVisibility(View.VISIBLE);
        } else {
            mIvCompanyV.setVisibility(View.INVISIBLE);
        }
        ImageLoader.getInstance().displayImage(mDetail.getHeadImg(),
                mIvHeadIcon, ImageLoaderHelper.getInstance(this).getDisplayOptions(8));
        mTvGetQuanTime.setText("领券日期" + mDetail.getAddTimeStr());
        mTvQuanCode.setText("消费码" + mDetail.getCode());
        mTvGetQuanUser.setText("领券人：" + mDetail.getUserName());
        /**
         * 轮播图
         */
        mAdapter = new ImageAdapter(mDetail.getUrls(), this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_validate_quan:
                mPresenter.useChunsunCoupon(mToken, mCode);
                break;
        }
    }
}
