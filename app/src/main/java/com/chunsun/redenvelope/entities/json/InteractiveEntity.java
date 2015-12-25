package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/9/12.
 */
public class InteractiveEntity extends BaseEntity {

    /**
     * result : {"list":[{"content":"求红包","img_url":"/upload/201509/08/201509081318115296.jpeg","nick_name":"深知Y","id":37562,"comment_id":7494,"floor":17,"add_time":"2015/9/8 13:43:11","thumb_img_url":"/upload/201509/08/thumb_201509081318115296.jpeg"},{"content":"怎么老是闪退？","img_url":"/upload/201507/13/201507131305172865.jpeg","nick_name":"匿名","id":5600,"comment_id":6711,"floor":16,"add_time":"2015/9/6 14:35:51","thumb_img_url":"/upload/201507/13/201507131305172865.jpeg"},{"content":"求私包","img_url":"","nick_name":"林","id":35792,"comment_id":6474,"floor":15,"add_time":"2015/9/6 8:39:00","thumb_img_url":""},{"content":"给我个私包","img_url":"/upload/201509/05/201509051038571307.jpeg","nick_name":"六娃","id":36955,"comment_id":6340,"floor":14,"add_time":"2015/9/5 10:40:54","thumb_img_url":"/upload/201509/05/thumb_201509051038571307.jpeg"},{"content":"来啦！","img_url":"/upload/201507/25/201507252041404270.jpeg","nick_name":"春笋科技","id":9,"comment_id":4844,"floor":13,"add_time":"2015/8/29 13:52:30","thumb_img_url":"/upload/201507/25/thumb_201507252041404270.jpeg"},{"content":"钱被抢啦","img_url":"/upload/201508/25/201508250803590479.jpeg","nick_name":"叛逆的时代","id":23020,"comment_id":3844,"floor":12,"add_time":"2015/8/25 12:56:15","thumb_img_url":"/upload/201508/25/thumb_201508250803590479.jpeg"},{"content":"给我个私包","img_url":"/upload/201508/25/201508250803590479.jpeg","nick_name":"叛逆的时代","id":23020,"comment_id":3843,"floor":11,"add_time":"2015/8/25 12:55:47","thumb_img_url":"/upload/201508/25/thumb_201508250803590479.jpeg"},{"content":"以后看见我发的广告多看几眼，我就会长到0.03，谢谢你们关注，常期的话私包","img_url":"/upload/201508/22/201508221051430201.jpeg","nick_name":"华少","id":27283,"comment_id":3600,"floor":10,"add_time":"2015/8/24 19:29:30","thumb_img_url":"/upload/201508/22/thumb_201508221051430201.jpeg"},{"content":"等待有缘人","img_url":"/upload/201508/24/201508241805140834.jpeg","nick_name":"等待有缘人","id":29828,"comment_id":3576,"floor":9,"add_time":"2015/8/24 18:00:30","thumb_img_url":"/upload/201508/24/thumb_201508241805140834.jpeg"},{"content":"抢","img_url":"/upload/201508/24/201508241719589991.jpeg","nick_name":"莹城","id":22218,"comment_id":3504,"floor":8,"add_time":"2015/8/24 14:34:48","thumb_img_url":"/upload/201508/24/thumb_201508241719589991.jpeg"},{"content":"抡","img_url":"/upload/201508/24/201508241719589991.jpeg","nick_name":"莹城","id":22218,"comment_id":3503,"floor":7,"add_time":"2015/8/24 14:34:31","thumb_img_url":"/upload/201508/24/thumb_201508241719589991.jpeg"},{"content":"赞赞赞","img_url":"/upload/201508/23/201508232026233084.jpeg","nick_name":"红包君","id":6793,"comment_id":2982,"floor":6,"add_time":"2015/8/22 22:29:39","thumb_img_url":"/upload/201508/23/thumb_201508232026233084.jpeg"},{"content":"👍👍👍","img_url":"/upload/201508/19/201508190356446182.jpeg","nick_name":"小敏","id":22286,"comment_id":2474,"floor":5,"add_time":"2015/8/21 14:06:29","thumb_img_url":"/upload/201508/19/thumb_201508190356446182.jpeg"},{"content":"啦啦啦","img_url":"/upload/201508/17/201508171703117932.jpeg","nick_name":"林夕","id":22739,"comment_id":1403,"floor":4,"add_time":"2015/8/17 22:03:26","thumb_img_url":"/upload/201508/17/thumb_201508171703117932.jpeg"},{"content":"11:07没有","img_url":"/upload/201505/29/201505291516452606.jpeg","nick_name":"鍚銧","id":777,"comment_id":610,"floor":3,"add_time":"2015/7/23 11:07:09","thumb_img_url":"/upload/201505/29/201505291516452606.jpeg"}],"notice":[{"content":"30%收费600元/年，10%免费开启3个月。详情咨询：0379-80859669QQ：576806999","id":136,"title":"春笋代理免费啦","add_time":"2015/9/2 18:46:28"}],"total_count":"17"}
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
         * list : [{"content":"求红包","img_url":"/upload/201509/08/201509081318115296.jpeg","nick_name":"深知Y","id":37562,"comment_id":7494,"floor":17,"add_time":"2015/9/8 13:43:11","thumb_img_url":"/upload/201509/08/thumb_201509081318115296.jpeg"},{"content":"怎么老是闪退？","img_url":"/upload/201507/13/201507131305172865.jpeg","nick_name":"匿名","id":5600,"comment_id":6711,"floor":16,"add_time":"2015/9/6 14:35:51","thumb_img_url":"/upload/201507/13/201507131305172865.jpeg"},{"content":"求私包","img_url":"","nick_name":"林","id":35792,"comment_id":6474,"floor":15,"add_time":"2015/9/6 8:39:00","thumb_img_url":""},{"content":"给我个私包","img_url":"/upload/201509/05/201509051038571307.jpeg","nick_name":"六娃","id":36955,"comment_id":6340,"floor":14,"add_time":"2015/9/5 10:40:54","thumb_img_url":"/upload/201509/05/thumb_201509051038571307.jpeg"},{"content":"来啦！","img_url":"/upload/201507/25/201507252041404270.jpeg","nick_name":"春笋科技","id":9,"comment_id":4844,"floor":13,"add_time":"2015/8/29 13:52:30","thumb_img_url":"/upload/201507/25/thumb_201507252041404270.jpeg"},{"content":"钱被抢啦","img_url":"/upload/201508/25/201508250803590479.jpeg","nick_name":"叛逆的时代","id":23020,"comment_id":3844,"floor":12,"add_time":"2015/8/25 12:56:15","thumb_img_url":"/upload/201508/25/thumb_201508250803590479.jpeg"},{"content":"给我个私包","img_url":"/upload/201508/25/201508250803590479.jpeg","nick_name":"叛逆的时代","id":23020,"comment_id":3843,"floor":11,"add_time":"2015/8/25 12:55:47","thumb_img_url":"/upload/201508/25/thumb_201508250803590479.jpeg"},{"content":"以后看见我发的广告多看几眼，我就会长到0.03，谢谢你们关注，常期的话私包","img_url":"/upload/201508/22/201508221051430201.jpeg","nick_name":"华少","id":27283,"comment_id":3600,"floor":10,"add_time":"2015/8/24 19:29:30","thumb_img_url":"/upload/201508/22/thumb_201508221051430201.jpeg"},{"content":"等待有缘人","img_url":"/upload/201508/24/201508241805140834.jpeg","nick_name":"等待有缘人","id":29828,"comment_id":3576,"floor":9,"add_time":"2015/8/24 18:00:30","thumb_img_url":"/upload/201508/24/thumb_201508241805140834.jpeg"},{"content":"抢","img_url":"/upload/201508/24/201508241719589991.jpeg","nick_name":"莹城","id":22218,"comment_id":3504,"floor":8,"add_time":"2015/8/24 14:34:48","thumb_img_url":"/upload/201508/24/thumb_201508241719589991.jpeg"},{"content":"抡","img_url":"/upload/201508/24/201508241719589991.jpeg","nick_name":"莹城","id":22218,"comment_id":3503,"floor":7,"add_time":"2015/8/24 14:34:31","thumb_img_url":"/upload/201508/24/thumb_201508241719589991.jpeg"},{"content":"赞赞赞","img_url":"/upload/201508/23/201508232026233084.jpeg","nick_name":"红包君","id":6793,"comment_id":2982,"floor":6,"add_time":"2015/8/22 22:29:39","thumb_img_url":"/upload/201508/23/thumb_201508232026233084.jpeg"},{"content":"👍👍👍","img_url":"/upload/201508/19/201508190356446182.jpeg","nick_name":"小敏","id":22286,"comment_id":2474,"floor":5,"add_time":"2015/8/21 14:06:29","thumb_img_url":"/upload/201508/19/thumb_201508190356446182.jpeg"},{"content":"啦啦啦","img_url":"/upload/201508/17/201508171703117932.jpeg","nick_name":"林夕","id":22739,"comment_id":1403,"floor":4,"add_time":"2015/8/17 22:03:26","thumb_img_url":"/upload/201508/17/thumb_201508171703117932.jpeg"},{"content":"11:07没有","img_url":"/upload/201505/29/201505291516452606.jpeg","nick_name":"鍚銧","id":777,"comment_id":610,"floor":3,"add_time":"2015/7/23 11:07:09","thumb_img_url":"/upload/201505/29/201505291516452606.jpeg"}]
         * notice : [{"content":"30%收费600元/年，10%免费开启3个月。详情咨询：0379-80859669QQ：576806999","id":136,"title":"春笋代理免费啦","add_time":"2015/9/2 18:46:28"}]
         * total_count : 17
         */
        private List<ListEntity> list;
        private List<NoticeEntity> notice;
        private String total_count;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public void setNotice(List<NoticeEntity> notice) {
            this.notice = notice;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public List<NoticeEntity> getNotice() {
            return notice;
        }

        public String getTotal_count() {
            return total_count;
        }

        public static class ListEntity {
            /**
             * content : 求红包
             * img_url : /upload/201509/08/201509081318115296.jpeg
             * nick_name : 深知Y
             * id : 37562
             * comment_id : 7494
             * floor : 17
             * add_time : 2015/9/8 13:43:11
             * thumb_img_url : /upload/201509/08/thumb_201509081318115296.jpeg
             */
            private String content;
            private String img_url;
            private String nick_name;
            private int id;
            private int comment_id;
            private int floor;
            private String add_time;
            private String thumb_img_url;

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

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
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

            public int getComment_id() {
                return comment_id;
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
        }

        public static class NoticeEntity {
            /**
             * content : 30%收费600元/年，10%免费开启3个月。详情咨询：0379-80859669QQ：576806999
             * id : 136
             * title : 春笋代理免费啦
             * add_time : 2015/9/2 18:46:28
             */
            private String content;
            private int id;
            private String title;
            private String add_time;

            public void setContent(String content) {
                this.content = content;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getContent() {
                return content;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getAdd_time() {
                return add_time;
            }
        }
    }
}
