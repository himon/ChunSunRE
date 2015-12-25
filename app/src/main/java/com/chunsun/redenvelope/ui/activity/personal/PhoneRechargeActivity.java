package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity.ResultEntity.CzPoundageEntity;
import com.chunsun.redenvelope.presenter.PhoneRechargePresenter;
import com.chunsun.redenvelope.ui.adapter.PhoneRechargeAdapter;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.ui.view.IPhoneRechargeView;
import com.chunsun.redenvelope.utils.StringUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 手机话费充值
 */
public class PhoneRechargeActivity extends BaseActivity implements IPhoneRechargeView, View.OnClickListener {

    @Bind(R.id.et_phone)
    EditText mEtPhoneNum;
    @Bind(R.id.lv_recharge)
    ListView mLvRechargeList;

    private PhoneRechargePresenter mPresenter;
    private ArrayList<CzPoundageEntity> mCzPoundageEntityList;
    private PhoneRechargeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_recharge);
        ButterKnife.bind(this);
        mPresenter = new PhoneRechargePresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("手机话费充值", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            mCzPoundageEntityList = bundle.getParcelableArrayList(Constants.EXTRA_KEY);
            mAdapter = new PhoneRechargeAdapter(this, mCzPoundageEntityList, R.layout.adapter_recharge_phone_money_item_view);
            mLvRechargeList.setAdapter(mAdapter);
            mLvRechargeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CzPoundageEntity entity = (CzPoundageEntity) parent.getAdapter().getItem(position);
                    mPresenter.validatePhoneNum(entity, StringUtil.textview2String(mEtPhoneNum));
                }
            });
        }
    }

    @Override
    public void toRechargePhoneConfirm(BalanceEntity.ResultEntity.CzPoundageEntity entity, String phonenum){
        Intent intent = new Intent(this, PhoneRechargeConfirmActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, phonenum);
        intent.putExtra(Constants.EXTRA_KEY2, entity);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
        }
    }
}
