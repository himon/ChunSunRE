package com.chunsun.redenvelope.ui.base.fragment;

import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

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

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    protected abstract void initView();

    protected abstract void initData();
}
