package com.chunsun.redenvelope.presenter;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.CreateAdRepeatNextStepMode;
import com.chunsun.redenvelope.model.entity.BaseEntity;
import com.chunsun.redenvelope.model.entity.json.RepeatMealEntity;
import com.chunsun.redenvelope.model.impl.CreateAdRepeatNextStepModeImpl;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdRepeatNextStepActivity;
import com.chunsun.redenvelope.ui.view.ICreateAdRepeatNextStepView;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/8 11:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CreateAdRepeatNextStepPresenter extends BaseMultiLoadedListenerImpl<BaseEntity> {

    private ICreateAdRepeatNextStepView mICreateAdRepeatNextStepView;
    private CreateAdRepeatNextStepMode mCreateAdRepeatNextStepMode;

    public CreateAdRepeatNextStepPresenter(ICreateAdRepeatNextStepView ICreateAdRepeatNextStepView) {
        mICreateAdRepeatNextStepView = ICreateAdRepeatNextStepView;
        mCreateAdRepeatNextStepMode = new CreateAdRepeatNextStepModeImpl((CreateAdRepeatNextStepActivity) ICreateAdRepeatNextStepView);
    }

    /**
     * 获取转发类套餐
     */
    public void initData() {
        mCreateAdRepeatNextStepMode.getRepeatMeal(this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_REPEATE_MEAL://获取转发类套餐
                List<RepeatMealEntity.ResultEntity> result = ((RepeatMealEntity) data).getResult();
                mICreateAdRepeatNextStepView.getRepeatMealSuccess(result);
                break;
        }
    }
}
