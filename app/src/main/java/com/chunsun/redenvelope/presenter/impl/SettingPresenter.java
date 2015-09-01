package com.chunsun.redenvelope.presenter.impl;

import android.os.Environment;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.SettingMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.SettingModeImpl;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.ui.view.ISettingView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

/**
 * Created by Administrator on 2015/8/22.
 */
public class SettingPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private ISettingView mISettingView;
    private SettingMode mSettingMode;


    public SettingPresenter(ISettingView iSettingView) {
        this.mISettingView = iSettingView;
        this.mSettingMode = new SettingModeImpl((SettingActivity) iSettingView);
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
        String path = Environment.getExternalStorageDirectory()
                + Constants.CHUNSUN_IMAGE_FILE_PATH;
        File file = new File(path);
        if (file.exists()) {
            deleteChunsunFile(file);
        }
        ShowToast.Short("清除缓存成功");
    }

    /**
     * 退出
     */
    public void logout(String token) {
        mSettingMode.logout(token, this);
    }


    /**
     * 删除sd卡文件
     *
     * @param oldPath
     */
    private void deleteChunsunFile(File oldPath) {
        if (oldPath.isDirectory()) {
            File[] files = oldPath.listFiles();
            for (File file : files) {
                deleteChunsunFile(file);
                file.delete();
            }
        } else if (oldPath.isFile()) {
            oldPath.delete();
        }
    }

    @Override
    public void onSuccess(SampleResponseEntity entity) {
        if (entity.isSuccess()) {
            ShowToast.Short(entity.getMsg());
            mISettingView.toLogout();
        } else {
            ShowToast.Short(entity.getMsg());
        }
    }

    @Override
    public void onError(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        ShowToast.Short(msg);
    }
}
