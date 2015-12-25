package com.chunsun.redenvelope.presenter;

import android.text.TextUtils;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordDetailMode;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.RedDetailCommentEntity;
import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.RedDetailGetRedRecordEntity;
import com.chunsun.redenvelope.entities.json.RedSuperadditionEntity;
import com.chunsun.redenvelope.model.impl.SendRedEnvelopeRecordDetailModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordDetailActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordDetailView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SendRedEnvelopeRecordDetailPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

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
            case Constants.LISTENER_TYPE_RED_ENVELOPE_SUPERADDITION:
                mISendRedEnvelopeRecordDetailView.getSuperaddition((RedSuperadditionEntity) data);
                break;
        }
    }

    /**
     * 追加红包
     * @param hb_id
     */
    public void superaddition(String hb_id) {
        mSendRedEnvelopeRecordDetailMode.superaddition(hb_id, this);
    }
}
