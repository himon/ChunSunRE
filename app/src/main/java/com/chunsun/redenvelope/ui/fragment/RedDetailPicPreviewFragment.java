package com.chunsun.redenvelope.ui.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.event.RedDetailBackEvent;
import com.chunsun.redenvelope.scanlibrary.ScanResultActivity;
import com.chunsun.redenvelope.scanlibrary.constants.Constants;
import com.chunsun.redenvelope.scanlibrary.scan.ScanUtil;
import com.chunsun.redenvelope.scanlibrary.scan.decode.BitmapDecoder;
import com.chunsun.redenvelope.ui.base.fragment.BaseFragment;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.helper.ImageLoaderHelper;
import com.google.zxing.Result;
import com.google.zxing.client.result.ResultParser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * 红包详情图片预览Fragment
 */
public class RedDetailPicPreviewFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.iv_pic)
    ImageView mIvPicture;
    @Bind(R.id.rl_back)
    RelativeLayout mRlBack;
    @Bind(R.id.ll_exit)
    RelativeLayout mLLExit;
    @Bind(R.id.iv_exit)
    ImageView mIvExit;
    @Bind(R.id.tv_exit)
    TextView mTvExit;
    @Bind(R.id.rl_enter)
    RelativeLayout mRlEnter;
    @Bind(R.id.tv_enter)
    TextView mTvEnter;
    @Bind(R.id.ll_other)
    LinearLayout mLLOther;
    @Bind(R.id.tv_download_pic)
    TextView mTvDownloadPic;
    @Bind(R.id.tv_scanf)
    TextView mTvScan;

    private String mUrl;
    private boolean mIsShow;

    public RedDetailPicPreviewFragment() {
    }

    @SuppressLint("ValidFragment")
    public RedDetailPicPreviewFragment(String url) {
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
        initEvent();
    }

    private void initEvent() {
        mIvPicture.setOnClickListener(this);
        mRlBack.setOnClickListener(this);
        mIvExit.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
        mRlEnter.setOnClickListener(this);
        mTvEnter.setOnClickListener(this);
        mTvDownloadPic.setOnClickListener(this);
        mTvScan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ImageLoader.getInstance().displayImage(mUrl, mIvPicture, ImageLoaderHelper.getInstance(getActivity()).getDisplayOptions());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pic:
                if (mIsShow) {
                    mLLExit.setVisibility(View.GONE);
                    mLLOther.setVisibility(View.GONE);
                    mIsShow = false;
                } else {
                    mLLExit.setVisibility(View.VISIBLE);
                    mLLOther.setVisibility(View.VISIBLE);
                    mIsShow = true;
                }
                break;
            case R.id.tv_exit:
            case R.id.iv_exit:
            case R.id.rl_back:
                EventBus.getDefault().post(new RedDetailBackEvent(""));
                break;
            case R.id.tv_scanf:
                scan();
            case R.id.tv_download_pic:
                downloadPic();
                break;
            case R.id.tv_enter:
            case R.id.rl_enter:
                EventBus.getDefault().post(new RedDetailBackEvent("enter"));
                break;
        }
    }

    /**
     * 下载图片
     */
    private void downloadPic() {
        OkHttpUtils.get().url(mUrl).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), SystemClock.currentThreadTimeMillis() + ".jpg") {
            @Override
            public void inProgress(float progress) {

            }

            @Override
            public void onError(Request request, Exception e) {
                ShowToast.Short("下载到失败");
            }

            @Override
            public void onResponse(File file) {
                ShowToast.Short("下载到：" + file.getAbsolutePath());
            }
        });
    }

    /**
     * 扫描二维码
     */
    private void scan() {
        mIvPicture.setDrawingCacheEnabled(true);
        Bitmap bitmap = mIvPicture.getDrawingCache();
        BitmapDecoder decoder = new BitmapDecoder(getActivity());
        Result result = decoder.getRawResult(bitmap);
        if (result == null) {
            Toast.makeText(getActivity(), "无法识别", Toast.LENGTH_SHORT)
                    .show();
        } else {
            mIvPicture.setDrawingCacheEnabled(false);
            handleResult(ResultParser.parseResult(result).toString());
        }
    }

    /**
     * 根据是否是url跳转页面
     *
     * @param result
     */
    private void handleResult(String result) {
        if (ScanUtil.urlValidate(result)) {
            Uri uri = Uri.parse(result);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), ScanResultActivity.class);
            intent.putExtra(Constants.MESSAGE_EXTRA, result);
            startActivity(intent);
        }
    }
}
