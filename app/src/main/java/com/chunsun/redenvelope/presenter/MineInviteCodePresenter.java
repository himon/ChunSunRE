package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MineInviteCodeMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.InviteRecordEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.model.impl.MineInviteCodeModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeWebActivity;
import com.chunsun.redenvelope.ui.view.IMineInviteCodeView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MineInviteCodePresenter implements BaseMultiLoadedListener<BaseEntity> {

    private IMineInviteCodeView mIMineInviteCodeView;
    private MineInviteCodeMode mMineInviteCodeMode;

    public MineInviteCodePresenter(IMineInviteCodeView iMineInviteCodeView) {
        this.mIMineInviteCodeView = iMineInviteCodeView;
        this.mMineInviteCodeMode = new MineInviteCodeModeImpl((MineInviteCodeWebActivity) iMineInviteCodeView);
    }

    /**
     * 获取邀请信息列表
     *
     * @param token
     */
    public void getInviteRecord(String token) {
        this.mMineInviteCodeMode.getInviteRecord(token, this);
    }

    /**
     * 获取用户信息
     *
     * @param token
     */
    public void getUserInfo(String token) {
        this.mMineInviteCodeMode.getUserInfomation(token, this);
    }

    public void getInviteRecordSuccess(InviteRecordEntity response) {
        InviteRecordEntity.ResultEntity result = response.getResult();
        mIMineInviteCodeView.setData(result);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_USER_INVITE_INFO:
                getInviteRecordSuccess((InviteRecordEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_USER_INFO:
                UserEntity entity = (UserEntity) data;
                mIMineInviteCodeView.getShareUrlSuccess(entity);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }


}
