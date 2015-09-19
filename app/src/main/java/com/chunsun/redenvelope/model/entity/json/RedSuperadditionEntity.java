package com.chunsun.redenvelope.model.entity.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/9/18.
 */
public class RedSuperadditionEntity extends BaseEntity {


    /**
     * result : {"cover_img_url":"/upload/201507/25/QQ20150725114020.jpeg","img_url":"/upload/201507/24/201507241641180324.jpeg,/upload/201507/24/201507241641180685.jpeg,/upload/201507/24/201507241641181144.jpeg","delay_seconds":"15","everyday_count":"2000","range":"0","type":"1","is_need_receipt":"False","day_count":"1","city":"不限","content":"        对于众多微商而言，你的产品和服务能否被消费者认可和接受，宣传推广就显得尤为重要。作为一款专业型的营销APP，春笋红包创造性地将抢红包与营销结合起来。微商只需将广告和红包捆绑发送，就能在春笋红包用户群中形成狂热的抢红包浪潮，微商的营销效果也就在众粉的踊跃参与中达到最大化。 \n        与报纸、广播、电视、宣传页等传统营销方式相比，春笋红包显然要更加精准、更加可控，性价比也就更高。 \n\n        点击下载春笋红包，开启??您的微商之旅！","time":"","title":"精准高效营销 微商必备利器","price":"0.10","province":"不限"}
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
         * cover_img_url : /upload/201507/25/QQ20150725114020.jpeg
         * img_url : /upload/201507/24/201507241641180324.jpeg,/upload/201507/24/201507241641180685.jpeg,/upload/201507/24/201507241641181144.jpeg
         * delay_seconds : 15
         * everyday_count : 2000
         * range : 0
         * type : 1
         * is_need_receipt : False
         * day_count : 1
         * city : 不限
         * content :         对于众多微商而言，你的产品和服务能否被消费者认可和接受，宣传推广就显得尤为重要。作为一款专业型的营销APP，春笋红包创造性地将抢红包与营销结合起来。微商只需将广告和红包捆绑发送，就能在春笋红包用户群中形成狂热的抢红包浪潮，微商的营销效果也就在众粉的踊跃参与中达到最大化。
         * 与报纸、广播、电视、宣传页等传统营销方式相比，春笋红包显然要更加精准、更加可控，性价比也就更高。
         * <p/>
         * 点击下载春笋红包，开启??您的微商之旅！
         * time :
         * title : 精准高效营销 微商必备利器
         * price : 0.10
         * province : 不限
         */
        private String cover_img_url;
        private String img_url;
        private String delay_seconds;
        /**
         * 数量
         */
        private String everyday_count;
        private String range;
        private String type;
        private boolean is_need_receipt;
        private String day_count;
        private String city;
        private String content;
        private String time;
        private String title;
        private String price;
        private String province;

        public String getCover_img_url() {
            return Constants.IMG_HOST_URL + cover_img_url;
        }

        public void setCover_img_url(String cover_img_url) {
            this.cover_img_url = cover_img_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getDelay_seconds() {
            return delay_seconds;
        }

        public void setDelay_seconds(String delay_seconds) {
            this.delay_seconds = delay_seconds;
        }

        public String getEveryday_count() {
            return everyday_count;
        }

        public void setEveryday_count(String everyday_count) {
            this.everyday_count = everyday_count;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean is_need_receipt() {
            return is_need_receipt;
        }

        public void setIs_need_receipt(boolean is_need_receipt) {
            this.is_need_receipt = is_need_receipt;
        }

        public String getDay_count() {
            return day_count;
        }

        public void setDay_count(String day_count) {
            this.day_count = day_count;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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
            dest.writeByte(is_need_receipt ? (byte) 1 : (byte) 0);
            dest.writeString(this.day_count);
            dest.writeString(this.city);
            dest.writeString(this.content);
            dest.writeString(this.time);
            dest.writeString(this.title);
            dest.writeString(this.price);
            dest.writeString(this.province);
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
            this.is_need_receipt = in.readByte() != 0;
            this.day_count = in.readString();
            this.city = in.readString();
            this.content = in.readString();
            this.time = in.readString();
            this.title = in.readString();
            this.price = in.readString();
            this.province = in.readString();
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
