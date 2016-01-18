package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.event.CreateCircleResultEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CreateCircleResultPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.widget.popupwindow.ShareRedEnvelopePopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CreateCircleResultActivity extends BaseActivity {

    @Bind(R.id.rl_success_msg)
    RelativeLayout mRlSuccessMsg;
    @Bind(R.id.btn_to_circle)
    Button mBtnToCirlc;
    @Bind(R.id.btn_sync_friend)
    Button mBtnSync;
    @Bind(R.id.rl_error_msg)
    RelativeLayout mRlErrorMsg;
    @Bind(R.id.btn_error_to_circle)
    Button mBtnErrorToCircle;
    @Bind(R.id.btn_try_again)
    Button mBtnTry;

    private RedDetailEntity.ResultEntity.DetailEntity mDetail;
    private CreateCircleResultPresenter mPresenter;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle_result);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new CreateCircleResultPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("春笋圈子", "", "", Constants.TITLE_TYPE_NONE);
        initEvent();
    }

    private void initEvent() {
        mBtnToCirlc.setOnClickListener(this);
        mBtnSync.setOnClickListener(this);
        mBtnErrorToCircle.setOnClickListener(this);
        mBtnTry.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            mDetail = intent.getParcelableExtra(Constants.EXTRA_KEY);
            if (mDetail != null) {
                mRlSuccessMsg.setVisibility(View.VISIBLE);
                mRlErrorMsg.setVisibility(View.GONE);
            } else {
                mRlSuccessMsg.setVisibility(View.GONE);
                mRlErrorMsg.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.btn_to_circle:
            case R.id.btn_error_to_circle:
                toCircle();
                break;
            case R.id.btn_sync_friend:
                sync();
                break;
            case R.id.btn_try_again:
                back();
                break;
        }
    }

    private void sync() {
        ShareRedEnvelopePopupWindow share = new ShareRedEnvelopePopupWindow(this, mDetail, "");
        share.circleSyncFriend();
    }

    private void toCircle() {
        back();
        AppManager.getAppManager().finishActivity(CreateAdContentActivity.class);
        AppManager.getAppManager().finishActivity(CreateCircleActivity.class);

    }

    public void onEvent(CreateCircleResultEvent event) {
        String msg = event.getMsg();
        if ("success".equals(msg)) {
            toCircle();
            mPresenter.userOperateCircle(mToken, MainApplication.getContext().getProvince(), MainApplication.getContext().getCity(), MainApplication.getContext().getLongitude() + "", MainApplication.getContext().getLatitude() + "", 4, mDetail.getId());
            ShowToast.Short("同步朋友圈成功！");
        } else if ("cancel".equals(msg)) {
            ShowToast.Short("取消分享！");
        }
    }
}
