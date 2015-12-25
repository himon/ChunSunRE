package com.chunsun.redenvelope.ui.base.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/18 16:45
 * @des Presenter的父类
 */
public abstract class BasePresenter<T> {

    /**
     * View接口类型的弱引用
     */
    protected Reference<T> mViewRef;

    /**
     * 建立关联
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 获取View
     *
     * @return
     */
    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否与View建立了关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
