package com.chunsun.redenvelope.presenter;

import android.support.v4.app.Fragment;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.GrabEntity;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.entities.json.UserInfoEntity;
import com.chunsun.redenvelope.event.RedDetailEvent;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailFragmentMode;
import com.chunsun.redenvelope.model.impl.RedDetailFragmentModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.IRedDetailFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RedDetailFragmentPresenter extends UserLosePresenter<IRedDetailFragmentView> implements UserLoseMultiLoadedListener<BaseEntity> {

    private IRedDetailFragmentView redDetailFragmentView;
    private RedDetailFragmentMode redDetailFragmentMode;
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public RedDetailFragmentPresenter(IRedDetailFragmentView redDetailFragmentView) {
        this.redDetailFragmentView = redDetailFragmentView;
        redDetailFragmentMode = new RedDetailFragmentModeImpl((Fragment) redDetailFragmentView);
    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        redDetailFragmentMode.getCommentList(hb_id, page_index, this);
    }

    public void getGrabByToken(String token, String id) {
        redDetailFragmentMode.getGrabByToken(token, id, this);
    }

    /**
     * 获取领取记录列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getRedRecordList(String hb_id, int page_index) {
        redDetailFragmentMode.getRedRecordList(hb_id, page_index, this);
    }

    /**
     * 设置收藏当前红包
     *
     * @param mToken
     * @param id
     */
    public void favorite(String mToken, String id) {
        redDetailFragmentMode.setFavorite(mToken, id, this);
    }

    /**
     * 发送评论
     *
     * @param comment
     */
    public void sendComment(String comment, String token, String id, String at) {
        UserInfoEntity userEntity = MainApplication.getContext().getUserEntity();

        if(userEntity == null){
            ShowToast.Short("正在获取用户信息，请稍后发送！");
            return;
        }

        if ("4".equals(userEntity.getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        redDetailFragmentMode.sendComment(token, id, comment, at, this);
    }

    /**
     * 红包倒计时
     *
     * @param delay_seconds
     */
    public void countDown(final int delay_seconds) {
        new Thread(new Runnable() {
            int count = delay_seconds;

            @Override
            public void run() {
                while (loop && count > 0) {
                    try {
                        Thread.sleep(1000);
                        EventBus.getDefault().post(new RedDetailEvent(count));
                        count--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                EventBus.getDefault().post(new RedDetailEvent(0));
            }
        }).start();
    }

    /**
     * 成功获取评论列表
     *
     * @param response
     */
    public void onGetCommentSuccesss(RedDetailCommentEntity response) {
        RedDetailCommentEntity.ResultEntity result = response.getResult();
        redDetailFragmentView.setCommentData(result);
    }

    /**
     * 成功获取领取记录列表
     *
     * @param response
     */
    public void onGetRedRecordSuccesss(RedDetailGetRedRecordEntity response) {
        RedDetailGetRedRecordEntity.ResultEntity result = response.getResult();
        redDetailFragmentView.setGetRedRecord(result);
    }

    /**
     * 收藏成功
     *
     * @param response
     */
    public void onSetFavoriteSuccess(SampleResponseEntity response) {
        redDetailFragmentView.setFavoriteSuccess(response);
    }

    /**
     * 评论成功
     */
    public void onCommentSuccess(SampleResponseEntity response) {
        if (response.isSuccess()) {
            ShowToast.Short(response.getMsg());
            redDetailFragmentView.commentSuccess();
        } else {
            ShowToast.Short(response.getMsg());
        }
    }

    /**
     * 分享
     *  @param token
     * @param grab_id
     * @param shareType
     */
    public void shareOpen(String token, String grab_id, String shareType) {
        redDetailFragmentMode.shareOpen(token, grab_id, shareType, this);
    }

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     */
    public void justOpen(String token, String grab_id) {
        redDetailFragmentMode.justOpen(token, grab_id, this);
    }

    /**
     * 创建春笋券
     * @param token
     * @param grab_id
     */
    public void createChunsunTicket(String token, String grab_id) {
        redDetailFragmentMode.createChunsunTicket(token, grab_id, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_COMMENT_LIST:
                onGetCommentSuccesss((RedDetailCommentEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_RECORD_LIST:
                onGetRedRecordSuccesss((RedDetailGetRedRecordEntity) data);
                break;
            case Constants.LISTENER_TYPE_COMMENT:
                onCommentSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_FAVORITE:
                onSetFavoriteSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_JUST_OPEN_RED:
            case Constants.LISTENER_TYPE_SHARE_OPEN_RED:
                ShowToast.Short(((SampleResponseEntity) data).getMsg());
                redDetailFragmentView.shareSuccess();
                break;
            case Constants.LISTENER_TYPE_GET_USER_GRAB_BY_TOKEN:
                grabData((GrabEntity) data);
                break;
        }
    }

    private void grabData(GrabEntity entity) {
        redDetailFragmentView.setGrab(entity);
    }


}
