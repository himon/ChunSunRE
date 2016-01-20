package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RedDetailCommentEntity extends BaseEntity {

    /**
     * result : {"list":[{"content":"sfsaf","img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"floor":16,"add_time":"2015/8/8 16:07:54","thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"content":"杀马特","img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"floor":15,"add_time":"2015/8/8 15:43:20","thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"content":"咖喱鸡","img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"floor":14,"add_time":"2015/8/8 15:43:13","thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"content":"Vjjj","img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"floor":13,"add_time":"2015/8/8 15:24:44","thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"},{"content":"同婆婆","img_url":"","nick_name":"悄悄话","id":6775,"floor":12,"add_time":"2015/8/8 15:23:08","thumb_img_url":""},{"content":"来来来","img_url":"","nick_name":"悄悄话","id":6775,"floor":11,"add_time":"2015/8/8 15:22:22","thumb_img_url":""},{"content":"咯哦哦哟哟哟哦哦","img_url":"","nick_name":"悄悄话","id":6775,"floor":10,"add_time":"2015/8/8 15:21:55","thumb_img_url":""},{"content":"他咯","img_url":"","nick_name":"悄悄话","id":6775,"floor":9,"add_time":"2015/8/8 15:21:32","thumb_img_url":""},{"content":"来来来","img_url":"","nick_name":"悄悄话","id":6775,"floor":8,"add_time":"2015/8/8 15:20:47","thumb_img_url":""},{"content":"Uhhgf","img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"floor":7,"add_time":"2015/8/8 15:20:24","thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"}],"total_count":"16"}
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
         * list : [{"content":"sfsaf","img_url":"/upload/201507/24/201507241137027311.jpeg","nick_name":"恭喜发财","id":3110,"floor":16,"add_time":"2015/8/8 16:07:54","thumb_img_url":"/upload/201507/24/thumb_201507241137027311.jpeg"},{"content":"杀马特","img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"floor":15,"add_time":"2015/8/8 15:43:20","thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"content":"咖喱鸡","img_url":"/upload/201507/24/201507240952243669.jpeg","nick_name":"听风者","id":6,"floor":14,"add_time":"2015/8/8 15:43:13","thumb_img_url":"/upload/201507/24/thumb_201507240952243669.jpeg"},{"content":"Vjjj","img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"floor":13,"add_time":"2015/8/8 15:24:44","thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"},{"content":"同婆婆","img_url":"","nick_name":"悄悄话","id":6775,"floor":12,"add_time":"2015/8/8 15:23:08","thumb_img_url":""},{"content":"来来来","img_url":"","nick_name":"悄悄话","id":6775,"floor":11,"add_time":"2015/8/8 15:22:22","thumb_img_url":""},{"content":"咯哦哦哟哟哟哦哦","img_url":"","nick_name":"悄悄话","id":6775,"floor":10,"add_time":"2015/8/8 15:21:55","thumb_img_url":""},{"content":"他咯","img_url":"","nick_name":"悄悄话","id":6775,"floor":9,"add_time":"2015/8/8 15:21:32","thumb_img_url":""},{"content":"来来来","img_url":"","nick_name":"悄悄话","id":6775,"floor":8,"add_time":"2015/8/8 15:20:47","thumb_img_url":""},{"content":"Uhhgf","img_url":"/upload/201508/08/201508080857408546.jpeg","nick_name":"墨迹","id":6782,"floor":7,"add_time":"2015/8/8 15:20:24","thumb_img_url":"/upload/201508/08/thumb_201508080857408546.jpeg"}]
         * total_count : 16
         */
        private List<ListEntity> list;
        private String total_count;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public String getTotal_count() {
            return total_count;
        }

        public static class ListEntity {
            /**
             * content : sfsaf
             * img_url : /upload/201507/24/201507241137027311.jpeg
             * nick_name : 恭喜发财
             * id : 3110
             * floor : 16
             * add_time : 2015/8/8 16:07:54
             * thumb_img_url : /upload/201507/24/thumb_201507241137027311.jpeg
             */
            private String content;
            private String img_url;
            private String nick_name;
            private int id;
            private int floor;
            private String add_time;
            private String thumb_img_url;
            private String at_user_name;

            public void setContent(String content) {
                this.content = content;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setFloor(int floor) {
                this.floor = floor;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setThumb_img_url(String thumb_img_url) {
                this.thumb_img_url = thumb_img_url;
            }

            public String getContent() {
                return content;
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

            public int getFloor() {
                return floor;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getThumb_img_url() {
                return Constants.IMG_HOST_URL + thumb_img_url;
            }

            public String getAt_user_name() {
                return at_user_name;
            }

            public void setAt_user_name(String at_user_name) {
                this.at_user_name = at_user_name;
            }
        }
    }
}
