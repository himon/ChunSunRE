package com.chunsun.redenvelope.model.entity.json;

import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CreateAdResultEntity extends BaseEntity {

    /**
     * result : {"total_amount":"61.20","id":"2151","order_no":"HB15090815565846"}
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
         * total_amount : 61.20
         * id : 2151
         * order_no : HB15090815565846
         */
        private String total_amount;
        private String id;
        private String order_no;

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public String getId() {
            return id;
        }

        public String getOrder_no() {
            return order_no;
        }
    }
}
