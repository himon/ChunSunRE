package com.chunsun.redenvelope.ui.base;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.CustomProgressDialog;

import butterknife.Bind;

public abstract class BaseActivity extends ActionBarActivity {

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

    protected CustomProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppManager.getAppManager().addActivity(this);
        mDialog = new CustomProgressDialog(this, "努力加载ing");
    }

    protected abstract void initView();

    protected abstract void initData();

    protected void initTitleBar(String title, String left, String right, int type){

        switch (type){
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

    protected void back(){
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
