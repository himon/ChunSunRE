package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.BalanceEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.event.WithdrawCashEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.WithdrawCashBankPresenter;
import com.chunsun.redenvelope.ui.activity.SelectListInfoActivity;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.IWithdrawCashBankView;
import com.chunsun.redenvelope.utils.DensityUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 银行提现Ativity
 */
public class WithdrawCashBankActivity extends MBaseActivity<IWithdrawCashBankView, WithdrawCashBankPresenter> implements IWithdrawCashBankView {

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_bank_card_num)
    EditText mEtBankCardNum;
    @Bind(R.id.ll_bank_container)
    LinearLayout mLLBank;
    @Bind(R.id.tv_bank)
    TextView mTvBank;
    @Bind(R.id.et_open_bank_name)
    EditText mEtOpenBankName;
    @Bind(R.id.ll_province_container)
    LinearLayout mLLProvince;
    @Bind(R.id.tv_province)
    TextView mTvProvince;
    @Bind(R.id.ll_city_container)
    LinearLayout mLLCity;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.rg_withdrawal_money)
    RadioGroup mRgMoney;
    @Bind(R.id.btn_apply_withdrawal)
    Button mBtnApply;

    private ArrayList<BalanceEntity.ResultEntity.CashPoundageRateEntity> mBankPoundage;
    private BalanceEntity.ResultEntity.CashPoundageRateEntity mBankEntity;

    private WithdrawCashBankPresenter mPresenter;
    private String mToken;
    /**
     * 银行列表
     */
    private ArrayList<SampleEntity> mBanks;
    /**
     * 省列表
     */
    private ArrayList<DistrictEntity.AreaEntity> mProvinceList;
    /**
     * 市列表
     */
    private ArrayList<DistrictEntity.AreaEntity.CcEntity> mCityList;
    /**
     * 选择的银行
     */
    private SampleEntity mBank;
    /**
     * 选择的省
     */
    private DistrictEntity.AreaEntity mProvince;
    /**
     * 选择的市
     */
    private DistrictEntity.AreaEntity.CcEntity mCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_cash_bank);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = (WithdrawCashBankPresenter) mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new WithdrawCashBankPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("提现到银行卡", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {

        mLLBank.setOnClickListener(this);
        mLLProvince.setOnClickListener(this);
        mLLCity.setOnClickListener(this);
        mBtnApply.setOnClickListener(this);

        mRgMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mBankPoundage != null && mBankPoundage.size() > 0) {
                    mBankEntity = mBankPoundage.get(checkedId);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mToken = new Preferences(this).getToken();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            mBankPoundage = bundle.getParcelableArrayList(Constants.EXTRA_KEY);
            buildMoneyGroup(mBankPoundage);
        }
        mBanks = mPresenter.buildBankList();
        mPresenter.initProvinceAndCity();
    }

    private void buildMoneyGroup(ArrayList<BalanceEntity.ResultEntity.CashPoundageRateEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            BalanceEntity.ResultEntity.CashPoundageRateEntity info = list.get(i);
            RadioButton rbItem = new RadioButton(this);
            rbItem.setId(i);
            rbItem.setBackgroundResource(R.drawable.selector_withdraw_cash_money);
            rbItem.setButtonDrawable(R.color.transparency);
            rbItem.setGravity(Gravity.CENTER);
            rbItem.setPadding(0, DensityUtils.dip2px(this, 5), 0,
                    DensityUtils.dip2px(this, 5));
            rbItem.setText(info.getAmount() + "元");
            rbItem.setTextSize(16);

            try {
                // 设置颜色选择器
                Resources resource = (Resources) getBaseContext()
                        .getResources();
                ColorStateList csl = (ColorStateList) resource
                        .getColorStateList(R.color.selector_radio_button_text_red_white);
                rbItem.setTextColor(csl);
            } catch (Exception e) {
                e.printStackTrace();
            }

            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(0,
                    RadioGroup.LayoutParams.WRAP_CONTENT, 1);
            params.rightMargin = DensityUtils.dip2px(this, 3);

            mRgMoney.addView(rbItem, params);
        }

        if (mRgMoney.getChildCount() > 0) {
            mRgMoney.check(0);
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.ll_bank_container:
                toBankSelect();
                break;
            case R.id.ll_province_container:
                toProvinceSelect();
                break;
            case R.id.ll_city_container:
                toCitySelect();
                break;
            case R.id.btn_apply_withdrawal:
                commit();
                break;
        }
    }

    /**
     * 提交
     */
    private void commit() {
        String name = mEtName.getText().toString();
        String bankCardNum = mEtBankCardNum.getText().toString();
        String bankName = mEtOpenBankName.getText().toString();
        mPresenter.validate(name, bankCardNum, bankName, mBank, mProvince, mCity);
    }

    private void toBankSelect() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "选择银行");
        intent.putExtra(Constants.EXTRA_LIST_KEY, mBanks);
        startActivity(intent);
    }

    private void toProvinceSelect() {
        Intent intent = new Intent(this, SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "省");
        intent.putExtra(Constants.EXTRA_LIST_KEY, mProvinceList);
        startActivity(intent);
    }

    private void toCitySelect() {
        if (mCityList == null || mCityList.size() == 0)
            return;

        Intent intent = new Intent(this,
                SelectListInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "市");
        intent.putExtra(Constants.EXTRA_LIST_KEY, mCityList);
        startActivity(intent);
    }

    @Override
    public void setAddressInfo(DistrictEntity entity) {
        hideCircleLoading();
        mProvinceList = (ArrayList<DistrictEntity.AreaEntity>) entity.getArea();
    }

    @Override
    public void toConfirm(String[] data) {
        Intent intent = new Intent(this, WithdrawCashBankConfirmActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, data);
        intent.putExtra(Constants.EXTRA_KEY2, mBankEntity);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        showCircleLoading();
    }

    @Override
    public void hideLoading() {
        hideCircleLoading();
    }

    public void onEvent(WithdrawCashEvent event) {
        switch (event.getType()) {
            case 1:
                checkBank(event.getMsg());
                break;
            case 2:
                checkProvince(event.getMsg());
                initCity(event.getMsg());
                break;
            case 3:
                checkCity(event.getMsg());
                break;
        }
    }

    private void checkBank(String bank) {
        mTvBank.setText(bank);
        for (SampleEntity item : mBanks) {
            if (item.getValue().equals(bank)) {
                item.setCheck(true);
                mBank = item;
            }
        }
    }

    private void checkCity(String city) {
        mTvCity.setText(city);
        for (DistrictEntity.AreaEntity.CcEntity item : mCityList) {
            if (item.getC().equals(city)) {
                item.setCheck(true);
                mCity = item;
            }
        }
    }

    private void checkProvince(String province) {
        mTvProvince.setText(province);
        for (DistrictEntity.AreaEntity item : mProvinceList) {
            if (item.getP().equals(province)) {
                item.setCheck(true);
                mProvince = item;
            }
        }
    }

    private void initCity(String province) {
        for (DistrictEntity.AreaEntity item : mProvinceList) {
            if (province.equals(item.getP())) {
                mCityList = item.getCc();
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
