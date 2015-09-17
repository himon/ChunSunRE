package com.chunsun.redenvelope.scanlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.chunsun.redenvelope.scanlibrary.constants.Constants;

public class ScanResultActivity extends Activity implements OnClickListener {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        initView();
        initData();
    }

    protected void initView() {
        TextView navTxtTitle = (TextView) this.findViewById(R.id.nav_txt_title);
        navTxtTitle.setText("扫描结果");
        TextView navTxtLeft = (TextView) this.findViewById(R.id.nav_img_back);
        navTxtLeft.setOnClickListener(this);

        mText = (TextView) findViewById(R.id.tv_text);
    }

    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra(Constants.MESSAGE_EXTRA);
            mText.setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.nav_img_back) {
            finish();
        } else {
        }
    }
}
