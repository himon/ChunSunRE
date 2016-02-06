package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.app.context.LoginContext;
import com.chunsun.redenvelope.app.state.impl.LogoutState;
import com.chunsun.redenvelope.callback.MyCallback;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.ApkVersionEntity;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.SettingPresenter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.ISettingView;
import com.chunsun.redenvelope.utils.manager.ApkUpdateManager;
import com.chunsun.redenvelope.widget.SettingItem;
import com.chunsun.redenvelope.widget.TextButtonDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 设置Activity
 */
public class SettingActivity extends BaseActivity implements ISettingView {

    @Bind(R.id.si_about_us)
    SettingItem mSiAboutUs;
    @Bind(R.id.si_check_update)
    SettingItem mSiCheckUpdate;
    @Bind(R.id.si_clear)
    SettingItem mSiClear;
    @Bind(R.id.si_update_pwd)
    SettingItem mSiUpdatePwd;
    @Bind(R.id.si_privacy)
    SettingItem mSiPrivacy;
    @Bind(R.id.si_logout)
    SettingItem mSiLogout;

    private SettingPresenter mPresenter;
    private TextButtonDialog mExitDialog;
    private String mToken;
    private ApkVersionEntity mApkInfo;

    private TextButtonDialog mUpdateDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mPresenter = new SettingPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        initTitleBar("设置", "", "", Constants.TITLE_TYPE_SAMPLE);

        mSiAboutUs.setContent("关于我们");
        mSiCheckUpdate.setContent("检查更新");
        mSiClear.setContent("清除缓存");
        mSiUpdatePwd.setContent("修改密码");
        mSiPrivacy.setContent("隐私");
        mSiLogout.setContent("退出账号");

        mUpdateDialog = new TextButtonDialog(this, R.style.progress_dialog,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        switch (arg0.getId()) {
                            case R.id.btn_confirm_ok:
                                // TODO升级
                                mPresenter.downloadApk(mApkInfo, SettingActivity.this);
                                mUpdateDialog.checkSetEnabled();
                                break;
                            case R.id.btn_confirm_cancel:
                                mUpdateDialog.cancel();
                                break;
                        }
                    }
                });

        initEvent();
    }

    private void initEvent() {

        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);

        mSiAboutUs.setOnClickListener(this);
        mSiCheckUpdate.setOnClickListener(this);
        mSiClear.setOnClickListener(this);
        mSiUpdatePwd.setOnClickListener(this);
        mSiPrivacy.setOnClickListener(this);
        mSiLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        mExitDialog = new TextButtonDialog(this, R.style.progress_dialog, mExitListener);
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.si_about_us:
                toAboutUs();
                break;
            case R.id.si_check_update:
                mPresenter.upGrade();
                break;
            case R.id.si_clear:
                mPresenter.clearCache();
                break;
            case R.id.si_update_pwd:
                toUpdatePwd();
                break;
            case R.id.si_privacy:
                toUpdatePrivacy();
                break;
            case R.id.si_logout:
                mExitDialog.show();
                mExitDialog.setDialogContent("确定退出当前账户？");
                break;
        }
    }

    @Override
    public void toAboutUs() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void toUpdatePwd() {
        Intent intent = new Intent(this, UpdatePasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void toLogout() {
        LoginContext.getLoginContext().setState(new LogoutState());
        new Preferences(this).setToken("");
        MainApplication.getContext().setmUserEntity(null);
        EventBus.getDefault().post(new MainEvent(Constants.FROM_LOGIN_BACK));
        back();
    }

    @Override
    public void toUpdatePrivacy() {
        Intent intent = new Intent(this, UserPrivacyActivity.class);
        startActivity(intent);
    }

    @Override
    public void isUpGrade(final ApkVersionEntity apk) {
        mApkInfo = apk;
        ApkUpdateManager manager = new ApkUpdateManager(getApplicationContext());
        manager.upCheckGrade(apk, new MyCallback() {
            @Override
            public void callback() {
                mUpdateDialog.show();
                mUpdateDialog.setDialogContent(apk.getDescription(), 15);
                mUpdateDialog.isCheckUpGrade();
            }
        }, new MyCallback() {
            @Override
            public void callback() {
                mUpdateDialog.show();
                mUpdateDialog.setDialogContent("已经是最新版本", 15);
                mUpdateDialog.singleButtonDialog();
            }
        });
    }

    private View.OnClickListener mExitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_confirm_cancel:
                    mExitDialog.dismiss();
                    break;
                case R.id.btn_confirm_ok:
                    mExitDialog.dismiss();
                    mPresenter.logout(mToken);
                    break;
            }
        }
    };
}
