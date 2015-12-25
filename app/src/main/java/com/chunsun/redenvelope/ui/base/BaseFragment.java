package com.chunsun.redenvelope.ui.base;


import android.support.v4.app.Fragment;

import com.chunsun.redenvelope.net.RequestManager;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected abstract void initView();

    protected abstract void initData();
}
