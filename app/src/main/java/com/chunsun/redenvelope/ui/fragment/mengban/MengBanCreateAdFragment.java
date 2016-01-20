package com.chunsun.redenvelope.ui.fragment.mengban;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdActivity;
import com.chunsun.redenvelope.ui.base.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MengBanCreateAdFragment extends BaseFragment {

    @Bind(R.id.iv_click)
    ImageView mIvClick;

    public MengBanCreateAdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meng_ban_create_ad, null);
        ButterKnife.bind(this, view);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mIvClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(getActivity() instanceof CreateAdActivity){
                    ((CreateAdActivity)getActivity()).mengBanClick();
                }
            }
        });

        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}
