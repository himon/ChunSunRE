package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;

/**
 * Created by Administrator on 2015/8/11.
 */
public interface IWebRedDetailView {

    void loadUrl(RedDetailEntity.ResultEntity.DetailEntity entity);

    void setData(RedDetailEntity.ResultEntity.DetailEntity entity);
}
