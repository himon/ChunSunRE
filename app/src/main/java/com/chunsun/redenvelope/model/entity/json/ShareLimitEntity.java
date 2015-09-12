package com.chunsun.redenvelope.model.entity.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/9/10.
 */
public class ShareLimitEntity extends BaseEntity {


    /**
     * result : {"qz":"True","qz_count":"100","wf_count":"100","wc":"True","share_min_amount":"1","sw":"True","wf":"True","share_host":"http://ad.chunsunkeji.com/","sw_count":"100","wc_count":"1000"}
     */
    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity implements Parcelable {
        /**
         * qz : True
         * qz_count : 100
         * wf_count : 100
         * wc : True
         * share_min_amount : 1
         * sw : True
         * wf : True
         * share_host : http://ad.chunsunkeji.com/
         * sw_count : 100
         * wc_count : 1000
         */
        private boolean qz;
        private String qz_count;
        private String wf_count;
        private boolean wc;
        private String share_min_amount;
        private boolean sw;
        private boolean wf;
        private String share_host;
        private String sw_count;
        private String wc_count;

        public boolean isQz() {
            return qz;
        }

        public void setQz(boolean qz) {
            this.qz = qz;
        }

        public String getQz_count() {
            return qz_count;
        }

        public void setQz_count(String qz_count) {
            this.qz_count = qz_count;
        }

        public String getWf_count() {
            return wf_count;
        }

        public void setWf_count(String wf_count) {
            this.wf_count = wf_count;
        }

        public boolean isWc() {
            return wc;
        }

        public void setWc(boolean wc) {
            this.wc = wc;
        }

        public String getShare_min_amount() {
            return share_min_amount;
        }

        public void setShare_min_amount(String share_min_amount) {
            this.share_min_amount = share_min_amount;
        }

        public boolean isSw() {
            return sw;
        }

        public void setSw(boolean sw) {
            this.sw = sw;
        }

        public boolean isWf() {
            return wf;
        }

        public void setWf(boolean wf) {
            this.wf = wf;
        }

        public String getShare_host() {
            return share_host;
        }

        public void setShare_host(String share_host) {
            this.share_host = share_host;
        }

        public String getSw_count() {
            return sw_count;
        }

        public void setSw_count(String sw_count) {
            this.sw_count = sw_count;
        }

        public String getWc_count() {
            return wc_count;
        }

        public void setWc_count(String wc_count) {
            this.wc_count = wc_count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(qz ? (byte) 1 : (byte) 0);
            dest.writeString(this.qz_count);
            dest.writeString(this.wf_count);
            dest.writeByte(wc ? (byte) 1 : (byte) 0);
            dest.writeString(this.share_min_amount);
            dest.writeByte(sw ? (byte) 1 : (byte) 0);
            dest.writeByte(wf ? (byte) 1 : (byte) 0);
            dest.writeString(this.share_host);
            dest.writeString(this.sw_count);
            dest.writeString(this.wc_count);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.qz = in.readByte() != 0;
            this.qz_count = in.readString();
            this.wf_count = in.readString();
            this.wc = in.readByte() != 0;
            this.share_min_amount = in.readString();
            this.sw = in.readByte() != 0;
            this.wf = in.readByte() != 0;
            this.share_host = in.readString();
            this.sw_count = in.readString();
            this.wc_count = in.readString();
        }

        public static final Parcelable.Creator<ResultEntity> CREATOR = new Parcelable.Creator<ResultEntity>() {
            public ResultEntity createFromParcel(Parcel source) {
                return new ResultEntity(source);
            }

            public ResultEntity[] newArray(int size) {
                return new ResultEntity[size];
            }
        };
    }
}
