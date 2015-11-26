package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.EditInfoMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.EditInfoModeImpl;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.ui.view.IEditInfoView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EditInfoPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private IEditInfoView mEditInfoView;
    private EditInfoMode mEditInfoMode;

    private int mType;

    public EditInfoPresenter(IEditInfoView editInfoView) {
        this.mEditInfoView = editInfoView;
        mEditInfoMode = new EditInfoModeImpl((EditInfoActivity) editInfoView);
    }

    public void complaintRedEnvelope(String token, String hb_id, String reason) {
        if (TextUtils.isEmpty(reason)) {
            mEditInfoView.contentIsEmpty();
        } else {
            mEditInfoMode.complaintRedEnvelope(token, hb_id, reason, this);
        }
    }

    /**
     * 修改用户基本信息
     *
     * @param token
     * @param type
     * @param field_value
     */
    public void editUserInfo(String token, int type, String field_value) {
        String field_name = "";
        mType = type;
        switch (type) {
            case Constants.EDIT_TYPE_NICK_NAME:
                field_name = "nick_name";
                break;
            case Constants.EDIT_TYPE_CHUNSUN_ACCOUNT:
                field_name = "user_name";
                break;
            case Constants.EDIT_TYPE_TEL:
                field_name = "telphone";
                break;
            case Constants.EDIT_TYPE_WECHAT:
                field_name = "weixin";
                break;
            case Constants.EDIT_TYPE_ALIPAY:
                field_name = "zhifubao";
                break;
            case Constants.EDIT_TYPE_ID_CARD:
                field_name = "ID_num";
                break;
            case Constants.EDIT_TYPE_DESC:
                field_name = "remark";
                break;
            case Constants.EDIT_TYPE_QQ:
                field_name = "qq";
                break;
        }
        mEditInfoMode.editUserInfo(token, field_name, field_value, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_COMLAINT_RED_ENVELOPE:
                mEditInfoView.complaintRedEnvelopeSuccess(((SampleResponseEntity)data).getMsg());
                break;
            case Constants.LISTENER_TYPE_EDIT_USER_INFO:
                ShowToast.Short(((SampleResponseEntity)data).getMsg());
                mEditInfoView.editSuccess(mType);
                break;
        }
    }
}
