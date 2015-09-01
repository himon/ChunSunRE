package com.chunsun.redenvelope.model.entity.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 * 红包列表轮播广告实体
 */
public class RedAutoAdEntity extends BaseEntity {

    /**
     * result : {"advert":[{"img_url":"/upload/201507/21/201507210910071288.png","title":"你发广告，春笋买单","zhaiyao":"你发广告，春笋买单<br />","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210914541343.png","title":"324","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210910262348.jpg","title":"34","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210910165558.png","title":"1的","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"}],"notice":[]}
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
         * advert : [{"img_url":"/upload/201507/21/201507210910071288.png","title":"你发广告，春笋买单","zhaiyao":"你发广告，春笋买单<br />","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210914541343.png","title":"324","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210910262348.jpg","title":"34","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"},{"img_url":"/upload/201507/21/201507210910165558.png","title":"1的","zhaiyao":"","target_url":"http://sv.chunsunkeji.com/pages/shuoming/index.html"}]
         * notice : []
         */
        private List<AdvertEntity> advert;
        private List<?> notice;

        public void setAdvert(List<AdvertEntity> advert) {
            this.advert = advert;
        }

        public void setNotice(List<?> notice) {
            this.notice = notice;
        }

        public List<AdvertEntity> getAdvert() {
            return advert;
        }

        public List<?> getNotice() {
            return notice;
        }

        public static class AdvertEntity {
            /**
             * img_url : /upload/201507/21/201507210910071288.png
             * title : 你发广告，春笋买单
             * zhaiyao : 你发广告，春笋买单<br />
             * target_url : http://sv.chunsunkeji.com/pages/shuoming/index.html
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
                return Constants.IMG_HOST_URL + img_url.trim();
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
    }
}
