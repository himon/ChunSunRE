package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/20 18:10
 * @des 红包grab
 */
public class GrabEntity extends BaseEntity {

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

    public static class ResultEntity {
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
        private String must_share;

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

        public void setMust_share(String must_share) {
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

        public String getMust_share() {
            return must_share;
        }

        public static class ShareLimitEntity {
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
            private String qz;
            private String qz_count;
            private String wf_count;
            private String wc;
            private String share_min_amount;
            private String sw;
            private String wf;
            private String share_host;
            private String sw_count;
            private String wc_count;

            public void setQz(String qz) {
                this.qz = qz;
            }

            public void setQz_count(String qz_count) {
                this.qz_count = qz_count;
            }

            public void setWf_count(String wf_count) {
                this.wf_count = wf_count;
            }

            public void setWc(String wc) {
                this.wc = wc;
            }

            public void setShare_min_amount(String share_min_amount) {
                this.share_min_amount = share_min_amount;
            }

            public void setSw(String sw) {
                this.sw = sw;
            }

            public void setWf(String wf) {
                this.wf = wf;
            }

            public void setShare_host(String share_host) {
                this.share_host = share_host;
            }

            public void setSw_count(String sw_count) {
                this.sw_count = sw_count;
            }

            public void setWc_count(String wc_count) {
                this.wc_count = wc_count;
            }

            public String getQz() {
                return qz;
            }

            public String getQz_count() {
                return qz_count;
            }

            public String getWf_count() {
                return wf_count;
            }

            public String getWc() {
                return wc;
            }

            public String getShare_min_amount() {
                return share_min_amount;
            }

            public String getSw() {
                return sw;
            }

            public String getWf() {
                return wf;
            }

            public String getShare_host() {
                return share_host;
            }

            public String getSw_count() {
                return sw_count;
            }

            public String getWc_count() {
                return wc_count;
            }
        }
    }
}
