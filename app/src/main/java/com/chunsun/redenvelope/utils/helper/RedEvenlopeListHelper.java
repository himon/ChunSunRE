package com.chunsun.redenvelope.utils.helper;

import android.content.Context;
import android.content.Intent;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/22 10:16
 * @des 红包列表帮助类
 */
public class RedEvenlopeListHelper {

    private Context mContext;

    public RedEvenlopeListHelper(Context context) {
        this.mContext = context;
    }

    public void toRedDetail(String id, int type) {
        Intent intent = new Intent(mContext, RedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        intent.putExtra(Constants.EXTRA_KEY2, type);
        mContext.startActivity(intent);
    }

    public void toWebRedDetail(String id, int type) {
        Intent intent = new Intent(mContext, WebRedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        intent.putExtra(Constants.EXTRA_KEY2, type);
        mContext.startActivity(intent);
    }

    public void toRepeatRedDetail(String id) {
        Intent intent = new Intent(mContext, RepeatRedDetailActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        mContext.startActivity(intent);
    }

    public void toAdWebView(String title, String url) {
        Intent intent = new Intent(mContext, CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, url);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, title);
        mContext.startActivity(intent);
    }

    public void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_TAB1);
        mContext.startActivity(intent);
    }
}
