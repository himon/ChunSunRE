package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.ui.base.view.LoadingView;

/**
 * Created by Administrator on 2015/8/1.
 */
public interface IQuickLoginView extends LoadingView {

    void success();

    void showServiceProtocol();

     void getFocus();
}
