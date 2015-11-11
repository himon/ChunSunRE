package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.MineInviteCodePresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IMineInviteCodeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的邀请码Activity
 */
public class MineInviteCodeActivity extends BaseActivity implements IMineInviteCodeView, View.OnClickListener {

    @Bind(R.id.tv_code)
    TextView mTvInviteCode;
    @Bind(R.id.tv_sum)
    TextView mTvAmount;
    @Bind(R.id.btn_invite)
    Button mBtnInvite;
    @Bind(R.id.btn_invite_record)
    Button mBtnInviteRecord;

    private MineInviteCodePresenter mPresenter;
    private String mToken;
    private InviteRecordEntity.ResultEntity mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_invite_code);
        ButterKnife.bind(this);
        mPresenter = new MineInviteCodePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("我的邀请码", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mBtnInvite.setOnClickListener(this);
        mBtnInviteRecord.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        mTvInviteCode.setText(MainApplication.getContext().getUserEntity().getInvitation_code());
        mPresenter.getInviteRecord(mToken);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_invite:
                break;
            case R.id.btn_invite_record:
                toInviteRecordList();
                break;
        }
    }

    @Override
    public void setData(InviteRecordEntity.ResultEntity result) {
        mTvAmount.setText(result.getAmount());
        mResult = result;
    }

    @Override
    public void toInviteRecordList() {
        Intent intent = new Intent(this, InviteRecordListActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mResult);
        startActivity(intent);
    }

    @Override
    public void getShareUrlSuccess(UserEntity entity) {

    }
}
