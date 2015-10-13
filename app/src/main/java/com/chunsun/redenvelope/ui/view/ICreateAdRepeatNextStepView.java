package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.model.entity.json.RepeatMealEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/8 11:59
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface ICreateAdRepeatNextStepView {

    void getRepeatMealSuccess(List<RepeatMealEntity.ResultEntity> result);
}
