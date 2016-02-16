package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 * 红包列表轮播广告实体
 */
public class RedAutoAdEntity extends BaseEntity {


    /**
     * result : {"advert":[{"img_url":"/upload/201601/29/201601291743368431.png","title":"圈子","zhaiyao":"","target_url":"http://viewer.maka.im/k/EEJDLI6E?DSCKID=6dcc8eba-6de0-449c-b9b4-f301f6a05917&DSTIMESTAMP=1450520232113"},{"img_url":"/upload/201601/29/201601291742457072.png","title":"圈子","zhaiyao":"","target_url":"http://eqxiu.com/s/tFGXjEEZ?from=singlemessage&isappinstalled=0"}],"notice":[{"id":148,"title":"红包来了"}]}
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
         * advert : [{"img_url":"/upload/201601/29/201601291743368431.png","title":"圈子","zhaiyao":"","target_url":"http://viewer.maka.im/k/EEJDLI6E?DSCKID=6dcc8eba-6de0-449c-b9b4-f301f6a05917&DSTIMESTAMP=1450520232113"},{"img_url":"/upload/201601/29/201601291742457072.png","title":"圈子","zhaiyao":"","target_url":"http://eqxiu.com/s/tFGXjEEZ?from=singlemessage&isappinstalled=0"}]
         * notice : [{"id":148,"title":"红包来了"}]
         */
        private List<AdvertEntity> advert;
        private List<NoticeEntity> notice;

        public void setAdvert(List<AdvertEntity> advert) {
            this.advert = advert;
        }

        public void setNotice(List<NoticeEntity> notice) {
            this.notice = notice;
        }

        public List<AdvertEntity> getAdvert() {
            return advert;
        }

        public List<NoticeEntity> getNotice() {
            return notice;
        }

        public static class AdvertEntity {
            /**
             * img_url : /upload/201601/29/201601291743368431.png
             * title : 圈子
             * zhaiyao :
             * target_url : http://viewer.maka.im/k/EEJDLI6E?DSCKID=6dcc8eba-6de0-449c-b9b4-f301f6a05917&DSTIMESTAMP=1450520232113
             */
            private String img_url;
            private String title;
            private String zhaiyao;
            private String target_url;

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setZhaiyao(String zhaiyao) {
                this.zhaiyao = zhaiyao;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getTitle() {
                return title;
            }

            public String getZhaiyao() {
                return zhaiyao;
            }

            public String getTarget_url() {
                return target_url;
            }
        }

        public static class NoticeEntity {
            /**
             * id : 148
             * title : 红包来了
             */
            private int id;
            private String title;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
