package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CouponRedDetailFragmentMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.event.CouponRedDetailEvent;
import com.chunsun.redenvelope.model.impl.CouponRedDetailFragmentModeImpl;
import com.chunsun.redenvelope.ui.fragment.CouponRedDetailFragment;
import com.chunsun.redenvelope.ui.view.ICouponRedDetailFragmentView;
import com.chunsun.redenvelope.utils.ShowToast;

import de.greenrobot.event.EventBus;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/3 14:03
 * @des 券红包详情Presenter
 */
public class CouponRedDetailPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private ICouponRedDetailFragmentView mICouponRedDetailFragmentView;
    private CouponRedDetailFragmentMode mCouponRedDetailFragmentMode;

    public CouponRedDetailPresenter(ICouponRedDetailFragmentView iCouponRedDetailFragmentView) {
        mICouponRedDetailFragmentView = iCouponRedDetailFragmentView;
        mCouponRedDetailFragmentMode = new CouponRedDetailFragmentModeImpl((CouponRedDetailFragment) iCouponRedDetailFragmentView);
    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        mCouponRedDetailFragmentMode.getCommentList(hb_id, page_index, this);
    }

    /**
     * 获取领取记录列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getRedRecordList(String hb_id, int page_index) {
        mCouponRedDetailFragmentMode.getRedRecordList(hb_id, page_index, this);
    }

    /**
     * 设置收藏当前红包
     *
     * @param mToken
     * @param id
     */
    public void favorite(String mToken, String id) {
        mCouponRedDetailFragmentMode.setFavorite(mToken, id, this);
    }

    /**
     * 发送评论
     *
     * @param comment
     */
    public void sendComment(String comment, String token, String id) {
        if ("4".equals(MainApplication.getContext().getUserEntity().getStatus())) {
            ShowToast.Short("您已被禁言，有什么疑问请联系客服!");
            return;
        }
        mCouponRedDetailFragmentMode.sendComment(token, id, comment, this);
    }

    /**
     * 分享
     *
     * @param token
     * @param grab_id
     * @param shareType
     */
    public void shareOpen(String token, String grab_id, String shareType) {
        mCouponRedDetailFragmentMode.shareOpen(token, grab_id, shareType, this);
    }

    /**
     * 直接领钱
     *
     * @param token
     * @param grab_id
     */
    public void justOpen(String token, String grab_id) {
        mCouponRedDetailFragmentMode.justOpen(token, grab_id, this);
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
                mICouponRedDetailFragmentView.shareSuccess();
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onError(int event_tag, String msg) {

    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }

    /**
     * 评论成功
     */
    public void onCommentSuccess(SampleResponseEntity response) {
        if (response.isSuccess()) {
            ShowToast.Short(response.getMsg());
            mICouponRedDetailFragmentView.commentSuccess();
        } else {
            ShowToast.Short(response.getMsg());
        }
    }

    /**
     * 成功获取评论列表
     *
     * @param response
     */
    public void onGetCommentSuccesss(RedDetailCommentEntity response) {
        RedDetailCommentEntity.ResultEntity result = response.getResult();
        mICouponRedDetailFragmentView.setCommentData(result);
    }

    /**
     * 成功获取领取记录列表
     *
     * @param response
     */
    public void onGetRedRecordSuccesss(RedDetailGetRedRecordEntity response) {
        RedDetailGetRedRecordEntity.ResultEntity result = response.getResult();
        mICouponRedDetailFragmentView.setGetRedRecord(result);
    }

    /**
     * 收藏成功
     *
     * @param response
     */
    public void onSetFavoriteSuccess(SampleResponseEntity response) {
        mICouponRedDetailFragmentView.setFavoriteSuccess(response);
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
                while (count > 0) {
                    try {
                        Thread.sleep(1000);
                        EventBus.getDefault().post(new CouponRedDetailEvent(count));
                        count--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                EventBus.getDefault().post(new CouponRedDetailEvent(0));
            }
        }).start();
    }
}
