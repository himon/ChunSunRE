package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.SampleResponseEntity;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.MyCircleListDetailMode;
import com.chunsun.redenvelope.model.impl.MyCircleListDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.MyCircleListDetailActivity;
import com.chunsun.redenvelope.ui.view.IMyCircleListDetailView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/25 13:58
 * @des
 */
public class MyCircleListDetailPresenter extends BaseMultiLoadedListenerImpl<BaseEntity>{

    private IMyCircleListDetailView mIMyCircleListDetailView;
    private MyCircleListDetailMode mMyCircleListDetailMode;

    public MyCircleListDetailPresenter(IMyCircleListDetailView iMyCircleListDetailView) {
        this.mIMyCircleListDetailView = iMyCircleListDetailView;
        this.mMyCircleListDetailMode = new MyCircleListDetailModeImpl((MyCircleListDetailActivity)iMyCircleListDetailView);
    }

    public void getData(String token, String id) {
        mMyCircleListDetailMode.getRedData(token, id, this);
    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        mMyCircleListDetailMode.getCommentList(hb_id, page_index, this);
    }

    /**
     * 设置收藏当前红包
     *
     * @param mToken
     * @param id
     */
    public void favorite(String mToken, String id) {
        mMyCircleListDetailMode.setFavorite(mToken, id, this);
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
        mMyCircleListDetailMode.sendComment(token, id, comment, this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL:
                getDataSuccess((RedDetailEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_COMMENT_LIST:
                onGetCommentSuccesss((RedDetailCommentEntity) data);
                break;
            case Constants.LISTENER_TYPE_COMMENT:
                onCommentSuccess((SampleResponseEntity) data);
                break;
            case Constants.LISTENER_TYPE_FAVORITE:
                onSetFavoriteSuccess((SampleResponseEntity) data);
                break;
        }
    }

    public void getDataSuccess(RedDetailEntity entity) {
        RedDetailEntity.ResultEntity.DetailEntity detail = entity.getResult().getDetail();

        ArrayList<String> list = new ArrayList<String>();
        list.add(Constants.HOST_URL + detail.getCover_img_url());

        if (!TextUtils.isEmpty(detail.getImg_url())) {
            String[] url = detail.getImg_url().split(",");
            for (String str : url) {
                if (!TextUtils.isEmpty(str)) {
                    list.add(Constants.HOST_URL + str);
                }
            }
        }
        mIMyCircleListDetailView.setData(list, detail);
    }

    /**
     * 成功获取评论列表
     *
     * @param response
     */
    public void onGetCommentSuccesss(RedDetailCommentEntity response) {
        RedDetailCommentEntity.ResultEntity result = response.getResult();
        mIMyCircleListDetailView.setCommentData(result);
    }

    /**
     * 收藏成功
     *
     * @param response
     */
    public void onSetFavoriteSuccess(SampleResponseEntity response) {
        mIMyCircleListDetailView.setFavoriteSuccess(response);
    }

    /**
     * 评论成功
     */
    public void onCommentSuccess(SampleResponseEntity response) {
        if (response.isSuccess()) {
            ShowToast.Short(response.getMsg());
            mIMyCircleListDetailView.commentSuccess();
        } else {
            ShowToast.Short(response.getMsg());
        }
    }
}
