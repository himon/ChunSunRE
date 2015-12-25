package com.chunsun.redenvelope.ui.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.utils.DensityUtils;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.CustomProgressDialog;

import butterknife.Bind;

public abstract class BaseActivity extends FragmentActivity {

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
    private LinearLayout mLLCircle;
    @Bind(R.id.ll_order)
    private LinearLayout mLLOrder;
    @Bind(R.id.tv_order)
    private TextView mTvOrder;
    @Bind(R.id.rl_search)
    private RelativeLayout mRlSearch;
    @Bind(R.id.iv_search)
    private ImageView mIvSearch;
    @Bind(R.id.rl_create)
    private RelativeLayout mRlCreate;
    @Bind(R.id.iv_create)
    private ImageView mIvCreate;

    protected CustomProgressDialog mDialog;

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

    protected void initTitleBar(String title, String left, String right, int type) {

        switch (type) {
            case Constants.TITLE_TYPE_SAMPLE:
                mNavIcon.setVisibility(View.VISIBLE);
                mNavIcon.setImageResource(R.drawable.img_back);
                mNavLeft.setText("".equals(left) ? "返回" : left);
                mNavLeft.setVisibility(View.VISIBLE);
                mNavTitle.setText(title);
                mNavRight.setText(right);
                mNavRight.setVisibility(View.VISIBLE);
                mNavRightIcon.setVisibility(View.INVISIBLE);
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
                break;
            case Constants.TITLE_TYPE_NONE:
                mNavIcon.setVisibility(View.GONE);
                mNavLeft.setVisibility(View.GONE);
                mNavTitle.setText(title);
                mNavRight.setVisibility(View.GONE);
                mNavRightIcon.setVisibility(View.GONE);
                break;
            case Constants.TITLE_TYPE_AD:
                mNavIcon.setVisibility(View.INVISIBLE);
                mNavLeft.setVisibility(View.INVISIBLE);
                mNavTitle.setText(title);
                mNavRight.setText(right);
                mNavRight.setVisibility(View.VISIBLE);
                mNavRightIcon.setVisibility(View.INVISIBLE);
                break;
        }
    }

    protected void showCircleLoading() {
        if (mDialog != null) {
            mDialog.show();
            Window dialogWindow = mDialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = DensityUtils.dip2px(this, 180);
            lp.height = DensityUtils.dip2px(this, 180);
            dialogWindow.setAttributes(lp);
        }
    }

    protected void hideCircleLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
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
}
