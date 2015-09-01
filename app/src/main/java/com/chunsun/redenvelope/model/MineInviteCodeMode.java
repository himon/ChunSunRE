package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.presenter.OnGetInviteRecordListener;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface MineInviteCodeMode {

    void getInviteRecord(String token, BaseSingleLoadedListener listener);
}
