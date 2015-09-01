package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.model.entity.json.RedDetailCommentEntity;

/**
 * Created by Administrator on 2015/8/12.
 */
public interface OnRedDetailGetCommentListListener {

    void onGetCommentSuccesss(RedDetailCommentEntity response);

    void onGetCommentError();
}
