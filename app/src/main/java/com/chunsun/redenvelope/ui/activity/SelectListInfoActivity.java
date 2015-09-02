package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.event.SelectAdDelaySecondsRateEvent;
import com.chunsun.redenvelope.ui.adapter.SelectListInfoAdaper;
import com.chunsun.redenvelope.ui.base.BaseActivity;
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
    private ArrayList<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> mList;

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
                AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity entity = (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) parent.getAdapter().getItem(position);
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
    public void selectAdDelaySecondsRate(AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity entity) {

        for (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity item : mList) {
            item.setCheck(false);
        }
        entity.setCheck(true);
        mAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new SelectAdDelaySecondsRateEvent(entity.getId() + ""));
        back();
    }
}
