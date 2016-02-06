package com.chunsun.redenvelope.ui.view;

import com.chunsun.redenvelope.entities.json.ApkVersionEntity;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface ISettingView {

    void toAboutUs();

    void toUpdatePwd();

    void toLogout();

    void toUpdatePrivacy();

    /**
     * 下载apk
     *
     * @param apk
     */
    void isUpGrade(ApkVersionEntity apk);
}
