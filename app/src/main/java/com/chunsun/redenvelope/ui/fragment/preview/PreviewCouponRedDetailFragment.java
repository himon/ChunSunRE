package com.chunsun.redenvelope.ui.fragment.preview;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewCouponRedDetailFragment extends BaseFragment {

    @Bind(R.id.iv_head_logo)
    ImageView mIvHead;
    @Bind(R.id.tv_red_title)
    TextView mTvTitle;
    @Bind(R.id.tv_red_name)
    TextView mTvUserName;
    @Bind(R.id.iv_company_v)
    ImageView mIvCompanyIcon;
    @Bind(R.id.tv_red_time)
    TextView mTvTime;
    @Bind(R.id.vp_pictures)
    GuideGallery mViewPager;
    @Bind(R.id.tv_effective_date)
    TextView mTvEffectiveDate;
    @Bind(R.id.tv_red_content)
    TextView mTvContent;

    public PreviewCouponRedDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview_coupon_red_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}
