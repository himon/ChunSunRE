package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordDetailMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.model.impl.SendRedEnvelopeRecordDetailModeImpl;
import com.chunsun.redenvelope.presenter.OnGetRedDetailListener;
import com.chunsun.redenvelope.presenter.OnRedDetailGetCommentListListener;
import com.chunsun.redenvelope.presenter.OnRedDetailGetRedRecordListListener;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordDetailActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordDetailView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SendRedEnvelopeRecordDetailPresenter implements BaseMultiLoadedListener<BaseEntity> {

    private ISendRedEnvelopeRecordDetailView mISendRedEnvelopeRecordDetailView;
    private SendRedEnvelopeRecordDetailMode mSendRedEnvelopeRecordDetailMode;

    public SendRedEnvelopeRecordDetailPresenter(ISendRedEnvelopeRecordDetailView iSendRedEnvelopeRecordDetailView) {
        this.mISendRedEnvelopeRecordDetailView = iSendRedEnvelopeRecordDetailView;
        this.mSendRedEnvelopeRecordDetailMode = new SendRedEnvelopeRecordDetailModeImpl((SendRedEnvelopeRecordDetailActivity) iSendRedEnvelopeRecordDetailView);
    }


    /**
     * 获取红包详情
     *
     * @param token
     * @param hb_id
     */
    public void getRedEnvelopeDetail(String token, String hb_id) {
        mSendRedEnvelopeRecordDetailMode.getRedEnvelopeDetail(token, hb_id, this);
    }

    /**
     * 获取评论列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getCommentList(String hb_id, int page_index) {
        mSendRedEnvelopeRecordDetailMode.getCommentList(hb_id, page_index, this);
    }

    /**
     * 获取领取记录列表
     *
     * @param hb_id
     * @param page_index
     */
    public void getRedRecordList(String hb_id, int page_index) {
        mSendRedEnvelopeRecordDetailMode.getRedRecordList(hb_id, page_index, this);
    }

    public void onGetDetailSuccess(RedDetailEntity entity) {
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
        mISendRedEnvelopeRecordDetailView.setRedEnvelopeDetail(list, detail);
    }

    public void onGetCommentSuccesss(RedDetailCommentEntity response) {
        RedDetailCommentEntity.ResultEntity result = response.getResult();
        mISendRedEnvelopeRecordDetailView.setCommentData(result);
    }

    public void onGetRedRecordSuccesss(RedDetailGetRedRecordEntity response) {
        RedDetailGetRedRecordEntity.ResultEntity result = response.getResult();
        mISendRedEnvelopeRecordDetailView.setGetRedRecord(result);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_RED_ENVELOPE_DETAIL:
                onGetDetailSuccess((RedDetailEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_COMMENT_LIST:
                onGetCommentSuccesss((RedDetailCommentEntity) data);
                break;
            case Constants.LISTENER_TYPE_GET_RECORD_LIST:
                onGetRedRecordSuccesss((RedDetailGetRedRecordEntity) data);
                break;
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
