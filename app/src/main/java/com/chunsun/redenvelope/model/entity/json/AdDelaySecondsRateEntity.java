package com.chunsun.redenvelope.model.entity.json;

import com.chunsun.redenvelope.model.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/24.
 */
public class AdDelaySecondsRateEntity extends BaseEntity {


    /**
     * result : {"delay_seconds_rate":[{"id":13,"delay_seconds":5,"delay_poundage":0},{"id":14,"delay_seconds":10,"delay_poundage":0},{"id":1,"delay_seconds":15,"delay_poundage":0},{"id":8,"delay_seconds":20,"delay_poundage":0.05},{"id":9,"delay_seconds":25,"delay_poundage":0.1},{"id":10,"delay_seconds":30,"delay_poundage":0.15},{"id":11,"delay_seconds":35,"delay_poundage":0.2},{"id":12,"delay_seconds":40,"delay_poundage":0.25}],"hb_min_amount":"0.01","hb_min_price":"0.01"}
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
         * delay_seconds_rate : [{"id":13,"delay_seconds":5,"delay_poundage":0},{"id":14,"delay_seconds":10,"delay_poundage":0},{"id":1,"delay_seconds":15,"delay_poundage":0},{"id":8,"delay_seconds":20,"delay_poundage":0.05},{"id":9,"delay_seconds":25,"delay_poundage":0.1},{"id":10,"delay_seconds":30,"delay_poundage":0.15},{"id":11,"delay_seconds":35,"delay_poundage":0.2},{"id":12,"delay_seconds":40,"delay_poundage":0.25}]
         * hb_min_amount : 0.01
         * hb_min_price : 0.01
         */
        private List<DelaySecondsRateEntity> delay_seconds_rate;
        private String hb_min_amount;
        private String hb_min_price;

        public void setDelay_seconds_rate(List<DelaySecondsRateEntity> delay_seconds_rate) {
            this.delay_seconds_rate = delay_seconds_rate;
        }

        public void setHb_min_amount(String hb_min_amount) {
            this.hb_min_amount = hb_min_amount;
        }

        public void setHb_min_price(String hb_min_price) {
            this.hb_min_price = hb_min_price;
        }

        public List<DelaySecondsRateEntity> getDelay_seconds_rate() {
            return delay_seconds_rate;
        }

        public String getHb_min_amount() {
            return hb_min_amount;
        }

        public String getHb_min_price() {
            return hb_min_price;
        }

        public static class DelaySecondsRateEntity {
            /**
             * id : 13
             * delay_seconds : 5
             * delay_poundage : 0
             */
            private int id;
            private int delay_seconds;
            private int delay_poundage;

            public void setId(int id) {
                this.id = id;
            }

            public void setDelay_seconds(int delay_seconds) {
                this.delay_seconds = delay_seconds;
            }

            public void setDelay_poundage(int delay_poundage) {
                this.delay_poundage = delay_poundage;
            }

            public int getId() {
                return id;
            }

            public int getDelay_seconds() {
                return delay_seconds;
            }

            public int getDelay_poundage() {
                return delay_poundage;
            }
        }
    }
}
