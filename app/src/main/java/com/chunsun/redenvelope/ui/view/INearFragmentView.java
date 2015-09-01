package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedAutoAdEntity;
import com.chunsun.redenvelope.model.entity.json.RedListDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface INearFragmentView {

    void setData(RedListDetailEntity.ResultEntity entity);

    void setAdData(List<RedAutoAdEntity.ResultEntity.AdvertEntity> advert);

    void toRedDetail(String id);

    void toAdWebView(String title, String url);

    void gradRedEnvelopeSuccess(String id);
}
