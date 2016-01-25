package com.chunsun.redenvelope.utils.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.EditInfoActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailPicShowActivity;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.utils.HtmlUtil;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/22 11:02
 * @des 红包详情帮助类
 */
public class RedDetailHelper {

    private Context mContext;

    public RedDetailHelper(Context context) {
        this.mContext = context;
    }

    /**
     * 跳转图片查看详情
     */
    public void toPicShowActivity(ArrayList url, int position) {
        Intent intent = new Intent(mContext, RedDetailPicShowActivity.class);
        intent.putExtra(Constants.EXTRA_LIST_KEY, url);
        intent.putExtra(Constants.EXTRA_KEY, position);
        mContext.startActivity(intent);
    }

    /**
     * 查看担保交易详情
     */
    public void toGuaranteeActivity() {
        Intent intentWeb = new Intent(mContext,
                CommonWebActivity.class);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.SEND_GUARANTEE_URL);
        intentWeb.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "担保交易");
        mContext.startActivity(intentWeb);
    }

    /**
     * 跳转用户奖励页面
     *
     * @param id
     * @param detailId
     */
    public void toUserRewardActivity(String id, String detailId) {
        Intent intent = new Intent(mContext, UserRewardActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, id);
        intent.putExtra(Constants.EXTRA_KEY2, detailId);
        mContext.startActivity(intent);
    }

    /**
     * 改变收藏标示
     *
     * @param entity
     * @param collect
     * @param b
     */
    public void setFavoriteSuccess(SampleResponseEntity entity, ImageView collect, boolean b) {


        if (entity.getResult().equalsIgnoreCase("true")) {
            if (b) {
                collect.setImageResource(R.drawable.img_collect_select);
            }else{
                collect.setImageResource(R.drawable.img_circle_collected_icon);
            }
        } else {
            if (b) {
                collect.setImageResource(R.drawable.img_collect_normal);
            }else{
                collect.setImageResource(R.drawable.img_circle_collect_icon);
            }
        }
        ShowToast.Short(entity.getMsg());
    }

    /**
     * 跳转举报
     *
     * @param id
     */
    public void toComplaintActivity(String id) {
        Intent intent = new Intent(mContext, EditInfoActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_ID, id);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, "举报");
        intent.putExtra(Constants.EXTRA_KEY_TEXT, "");
        intent.putExtra(Constants.EXTRA_KEY_LINES, 5);
        intent.putExtra(Constants.EXTRA_KEY_TYPE, Constants.EDIT_TYPE_COMPLAINT);
        mContext.startActivity(intent);
    }

    /**
     *
     * @param content
     */
    public void webViewSetText(WebView webView, String content){
        webView.setVerticalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                // url为你要链接的地址
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
                return true;
            }
        });
        webView.loadDataWithBaseURL("",
                HtmlUtil.getHtml(content), "text/html", "utf-8", "");
    }
}
