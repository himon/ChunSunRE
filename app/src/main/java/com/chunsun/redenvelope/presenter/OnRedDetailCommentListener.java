package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface OnRedDetailCommentListener {

    void onCommentSuccess(SampleResponseEntity response);

    void onCommentError();
}
