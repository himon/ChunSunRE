package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SendRedEnvelopeRecordClassifyMode;
import com.chunsun.redenvelope.entities.SampleEntity;
import com.chunsun.redenvelope.entities.json.RedDetailSendRecordClassifyEntity;
import com.chunsun.redenvelope.model.impl.SendRedEnvelopeRecordClassifyModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.SendRedEnvelopeRecordClassifyActivity;
import com.chunsun.redenvelope.ui.view.ISendRedEnvelopeRecordClassifyView;
import com.chunsun.redenvelope.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public class SendRedEnvelopeRecordClassifyPresenter implements BaseSingleLoadedListener<RedDetailSendRecordClassifyEntity> {

    private ISendRedEnvelopeRecordClassifyView mISendRedEnvelopeRecordClassifyView;
    private SendRedEnvelopeRecordClassifyMode mSendRedEnvelopeRecordClassifyMode;

    public SendRedEnvelopeRecordClassifyPresenter(ISendRedEnvelopeRecordClassifyView sendRedEnvelopeRecordClassifyView) {
        this.mISendRedEnvelopeRecordClassifyView = sendRedEnvelopeRecordClassifyView;
        this.mSendRedEnvelopeRecordClassifyMode = new SendRedEnvelopeRecordClassifyModeImpl((SendRedEnvelopeRecordClassifyActivity) sendRedEnvelopeRecordClassifyView);
    }

    public void loadData(String token) {
        mSendRedEnvelopeRecordClassifyMode.loadRedEnvelopeSendRecordClassifyData(token, this);
    }

    @Override
    public void onSuccess(RedDetailSendRecordClassifyEntity response) {
        if (response.isSuccess()) {

            RedDetailSendRecordClassifyEntity.ResultEntity result = response.getResult();

            List<SampleEntity> list = new ArrayList<SampleEntity>();

            SampleEntity dzf = new SampleEntity();
            dzf.setKey(Constants.RED_DETAIL_STATUS_DZF);
            dzf.setValue("未支付");
            dzf.setCount(result.getDzf_count());
            list.add(dzf);

            SampleEntity shz = new SampleEntity();
            shz.setKey(Constants.RED_DETAIL_STATUS_SHZ);
            shz.setValue("审核中");
            shz.setCount(result.getShz_count());
            list.add(shz);

            SampleEntity yfb = new SampleEntity();
            yfb.setKey(Constants.RED_DETAIL_STATUS_YFB);
            yfb.setValue("已发布");
            yfb.setCount(result.getYfb_count());
            list.add(yfb);

            SampleEntity wtg = new SampleEntity();
            wtg.setKey(Constants.RED_DETAIL_STATUS_WTG);
            wtg.setValue("未通过");
            wtg.setCount(result.getWtg_count());
            list.add(wtg);

            SampleEntity yqw = new SampleEntity();
            yqw.setKey(Constants.RED_DETAIL_STATUS_YQW);
            yqw.setValue("已抢完");
            yqw.setCount(result.getYqw_count());
            list.add(yqw);

            SampleEntity ydj = new SampleEntity();
            ydj.setKey(Constants.RED_DETAIL_STATUS_YDJ);
            ydj.setValue("已冻结");
            ydj.setCount(result.getYdj_count());
            list.add(ydj);

            mISendRedEnvelopeRecordClassifyView.setData(list);
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {

    }
}
