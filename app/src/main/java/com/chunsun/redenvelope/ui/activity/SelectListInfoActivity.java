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
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.event.SelectAdDelaySecondsRateEvent;
import com.chunsun.redenvelope.model.event.SelectAdNextPageEvent;
import com.chunsun.redenvelope.ui.adapter.SelectListInfoAdaper;
import com.chunsun.redenvelope.ui.base.BaseActivity;
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

        if (entity instanceof AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) {
            AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySecondsRateEntity = (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity) entity;
            EventBus.getDefault().post(new SelectAdDelaySecondsRateEvent(delaySecondsRateEntity.getId() + ""));
        } else if (entity instanceof SampleEntity) {
            SampleEntity sampleEntity = (SampleEntity) entity;
            EventBus.getDefault().post(new SelectAdNextPageEvent(sampleEntity));
        } else if (entity instanceof DistrictEntity.AreaEntity) {
            DistrictEntity.AreaEntity areaEntity = (DistrictEntity.AreaEntity) entity;
            SampleEntity sampleEntity = new SampleEntity();
            sampleEntity.setType(Constants.AD_SELECT_LIST_PROVINCE);
            sampleEntity.setKey(areaEntity.getP());
            EventBus.getDefault().post(new SelectAdNextPageEvent(sampleEntity));
        } else if (entity instanceof DistrictEntity.AreaEntity.CcEntity) {
            DistrictEntity.AreaEntity.CcEntity ccEntity = (DistrictEntity.AreaEntity.CcEntity) entity;
            SampleEntity sampleEntity = new SampleEntity();
            sampleEntity.setType(Constants.AD_SELECT_LIST_CITY);
            sampleEntity.setKey(ccEntity.getC());
            EventBus.getDefault().post(new SelectAdNextPageEvent(sampleEntity));
        }

        for (SelectListBase item : mList) {
            item.setCheck(false);
        }

        entity.setCheck(true);
        mAdapter.notifyDataSetChanged();
        back();
    }
}
