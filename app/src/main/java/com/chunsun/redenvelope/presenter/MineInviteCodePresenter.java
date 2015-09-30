package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.MineInviteCodeMode;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.impl.MineInviteCodeModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeActivity;
import com.chunsun.redenvelope.ui.view.IMineInviteCodeView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MineInviteCodePresenter implements BaseSingleLoadedListener<InviteRecordEntity>{

    private IMineInviteCodeView mIMineInviteCodeView;
    private MineInviteCodeMode mMineInviteCodeMode;

    public MineInviteCodePresenter(IMineInviteCodeView iMineInviteCodeView) {
        this.mIMineInviteCodeView = iMineInviteCodeView;
        this.mMineInviteCodeMode = new MineInviteCodeModeImpl((MineInviteCodeActivity) iMineInviteCodeView);
    }

    public void getInviteRecord(String token){
        this.mMineInviteCodeMode.getInviteRecord(token, this);
    }

    @Override
    public void onSuccess(InviteRecordEntity response) {
        InviteRecordEntity.ResultEntity result = response.getResult();
        mIMineInviteCodeView.setData(result);
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
