package com.chunsun.redenvelope.ui.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/9/6.
 */
public class SelectListBase implements Parcelable {

    private boolean check;

    private int type;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(check ? (byte) 1 : (byte) 0);
        dest.writeInt(this.type);
    }

    public SelectListBase() {
    }

    protected SelectListBase(Parcel in) {
        this.check = in.readByte() != 0;
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<SelectListBase> CREATOR = new Parcelable.Creator<SelectListBase>() {
        public SelectListBase createFromParcel(Parcel source) {
            return new SelectListBase(source);
        }

        public SelectListBase[] newArray(int size) {
            return new SelectListBase[size];
        }
    };
}
