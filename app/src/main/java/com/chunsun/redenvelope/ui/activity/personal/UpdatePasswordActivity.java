package com.chunsun.redenvelope.ui.activity.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.UpdatePasswordPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IUpdatePasswordView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.widget.TextButtonDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdatePasswordActivity extends BaseActivity implements IUpdatePasswordView, View.OnClickListener {

    @Bind(R.id.ll_old_pwd_container)
    LinearLayout mLLOldPwdContainer;
    @Bind(R.id.et_input_old_pwd)
    EditText mEtOldPwd;
    @Bind(R.id.et_input_new_pwd)
    EditText mEtNewPwd;
    @Bind(R.id.et_confirm_new_pwd)
    EditText mEtConfrimPwd;

    private UpdatePasswordPresenter mPresenter;
    private String mToken;
    private boolean mIsHas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        mPresenter = new UpdatePasswordPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("修改密码", "", "确定", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        mIsHas = MainApplication.getContext().getUserEntity().isHas_pwd();
        if (mIsHas) {
            mLLOldPwdContainer.setVisibility(View.VISIBLE);
        } else {
            mLLOldPwdContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.tv_nav_right:
                mPresenter.updatePassword(mToken, StringUtil.textview2String(mEtOldPwd), StringUtil.textview2String(mEtNewPwd), StringUtil.textview2String(mEtConfrimPwd), mIsHas);
                break;
        }
    }

    @Override
    public void updateSuccess() {
        back();
    }
}
