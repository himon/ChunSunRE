package com.chunsun.redenvelope.ui.fragment.mengban;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.event.MainMengBanEvent;
import com.chunsun.redenvelope.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class MengBanTab4StepTwoFragment extends BaseFragment {

    @Bind(R.id.ib_know)
    ImageButton mIbKnow;

    public MengBanTab4StepTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meng_ban_tab4_step_two, container, false);
        ButterKnife.bind(this, view);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mIbKnow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MainMengBanEvent(5));
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
