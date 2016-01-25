package com.chunsun.redenvelope.ui.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.utils.ProgressUtil;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.CustomProgressDialog;

import butterknife.Bind;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Bind(R.id.iv_nav_icon)
    protected ImageView mNavIcon;
    @Bind(R.id.tv_nav_left)
    protected TextView mNavLeft;
    @Bind(R.id.tv_nav_mid)
    protected TextView mNavTitle;
    @Bind(R.id.tv_nav_right)
    protected TextView mNavRight;
    @Bind(R.id.ib_nav_right)
    protected ImageButton mNavRightIcon;
    @Bind(R.id.ll_circle)
    protected LinearLayout mLLCircle;
    @Bind(R.id.ll_order)
    protected LinearLayout mLLOrder;
    @Bind(R.id.tv_order)
    protected TextView mTvOrder;
    @Bind(R.id.rl_search)
    protected RelativeLayout mRlSearch;
    @Bind(R.id.iv_search)
    protected ImageView mIvSearch;
    @Bind(R.id.rl_create)
    protected RelativeLayout mRlCreate;
    @Bind(R.id.iv_create)
    protected ImageView mIvCreate;

    protected CustomProgressDialog mDialog;
    /**
     * 左上角按钮是否是返回键
     * 0 : back, 1 : web back, 2 : other
     */
    private int isLeftType = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (mDialog == null) {
            mDialog = new CustomProgressDialog(this, "努力加载ing");
        }
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void click(View v);

    protected void initTitleBar(String title, String left, String right, int type) {

        switch (type) {
            case Constants.TITLE_TYPE_SAMPLE_WEB:
                isLeftType = 1;
            case Constants.TITLE_TYPE_SAMPLE:
                mNavIcon.setVisibility(View.VISIBLE);
                mNavIcon.setImageResource(R.drawable.img_back);
                mNavLeft.setText("".equals(left) ? "返回" : left);
                mNavLeft.setVisibility(View.VISIBLE);
                mNavTitle.setText(title);
                mNavRight.setText(right);
                mNavRight.setVisibility(View.VISIBLE);
                mNavRightIcon.setVisibility(View.INVISIBLE);
                mLLCircle.setVisibility(View.GONE);
                mNavTitle.setVisibility(View.VISIBLE);
                break;
            case Constants.TITLE_TYPE_HOME:
                mNavIcon.setVisibility(View.VISIBLE);
                mNavIcon.setImageResource(R.drawable.img_place);
                mNavLeft.setText("".equals(left) ? "返回" : left);
                mNavLeft.setVisibility(View.VISIBLE);
                mNavTitle.setText(title);
                mNavRight.setVisibility(View.INVISIBLE);
                mNavRightIcon.setImageResource(R.drawable.img_add);
                mNavRightIcon.setVisibility(View.VISIBLE);
                mLLCircle.setVisibility(View.GONE);
                mNavTitle.setVisibility(View.VISIBLE);
                isLeftType = 2;
                break;
            case Constants.TITLE_TYPE_NONE:
                mNavIcon.setVisibility(View.GONE);
                mNavLeft.setVisibility(View.GONE);
                mNavTitle.setText(title);
                mNavRight.setVisibility(View.GONE);
                mNavRightIcon.setVisibility(View.GONE);
                mLLCircle.setVisibility(View.GONE);
                mNavTitle.setVisibility(View.VISIBLE);
                break;
            case Constants.TITLE_TYPE_AD:
                mNavIcon.setVisibility(View.INVISIBLE);
                mNavLeft.setVisibility(View.INVISIBLE);
                mNavTitle.setText(title);
                mNavRight.setText(right);
                mNavRight.setVisibility(View.VISIBLE);
                mNavRightIcon.setVisibility(View.INVISIBLE);
                mLLCircle.setVisibility(View.GONE);
                mNavTitle.setVisibility(View.VISIBLE);
                break;
            case Constants.TITLE_TYPE_CIRCLE:
                mNavIcon.setVisibility(View.VISIBLE);
                mNavIcon.setImageResource(R.drawable.img_place);
                mNavLeft.setText("".equals(left) ? "返回" : left);
                mNavLeft.setVisibility(View.VISIBLE);
                mNavTitle.setVisibility(View.GONE);
                mNavRight.setVisibility(View.GONE);
                mNavRightIcon.setVisibility(View.GONE);
                mLLCircle.setVisibility(View.VISIBLE);
                isLeftType = 2;
                break;
        }
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    protected void showCircleLoading() {
//        if (mDialog != null) {
//            mDialog.show();
//            Window dialogWindow = mDialog.getWindow();
//            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.width = DensityUtils.dip2px(this, 180);
//            lp.height = DensityUtils.dip2px(this, 180);
//            dialogWindow.setAttributes(lp);
//        }
        ProgressUtil.showCircleLoading(this);
    }

    protected void hideCircleLoading() {
//        if (mDialog != null && mDialog.isShowing()) {
//            mDialog.dismiss();
//        }
        ProgressUtil.hideCircleLoading();
    }

    protected void back() {
        hideKeyboard();
        finish();
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        RequestManager.cancelAll(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                if (isLeftType == 0) {
                    back();
                } else if (isLeftType == 1) {
                    onBackPressed();
                } else if (isLeftType == 2) {
                    MainApplication.getContext().getLocationClient().start();
                    ShowToast.Short("重新获取位置");
                }
                break;
        }
        click(v);
    }
}
