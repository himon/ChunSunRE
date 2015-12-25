package com.chunsun.redenvelope.ui.base.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/18 16:44
 * @des
 */
public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {

    /**
     * Presenter对象
     */
    protected T mMPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMPresenter = createPresenter();
        mMPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
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

