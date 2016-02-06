package com.chunsun.redenvelope.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.chunsun.redenvelope.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/7/31.
 * 确定取消对话框
 */
public class TextButtonDialog extends Dialog {

    @Bind(R.id.dialog_content)
    TextView tvContent;
    @Bind(R.id.btn_confirm_cancel)
    Button btnCancel;
    @Bind(R.id.btn_confirm_ok)
    Button btnOk;

    private boolean isCanHide;
    private View.OnClickListener mListener;

    public TextButtonDialog(Context context, int theme, View.OnClickListener listener) {
        super(context, theme);
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_text_buttons);
        ButterKnife.bind(this);

        WindowManager windowManager = (WindowManager) this.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        getWindow().getAttributes().width = dm.widthPixels - 100;
        getWindow().getAttributes().dimAmount = 0.3f;
        getWindow().getAttributes().y = -dm.heightPixels * 1 / 10;

        //调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
        this.setCancelable(isCanHide);
        //调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        this.setCanceledOnTouchOutside(isCanHide);

        initEvent();
    }

    private void initEvent() {
        btnCancel.setOnClickListener(mListener);
        btnOk.setOnClickListener(mListener);
    }

    public void setIsCanHide(boolean iscan) {
        isCanHide = iscan;
    }

    /**
     * 设置Dialog内容
     *
     * @param content
     */
    public void setDialogContent(String content) {
        tvContent.setText(content);
    }

    public void setDialogContent(String content, float textSize) {
        tvContent.setText(content);
        tvContent.setTextSize(textSize);
    }

    /**
     * 注册成功显示的样式
     */
    public void diyGetRedDialog() {
        if (btnCancel != null && btnOk != null) {
            btnCancel.setText("去抢红包");
            btnCancel.setTextColor(Color.rgb(255, 86, 58));
            btnOk.setText("完善个人信息");
            btnOk.setTextColor(Color.rgb(0, 122, 255));
        }
    }

    /**
     * 注册失败后显示的样式
     */
    public void singleButtonDialog() {
        if (btnCancel != null && btnOk != null) {
            btnCancel.setText("取消");
            btnOk.setText("退出");
            btnOk.setTextColor(Color.rgb(255, 84, 18));
            btnOk.setVisibility(View.GONE);
        }
    }

    public void diyRechargeDialog(boolean flag) {
        if (btnCancel != null && btnOk != null) {
            btnCancel.setText("取消");
            btnCancel.setTextColor(Color.rgb(0, 0, 0));
            btnOk.setText(flag ? "确定" : "去充值");
            btnOk.setTextColor(Color.rgb(255, 84, 18));
        }
    }

    public void okSetEnabled() {
        btnOk.setEnabled(false);
        btnOk.setText("正在下载中...");
        tvContent.setText("下载完成后，请到消息中心点击安装！");
    }

    public void setDownloadApkProgress(int progress) {
        btnOk.setText("正在下载中... " + progress + "%");
    }

    /**
     * 强制升级
     *
     * @param btnText
     */
    public void isSingleButton(String btnText) {
        if (btnCancel != null && btnOk != null) {
            btnCancel.setVisibility(View.GONE);
            btnOk.setBackgroundResource(R.drawable.selector_radius_apk_update);
            btnOk.setTextColor(Color.rgb(255, 84, 18));
            btnOk.setText(btnText);
        }
    }

    /**
     * 设置检查更新
     */
    public void isCheckUpGrade() {
        if (btnCancel != null && btnOk != null) {
            btnCancel.setText("取消");
            btnCancel.setTextColor(Color.rgb(0, 0, 0));
            btnOk.setText("升级");
            btnOk.setTextColor(Color.rgb(255, 84, 18));
        }
    }

    /**
     * 设置检查更新 点击后
     */
    public void checkSetEnabled() {
        btnCancel.setVisibility(View.GONE);
        btnOk.setEnabled(false);
        btnOk.setText("正在下载中...");
        tvContent.setText("下载完成后，请到消息中心点击安装！");
    }

}
