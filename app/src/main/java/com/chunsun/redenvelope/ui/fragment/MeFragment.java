package com.chunsun.redenvelope.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.MeFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.personal.BalanceActivity;
import com.chunsun.redenvelope.ui.activity.personal.CollectRedEnvelopeListActivity;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeActivity;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.ui.adapter.MeFragmentAdapter;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.model.entity.MeFragmentEntity;
import com.chunsun.redenvelope.ui.view.IMeFragmentView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 个人信息Fragment
 */
public class MeFragment extends BaseFragment implements IMeFragmentView {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.lv_main)
    ListView mListView;

    private MeFragmentAdapter mAdapter;
    private ArrayList<MeFragmentEntity> mList;

    private MeFragmentPresenter mPresenter;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new MeFragmentPresenter(this);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getData();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.autoRefresh();
            }
        }, 100);

        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MeFragmentEntity entity = (MeFragmentEntity) parent.getAdapter().getItem(position);
                if (entity.getType() == 0) {

                } else if (entity.getType() == 1) {
                    switch (entity.getId()) {
                        case Constants.ME_FRAGMENT_TYPE_MINE:
                            break;
                        case Constants.ME_FRAGMENT_TYPE_INVITE_CODE:
                            toMineInviteCode();
                            break;
                        case Constants.ME_FRAGMENT_TYPE_BALANCE:
                            toBalance();
                            break;
                        case Constants.ME_FRAGMENT_TYPE_RECORD:
                            toAdRecord();
                            break;
                        case Constants.ME_FRAGMENT_TYPE_NOT_RECEIVING_RED:
                            toNotReceivingRed();
                            break;
                        case Constants.ME_FRAGMENT_TYPE_COLLECT:
                            toCollect();
                            break;
                        case Constants.ME_FRAGMENT_TYPE_SETTING:
                            toSetting();
                            break;
                    }
                }
            }
        });
    }

    private void getData() {

        mList = mPresenter.getData(new Preferences(getActivity()).getToken());
        if (mList != null) {
            mAdapter = new MeFragmentAdapter(getActivity(), mList);
            mListView.setAdapter(mAdapter);
            mPtr.refreshComplete();
        }
    }

    @Override
    protected void initData() {

    }

    /**
     * 跳转个人中心
     */
    @Override
    public void toMeInfomation() {

    }

    /**
     * 跳转我的邀请码
     */
    @Override
    public void toMineInviteCode() {
        Intent intent = new Intent(getActivity(), MineInviteCodeActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转余额
     */
    @Override
    public void toBalance() {
        Intent intent = new Intent(getActivity(), BalanceActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转发广告记录
     */
    @Override
    public void toAdRecord() {
        Intent intent = new Intent(getActivity(), SendRedEnvelopeRecordClassifyActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转未领取红包
     */
    @Override
    public void toNotReceivingRed() {
        Intent intent = new Intent(getActivity(), NotReceivingRedActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转收藏
     */
    @Override
    public void toCollect() {
        Intent intent = new Intent(getActivity(), CollectRedEnvelopeListActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转设置
     */
    @Override
    public void toSetting() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void refresh(ArrayList<MeFragmentEntity> list) {
        if (list != null) {
            mList = list;
            mAdapter = new MeFragmentAdapter(getActivity(), mList);
            mListView.setAdapter(mAdapter);
        }
        mPtr.refreshComplete();
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
