package com.chunsun.redenvelope.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.RedDetailShowPicBackEvent;
import com.chunsun.redenvelope.scanlibrary.ScanResultActivity;
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
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedDetailPicShowFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.iv_pic)
    PhotoView mIvPicture;
    @Bind(R.id.ll_back)
    LinearLayout mLLBack;
    @Bind(R.id.iv_exit)
    ImageView mIvBack;
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

    private String mUrl;
    private boolean mIsShow;

    public RedDetailPicShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_red_detail_pic_show, container, false);
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
        mLLBack.setOnClickListener(this);
        mTvExit.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvDownloadPic.setOnClickListener(this);
        mTvScan.setOnClickListener(this);
        mIvPicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showTools();
                return false;
            }
        });

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        mUrl = bundle.getString(Constants.EXTRA_KEY);
        boolean isHead = bundle.getBoolean(Constants.EXTRA_KEY2);
        if (isHead) {
            mLLExit.setVisibility(View.GONE);
            mLLOther.setVisibility(View.GONE);
            //默认头像
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.img_default_head);
            ImageLoader.getInstance().displayImage(mUrl, mIvPicture, ImageLoaderHelper.getInstance(getActivity()).getDisplayOptions(drawable));
        } else {
            ImageLoader.getInstance().displayImage(mUrl, mIvPicture, ImageLoaderHelper.getInstance(getActivity()).getDisplayOptions());
        }
        mIvPicture.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                EventBus.getDefault().post(new RedDetailShowPicBackEvent(""));
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit:
            case R.id.iv_exit:
            case R.id.ll_back:
                EventBus.getDefault().post(new RedDetailShowPicBackEvent(""));
                break;
            case R.id.tv_scanf:
                scan();
            case R.id.tv_download_pic:
                downloadPic();
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

    private void showTools() {
        if (mIsShow) {
            mLLExit.setVisibility(View.GONE);
            mLLOther.setVisibility(View.GONE);
            mIsShow = false;
        } else {
            mLLExit.setVisibility(View.VISIBLE);
            mLLOther.setVisibility(View.VISIBLE);
            mIsShow = true;
        }
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
            intent.putExtra(com.chunsun.redenvelope.scanlibrary.constants.Constants.MESSAGE_EXTRA, result);
            startActivity(intent);
        }
    }

}
