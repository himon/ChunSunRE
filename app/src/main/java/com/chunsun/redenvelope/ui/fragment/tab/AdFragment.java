package com.chunsun.redenvelope.ui.fragment.tab;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdActivity;
import com.chunsun.redenvelope.ui.activity.ad.CreateCircleActivity;
import com.chunsun.redenvelope.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 发广告和圈子Fragment
 */
public class AdFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_create_red)
    Button mCreateRed;
    @Bind(R.id.btn_create_circle)
    Button mCreateCircle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    protected void initView() {
        mCreateRed.setOnClickListener(this);
        mCreateCircle.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_red:
                toCreateRed();
                break;
            case R.id.btn_create_circle:
                toCreateCircle();
                break;
        }
    }

    private void toCreateCircle() {
        Intent intent = new Intent(getActivity(), CreateCircleActivity.class);
        startActivity(intent);
    }

    private void toCreateRed() {
        Intent intent = new Intent(getActivity(), CreateAdActivity.class);
        startActivity(intent);
    }


}
