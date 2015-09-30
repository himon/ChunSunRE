package com.chunsun.redenvelope.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.event.EditUserInfoEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.EditInfoPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IEditInfoView;
import com.chunsun.redenvelope.utils.EditUtils;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 编辑信息Activity
 */
public class EditInfoActivity extends BaseActivity implements View.OnClickListener, IEditInfoView {

    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.iv_delete_text)
    ImageView mIvDeleteContent;

    private String mId;
    /**
     * toolsbar标题
     */
    private String mTitle;
    /**
     * 回显内容
     */
    private String mText;
    private int mType;
    /**
     * EditText显示的行数
     */
    private int mLines;

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
            mType = intent.getIntExtra(Constants.EXTRA_KEY_TYPE, 0);
            mLines = intent.getIntExtra(Constants.EXTRA_KEY_LINES, 1);
        }
        initTitleBar(mTitle, "", "保存", Constants.TITLE_TYPE_SAMPLE);

        /**
         * 设置EditText行数
         */
        mEtContent.setMinLines(mLines);
        if (mLines == 1) {
            mEtContent.setSingleLine(true);
        }
        mEtContent.setText(mText);

        if (Constants.EDIT_TYPE_COMPLAINT == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        } else if (Constants.EDIT_TYPE_CHUNSUN_ACCOUNT == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        } else if (Constants.EDIT_TYPE_NICK_NAME == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        } else if (Constants.EDIT_TYPE_PHONE == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (Constants.EDIT_TYPE_TEL == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
            mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (Constants.EDIT_TYPE_WECHAT == mType) {
            //限制输入字母数字
            EditUtils.limitCharAndNumber(mEtContent);
        } else if (Constants.EDIT_TYPE_QQ == mType) {
            mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (Constants.EDIT_TYPE_ALIPAY == mType) {
            // 限制字母数字特殊字符
            EditUtils.limitNotInputHanzi(mEtContent);
        } else if (Constants.EDIT_TYPE_ID_CARD == mType) {
            EditUtils.limitCharAndNumber(mEtContent);
        } else if (Constants.EDIT_TYPE_DESC == mType) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(160)});
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.tv_nav_right:
                submit();
                break;

        }
    }

    private void submit() {
        if (mType == Constants.EDIT_TYPE_COMPLAINT) {
            mPresenter.complaintRedEnvelope(mToken, mId, StringUtil.textview2String(mEtContent));
        }else{
            mPresenter.editUserInfo(mToken, mType, StringUtil.textview2String(mEtContent));
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

    @Override
    public void editSuccess(int type) {
        EventBus.getDefault().post(new EditUserInfoEvent(type, StringUtil.textview2String(mEtContent)));
        back();
    }
}
