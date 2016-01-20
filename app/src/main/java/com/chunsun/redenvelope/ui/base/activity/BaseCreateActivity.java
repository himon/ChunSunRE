package com.chunsun.redenvelope.ui.base.activity;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.DistrictEntity;
import com.chunsun.redenvelope.presenter.CreateAdPresenter;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/23 16:28
 * @des 创建红包和圈子省市、范围页面的父类
 */
public abstract class BaseCreateActivity<V, T extends BasePresenter<V>> extends MBaseActivity {

    @Bind(R.id.main_nav)
    protected RelativeLayout mToolsBar;
    @Bind(R.id.picker_ui_view)
    protected PickerUI mPickerUI;
    @Bind(R.id.tv_province)
    protected TextView mTvProvince;
    @Bind(R.id.tv_city)
    protected TextView mTvCity;
    @Bind(R.id.tv_range)
    protected TextView mTvRange;

    protected CreateAdPresenter mPresenter;
    /**
     * 封装广告数据
     */
    protected AdEntity mAdEntity = new AdEntity();
    /**
     * 距离列表
     */
    protected ArrayList<SampleEntity> mDistanceList;
    /**
     * 广告类型列表
     */
    protected ArrayList<SampleEntity> mTypeList;
    /**
     * 地址列表
     */
    protected ArrayList<DistrictEntity.AreaEntity> mDistrictList;
    /**
     * 当前省信息
     */
    protected DistrictEntity.AreaEntity mCurrentProvince;
    /**
     * 当前市信息
     */
    protected DistrictEntity.AreaEntity.CcEntity mCurrentCity;
    /**
     * 默认省信息
     */
    protected DistrictEntity.AreaEntity mDefaultProvince;
    /**
     * 默认市信息
     */
    protected DistrictEntity.AreaEntity.CcEntity mDefaultCity;
    /**
     * 标示正在选择选项
     */
    protected String mCurrentSelectType;
    /**
     * pickUi选中的item
     */
    protected int currentPosition = 1;
    /**
     * PickerUi显示的数据列表
     */
    protected List<String> options;

    /**
     * 初始化TitleBar
     */
    protected void initTitle() {
        initTitleBar("类型范围", "", "", Constants.TITLE_TYPE_SAMPLE);
    }

    /**
     * 获取范围
     *
     * @return
     */
    protected List<String> getDistance() {
        List<String> list = new ArrayList<>();
        for (SampleEntity item : mDistanceList) {
            list.add(item.getValue());
        }
        return list;
    }

    /**
     * 获取城市
     *
     * @return
     */
    protected List<String> getCity() {
        List<String> list = new ArrayList<>();
        DistrictEntity.AreaEntity province = mAdEntity.getProvince();
        for (DistrictEntity.AreaEntity.CcEntity item : province.getCc()) {
            list.add(item.getC());
        }
        return list;
    }

    /**
     * 获取省份
     *
     * @return
     */
    protected List<String> getProvince() {
        List<String> list = new ArrayList<>();
        for (DistrictEntity.AreaEntity item : mDistrictList) {
            list.add(item.getP());
        }
        return list;
    }

    /**
     * 弹出PickerUI
     */
    protected void select() {
        PickerUISettings pickerUISettings = new PickerUISettings.Builder()
                .withItems(options).withBackgroundColor(-1)
                .withAutoDismiss(false)// 自动消失
                .withItemsClickables(true)// 选中状态
                .withUseBlur(false).build();
        mPickerUI.setSettings(pickerUISettings);
        mPickerUI.slide(0);
    }

    protected void chooseFinish(int currentPosition) {
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

    protected void setEvent() {
        mPickerUI.setColorTextCenter(R.color.background_picker);
        mPickerUI.setColorTextNoCenter(R.color.background_picker);
        mPickerUI.setBackgroundColorPanel(R.color.background_picker);
        mPickerUI.setLinesColor(getResources().getColor(
                R.color.background_picker));
        mPickerUI.setItemsClickables(true);
        mPickerUI.setAutoDismiss(false);
    }
}
