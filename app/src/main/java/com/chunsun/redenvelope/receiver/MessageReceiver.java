package com.chunsun.redenvelope.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.ui.activity.personal.MyCircleListActivity;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.ui.activity.red.PushNoticeShowActivity;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.RepeatRedDetailActivity;
import com.chunsun.redenvelope.ui.activity.red.web.WebRedDetailActivity;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/12 11:30
 * @des 信鸽推送消息的接收和操作
 */
public class MessageReceiver extends XGPushBaseReceiver {

    private static final Random random = new Random(System.currentTimeMillis());


    /**
     * 注册结果
     *
     * @param context
     * @param i
     * @param xgPushRegisterResult
     */
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {

    }

    /**
     * 反注册结果
     *
     * @param context
     * @param i
     */
    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    /**
     * 设置标签结果
     *
     * @param context
     * @param i
     * @param s
     */
    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    /**
     * 删除标签结果
     *
     * @param context
     * @param i
     * @param s
     */
    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    /**
     * 收到消息
     *
     * @param context
     * @param xgPushTextMessage
     */
    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        // 获取自定义key-value
        String customContent = xgPushTextMessage.getCustomContent();
        if (!TextUtils.isEmpty(customContent)) {
            try {
                JSONObject obj = new JSONObject(customContent);
                runClickIntent(context, xgPushTextMessage.getTitle(),
                        xgPushTextMessage.getContent(), obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent();
            notification(context, intent, xgPushTextMessage.getTitle(),
                    xgPushTextMessage.getContent());
        }
    }

    /**
     * 通知被展示触发的结果，可以在此保存APP收到的通知
     *
     * @param context
     * @param xgPushClickedResult
     */
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        if (context == null || xgPushClickedResult == null) {
            return;
        }
    }

    /**
     * 通知被打开触发的结果
     *
     * @param context
     * @param xgPushShowedResult
     */
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

    }

    private void runClickIntent(final Context context, final String title,
                                final String content, JSONObject objCustom) {
        Intent intent = null;
        String type = "";

        try {
            type = objCustom.getString("type");

            if ("wdhb".equals(type)) {
                intent = new Intent(context,
                        SendRedEnvelopeRecordClassifyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else if ("wdqz".equals(type)) {//我的圈子
                intent = new Intent(context, MyCircleListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else if (type.equals("v")) {
                new Thread() {
                    public void run() {
                        String token = new Preferences(context).getToken();
                        if (!TextUtils.isEmpty(token)) {

                        }
                    }
                }.start();
                // 一定要在此return
                return;
            } else if (type.equals("cash")) {
                intent = new Intent(context, WalletActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else if (type.equals("notice")) {
                String value = objCustom.getString("val");
                if (!TextUtils.isEmpty(value)) {
                    intent = new Intent(context, CommonWebActivity.class);
                    intent.putExtra(
                            Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL,
                            Constants.SYSTEM_NOTICE_URL + value);
                    intent.putExtra(
                            Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                            "公告");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            } else if (type.equals("tooltip")) {
                intent = new Intent(context, PushNoticeShowActivity.class);
                intent.putExtra(
                        Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE,
                        title);
                intent.putExtra(
                        Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_CONTENT,
                        content);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else if (type.equals("hb")) {// 红包被@
                String value = objCustom.getString("id");
                String sub_type = objCustom.getString("sub_type");
                if ("tw".equals(sub_type) || "q".equals(sub_type)
                        || "ptw".equals(sub_type)) {
                    intent = new Intent(context,
                            RedDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, value);
                    intent.putExtra(Constants.EXTRA_KEY2, sub_type);
                } else if ("lj".equals(sub_type) || "plj".equals(sub_type)) {
                    intent = new Intent(context,
                            WebRedDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, value);
                    intent.putExtra(Constants.EXTRA_KEY2, sub_type);
                } else if ("zf".equals(sub_type)) {
                    intent = new Intent(context,
                            RepeatRedDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, value);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //
                EventBus.getDefault().post(new MainEvent("user_no_read_count"));
            } else if (type.equals("qz")) {// 圈子被@
                String value = objCustom.getString("id");
                String sub_type = objCustom.getString("sub_type");
                if ("tw".equals(sub_type)) {
                    intent = new Intent(context,
                            RedDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, value);
                    intent.putExtra(Constants.EXTRA_KEY2, sub_type);
                } else if ("lj".equals(sub_type)) {
                    intent = new Intent(context,
                            WebRedDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, value);
                    intent.putExtra(Constants.EXTRA_KEY2, sub_type);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //
                EventBus.getDefault().post(new MainEvent("user_no_read_count"));
            } else if ("hdpt".equals(type)) {// 互动平台被@
                String value = objCustom.getString("city");
                if ("全国".equals(value)) {
                    intent = new Intent(context,
                            InteractivePlatformActivity.class);
                } else {
                    intent = new Intent(context,
                            InteractivePlatformActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY, "city");
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                EventBus.getDefault().post(new MainEvent("user_no_read_count"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        notification(context, intent, title, content);
    }

    // 发个通知
    @SuppressLint("NewApi")
    private void notification(Context context, Intent intentClick,
                              String title, String content) {
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if (intentClick != null) {
            PendingIntent contentIntent = PendingIntent.getActivity(context,
                    random.nextInt(), intentClick,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                notification = new Notification();
                notification.icon = R.mipmap.ic_launcher;
                notification.defaults = Notification.DEFAULT_LIGHTS;
                notification.defaults |= Notification.DEFAULT_SOUND;
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notification.when = System.currentTimeMillis();

                notification.setLatestEventInfo(context, title, content,
                        contentIntent);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                Notification.Builder builder = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setDefaults(
                                Notification.DEFAULT_LIGHTS
                                        | Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setWhen(System.currentTimeMillis());
                notification = builder.getNotification();
            } else {
                notification = new Notification.Builder(context)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setDefaults(
                                Notification.DEFAULT_LIGHTS
                                        | Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setWhen(System.currentTimeMillis()).build();
            }
            notificationManager.notify(random.nextInt(), notification);
        }
    }
}
