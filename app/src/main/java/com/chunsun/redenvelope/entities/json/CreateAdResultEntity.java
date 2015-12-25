package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CreateAdResultEntity extends BaseEntity {

    /**
     * result : {"total_amount":"61.20","id":"2151","order_no":"HB15090815565846"}
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
         * total_amount : 61.20
         * id : 2151
         * order_no : HB15090815565846
         */
        private String total_amount;
        private String id;
        private String order_no;
        private String share_host;

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public String getId() {
            return id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public String getShare_host() {
            return share_host;
        }

        public void setShare_host(String share_host) {
            this.share_host = share_host;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.total_amount);
            dest.writeString(this.id);
            dest.writeString(this.order_no);
            dest.writeString(this.share_host);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.total_amount = in.readString();
            this.id = in.readString();
            this.order_no = in.readString();
            this.share_host = in.readString();
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
