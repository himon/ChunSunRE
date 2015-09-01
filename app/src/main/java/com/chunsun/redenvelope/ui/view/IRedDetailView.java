package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RedDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public interface IRedDetailView  {

    void setData(ArrayList<String> list,  RedDetailEntity.ResultEntity.DetailEntity detail);
}
