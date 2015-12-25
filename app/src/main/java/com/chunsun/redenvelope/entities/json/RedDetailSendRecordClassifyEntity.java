package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/8/15.
 */
public class RedDetailSendRecordClassifyEntity extends BaseEntity {

    /**
     * result : {"shz_count":"0","dzf_count":"5","yqw_count":"13","ydj_count":"33","wtg_count":"0","yfb_count":"14"}
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
         * shz_count : 0
         * dzf_count : 5
         * yqw_count : 13
         * ydj_count : 33
         * wtg_count : 0
         * yfb_count : 14
         */
        /**
         * 审核中
         */
        private String shz_count;
        /**
         * 未支付
         */
        private String dzf_count;
        /**
         * 已抢完
         */
        private String yqw_count;
        /**
         * 已冻结
         */
        private String ydj_count;
        /**
         * 未通过
         */
        private String wtg_count;
        /**
         * 已发布
         */
        private String yfb_count;

        public void setShz_count(String shz_count) {
            this.shz_count = shz_count;
        }

        public void setDzf_count(String dzf_count) {
            this.dzf_count = dzf_count;
        }

        public void setYqw_count(String yqw_count) {
            this.yqw_count = yqw_count;
        }

        public void setYdj_count(String ydj_count) {
            this.ydj_count = ydj_count;
        }

        public void setWtg_count(String wtg_count) {
            this.wtg_count = wtg_count;
        }

        public void setYfb_count(String yfb_count) {
            this.yfb_count = yfb_count;
        }

        public String getShz_count() {
            return shz_count;
        }

        public String getDzf_count() {
            return dzf_count;
        }

        public String getYqw_count() {
            return yqw_count;
        }

        public String getYdj_count() {
            return ydj_count;
        }

        public String getWtg_count() {
            return wtg_count;
        }

        public String getYfb_count() {
            return yfb_count;
        }
    }
}
