package com.chunsun.redenvelope.ui.fragment;


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
import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.presenter.impl.AdFragmentPresenter;
import com.chunsun.redenvelope.ui.base.BaseFragment;
import com.chunsun.redenvelope.ui.view.IAdFragment;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    private float mMinPrice;
    private float mMinAmount;
    private List<AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity> mDelaySecondsRate;
    private AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity mChooseDelaySecondsRate;

    public AdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new AdFragmentPresenter(this);
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

        mEtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String price = s.toString();
                if (!TextUtils.isEmpty(price)) {
                    if ("0.01".equals(price)) {
                        mChooseDelaySecondsRate = mDelaySecondsRate.get(0);
                    } else if ("0.02".equals(price)) {
                        mChooseDelaySecondsRate = mDelaySecondsRate.get(1);
                    } else {
                        mChooseDelaySecondsRate = mDelaySecondsRate.get(2);
                    }
                    mTvShowTime.setText(mChooseDelaySecondsRate.getDelay_seconds());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

        mPresenter.getAdDelaySecondsRate();
    }


    @Override
    public void setDelaySecondsRateData(AdDelaySecondsRateEntity.ResultEntity result) {
        mMinPrice = Float.parseFloat(result.getHb_min_price());
        mMinAmount = Float.parseFloat(result.getHb_min_amount());
        mDelaySecondsRate = result.getDelay_seconds_rate();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_explain:
                break;
        }
    }
}
