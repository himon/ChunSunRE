package com.chunsun.redenvelope.ui.base;

import com.chunsun.redenvelope.net.RequestManager;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/21 11:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class MBaseFragment<V, T extends BasePresenter<V>> extends MVPBaseFragment {

    public MBaseFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected abstract void initView();

    protected abstract void initData();
}
