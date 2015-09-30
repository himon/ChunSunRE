package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/9/29 10:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface CreateAdMode {

    /**
     * 获取省市列表
     *
     * @param listener
     */
    void initProvinceAndCity(BaseMultiLoadedListener listener);
}
