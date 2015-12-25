package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/9/28 13:55
 * @des ${TODO}
 */
public class RepeatRedEnvelopeGetHostEntity extends BaseEntity{


    /**
     * result : {"share_url":"http://1.193.162.20:9101/pages/forward/index.html?forwardId=131"}
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
         * share_url : http://1.193.162.20:9101/pages/forward/index.html?forwardId=131
         */
        private String share_url;

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShare_url() {
            return share_url;
        }
    }
}
