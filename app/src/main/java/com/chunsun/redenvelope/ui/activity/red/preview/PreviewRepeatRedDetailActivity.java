package com.chunsun.redenvelope.ui.activity.red.preview;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.utils.helper.RedDetailHelper;
import com.chunsun.redenvelope.widget.autoscrollviewpager.GuideGallery;
import com.chunsun.redenvelope.widget.autoscrollviewpager.ImageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.iwf.photopicker.entity.Photo;

public class PreviewRepeatRedDetailActivity extends BaseActivity {

    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_rest_num)
    TextView mTvRestNum;
    @Bind(R.id.ll_repeat)
    LinearLayout mLLRepeat;
    @Bind(R.id.tv_red_title)
    TextView mTvTitle;
    @Bind(R.id.iv_head_logo)
    ImageView mIvHead;
    @Bind(R.id.tv_red_name)
    TextView mTvUserName;
    @Bind(R.id.iv_company_v)
    ImageView mIvCompanyIcon;
    @Bind(R.id.tv_red_time)
    TextView mTvTime;
    @Bind(R.id.vp_pictures)
    GuideGallery mViewPager;
    @Bind(R.id.wv_red_content)
    WebView mWvContent;

    //轮播图adapter
    private ImageAdapter mAdapter;
    //红包数据
    private AdEntity mDetail;
    //图片Url
    private ArrayList<Photo> mPhotos = new ArrayList<>();

    /**
     * 红包帮助类
     */
    RedDetailHelper mRedDetailHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_repeat_red_detail);
        ButterKnife.bind(this);
        mRedDetailHelper = new RedDetailHelper(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("春笋红包", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mDetail = intent.getParcelableExtra(Constants.EXTRA_KEY);
            mPhotos = intent.getParcelableArrayListExtra(Constants.EXTRA_KEY2);
            getRedDetailSuccess();
        }
    }

    @Override
    protected void click(View v) {

    }

    public void getRedDetailSuccess() {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String currentTime = format.format(date);

        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();

        mTvPrice.setText("每次点击收益：" + mDetail.getMeal().getPrice() + "元");
        mTvRestNum.setText("剩余收益次数：" + mDetail.getMeal().getNumber_of_copies());

        mTvTitle.setText(mDetail.getTitle());
        mTvUserName.setText(userEntity.getNick_name());
        //判断是否是代理
        if (userEntity.getIs_v().equals("2")) {
            mIvCompanyIcon.setVisibility(View.VISIBLE);
        } else {
            mIvCompanyIcon.setVisibility(View.GONE);
        }
        mTvTime.setText(currentTime);
        mRedDetailHelper.webViewSetText(mWvContent, mDetail.getContent());
        ImageLoader.getInstance().displayImage(Constants.IMG_HOST_URL +userEntity.getImg_url(), mIvHead, MainApplication.getContext().getHeadOptions());

        ArrayList<String> list = initUrls();
        if (list.size() > 0) {
            /**
             * 轮播图
             */
            mAdapter = new ImageAdapter(list, this);
            mViewPager.setAdapter(mAdapter);
        }
    }

    private ArrayList<String> initUrls() {

        ArrayList<String> urls = new ArrayList<>();

        if (TextUtils.isEmpty(mDetail.getCoverImagePath())) {

        } else {
            urls.add("file://" + mDetail.getCoverImagePath());
        }

        for (int i = 0; i < mPhotos.size(); i++) {
            if (TextUtils.isEmpty(mPhotos.get(i).getPath())) {
                return urls;
            }
            urls.add("file://" + mPhotos.get(i).getPath());
        }

        return urls;
    }
}
