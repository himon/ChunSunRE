package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/12/24 15:50
 * @des
 */
public interface CreateCircleResultMode {

    void userOperateCircle(String token, String province, String city, String longitude, String latitude, int operate_type, String hb_id, BaseSingleLoadedListener listener);
}
