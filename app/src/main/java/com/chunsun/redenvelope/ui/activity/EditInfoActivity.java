package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.impl.EditInfoPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IEditInfoView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 编辑信息Activity
 */
public class EditInfoActivity extends BaseActivity implements View.OnClickListener, IEditInfoView {

    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.iv_delete_text)
    ImageView mIvDeleteContent;

    private String mId;
    private String mTitle;
    private String mText;
    private String mType;

    private EditInfoPresenter mPresenter;
    private String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ButterKnife.bind(this);
        mPresenter = new EditInfoPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initEvent();
    }

    private void initEvent() {

        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(StringUtil.textview2String(mEtContent))) {
                    mIvDeleteContent.setVisibility(View.GONE);
                } else {
                    mIvDeleteContent.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

        mToken = new Preferences(this).getToken();

        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getStringExtra(Constants.EXTRA_KEY_ID);
            mTitle = intent.getStringExtra(Constants.EXTRA_KEY_TITLE);
            mText = intent.getStringExtra(Constants.EXTRA_KEY_TEXT);
            mType = intent.getStringExtra(Constants.EXTRA_KEY_TYPE);
        }
        initTitleBar(mTitle, "", mText, Constants.TITLE_TYPE_SAMPLE);

        if (Constants.EXTRA_KEY_TYPE_COMPLAINT.equals(mType)) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.tv_nav_right:
                mPresenter.complaintRedEnvelope(mToken, mId, StringUtil.textview2String(mEtContent));
                break;
        }
    }

    @Override
    public void contentIsEmpty() {
        ShowToast.Short("填写内容不能为空！");
    }

    /**
     * 举报成功
     *
     * @param msg
     */
    @Override
    public void complaintRedEnvelopeSuccess(String msg) {
        ShowToast.Short(msg);
        back();
    }
}
