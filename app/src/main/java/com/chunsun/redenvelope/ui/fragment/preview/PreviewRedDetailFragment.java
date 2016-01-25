package com.chunsun.redenvelope.ui.fragment.preview;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailPicShowActivity;
import com.chunsun.redenvelope.ui.base.fragment.BaseFragment;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewRedDetailFragment extends BaseFragment {

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
    @Bind(R.id.wv_red_content)
    WebView mWvContent;

    //红包数据
    private AdEntity mDetail;
    //图片list
    private ArrayList<String> mUrls;
    //轮播图adapter
    private ImageAdapter mAdapter;

    /**
     * 红包帮助类
     */
    RedDetailHelper mRedDetailHelper;

    public PreviewRedDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview_red_detail, container, false);
        ButterKnife.bind(this, view);
        mRedDetailHelper = new RedDetailHelper(getActivity());
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        mViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RedDetailPicShowActivity.class);
                intent.putExtra(Constants.EXTRA_LIST_KEY, mUrls);
                intent.putExtra(Constants.EXTRA_KEY, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        mDetail = bundle.getParcelable(Constants.EXTRA_KEY);
        mUrls = bundle.getStringArrayList(Constants.EXTRA_KEY2);

        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String currentTime = format.format(date);

        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(userEntity.getNick_name());
        mTvTime.setText(currentTime);
        mRedDetailHelper.webViewSetText(mWvContent, mDetail.getContent());
        ImageLoader.getInstance().displayImage(userEntity.getImg_url(), mIvHead, MainApplication.getContext().getHeadOptions());
        //判断是否是代理
        if (userEntity.getIs_v().equals("2")) {
            mIvCompanyIcon.setVisibility(View.VISIBLE);
        } else {
            mIvCompanyIcon.setVisibility(View.GONE);
        }
        /**
         * 轮播图
         */
        if (mUrls.size() > 0) {
            mAdapter = new ImageAdapter(mUrls, getActivity());
            mViewPager.setAdapter(mAdapter);
        }

        if (mDetail.getType().getKey().equals(Constants.RED_DETAIL_TYPE_COUPON + "")) {
            mTvEffectiveDate.setText("有效期：" + mDetail.getCouponStartTime() + " - " + mDetail.getCouponEndTime());
            mTvEffectiveDate.setVisibility(View.VISIBLE);
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

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stopAutoScroll();
    }

}
