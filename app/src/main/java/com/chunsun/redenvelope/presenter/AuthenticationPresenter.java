package com.chunsun.redenvelope.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AuthenticationMode;
import com.chunsun.redenvelope.model.impl.AuthenticationModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IAuthenticationView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/1 10:42
 * @des
 */
public class AuthenticationPresenter extends UserLosePresenter<IAuthenticationView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IAuthenticationView mIAuthenticationView;
    private AuthenticationMode mAuthenticationMode;


    public AuthenticationPresenter(IAuthenticationView view) {
        this.mIAuthenticationView = view;
        this.mAuthenticationMode = new AuthenticationModeImpl((Activity) view);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        mIAuthenticationView.hideLoading();
        switch (event_tag) {
            case Constants.LISTENER_TYPE_USER_CMP:
                ShowToast.Short(((SampleResponseEntity) data).getMsg());
                break;
        }
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        mIAuthenticationView.hideLoading();
    }

    public void commit(String token, String name, String tel, String addr, String phone, String operator, String bankNum, String bankName, String taxNum, String image1, String image2) {
        if (TextUtils.isEmpty(name)) {
            ShowToast.Short("公司名称不能为空！");
            return;
        }

        if (TextUtils.isEmpty(tel)) {
            ShowToast.Short("公司电话不能为空！");
            return;
        }

        if (TextUtils.isEmpty(addr)) {
            ShowToast.Short("公司地址不能为空！");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            ShowToast.Short("手机不能为空！");
            return;
        }

        if (TextUtils.isEmpty(operator)) {
            ShowToast.Short("经办人不能为空！");
            return;
        }

        if (TextUtils.isEmpty(bankNum)) {
            ShowToast.Short("银行账号不能为空！");
            return;
        }

        if (TextUtils.isEmpty(bankName)) {
            ShowToast.Short("开户行不能为空！");
            return;
        }

        if (TextUtils.isEmpty(taxNum)) {
            ShowToast.Short("税务登记号不能为空！");
            return;
        }

        if (TextUtils.isEmpty(image1)) {
            ShowToast.Short("营业执照不能为空！");
            return;
        }

        if (TextUtils.isEmpty(image2)) {
            ShowToast.Short("手持身份证照不能为空！");
            return;
        }
        mIAuthenticationView.showLoading();
        mAuthenticationMode.userCmp(token, name, tel, addr, phone, operator, bankNum, bankName, taxNum, image1, image2, this);
    }
}
