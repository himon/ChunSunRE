package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

/**
 * Created by Administrator on 2015/8/21.
 */
public class BalanceRechargeEntity extends BaseEntity {

    /**
     * result : {"amount":"10","order_no":"R15082117241252"}
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
         * amount : 10
         * order_no : R15082117241252
         */
        private String amount;
        private String order_no;

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getAmount() {
            return amount;
        }

        public String getOrder_no() {
            return order_no;
        }
    }
}
