package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedDetailGetRedRecordEntity;

/**
 * Created by Administrator on 2015/8/13.
 */
public interface OnRedDetailGetRedRecordListListener {

    void onGetRedRecordSuccesss(RedDetailGetRedRecordEntity response);

    void onGetRedRecordError();
}
