package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.AtMessageEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/19 17:52
 * @des
 */
public interface IAtMeView {

    void setData(List<AtMessageEntity.ResultEntity> list);

    /**
     * 未读消息清空
     */
    void clearNoReadCount();
}
