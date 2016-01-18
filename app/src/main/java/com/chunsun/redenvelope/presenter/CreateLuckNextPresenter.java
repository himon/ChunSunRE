package com.chunsun.redenvelope.presenter;

import android.app.Activity;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.entities.json.LuckMealsEntity;
import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateLuckNextMode;
import com.chunsun.redenvelope.model.impl.CreateLuckNextModeImpl;
import com.chunsun.redenvelope.ui.base.presenter.UserLosePresenter;
import com.chunsun.redenvelope.ui.view.ICreateLuckNextView;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/18 11:27
 * @des
 */
public class CreateLuckNextPresenter extends UserLosePresenter<ICreateLuckNextView> implements BaseMultiLoadedListener<BaseEntity> {

    private ICreateLuckNextView mICreateLuckNextView;
    private CreateLuckNextMode mCreateLuckNextMode;

    public CreateLuckNextPresenter(ICreateLuckNextView view) {
        this.mICreateLuckNextView = view;
        this.mCreateLuckNextMode = new CreateLuckNextModeImpl((Activity) view);
    }

    /**
     * 获取拼手气套餐列表
     */
    public void getFightLuckPackageList() {
        this.mCreateLuckNextMode.getFightLuckPackageList(this);
    }

    @Override
    public void onSuccess(int event_tag, BaseEntity data) {
        switch (event_tag) {
            case Constants.LISTENER_TYPE_GET_FIGHT_LUCK_PACKAGE_LIST:
                List<LuckMealsEntity.ResultEntity> result = ((LuckMealsEntity) data).getResult();
                mICreateLuckNextView.setFightPackageList(result);
                break;
        }
    }
}
