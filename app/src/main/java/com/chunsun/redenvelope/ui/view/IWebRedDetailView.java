package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;
import com.chunsun.redenvelope.model.entity.json.ShareLimitEntity;
import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface IWebRedDetailView extends BaseView{

    void loadUrl(RedDetailEntity.ResultEntity.DetailEntity entity);

    void getShareLimit(ShareLimitEntity.ResultEntity result);

    void shareSuccess();
}
