package com.chunsun.redenvelope.clip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.clip.widget.ClipLayout;
import com.chunsun.redenvelope.clip.widget.ClipRectLayout;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.utils.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicClipActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.main_nav)
    RelativeLayout mToolsBar;
    @Bind(R.id.clip_layout)
    ClipLayout mClipLayout;
    @Bind(R.id.clip_rect_layout)
    ClipRectLayout mClipRectLayout;

    private String imgPath;
    private Intent intent;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_clip);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mToolsBar.setVisibility(View.GONE);

        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.btn_selected).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_turn_left).setOnClickListener(this);
        findViewById(R.id.btn_turn_right).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        if (intent != null) {
            flag = intent.getBooleanExtra(Constants.EXTRA_KEY2, false);
            if (flag) {
                mClipLayout.setVisibility(View.VISIBLE);
            } else {
                mClipRectLayout.setVisibility(View.VISIBLE);
            }
        }
        initBitmap();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_selected:
                clipBitmap();
                break;
            case R.id.btn_cancel:
                back();
                break;
            case R.id.btn_turn_left:
                if (flag) {
                    mClipLayout.rotate(-90);
                } else {
                    mClipRectLayout.rotate(-90);
                }
                break;
            case R.id.btn_turn_right:
                if (flag) {
                    mClipLayout.rotate(90);
                } else {
                    mClipRectLayout.rotate(90);
                }
            default:
                break;
        }
    }

    private void initBitmap() {
        imgPath = intent.getStringExtra(Constants.EXTRA_KEY);
        File file = new File(imgPath);

        int degree = ImageUtils.readPictureDegree(imgPath);

        if (file.exists()) {
            Window window = getWindow();
            if (flag) {
                Bitmap bitmap = ImageUtils.rotaingImageView(degree,
                        BitmapClipUtils
                                .createImageThumbnailScale(imgPath, 1280));
                mClipLayout.setSourceImage(bitmap, window);
            } else {
                mClipRectLayout
                        .setSourceImage(BitmapClipUtils
                                        .createImageThumbnailScale(imgPath, 800),
                                window);
            }
        } else {
            finish();
        }
    }

    private void clipBitmap() {
        Bitmap bitmap = null;
        if (flag) {
            bitmap = mClipLayout.getBitmap();
        } else {
            bitmap = mClipRectLayout.getBitmap();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bitmapByte = baos.toByteArray();
        if(flag) {
            intent = new Intent(this, CreateAdContentActivity.class);
        }else{
            intent = new Intent(this, UserInfoActivity.class);
        }
        intent.putExtra(Constants.EXTRA_KEY, imgPath);
        intent.putExtra(Constants.EXTRA_KEY2, bitmapByte);
        setResult(RESULT_OK, intent);
        back();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (flag) {
            mClipLayout.onDestory();
        } else {
            mClipRectLayout.onDestory();
        }
    }
}
