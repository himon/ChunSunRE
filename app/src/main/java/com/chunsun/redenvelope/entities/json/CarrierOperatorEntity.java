package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public class CarrierOperatorEntity extends BaseEntity{


    /**
     * area : 7
     * areaName : 河南
     * providerName : 移动
     * provider : 1
     */
    private String area;
    private String areaName;
    private String providerName;
    private int provider;

    public void setArea(String area) {
        this.area = area;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public String getArea() {
        return area;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getProviderName() {
        return providerName;
    }

    public int getProvider() {
        return provider;
    }
}
