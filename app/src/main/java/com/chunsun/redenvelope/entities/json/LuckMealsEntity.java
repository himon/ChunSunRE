package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/18 13:32
 * @des 拼手气红包套餐信息
 */
public class LuckMealsEntity extends BaseEntity {


    /**
     * result : [{"PriceStr":"20","ForwardTimes":"50","Poundage":"0.0000000","DeleteTime":"0001/1/1 0:00:00","PoundageStr":"0","IsValid":"True","Price":"20.0000000","Id":"12","ReadTimes":"500","PotentialReadTimes":"6000","AddTime":"2016/1/9 16:14:53"},{"PriceStr":"66","ForwardTimes":"200","Poundage":"10.0000000","DeleteTime":"0001/1/1 0:00:00","PoundageStr":"10","IsValid":"True","Price":"66.0000000","Id":"7","ReadTimes":"1650","PotentialReadTimes":"30000","AddTime":"2016/1/5 13:02:02"},{"PriceStr":"108","ForwardTimes":"500","Poundage":"15.0000000","DeleteTime":"0001/1/1 0:00:00","PoundageStr":"15","IsValid":"True","Price":"108.0000000","Id":"8","ReadTimes":"2700","PotentialReadTimes":"100000","AddTime":"2016/1/5 13:02:35"},{"PriceStr":"500","ForwardTimes":"2500","Poundage":"20.0000000","DeleteTime":"0001/1/1 0:00:00","PoundageStr":"20","IsValid":"True","Price":"500.0000000","Id":"9","ReadTimes":"12500","PotentialReadTimes":"800000","AddTime":"2016/1/5 13:03:07"}]
     */
    private List<ResultEntity> result;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * PriceStr : 20
         * ForwardTimes : 50
         * Poundage : 0.0000000
         * DeleteTime : 0001/1/1 0:00:00
         * PoundageStr : 0
         * IsValid : True
         * Price : 20.0000000
         * Id : 12
         * ReadTimes : 500
         * PotentialReadTimes : 6000
         * AddTime : 2016/1/9 16:14:53
         */
        private String PriceStr;
        private int ForwardTimes;
        private String Poundage;
        private String DeleteTime;
        private String PoundageStr;
        private boolean IsValid;
        private String Price;
        private String Id;
        private int ReadTimes;
        private int PotentialReadTimes;
        private String AddTime;

        public String getPriceStr() {
            return PriceStr;
        }

        public void setPriceStr(String priceStr) {
            PriceStr = priceStr;
        }

        public int getForwardTimes() {
            return ForwardTimes;
        }

        public void setForwardTimes(int forwardTimes) {
            ForwardTimes = forwardTimes;
        }

        public String getPoundage() {
            return Poundage;
        }

        public void setPoundage(String poundage) {
            Poundage = poundage;
        }

        public String getDeleteTime() {
            return DeleteTime;
        }

        public void setDeleteTime(String deleteTime) {
            DeleteTime = deleteTime;
        }

        public String getPoundageStr() {
            return PoundageStr;
        }

        public void setPoundageStr(String poundageStr) {
            PoundageStr = poundageStr;
        }

        public boolean isValid() {
            return IsValid;
        }

        public void setIsValid(boolean isValid) {
            IsValid = isValid;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public int getReadTimes() {
            return ReadTimes;
        }

        public void setReadTimes(int readTimes) {
            ReadTimes = readTimes;
        }

        public int getPotentialReadTimes() {
            return PotentialReadTimes;
        }

        public void setPotentialReadTimes(int potentialReadTimes) {
            PotentialReadTimes = potentialReadTimes;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String addTime) {
            AddTime = addTime;
        }
    }
}
