package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.RedDetailEntity;
import com.chunsun.redenvelope.entities.json.ShareLimitEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface IRedDetailView  {

    void setData(ArrayList<String> list,  RedDetailEntity.ResultEntity.DetailEntity detail);

    void getShareLimit(ShareLimitEntity.ResultEntity result);
}
