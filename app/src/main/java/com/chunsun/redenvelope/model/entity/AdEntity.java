package com.chunsun.redenvelope.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.model.entity.json.AdDelaySecondsRateEntity;
import com.chunsun.redenvelope.model.entity.json.DistrictEntity;

/**
 * Created by Administrator on 2015/9/1.
 */
public class AdEntity implements Parcelable {

    /**
     * 广告类型
     */
    private String adType;

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

    /**
     * 广告类型
     */
    private SampleEntity type;

    /**
     * 距离
     */
    private SampleEntity distance;

    /**
     * 省
     */
    private DistrictEntity.AreaEntity province;

    /**
     * 市
     */
    private DistrictEntity.AreaEntity.CcEntity city;

    /**
     * 封面图片
     */
    private String coverImagePath;

    private String imagePath = "";

    private String imagePath2 = "";

    private String imagePath3 = "";

    private String imagePath4 = "";

    private String imagePath5 = "";

    private String imagePath6 = "";

    private String imagePath7 = "";

    private String imagePath8 = "";

    /**
     * 套餐的倍数
     */
    private String formula_multiple;

    /**
     * 选择的套餐
     */
    private String forwarding_packages_id;


    /** 企业账号 */


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

    public SampleEntity getType() {
        return type;
    }

    public void setType(SampleEntity type) {
        this.type = type;
    }

    public SampleEntity getDistance() {
        return distance;
    }

    public void setDistance(SampleEntity distance) {
        this.distance = distance;
    }

    public DistrictEntity.AreaEntity getProvince() {
        return province;
    }

    public void setProvince(DistrictEntity.AreaEntity province) {
        this.province = province;
    }

    public DistrictEntity.AreaEntity.CcEntity getCity() {
        return city;
    }

    public void setCity(DistrictEntity.AreaEntity.CcEntity city) {
        this.city = city;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3;
    }

    public String getImagePath4() {
        return imagePath4;
    }

    public void setImagePath4(String imagePath4) {
        this.imagePath4 = imagePath4;
    }

    public String getImagePath5() {
        return imagePath5;
    }

    public void setImagePath5(String imagePath5) {
        this.imagePath5 = imagePath5;
    }

    public String getImagePath6() {
        return imagePath6;
    }

    public void setImagePath6(String imagePath6) {
        this.imagePath6 = imagePath6;
    }

    public String getImagePath7() {
        return imagePath7;
    }

    public void setImagePath7(String imagePath7) {
        this.imagePath7 = imagePath7;
    }

    public String getImagePath8() {
        return imagePath8;
    }

    public void setImagePath8(String imagePath8) {
        this.imagePath8 = imagePath8;
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
        dest.writeParcelable(this.type, 0);
        dest.writeParcelable(this.distance, 0);
        dest.writeParcelable(this.province, 0);
        dest.writeParcelable(this.city, 0);
        dest.writeString(this.coverImagePath);
        dest.writeString(this.imagePath);
        dest.writeString(this.imagePath2);
        dest.writeString(this.imagePath3);
        dest.writeString(this.imagePath4);
        dest.writeString(this.imagePath5);
        dest.writeString(this.imagePath6);
        dest.writeString(this.imagePath7);
        dest.writeString(this.imagePath8);
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
        this.type = in.readParcelable(SampleEntity.class.getClassLoader());
        this.distance = in.readParcelable(SampleEntity.class.getClassLoader());
        this.province = in.readParcelable(DistrictEntity.AreaEntity.class.getClassLoader());
        this.city = in.readParcelable(DistrictEntity.AreaEntity.CcEntity.class.getClassLoader());
        this.coverImagePath = in.readString();
        this.imagePath = in.readString();
        this.imagePath2 = in.readString();
        this.imagePath3 = in.readString();
        this.imagePath4 = in.readString();
        this.imagePath5 = in.readString();
        this.imagePath6 = in.readString();
        this.imagePath7 = in.readString();
        this.imagePath8 = in.readString();
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
