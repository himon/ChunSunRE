package com.chunsun.redenvelope.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;

/**
 * Created by Administrator on 2015/9/1.
 */
public class AdEntity implements Parcelable {

    /**
     * 单价
     */
    private String price;

    /**
     * 数量
     */
    private String num;

    /**
     * 发放天数
     */
    private String days;

    /**
     * 显示时间
     */
    private AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySeconds;

    /**
     * 定时
     */
    private String startTime;

    /**
     * 广告总价
     */
    private String adPrice;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity getDelaySeconds() {
        return delaySeconds;
    }

    public void setDelaySeconds(AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(String adPrice) {
        this.adPrice = adPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.price);
        dest.writeString(this.num);
        dest.writeString(this.days);
        dest.writeParcelable(this.delaySeconds, 0);
        dest.writeString(this.startTime);
        dest.writeString(this.adPrice);
    }

    public AdEntity() {
    }

    protected AdEntity(Parcel in) {
        this.price = in.readString();
        this.num = in.readString();
        this.days = in.readString();
        this.delaySeconds = in.readParcelable(AdDelaySecondsRateEntity.ResultEntity.DelaySecondsRateEntity.class.getClassLoader());
        this.startTime = in.readString();
        this.adPrice = in.readString();
    }

    public static final Parcelable.Creator<AdEntity> CREATOR = new Parcelable.Creator<AdEntity>() {
        public AdEntity createFromParcel(Parcel source) {
            return new AdEntity(source);
        }

        public AdEntity[] newArray(int size) {
            return new AdEntity[size];
        }
    };
}
