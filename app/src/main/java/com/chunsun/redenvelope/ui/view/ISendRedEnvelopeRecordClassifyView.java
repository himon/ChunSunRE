package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.SampleEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface ISendRedEnvelopeRecordClassifyView {

    void setData(List<SampleEntity> list);

    void toChildListActivity(SampleEntity entity);
}
