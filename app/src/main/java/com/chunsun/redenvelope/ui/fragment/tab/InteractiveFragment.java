package com.chunsun.redenvelope.ui.fragment.tab;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.InteractivePlatformPresenter;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.adapter.InteractivePlatformAdapter;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.view.IInteractivePlatformView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.utils.helper.InteractiveHelper;
import com.chunsun.redenvelope.widget.GetMoreListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractiveFragment extends BaseFragment implements IInteractivePlatformView, View.OnClickListener {

    @Bind(R.id.ptr_main)
    PtrClassicFrameLayout mPtr;
    @Bind(R.id.gmlv_main)
    GetMoreListView mListView;
    @Bind(R.id.et_comment)
    EditText mEtComment;
    @Bind(R.id.btn_send_comment)
    Button mBtnSendComment;

    TextView mTvTitle;
    TextView mTvContent;
    TextView mTvTime;
    RadioGroup mRgType;
    RadioButton mRbCountry;
    RadioButton mRbLocal;

    private InteractivePlatformPresenter mPresenter;
    private InteractivePlatformAdapter mDataAdapter;
    //全国列表
    private List<InteractiveEntity.ResultEntity.ListEntity> mListCountry = new ArrayList<>();
    //本地列表
    private List<InteractiveEntity.ResultEntity.ListEntity> mListLocal = new ArrayList<>();
    //是否是下拉刷新
    private boolean isRefresh;
    //当前全国显示页数
    private int mCurrentCountryPage = 1;
    //当前本地显示页数
    private int mCurrentLocalPage = 1;
    //全国列表总数
    private int mTotalCountry = 0;
    //本地列表总数
    private int mTotalLocal = 0;
    //标示当前列表显示的是全国还是本地， 0 ： 全国， 1 ： 本地
    private int mCurrentCheckType = 0;
    //本地评论是否已滑动到底
    private boolean isLocalFinished = false;

    private String mToken;
    /**
     * 互动平台帮助类
     */
    private InteractiveHelper mInteractiveHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interaction, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new InteractivePlatformPresenter(this);
        mInteractiveHelper = new InteractiveHelper(getActivity());
        initView();
        initData();
        return view;
    }


    @Override
    protected void initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_interactive_platform_top_item_view, null);
        mTvTitle = (TextView) view.findViewById(R.id.tv_system_title);
        mTvContent = (TextView) view.findViewById(R.id.tv_system_content);
        mTvTime = (TextView) view.findViewById(R.id.tv_system_content_time);
        mRgType = (RadioGroup) view.findViewById(R.id.rg_record_type);
        mRbCountry = (RadioButton) view.findViewById(R.id.rb_comment_country);
        mRbLocal = (RadioButton) view.findViewById(R.id.rb_comment_local);

        /**
         * ListView
         */
        mListView.addHeaderView(view);
        mListView.setOnGetMoreListener(new GetMoreListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                isRefresh = false;
                getData();
            }
        });

        mDataAdapter = new InteractivePlatformAdapter(getActivity(), mListCountry, mListLocal, mCurrentCheckType, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head_logo:
                        Object tag = v.getTag();
                        toUserRewardActivity(tag.toString());
                        break;
                }
            }
        });
        mListView.setAdapter(mDataAdapter);

        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mListView.setHasMore();
                isRefresh = true;
                isLocalFinished = false;
                getAllData();
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
        mRbCountry.setOnClickListener(this);
        mRbLocal.setOnClickListener(this);
        mBtnSendComment.setOnClickListener(this);

        //设置发送按钮不可点击
        mBtnSendComment.setEnabled(false);
        mEtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(StringUtil.textview2String(mEtComment))) {
                    mBtnSendComment.setTextColor(getResources().getColor(
                            R.color.font_hint_gray));
                    mBtnSendComment
                            .setBackgroundResource(R.drawable.shape_radius_gray);
                    mBtnSendComment.setEnabled(false);
                } else {
                    mBtnSendComment.setTextColor(getResources()
                            .getColor(R.color.white));
                    mBtnSendComment.setBackgroundResource(R.drawable.shape_radius_red);
                    mBtnSendComment.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    public void getAllData() {
        mToken = new Preferences(getActivity()).getToken();
        if (isRefresh) {
            mCurrentCountryPage = 1;
            mListCountry.clear();
            mCurrentLocalPage = 1;
            mListLocal.clear();
            mCurrentCheckType = 0;
        }
        mPresenter.getCountryList(mToken, mCurrentCountryPage);
        mPresenter.getLocalList(mToken, mCurrentLocalPage);
    }


    @Override
    public void showLoading() {
        ((MainActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((MainActivity) getActivity()).hideLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_comment_country:
                mCurrentCheckType = 0;
                mListView.setHasMore();
                changerDataList();
                break;
            case R.id.rb_comment_local:
                mCurrentCheckType = 1;
                if (isLocalFinished) {
                    mListView.setNoMore();
                } else {
                    mListView.setHasMore();
                }
                changerDataList();
                break;
            case R.id.btn_send_comment://评论
                mPresenter.sendComment(mToken, mCurrentCheckType, StringUtil.textview2String(mEtComment));
                mEtComment.setText("");
                break;
        }
    }

    @Override
    public void setCountryList(InteractiveEntity entity) {
        List<InteractiveEntity.ResultEntity.ListEntity> list = entity.getResult().getList();
        mTotalCountry = Integer.parseInt(entity.getResult().getTotal_count());
        if (mCurrentCheckType == 0 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
        }
        mCurrentCountryPage++;
        mListCountry.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        if (isRefresh) {
            setNoticeBoard(entity.getResult().getNotice());
        }
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void setLocalList(InteractiveEntity entity) {
        List<InteractiveEntity.ResultEntity.ListEntity> list = entity.getResult().getList();
        mTotalLocal = Integer.parseInt(entity.getResult().getTotal_count());
        mRbLocal.setText(MainApplication.getContext().getCity());
        if (mCurrentCheckType == 1 && list.size() < Constants.PAGE_NUM) {
            //设置没有更多的数据了,不再显示加载更多按钮
            mListView.setNoMore();
            isLocalFinished = true;
        }
        mCurrentLocalPage++;
        mListLocal.addAll(list);
        mDataAdapter.notifyDataSetChanged();
        //加载更多完成
        mListView.getMoreComplete();
        mPtr.refreshComplete();
    }

    @Override
    public void commentSuccess() {
        switch (mCurrentCheckType) {
            case 0:
                mCurrentCountryPage = 1;
                mListCountry.clear();
                mPresenter.getCountryList(mToken, mCurrentCountryPage);
                break;
            case 1:
                mCurrentLocalPage = 1;
                mListLocal.clear();
                mPresenter.getLocalList(mToken, mCurrentLocalPage);
                break;
        }
    }

    /**
     * 跳转用户奖励页面
     */
    @Override
    public void toUserRewardActivity(String id) {
        mInteractiveHelper.toUserRewardActivity(id, mCurrentCountryPage);
    }

    /**
     * 设置系统公告内容
     *
     * @param notice
     */
    @Override
    public void setNoticeBoard(List<InteractiveEntity.ResultEntity.NoticeEntity> notice) {
        InteractiveEntity.ResultEntity.NoticeEntity noticeEntity = notice.get(0);
        mInteractiveHelper.setNoticeBoard(noticeEntity, mTvTitle, mTvContent, mTvTime);
    }

    @Override
    public void toLogin() {
        mInteractiveHelper.toLogin();
    }

    /**
     * 切换列表
     */
    public void changerDataList() {
        mDataAdapter.setData(mListCountry, mListLocal, mCurrentCheckType);
        mDataAdapter.notifyDataSetChanged();
    }

    public void getData() {
        if (mCurrentCheckType == 0) {
            if (mCurrentCountryPage * Constants.PAGE_NUM > mTotalCountry + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getCountryList(mToken, mCurrentCountryPage);
        } else {
            if (mCurrentLocalPage * Constants.PAGE_NUM > mTotalLocal + Constants.PAGE_NUM) {
                mListView.getMoreComplete();
                mPtr.refreshComplete();
                return;
            }
            mPresenter.getLocalList(mToken, mCurrentLocalPage);
        }
    }
}
