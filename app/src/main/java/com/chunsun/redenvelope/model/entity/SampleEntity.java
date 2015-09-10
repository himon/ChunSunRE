package com.chunsun.redenvelope.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.ui.base.SelectListBase;

/**
 * Created by Administrator on 2015/8/17.
 */
public class SampleEntity extends SelectListBase {

    private String key;
    private String value;
    private String count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeString(this.count);
    }

    public SampleEntity() {
    }

    protected SampleEntity(Parcel in) {
        super(in);
        this.key = in.readString();
        this.value = in.readString();
        this.count = in.readString();
    }

    public static final Creator<SampleEntity> CREATOR = new Creator<SampleEntity>() {
        public SampleEntity createFromParcel(Parcel source) {
            return new SampleEntity(source);
        }

        public SampleEntity[] newArray(int size) {
            return new SampleEntity[size];
        }
    };
}
