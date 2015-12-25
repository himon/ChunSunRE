package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/9/18.
 */
public class RedSuperadditionEntity extends BaseEntity {


    /**
     * result : {"cover_img_url":"/upload/201510/12/201510120913334128.jpeg","img_url":"/upload/201510/12/201510120913334528.jpeg","delay_seconds":"0","everyday_count":"4500","range":"0","type":"5","is_need_receipt":"False","day_count":"1","forwarding_packages_id":"2","city":"不限","content":"daasdads","time":"","title":"sdasd","price":"0.03","formula_multiple":"1.5","province":"河南省","RangeString":"0km","delay_seconds_poundage_id":"0"}
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
         * cover_img_url : /upload/201510/12/201510120913334128.jpeg
         * img_url : /upload/201510/12/201510120913334528.jpeg
         * delay_seconds : 0
         * everyday_count : 4500
         * range : 0
         * type : 5
         * is_need_receipt : False
         * day_count : 1
         * forwarding_packages_id : 2
         * city : 不限
         * content : daasdads
         * time :
         * title : sdasd
         * price : 0.03
         * formula_multiple : 1.5
         * province : 河南省
         * RangeString : 0km
         * delay_seconds_poundage_id : 0
         */
        private String cover_img_url;
        private String img_url;
        private String delay_seconds;
        private String everyday_count;
        private String range;
        private String type;
        private String is_need_receipt;
        private String day_count;
        private String forwarding_packages_id;
        private String city;
        private String content;
        private String time;
        private String title;
        private String price;
        private String formula_multiple;
        private String province;
        private String RangeString;
        private String delay_seconds_poundage_id;
        private String start_time;
        private String end_time;

        public void setCover_img_url(String cover_img_url) {
            this.cover_img_url = cover_img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public void setDelay_seconds(String delay_seconds) {
            this.delay_seconds = delay_seconds;
        }

        public void setEveryday_count(String everyday_count) {
            this.everyday_count = everyday_count;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setIs_need_receipt(String is_need_receipt) {
            this.is_need_receipt = is_need_receipt;
        }

        public void setDay_count(String day_count) {
            this.day_count = day_count;
        }

        public void setForwarding_packages_id(String forwarding_packages_id) {
            this.forwarding_packages_id = forwarding_packages_id;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setFormula_multiple(String formula_multiple) {
            this.formula_multiple = formula_multiple;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setRangeString(String RangeString) {
            this.RangeString = RangeString;
        }

        public void setDelay_seconds_poundage_id(String delay_seconds_poundage_id) {
            this.delay_seconds_poundage_id = delay_seconds_poundage_id;
        }

        public String getCover_img_url() {
            return cover_img_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public String getDelay_seconds() {
            return delay_seconds;
        }

        public String getEveryday_count() {
            return everyday_count;
        }

        public String getRange() {
            return range;
        }

        public String getType() {
            return type;
        }

        public String getIs_need_receipt() {
            return is_need_receipt;
        }

        public String getDay_count() {
            return day_count;
        }

        public String getForwarding_packages_id() {
            return forwarding_packages_id;
        }

        public String getCity() {
            return city;
        }

        public String getContent() {
            return content;
        }

        public String getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public String getFormula_multiple() {
            return formula_multiple;
        }

        public String getProvince() {
            return province;
        }

        public String getRangeString() {
            return RangeString;
        }

        public String getDelay_seconds_poundage_id() {
            return delay_seconds_poundage_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cover_img_url);
            dest.writeString(this.img_url);
            dest.writeString(this.delay_seconds);
            dest.writeString(this.everyday_count);
            dest.writeString(this.range);
            dest.writeString(this.type);
            dest.writeString(this.is_need_receipt);
            dest.writeString(this.day_count);
            dest.writeString(this.forwarding_packages_id);
            dest.writeString(this.city);
            dest.writeString(this.content);
            dest.writeString(this.time);
            dest.writeString(this.title);
            dest.writeString(this.price);
            dest.writeString(this.formula_multiple);
            dest.writeString(this.province);
            dest.writeString(this.RangeString);
            dest.writeString(this.delay_seconds_poundage_id);
            dest.writeString(this.start_time);
            dest.writeString(this.end_time);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.cover_img_url = in.readString();
            this.img_url = in.readString();
            this.delay_seconds = in.readString();
            this.everyday_count = in.readString();
            this.range = in.readString();
            this.type = in.readString();
            this.is_need_receipt = in.readString();
            this.day_count = in.readString();
            this.forwarding_packages_id = in.readString();
            this.city = in.readString();
            this.content = in.readString();
            this.time = in.readString();
            this.title = in.readString();
            this.price = in.readString();
            this.formula_multiple = in.readString();
            this.province = in.readString();
            this.RangeString = in.readString();
            this.delay_seconds_poundage_id = in.readString();
            this.start_time = in.readString();
            this.end_time = in.readString();
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
