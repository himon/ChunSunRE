package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailUnReceiveAndCollectEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public interface INotReceivingRedView {

    void setData(List<RedDetailUnReceiveAndCollectEntity.ResultEntity> result);

    void toWebRedDetail(String id);

    void toRedDetail(String id);
}
