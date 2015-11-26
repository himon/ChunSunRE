package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.InteractiveEntity;
import com.chunsun.redenvelope.ui.base.BaseView;

/**
 * Created by Administrator on 2015/9/12.
 */
public interface IInteractivePlatformView extends BaseView{

    void setCountryList(InteractiveEntity entity);

    void setLocalList(InteractiveEntity entity);

    void commentSuccess();

    void toLogin();
}
