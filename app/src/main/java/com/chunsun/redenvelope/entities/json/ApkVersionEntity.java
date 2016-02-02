package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/2/2 15:27
 * @des apk升级信息
 */
public class ApkVersionEntity extends BaseEntity {


    /**
     * update : 1
     * apkVersion : 1.3.1
     * description :
     * 发现新版本v1.3.1 ：
     * 1、新增@用户评论。
     * 2、新增拼手气红包。
     * 3、修复其他小问题。
     * <p/>
     * versionCode : 12
     * url : http://121.42.198.130:2015/app/chunsun.apk
     */
    private int update;
    private String apkVersion;
    private String description;
    private int versionCode;
    private String url;

    public void setUpdate(int update) {
        this.update = update;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUpdate() {
        return update;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public String getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }
}
