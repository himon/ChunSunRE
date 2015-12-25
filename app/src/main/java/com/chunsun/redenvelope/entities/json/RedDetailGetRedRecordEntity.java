package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 * 红包详情领取记录
 */
public class RedDetailGetRedRecordEntity extends BaseEntity {

    /**
     * result : {"records":[{"img_url":"","nick_name":"匿名","id":6794,"grab_time":"2015/8/12 15:28:36","price":0.1,"thumb_img_url":""},{"img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"grab_time":"2015/8/10 17:36:49","price":0.1,"thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"},{"img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"grab_time":"2015/8/8 16:17:55","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"grab_time":"2015/8/8 15:43:00","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"img_url":"","nick_name":"匿名","id":6783,"grab_time":"2015/8/8 9:12:37","price":0.1,"thumb_img_url":""},{"img_url":"","nick_name":"悄悄话","id":6775,"grab_time":"2015/8/7 15:30:02","price":0.1,"thumb_img_url":""},{"img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"grab_time":"2015/8/7 10:08:07","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"img_url":"/upload/201506/15/201506150730056356.jpeg","nick_name":"小修","id":2820,"grab_time":"2015/8/5 13:09:18","price":0.1,"thumb_img_url":""}],"total_count":"8"}
     */
    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * records : [{"img_url":"","nick_name":"匿名","id":6794,"grab_time":"2015/8/12 15:28:36","price":0.1,"thumb_img_url":""},{"img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"grab_time":"2015/8/10 17:36:49","price":0.1,"thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"},{"img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"grab_time":"2015/8/8 16:17:55","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"grab_time":"2015/8/8 15:43:00","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"img_url":"","nick_name":"匿名","id":6783,"grab_time":"2015/8/8 9:12:37","price":0.1,"thumb_img_url":""},{"img_url":"","nick_name":"悄悄话","id":6775,"grab_time":"2015/8/7 15:30:02","price":0.1,"thumb_img_url":""},{"img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"grab_time":"2015/8/7 10:08:07","price":0.1,"thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"img_url":"/upload/201506/15/201506150730056356.jpeg","nick_name":"小修","id":2820,"grab_time":"2015/8/5 13:09:18","price":0.1,"thumb_img_url":""}]
         * total_count : 8
         */
        private List<RecordsEntity> records;
        private int total_count;

        public void setRecords(List<RecordsEntity> records) {
            this.records = records;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<RecordsEntity> getRecords() {
            return records;
        }

        public int getTotal_count() {
            return total_count;
        }

        public static class RecordsEntity {
            /**
             * img_url :
             * nick_name : 匿名
             * id : 6794
             * grab_time : 2015/8/12 15:28:36
             * price : 0.1
             * thumb_img_url :
             */
            private String img_url;
            private String nick_name;
            private int id;
            private String grab_time;
            private double price;
            private String thumb_img_url;

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setGrab_time(String grab_time) {
                this.grab_time = grab_time;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setThumb_img_url(String thumb_img_url) {
                this.thumb_img_url = thumb_img_url;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getNick_name() {
                return nick_name;
            }

            public int getId() {
                return id;
            }

            public String getGrab_time() {
                return grab_time;
            }

            public double getPrice() {
                return price;
            }

            public String getThumb_img_url() {
                return Constants.IMG_HOST_URL + thumb_img_url;
            }
        }
    }
}
