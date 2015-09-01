package com.chunsun.redenvelope.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.chunsun.redenvelope.R;

/**
 * Created by Administrator on 2015/7/28.
 * 自定义圆形进度条对话框
 */
public class CustomProgressDialog extends ProgressDialog {

    private String mContent;
    private TextView mProgressDialogContent;

    public CustomProgressDialog(Context context, String content) {
        super(context);
        this.mContent = content;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.dialog_custom_progress);
        mProgressDialogContent = (TextView)findViewById(R.id.progress_dialog_content);
    }

    private void initData() {
        mProgressDialogContent.setText(mContent);
    }

    public void setContent(String str){
        mProgressDialogContent.setText(str);
    }


}
