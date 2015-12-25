package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
public class RedDetailUnReceiveAndCollectEntity extends BaseEntity {


    /**
     * result : [{"comment_count":9,"cover_img_url":"/upload/201508/13/201508131341058235.jpeg","range":"全国","status":1,"type":1,"id":1813,"content":"","nick_name":"听风者","grab_time":"2015/8/15 14:02:08","title":"IPOMSN","payable_amount":60,"type_title":"生活","status_title":"待领取"}]
     */
    private List<ResultEntity> result;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * comment_count : 9
         * cover_img_url : /upload/201508/13/201508131341058235.jpeg
         * range : 全国
         * status : 1
         * type : 1
         * id : 1813
         * content :
         * nick_name : 听风者
         * grab_time : 2015/8/15 14:02:08
         * title : IPOMSN
         * payable_amount : 60.0
         * type_title : 生活
         * status_title : 待领取
         * send_time : 2015/8/15 14:02:08
         */
        private int comment_count;
        private String cover_img_url;
        private String range;
        private int status;
        private int type;
        private int id;
        private String content;
        private String nick_name;
        private String grab_time;
        private String title;
        private double payable_amount;
        private String type_title;
        private String status_title;
        //收藏接口
        private String send_time;

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public void setCover_img_url(String cover_img_url) {
            this.cover_img_url = cover_img_url;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public void setId(int id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setGrab_time(String grab_time) {
            this.grab_time = grab_time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPayable_amount(double payable_amount) {
            this.payable_amount = payable_amount;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        public int getComment_count() {
            return comment_count;
        }

        public String getCover_img_url() {
            return Constants.IMG_HOST_URL + cover_img_url;
        }

        public String getRange() {
            return range;
        }

        public int getStatus() {
            return status;
        }


        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getGrab_time() {
            return grab_time;
        }

        public String getTitle() {
            return title;
        }

        public double getPayable_amount() {
            return payable_amount;
        }

        public String getType_title() {
            return type_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
