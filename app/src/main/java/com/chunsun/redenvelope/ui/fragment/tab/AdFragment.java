package com.chunsun.redenvelope.ui.fragment.tab;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.event.SelectAdDelaySecondsRateEvent;
import com.chunsun.redenvelope.presenter.impl.AdFragmentPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.SelectListInfoActivity;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdNextPageActivity;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.view.IAdFragment;
import com.chunsun.redenvelope.utils.StringUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 发广告Fragment
 */
public class AdFragment extends BaseFragment implements IAdFragment, View.OnClickListener {

    @Bind(R.id.rg_send_type)
    RadioGroup mRgSendType;
    @Bind(R.id.rb_immediate_send)
    RadioButton mRbImmediate;
    @Bind(R.id.rb_time_send)
    RadioButton mRbTime;
    @Bind(R.id.et_price)
    EditText mEtPrice;
    @Bind(R.id.iv_explain)
    ImageView mIvExplain;
    @Bind(R.id.et_num)
    EditText mEtNum;
    @Bind(R.id.et_days)
    EditText mEtDays;
    @Bind(R.id.tv_show_time)
    TextView mTvShowTime;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tb_need_invoice)
    ToggleButton mTbInvoice;
    @Bind(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @Bind(R.id.btn_next_step)
    Button mBtnNextStep;
    @Bind(R.id.ll_send_days_container)
    LinearLayout mLLSendDays;
    @Bind(R.id.ll_show_time_container)
    LinearLayout mLLShowTime;
    @Bind(R.id.ll_time_container)
    LinearLayout mLLTime;
    @Bind(R.id.ll_invoice_container)
    LinearLayout mLLInvoice;

    private AdFragmentPresenter mPresenter;
    private AdEntity mAdEntity;

    private List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> mDelaySecondsRate;

    public AdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new AdFragmentPresenter(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {

        initEvent();
    }

    private void initEvent() {

        mIvExplain.setOnClickListener(this);
        mLLShowTime.setOnClickListener(this);
        mBtnNextStep.setOnClickListener(this);

        mRgSendType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_immediate_send:
                        mLLSendDays.setVisibility(View.GONE);
                        mLLTime.setVisibility(View.GONE);
                        break;
                    case R.id.rb_time_send:
                        mLLSendDays.setVisibility(View.VISIBLE);
                        mLLTime.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        mEtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = s.toString();

                int index = 0;

                if (!TextUtils.isEmpty(price)) {
                    if ("0.01".equals(price)) {
                        index = 0;
                        mLLShowTime.setEnabled(false);
                    } else if ("0.02".equals(price)) {
                        index = 1;
                        mLLShowTime.setEnabled(true);
                    } else {
                        index = 2;
                        mLLShowTime.setEnabled(true);
                    }

                    for (int i = 0; i < mDelaySecondsRate.size(); i++) {
                        AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity entity = mDelaySecondsRate.get(i);
                        if (index == i) {
                            entity.setCheck(true);
                            mTvShowTime.setText(entity.getDelay_seconds() + "秒");
                            calcAdTotalPrice(price, StringUtil.textview2String(mEtNum), StringUtil.textview2String(mEtDays));
                        } else {
                            entity.setCheck(false);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = s.toString();
                calcAdTotalPrice(StringUtil.textview2String(mEtPrice), num, StringUtil.textview2String(mEtDays));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String days = s.toString();
                calcAdTotalPrice(StringUtil.textview2String(mEtPrice), StringUtil.textview2String(mEtNum), days);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        //判断是否是企业用户
        if (MainApplication.getContext().getUserEntity() != null) {
            if (Constants.REGISTER_TYPE_COMPANY.equals(MainApplication.getContext().getUserEntity().getType())) {
                mLLInvoice.setVisibility(View.VISIBLE);
            } else {
                mLLInvoice.setVisibility(View.GONE);
            }
        }
        mPresenter.getAdDelaySecondsRate();
    }


    @Override
    public void setDelaySecondsRateData(List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> result) {
        mDelaySecondsRate = result;
        initDefaultData();
    }

    @Override
    public void toSelectDelaySeconds() {
        String price = mAdEntity.getPrice();
        if (StringUtil.isAvailAmout(price) && !"0.01".equals(price)) {
            ArrayList<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> list = new ArrayList<>();
            if (price.equals("0.02")) {
                list.add(mDelaySecondsRate.get(0));
                list.add(mDelaySecondsRate.get(1));
            } else if (price.equals("0.03") || price.equals("0.04")) {
                list.add(mDelaySecondsRate.get(0));
                list.add(mDelaySecondsRate.get(1));
                list.add(mDelaySecondsRate.get(2));
            } else {
                list.addAll(mDelaySecondsRate);
            }
            Intent intent = new Intent(getActivity(), SelectListInfoActivity.class);
            intent.putExtra(Constants.EXTRA_KEY_TITLE, "广告显示时间");
            intent.putParcelableArrayListExtra(Constants.EXTRA_LIST_KEY, list);
            startActivity(intent);
        }
    }

    @Override
    public void toAdPriceExplain() {
        Intent intent = new Intent(getActivity(), CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "说明");
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.SEND_PRICE_EXPLAIN_URL);
        startActivity(intent);
    }

    @Override
    public void toNextStep() {
        Intent intent = new Intent(getActivity(), CreateAdNextPageActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mAdEntity);
        startActivity(intent);
    }

    /**
     * 初始化广告数据
     */
    private void initDefaultData() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(date);

        mAdEntity = new AdEntity();
        mAdEntity.setDelaySeconds(mDelaySecondsRate.get(2));
        mAdEntity.setStartTime(time);

        calcAdTotalPrice(Constants.AD_DEFAULT_PRICE, Constants.AD_DEFAULT_NUM, Constants.AD_DEFAULT_DAYS);

        mEtPrice.setText(Constants.AD_DEFAULT_PRICE);
        mEtNum.setText(Constants.AD_DEFAULT_NUM);
        mEtDays.setText(Constants.AD_DEFAULT_DAYS);
        mTvTime.setText(time);
    }

    /**
     * 计算总价
     *
     * @param priceStr
     * @param numStr
     * @param daysStr
     * @return
     */
    private void calcAdTotalPrice(String priceStr, String numStr, String daysStr) {
        float price = Float.valueOf(priceStr);
        int num = Integer.valueOf(numStr);
        int days = Integer.valueOf(daysStr);

        DecimalFormat format = new DecimalFormat("0.00");
        String total = format.format(price * num * days);
        mTvTotalPrice.setText("￥" + total + "元");

        mAdEntity.setPrice(priceStr);
        mAdEntity.setDays(daysStr);
        mAdEntity.setNum(numStr);
        mAdEntity.setAdPrice(total);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_explain:
                toAdPriceExplain();
                break;
            case R.id.ll_show_time_container:
                toSelectDelaySeconds();
                break;
            case R.id.btn_next_step:
                mPresenter.toValidatePrice(mAdEntity);
                break;
        }
    }

    public void onEvent(SelectAdDelaySecondsRateEvent event) {
        int id = Integer.valueOf(event.getMsg());
        for (AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity item : mDelaySecondsRate) {
            if (item.getId() == id) {
                item.setCheck(true);
                mTvShowTime.setText(item.getDelay_seconds() + "秒");
            }else{
                item.setCheck(false);
            }
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
