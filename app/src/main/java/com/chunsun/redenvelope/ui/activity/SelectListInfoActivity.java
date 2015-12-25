package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.event.EditUserInfoEvent;
import com.chunsun.redenvelope.event.SelectAdDelaySecondsRateEvent;
import com.chunsun.redenvelope.ui.adapter.SelectListInfoAdaper;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.base.SelectListBase;
import com.chunsun.redenvelope.ui.view.ISelectListInfoView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 选择列表Activity
 */
public class SelectListInfoActivity extends BaseActivity implements ISelectListInfoView, View.OnClickListener {

    @Bind(R.id.lv_main)
    ListView mLvMain;

    private SelectListInfoAdaper mAdapter;
    private ArrayList<SelectListBase> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list_info);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);

        mLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectListBase entity = (SelectListBase) parent.getAdapter().getItem(position);
                selectAdDelaySecondsRate(entity);
            }
        });
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(Constants.EXTRA_KEY_TITLE);
            mList = intent.getParcelableArrayListExtra(Constants.EXTRA_LIST_KEY);
            initTitleBar(title, "", "", Constants.TITLE_TYPE_SAMPLE);

            mAdapter = new SelectListInfoAdaper(this, mList, R.layout.adapter_select_list_info);
            mLvMain.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                back();
                break;
        }
    }

    @Override
    public void selectAdDelaySecondsRate(SelectListBase entity) {

        if (entity instanceof AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) {//创建广告，选择延迟时间
            AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySecondsRateEntity = (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) entity;
            EventBus.getDefault().post(new SelectAdDelaySecondsRateEvent(delaySecondsRateEntity.getId() + ""));
        } else if (entity instanceof SampleEntity) {
            switch (((SampleEntity) entity).getType()) {
                case Constants.EDIT_TYPE_SEX://修改用户基本信息性别
                    EventBus.getDefault().post(new EditUserInfoEvent(Constants.EDIT_TYPE_SEX, ((SampleEntity) entity).getValue()));
                    break;
                case Constants.EDIT_TYPE_JOB://修改用户基本信息职业
                    EventBus.getDefault().post(new EditUserInfoEvent(Constants.EDIT_TYPE_JOB, ((SampleEntity) entity).getValue()));
                    break;
                default:
            }
        }

        for (SelectListBase item : mList) {
            item.setCheck(false);
        }

        entity.setCheck(true);
        mAdapter.notifyDataSetChanged();
        back();
    }
}
