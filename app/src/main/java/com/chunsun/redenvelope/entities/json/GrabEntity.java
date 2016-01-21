package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.math.BigDecimal;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/20 18:10
 * @des 红包grab
 */
public class GrabEntity extends BaseEntity implements Parcelable {

    /**
     * result : {"is_upper_limit":"False","has_grab":"False","share_limit":{"qz":"True","qz_count":"100","wf_count":"1000","wc":"True","share_min_amount":"0.05","sw":"True","wf":"True","share_host":"http://1.193.162.20:9101/","sw_count":"100","wc_count":"1000"},"hb_grab_id":"4837823","hb_price":"0.03","must_share":"False"}
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
         * is_upper_limit : False
         * has_grab : False
         * share_limit : {"qz":"True","qz_count":"100","wf_count":"1000","wc":"True","share_min_amount":"0.05","sw":"True","wf":"True","share_host":"http://1.193.162.20:9101/","sw_count":"100","wc_count":"1000"}
         * hb_grab_id : 4837823
         * hb_price : 0.03
         * must_share : False
         */
        private String is_upper_limit;
        private String has_grab;
        private ShareLimitEntity share_limit;
        private String hb_grab_id;
        private String hb_price;
        private boolean must_share;

        public void setIs_upper_limit(String is_upper_limit) {
            this.is_upper_limit = is_upper_limit;
        }

        public void setHas_grab(String has_grab) {
            this.has_grab = has_grab;
        }

        public void setShare_limit(ShareLimitEntity share_limit) {
            this.share_limit = share_limit;
        }

        public void setHb_grab_id(String hb_grab_id) {
            this.hb_grab_id = hb_grab_id;
        }

        public void setHb_price(String hb_price) {
            this.hb_price = hb_price;
        }

        public void setMust_share(boolean must_share) {
            this.must_share = must_share;
        }

        public String getIs_upper_limit() {
            return is_upper_limit;
        }

        public String getHas_grab() {
            return has_grab;
        }

        public ShareLimitEntity getShare_limit() {
            return share_limit;
        }

        public String getHb_grab_id() {
            return hb_grab_id;
        }

        public String getHb_price() {
            return hb_price;
        }

        public boolean isMust_share() {
            return must_share;
        }

        public static class ShareLimitEntity implements Parcelable {
            /**
             * qz : True
             * qz_count : 100
             * wf_count : 1000
             * wc : True
             * share_min_amount : 0.05
             * sw : True
             * wf : True
             * share_host : http://1.193.162.20:9101/
             * sw_count : 100
             * wc_count : 1000
             */
            private boolean qz;
            private String qz_count;
            private String wf_count;
            private boolean wc;
            private BigDecimal share_min_amount;
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

            public BigDecimal getShare_min_amount() {
                return share_min_amount;
            }

            public void setShare_min_amount(BigDecimal share_min_amount) {
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

            public static Creator<ShareLimitEntity> getCREATOR() {
                return CREATOR;
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
                dest.writeSerializable(this.share_min_amount);
                dest.writeByte(sw ? (byte) 1 : (byte) 0);
                dest.writeByte(wf ? (byte) 1 : (byte) 0);
                dest.writeString(this.share_host);
                dest.writeString(this.sw_count);
                dest.writeString(this.wc_count);
            }

            public ShareLimitEntity() {
            }

            protected ShareLimitEntity(Parcel in) {
                this.qz = in.readByte() != 0;
                this.qz_count = in.readString();
                this.wf_count = in.readString();
                this.wc = in.readByte() != 0;
                this.share_min_amount = (BigDecimal) in.readSerializable();
                this.sw = in.readByte() != 0;
                this.wf = in.readByte() != 0;
                this.share_host = in.readString();
                this.sw_count = in.readString();
                this.wc_count = in.readString();
            }

            public static final Parcelable.Creator<ShareLimitEntity> CREATOR = new Parcelable.Creator<ShareLimitEntity>() {
                public ShareLimitEntity createFromParcel(Parcel source) {
                    return new ShareLimitEntity(source);
                }

                public ShareLimitEntity[] newArray(int size) {
                    return new ShareLimitEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.is_upper_limit);
            dest.writeString(this.has_grab);
            dest.writeParcelable(this.share_limit, 0);
            dest.writeString(this.hb_grab_id);
            dest.writeString(this.hb_price);
            dest.writeByte(must_share ? (byte) 1 : (byte) 0);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.is_upper_limit = in.readString();
            this.has_grab = in.readString();
            this.share_limit = in.readParcelable(ShareLimitEntity.class.getClassLoader());
            this.hb_grab_id = in.readString();
            this.hb_price = in.readString();
            this.must_share = in.readByte() != 0;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.result, 0);
    }

    public GrabEntity() {
    }

    protected GrabEntity(Parcel in) {
        this.result = in.readParcelable(ResultEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<GrabEntity> CREATOR = new Parcelable.Creator<GrabEntity>() {
        public GrabEntity createFromParcel(Parcel source) {
            return new GrabEntity(source);
        }

        public GrabEntity[] newArray(int size) {
            return new GrabEntity[size];
        }
    };
}
