package com.chunsun.redenvelope.ui.base.mode;

import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/18 11:42
 * @des
 */
public class BaseModeImpl {

    protected HttpManager mManager;

    public BaseModeImpl() {
        this.mManager = new HttpManager();
    }
}
