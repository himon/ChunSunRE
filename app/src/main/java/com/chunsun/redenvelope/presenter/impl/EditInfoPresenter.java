package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.EditInfoMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.EditInfoModeImpl;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.ui.view.IEditInfoView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EditInfoPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private IEditInfoView mEditInfoView;
    private EditInfoMode mEditInfoMode;

    public EditInfoPresenter(IEditInfoView editInfoView) {
        this.mEditInfoView = editInfoView;
        mEditInfoMode = new EditInfoModeImpl((EditInfoActivity) editInfoView);
    }

    public void complaintRedEnvelope(String token, String hb_id, String reason){
        if(TextUtils.isEmpty(reason)){
            mEditInfoView.contentIsEmpty();
        }else{
            mEditInfoMode.complaintRedEnvelope(token, hb_id, reason, this);
        }

    }

    @Override
    public void onSuccess(SampleResponseEntity responseEntity) {
        mEditInfoView.complaintRedEnvelopeSuccess(responseEntity.getMsg());
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
