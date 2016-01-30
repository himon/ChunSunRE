package com.chunsun.redenvelope.utils.helper;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.InteractiveEntity;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/21 16:25
 * @des ${TODO}
 * @updateAuthor 互动平台帮助类
 */
public class InteractiveHelper {

    private Context mContext;

    public InteractiveHelper(Context context) {
        this.mContext = context;
    }

    /**
     * 跳转用户奖励页面
     */
    public void toUserRewardActivity(String id, int currentCountryPage) {
        Intent intent = new Intent(mContext, UserRewardActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        if (currentCountryPage == 0) {
            intent.putExtra(Constants.EXTRA_KEY2, Constants.INTERACTIVE_PLATFORM_COUNTRY);
        } else if (currentCountryPage == 1) {
            intent.putExtra(Constants.EXTRA_KEY2, Constants.INTERACTIVE_PLATFORM_LOCAL);
        }
        mContext.startActivity(intent);
    }

    /**
     * 设置系统公告
     *
     * @param noticeEntity
     * @param title
     * @param content
     * @param time
     */
    public void setNoticeBoard(InteractiveEntity.ResultEntity.NoticeEntity noticeEntity, TextView title, TextView content, TextView time) {
        if (TextUtils.isEmpty(noticeEntity.getTitle())) {
            title.setText("系统公告");
        } else {
            title.setText(Html.fromHtml("<html><head></head><body>" + noticeEntity.getTitle() + "</body></html>"));
        }
        content.setText(Html.fromHtml("<html><head></head><body>" + noticeEntity.getContent() + "</body></html>"));
        time.setText(noticeEntity.getAdd_time());
    }

    /**
     * 跳转登录
     */
    public void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, Constants.FROM_TAB1);
        mContext.startActivity(intent);
    }

}
