package com.chunsun.redenvelope.presenter.impl;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.model.entity.MeFragmentEntity;
import com.chunsun.redenvelope.model.entity.json.UserEntity;
import com.chunsun.redenvelope.model.entity.json.UserInfoEntity;
import com.chunsun.redenvelope.model.impl.MeFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.MeFragment;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentPresenter implements BaseSingleLoadedListener<UserEntity> {

    private IMeFragmentView meFragmentView;
    private MeFragmentMode meFragmentMode;

    public MeFragmentPresenter(IMeFragmentView meFragmentView) {
        this.meFragmentView = meFragmentView;
        meFragmentMode = new MeFragmentModeImpl((MeFragment) meFragmentView);
    }

    public ArrayList<MeFragmentEntity> getData(String token) {
        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();
        if (userEntity == null) {
            meFragmentMode.getUserInfomation(token, this);
            return null;
        } else {
            return installData(userEntity);
        }
    }

    private ArrayList<MeFragmentEntity> installData(UserInfoEntity userEntity) {
        ArrayList<MeFragmentEntity> list = new ArrayList<MeFragmentEntity>();

        MeFragmentEntity entity = new MeFragmentEntity();
        entity.setType(2);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_MINE);
        entity.setText(userEntity.getNick_name());
        entity.setCode("春笋号：" + userEntity.getUser_name());
        entity.setImg(userEntity.getImg_url());
        entity.setType(0);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_INVITE_CODE);
        entity.setText("我的邀请码");
        entity.setCode(userEntity.getInvitation_code());
        entity.setRes(R.drawable.img_invitecode);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(2);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_BALANCE);
        entity.setText("余额");
        entity.setRes(R.drawable.img_balance);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_RECORD);
        entity.setText("发广告记录");
        entity.setRes(R.drawable.img_send_red_record);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(2);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_NOT_RECEIVING_RED);
        entity.setText("未领取红包");
        entity.setRes(R.drawable.img_not_get_red);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_COLLECT);
        entity.setText("收藏");
        entity.setRes(R.drawable.img_collect);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(2);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setId(Constants.ME_FRAGMENT_TYPE_SETTING);
        entity.setText("设置");
        entity.setRes(R.drawable.img_setting);
        entity.setType(1);
        list.add(entity);

        entity = new MeFragmentEntity();
        entity.setType(3);
        list.add(entity);

        return list;
    }

    @Override
    public void onSuccess(UserEntity entity) {
        MainApplication.getContext().setmUserEntity(entity.getResult());
        meFragmentView.refresh(installData(entity.getResult()));
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        //ShowToast.Short(msg);
    }
}
