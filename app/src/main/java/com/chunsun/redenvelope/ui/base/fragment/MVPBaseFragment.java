package com.chunsun.redenvelope.ui.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/21 11:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    /**
     * Presenter对象
     */
    protected T mMPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMPresenter = createPresenter();
        mMPresenter.attachView((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMPresenter.detachView();
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract T createPresenter();
}
