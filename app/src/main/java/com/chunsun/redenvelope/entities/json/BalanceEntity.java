package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalanceEntity extends BaseEntity {

    /**
     * result : {"cz_amount":"0.00","sendhb_amount":"5228.83","amount":"85636.19","cz_poundage":[{"amount":20,"id":2,"real_amount":22},{"amount":30,"id":3,"real_amount":33},{"amount":50,"id":4,"real_amount":55},{"amount":100,"id":5,"real_amount":110},{"amount":200,"id":6,"real_amount":220}],"unusable_amount":"0.00","other_amount":"-10865.40","cash_amount":"0.00","enable_unionpay":"True","cash_poundage_rate":[{"amount":2000,"id":27,"rate":0.01}],"zfb_poundage":[{"amount":20,"id":2,"poundage":2},{"amount":50,"id":3,"poundage":5}],"openhb_amount":"2.52"}
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
         * cz_amount : 0.00
         * sendhb_amount : 5228.83
         * amount : 85636.19
         * cz_poundage : [{"amount":20,"id":2,"real_amount":22},{"amount":30,"id":3,"real_amount":33},{"amount":50,"id":4,"real_amount":55},{"amount":100,"id":5,"real_amount":110},{"amount":200,"id":6,"real_amount":220}]
         * unusable_amount : 0.00
         * other_amount : -10865.40
         * cash_amount : 0.00
         * enable_unionpay : True
         * cash_poundage_rate : [{"amount":2000,"id":27,"rate":0.01}]
         * zfb_poundage : [{"amount":20,"id":2,"poundage":2},{"amount":50,"id":3,"poundage":5}]
         * openhb_amount : 2.52
         */
        /**
         * 已充值
         */
        private String cz_amount;
        /**
         * 发出红包支出
         */
        private String sendhb_amount;
        /**
         * 可用余额
         */
        private String amount;
        /**
         * 不可用余额
         */
        private String unusable_amount;
        /**
         * 其他费用
         */
        private String other_amount;
        /**
         * 已提现
         */
        private String cash_amount;

        /**
         * 累计收益
         */
        private String cumulative_gain;

        /**
         * 邀请累计收益
         */
        private String invite_cumulative_gain;

        /**
         * 转发收益
         */
        private String forward_amount;

        /**
         * 领红包收入
         */
        private String openhb_amount;

        /**
         * 奖励金额
         */
        private String reward_amount;

        /**
         * 提成收益
         */
        private String commission_amount;

        /**
         * 是否开启银联支付，用于控制，是否可以使用银联充值
         */
        private boolean enable_unionpay;
        private ArrayList<CzPoundageEntity> cz_poundage;
        private ArrayList<CashPoundageRateEntity> cash_poundage_rate;
        private ArrayList<ZfbPoundageEntity> zfb_poundage;


        public void setCz_amount(String cz_amount) {
            this.cz_amount = cz_amount;
        }

        public void setSendhb_amount(String sendhb_amount) {
            this.sendhb_amount = sendhb_amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setCz_poundage(ArrayList<CzPoundageEntity> cz_poundage) {
            this.cz_poundage = cz_poundage;
        }

        public void setUnusable_amount(String unusable_amount) {
            this.unusable_amount = unusable_amount;
        }

        public void setOther_amount(String other_amount) {
            this.other_amount = other_amount;
        }

        public void setCash_amount(String cash_amount) {
            this.cash_amount = cash_amount;
        }


        public void setCash_poundage_rate(ArrayList<CashPoundageRateEntity> cash_poundage_rate) {
            this.cash_poundage_rate = cash_poundage_rate;
        }

        public void setZfb_poundage(ArrayList<ZfbPoundageEntity> zfb_poundage) {
            this.zfb_poundage = zfb_poundage;
        }

        public void setOpenhb_amount(String openhb_amount) {
            this.openhb_amount = openhb_amount;
        }

        public String getCz_amount() {
            return cz_amount;
        }

        public String getSendhb_amount() {
            return sendhb_amount;
        }

        public String getAmount() {
            return amount;
        }

        public ArrayList<CzPoundageEntity> getCz_poundage() {
            return cz_poundage;
        }

        public String getUnusable_amount() {
            return unusable_amount;
        }

        public String getOther_amount() {
            return other_amount;
        }

        public String getCash_amount() {
            return cash_amount;
        }

        public boolean isEnable_unionpay() {
            return enable_unionpay;
        }

        public void setEnable_unionpay(boolean enable_unionpay) {
            this.enable_unionpay = enable_unionpay;
        }

        public ArrayList<CashPoundageRateEntity> getCash_poundage_rate() {
            return cash_poundage_rate;
        }

        public ArrayList<ZfbPoundageEntity> getZfb_poundage() {
            return zfb_poundage;
        }

        public String getOpenhb_amount() {
            return openhb_amount;
        }

        public String getCumulative_gain() {
            return cumulative_gain;
        }

        public void setCumulative_gain(String cumulative_gain) {
            this.cumulative_gain = cumulative_gain;
        }

        public String getInvite_cumulative_gain() {
            return invite_cumulative_gain;
        }

        public void setInvite_cumulative_gain(String invite_cumulative_gain) {
            this.invite_cumulative_gain = invite_cumulative_gain;
        }

        public String getForward_amount() {
            return forward_amount;
        }

        public void setForward_amount(String forward_amount) {
            this.forward_amount = forward_amount;
        }

        public String getCommission_amount() {
            return commission_amount;
        }

        public void setCommission_amount(String commission_amount) {
            this.commission_amount = commission_amount;
        }

        public String getReward_amount() {
            return reward_amount;
        }

        public void setReward_amount(String reward_amount) {
            this.reward_amount = reward_amount;
        }

        /**
         * 手机充值费率
         */
        public static class CzPoundageEntity implements Parcelable {
            /**
             * amount : 20
             * id : 2
             * real_amount : 22
             */
            private String amount;
            private int id;
            private String real_amount;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReal_amount() {
                return real_amount;
            }

            public void setReal_amount(String real_amount) {
                this.real_amount = real_amount;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeInt(this.id);
                dest.writeString(this.real_amount);
            }

            public CzPoundageEntity() {
            }

            protected CzPoundageEntity(Parcel in) {
                this.amount = in.readString();
                this.id = in.readInt();
                this.real_amount = in.readString();
            }

            public static final Parcelable.Creator<CzPoundageEntity> CREATOR = new Parcelable.Creator<CzPoundageEntity>() {
                public CzPoundageEntity createFromParcel(Parcel source) {
                    return new CzPoundageEntity(source);
                }

                public CzPoundageEntity[] newArray(int size) {
                    return new CzPoundageEntity[size];
                }
            };
        }

        /**
         * 银行卡提现费率
         */
        public static class CashPoundageRateEntity implements Parcelable {
            /**
             * amount : 2000
             * id : 27
             * rate : 0.01
             */
            private String amount;
            private int id;
            private String rate;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeInt(this.id);
                dest.writeString(this.rate);
            }

            public CashPoundageRateEntity() {
            }

            protected CashPoundageRateEntity(Parcel in) {
                this.amount = in.readString();
                this.id = in.readInt();
                this.rate = in.readString();
            }

            public static final Parcelable.Creator<CashPoundageRateEntity> CREATOR = new Parcelable.Creator<CashPoundageRateEntity>() {
                public CashPoundageRateEntity createFromParcel(Parcel source) {
                    return new CashPoundageRateEntity(source);
                }

                public CashPoundageRateEntity[] newArray(int size) {
                    return new CashPoundageRateEntity[size];
                }
            };
        }

        /**
         * 支付宝提现费率
         */
        public static class ZfbPoundageEntity implements Parcelable {
            /**
             * amount : 20
             * id : 2
             * poundage : 2
             */
            private String amount;
            private int id;
            /**
             * 扣除的手续费
             */
            private String poundage;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPoundage() {
                return poundage;
            }

            public void setPoundage(String poundage) {
                this.poundage = poundage;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeInt(this.id);
                dest.writeString(this.poundage);
            }

            public ZfbPoundageEntity() {
            }

            protected ZfbPoundageEntity(Parcel in) {
                this.amount = in.readString();
                this.id = in.readInt();
                this.poundage = in.readString();
            }

            public static final Parcelable.Creator<ZfbPoundageEntity> CREATOR = new Parcelable.Creator<ZfbPoundageEntity>() {
                public ZfbPoundageEntity createFromParcel(Parcel source) {
                    return new ZfbPoundageEntity(source);
                }

                public ZfbPoundageEntity[] newArray(int size) {
                    return new ZfbPoundageEntity[size];
                }
            };
        }
    }
}
