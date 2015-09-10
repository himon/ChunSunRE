package com.chunsun.redenvelope.model.entity.json;

import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * Created by Administrator on 2015/9/8.
 */
public class AdPayAmountDetailEntity extends BaseEntity {


    /**
     * result : {"amount":"85002.39","total_amount":"61.20","id":"2157","receipt_express_fee":"0.00","poundage":"1.20","delay_amount":"0.00","payable_amount":"60.00","enable_unionpay":"False","receipt_fee":"0.00","order_no":"HB15090817543438"}
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
         * amount : 85002.39
         * total_amount : 61.20
         * id : 2157
         * receipt_express_fee : 0.00
         * poundage : 1.20
         * delay_amount : 0.00
         * payable_amount : 60.00
         * enable_unionpay : False
         * receipt_fee : 0.00
         * order_no : HB15090817543438
         */
        /**
         * 账户余额
         */
        private String amount;
        /**
         * 总金额
         */
        private String total_amount;
        private String id;
        /**
         * 发票邮寄费
         */
        private String receipt_express_fee;
        /**
         * 服务费
         */
        private String poundage;
        /**
         * 延时显示费
         */
        private String delay_amount;
        /**
         * 广告金额
         */
        private String payable_amount;
        /**
         * 是否支持银联支付
         */
        private boolean enable_unionpay;
        /**
         * 税费
         */
        private String receipt_fee;
        private String order_no;

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setReceipt_express_fee(String receipt_express_fee) {
            this.receipt_express_fee = receipt_express_fee;
        }

        public void setPoundage(String poundage) {
            this.poundage = poundage;
        }

        public void setDelay_amount(String delay_amount) {
            this.delay_amount = delay_amount;
        }

        public void setPayable_amount(String payable_amount) {
            this.payable_amount = payable_amount;
        }


        public void setEnable_unionpay(boolean enable_unionpay) {
            this.enable_unionpay = enable_unionpay;
        }

        public void setReceipt_fee(String receipt_fee) {
            this.receipt_fee = receipt_fee;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getAmount() {
            return amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public String getId() {
            return id;
        }

        public String getReceipt_express_fee() {
            return receipt_express_fee;
        }

        public String getPoundage() {
            return poundage;
        }

        public String getDelay_amount() {
            return delay_amount;
        }

        public String getPayable_amount() {
            return payable_amount;
        }

        public boolean isEnable_unionpay() {
            return enable_unionpay;
        }

        public String getReceipt_fee() {
            return receipt_fee;
        }

        public String getOrder_no() {
            return order_no;
        }
    }
}
