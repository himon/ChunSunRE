package com.chunsun.redenvelope.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.activity.RedDetailActivity;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 红包详情图片预览Fragment
 */
public class RedDetailPicPreviewFragment extends BaseFragment {

    @Bind(R.id.iv_pic)
    ImageView mIvPicture;
    @Bind(R.id.ll_exit)
    LinearLayout mLLExit;
    @Bind(R.id.tv_exit)
    TextView mTvExit;
    @Bind(R.id.ll_other)
    LinearLayout mLLOther;
    @Bind(R.id.tv_download_pic)
    TextView mTvDownloadPic;
    @Bind(R.id.tv_scanf)
    TextView mTvScan;

    private DisplayImageOptions mOptions;
    private String mUrl;

    public RedDetailPicPreviewFragment(){}

    @SuppressLint("ValidFragment")
    public RedDetailPicPreviewFragment(String url, DisplayImageOptions options) {
        this.mOptions = options;
        this.mUrl = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_red_detail_pic_preview, container, false);
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
        ImageLoader.getInstance().displayImage(mUrl, mIvPicture, mOptions);
    }
}
