package com.chunsun.redenvelope.ui.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.SampleEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;
import com.chunsun.redenvelope.model.entity.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.CreateAdPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.fragment.mengban.MengBanCreateAdFragment;
import com.chunsun.redenvelope.ui.view.ICreateAdView;
import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建广告首页
 */
public class CreateAdActivity extends BaseActivity implements ICreateAdView, View.OnClickListener {

    @Bind(R.id.tools_bar)
    RelativeLayout mToolsBar;
    @Bind(R.id.btn_company)
    RadioButton mRbCompany;
    @Bind(R.id.btn_repeat)
    RadioButton mRbRepeat;
    @Bind(R.id.btn_link)
    RadioButton mRbLink;
    @Bind(R.id.btn_lift)
    RadioButton mRbLift;
    @Bind(R.id.btn_near)
    RadioButton mRbNear;
    @Bind(R.id.rl_province)
    RelativeLayout mRlProvince;
    @Bind(R.id.rl_city)
    RelativeLayout mRlCity;
    @Bind(R.id.rl_range)
    RelativeLayout mRlRange;
    @Bind(R.id.tv_province)
    TextView mTvProvince;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.tv_range)
    TextView mTvRange;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;
    @Bind(R.id.picker_ui_view)
    PickerUI mPickerUI;


    private CreateAdPresenter mPresenter;
    private int currentPosition = 1;
    /**
     * 标示正在选择选项
     */
    private String mCurrentSelectType;
    /**
     * 封装广告数据
     */
    private AdEntity mAdEntity = new AdEntity();
    /**
     * 距离列表
     */
    private ArrayList<SampleEntity> mDistanceList;
    /**
     * 广告类型列表
     */
    private ArrayList<SampleEntity> mTypeList;
    /**
     * 地址列表
     */
    private ArrayList<DistrictEntity.AreaEntity> mDistrictList;
    /**
     * PickerUi显示的数据列表
     */
    private List<String> options;

    //追加的广告信息
    private RedSuperadditionEntity.ResultEntity mSuperadditionEntity;
    private MengBanCreateAdFragment mMengBanCreateAdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);
        ButterKnife.bind(this);
        mPresenter = new CreateAdPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitle();
        initEvent();
        isFirstSendAd();
    }

    private void isFirstSendAd() {
        Preferences preferences = new Preferences(this);
        if (preferences.getFirstSendAd()) {
            preferences.setFirstSendAd(false);
            mMengBanCreateAdFragment = new MengBanCreateAdFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.root, mMengBanCreateAdFragment).commit();
        }
    }

    private void initTitle() {
        initTitleBar("", "首页", "", Constants.TITLE_TYPE_SAMPLE);
        mToolsBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mRbCompany.setOnClickListener(this);
        mRbLift.setOnClickListener(this);
        mRbLink.setOnClickListener(this);
        mRbNear.setOnClickListener(this);
        mRbRepeat.setOnClickListener(this);
        mRlProvince.setOnClickListener(this);
        mRlCity.setOnClickListener(this);
        mRlRange.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

        mPickerUI.setColorTextCenter(R.color.background_picker);
        mPickerUI.setColorTextNoCenter(R.color.background_picker);
        mPickerUI.setBackgroundColorPanel(R.color.background_picker);
        mPickerUI.setLinesColor(getResources().getColor(
                R.color.background_picker));
        mPickerUI.setItemsClickables(true);
        mPickerUI.setAutoDismiss(false);

        mPickerUI
                .setOnClickItemPickerUIListener(new PickerUI.PickerUIItemClickListener() {

                    @Override
                    public void onItemClickPickerUI(int which, int position,
                                                    String valueResult) {
                        currentPosition = position;
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String item = (String) parent.getAdapter().getItem(
                                position - 2);
                        chooseFinish(position - 2);
                    }
                });
    }


    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            mSuperadditionEntity = intent.getParcelableExtra(Constants.EXTRA_KEY);
        }

        mPresenter.getData(mAdEntity, mSuperadditionEntity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_company:
                mAdEntity.setType(mTypeList.get(1));
                initSelected(true);
                break;
            case R.id.btn_repeat:
                mAdEntity.setType(mTypeList.get(4));
                initSelected(true);
                break;
            case R.id.btn_link:
                mAdEntity.setType(mTypeList.get(3));
                initSelected(true);
                break;
            case R.id.btn_lift:
                mAdEntity.setType(mTypeList.get(0));
                initSelected(true);
                break;
            case R.id.btn_near:
                mAdEntity.setType(mTypeList.get(2));
                initSelected(false);
                break;
            case R.id.rl_province:
                mCurrentSelectType = "province";
                options = getProvince();
                select();
                break;
            case R.id.rl_city:
                mCurrentSelectType = "city";
                options = getCity();
                select();
                break;
            case R.id.rl_range:
                mCurrentSelectType = "range";
                options = getDistance();
                select();
                break;
            case R.id.btn_next_step:
                nextCreateActivity();
                break;
        }
    }

    /**
     * 弹出PickerUI
     */
    private void select() {
        PickerUISettings pickerUISettings = new PickerUISettings.Builder()
                .withItems(options).withBackgroundColor(-1)
                .withAutoDismiss(false)// 自动消失
                .withItemsClickables(true)// 选中状态
                .withUseBlur(false).build();

        mPickerUI.setSettings(pickerUISettings);
        mPickerUI.slide(0);
    }

    /**
     * 切换页面显示
     *
     * @param flag
     */
    private void initSelected(boolean flag) {
        if (flag) {
            mRlProvince.setVisibility(View.VISIBLE);
            mRlCity.setVisibility(View.VISIBLE);
            mRlRange.setVisibility(View.GONE);
        } else {
            mRlProvince.setVisibility(View.GONE);
            mRlCity.setVisibility(View.GONE);
            mRlRange.setVisibility(View.VISIBLE);
        }
    }

    private void chooseFinish(int currentPosition) {
        mPickerUI.slide(currentPosition);
        String str = options.get(currentPosition);
        if ("province".equals(mCurrentSelectType)) {
            for (DistrictEntity.AreaEntity item : mDistrictList) {
                if (str.equals(item.getP())) {
                    mAdEntity.setProvince(item);
                    mTvProvince.setText(item.getP());
                    // 设置城区
                    ArrayList<DistrictEntity.AreaEntity.CcEntity> cc = item.getCc();
                    mAdEntity.setCity(cc.get(0));
                    mTvCity.setText(cc.get(0).getC());
                    break;
                }
            }
        } else if ("city".equals(mCurrentSelectType)) {
            DistrictEntity.AreaEntity province = mAdEntity.getProvince();
            ArrayList<DistrictEntity.AreaEntity.CcEntity> cityList = province.getCc();
            for (DistrictEntity.AreaEntity.CcEntity item : cityList) {
                if (str.equals(item.getC())) {
                    mAdEntity.setCity(item);
                    mTvCity.setText(item.getC());
                    break;
                }
            }
        } else if ("range".equals(mCurrentSelectType)) {
            for (SampleEntity item : mDistanceList) {
                if (str.equals(item.getValue())) {
                    mAdEntity.setDistance(item);
                    mTvRange.setText(item.getValue());
                    break;
                }
            }
        }
    }

    @Override
    public void setInitData(ArrayList<SampleEntity> distanceList, ArrayList<SampleEntity> typeList, ArrayList<DistrictEntity.AreaEntity> districtList, AdEntity adEntity) {
        mDistanceList = distanceList;
        mTypeList = typeList;
        mDistrictList = districtList;
        this.mAdEntity = adEntity;

        setStatus();
    }

    @Override
    public void nextCreateActivity() {
        Intent intent = null;
        if ((Constants.RED_DETAIL_TYPE_REPEAT + "").equals(mAdEntity.getType().getKey())) {
            intent = new Intent(this, CreateAdRepeatNextStepActivity.class);
        } else {
            intent = new Intent(this, CreateAdNextStepActivity.class);
        }
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        intent.putExtra(Constants.EXTRA_KEY2, mSuperadditionEntity);
        startActivity(intent);
    }

    private List<String> getDistance() {
        List<String> list = new ArrayList<String>();
        for (SampleEntity item : mDistanceList) {
            list.add(item.getValue());
        }
        return list;
    }

    private List<String> getCity() {
        List<String> list = new ArrayList<>();
        DistrictEntity.AreaEntity province = mAdEntity.getProvince();
        for (DistrictEntity.AreaEntity.CcEntity item : province.getCc()) {
            list.add(item.getC());
        }
        return list;
    }

    private List<String> getProvince() {
        List<String> list = new ArrayList<String>();
        for (DistrictEntity.AreaEntity item : mDistrictList) {
            list.add(item.getP());
        }
        return list;
    }


    /**
     * 设置页面控件显示数据
     */
    public void setStatus() {

        if (("" + Constants.RED_DETAIL_TYPE_LEFT).equals(mAdEntity.getType().getKey())) {
            mRbLift.setChecked(true);
        } else if (("" + Constants.RED_DETAIL_TYPE_COMPANY).equals(mAdEntity.getType().getKey())) {
            mRbCompany.setChecked(true);
        } else if (("" + Constants.RED_DETAIL_TYPE_NEAR).equals(mAdEntity.getType().getKey())) {
            mRbNear.setChecked(true);
            mTvRange.setText(mAdEntity.getDistance().getValue());
            initSelected(false);
        } else if (("" + Constants.RED_DETAIL_TYPE_LINK).equals(mAdEntity.getType().getKey())) {
            mRbLink.setChecked(true);
        } else if (("" + Constants.RED_DETAIL_TYPE_REPEAT).equals(mAdEntity.getType().getKey())) {
            mRbRepeat.setChecked(true);
        }

        mTvProvince.setText(mAdEntity.getProvince().getP());
        mTvCity.setText(mAdEntity.getCity().getC());
    }

    /**
     * 蒙版被点击
     */
    public void mengBanClick() {
        getSupportFragmentManager().beginTransaction()
                .remove(mMengBanCreateAdFragment).commit();
        mMengBanCreateAdFragment = null;
    }
}
