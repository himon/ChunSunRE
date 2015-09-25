package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 16:15
 */
public interface RepeatRedDetailMode {

    void getRedDetail(String token, String hb_id, BaseMultiLoadedListener listener);

    void getCommentList(String hb_id, int page_index, BaseMultiLoadedListener listener);
}
