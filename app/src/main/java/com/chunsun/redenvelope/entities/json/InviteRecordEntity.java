package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/22.
 */
public class InviteRecordEntity extends BaseEntity {


    /**
     * result : {"amount":"2987.40","zhuli":[{"nick_name":"匿名1","id":6808,"remark":"好友(15500000001)助力成功","value":"+3.00"},{"nick_name":"匿名2","id":6809,"remark":"好友(15500000002)助力成功","value":"+3.00"}],"ticheng":[{"nick_name":"匿名3","id":6810,"remark":"好友(15500000003)助力成功","value":"+3.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+29.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+290.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+145.00"},{"nick_name":"匿名4","id":6811,"remark":"好友(15500000004)助力成功","value":"+3.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+145.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+29.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+2320.00"},{"nick_name":"匿名","id":6813,"remark":"代理用户发广告奖励","value":"+17.40"}]}
     */
    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity implements Parcelable {
        /**
         * amount : 2987.40
         * zhuli : [{"nick_name":"匿名1","id":6808,"remark":"好友(15500000001)助力成功","value":"+3.00"},{"nick_name":"匿名2","id":6809,"remark":"好友(15500000002)助力成功","value":"+3.00"}]
         * ticheng : [{"nick_name":"匿名3","id":6810,"remark":"好友(15500000003)助力成功","value":"+3.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+29.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+290.00"},{"nick_name":"匿名3","id":6810,"remark":"代理用户发广告奖励","value":"+145.00"},{"nick_name":"匿名4","id":6811,"remark":"好友(15500000004)助力成功","value":"+3.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+145.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+29.00"},{"nick_name":"匿名5","id":6812,"remark":"代理用户发广告奖励","value":"+2320.00"},{"nick_name":"匿名","id":6813,"remark":"代理用户发广告奖励","value":"+17.40"}]
         */
        private String amount;
        private List<ZhuliEntity> zhuli;
        private List<TichengEntity> ticheng;

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setZhuli(List<ZhuliEntity> zhuli) {
            this.zhuli = zhuli;
        }

        public void setTicheng(List<TichengEntity> ticheng) {
            this.ticheng = ticheng;
        }

        public String getAmount() {
            return amount;
        }

        public List<ZhuliEntity> getZhuli() {
            return zhuli;
        }

        public List<TichengEntity> getTicheng() {
            return ticheng;
        }

        public static class BaseEntity implements Parcelable {

            /**
             * nick_name : 匿名1
             * id : 6808
             * remark : 好友(15500000001)助力成功
             * value : +3.00
             */
            private String nick_name;
            private int id;
            private String remark;
            private String value;
            private String price;
            private int type = 0;

            public static final Creator<BaseEntity> CREATOR = new Creator<BaseEntity>() {
                @Override
                public BaseEntity createFromParcel(Parcel in) {
                    return new BaseEntity(in);
                }

                @Override
                public BaseEntity[] newArray(int size) {
                    return new BaseEntity[size];
                }
            };

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.nick_name);
                dest.writeInt(this.id);
                dest.writeString(this.remark);
                dest.writeString(this.value);
                dest.writeString(this.price);
                dest.writeInt(this.type);
            }

            public BaseEntity() {
            }

            protected BaseEntity(Parcel in) {
                this.nick_name = in.readString();
                this.id = in.readInt();
                this.remark = in.readString();
                this.value = in.readString();
                this.price = in.readString();
                this.type = in.readInt();
            }

        }

        public static class ZhuliEntity extends BaseEntity {

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
            }

            public ZhuliEntity() {
            }

            protected ZhuliEntity(Parcel in) {
                super(in);
            }

            public static final Creator<ZhuliEntity> CREATOR = new Creator<ZhuliEntity>() {
                public ZhuliEntity createFromParcel(Parcel source) {
                    return new ZhuliEntity(source);
                }

                public ZhuliEntity[] newArray(int size) {
                    return new ZhuliEntity[size];
                }
            };
        }

        public static class TichengEntity extends BaseEntity {

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
            }

            public TichengEntity() {
            }

            protected TichengEntity(Parcel in) {
                super(in);
            }

            public static final Creator<TichengEntity> CREATOR = new Creator<TichengEntity>() {
                public TichengEntity createFromParcel(Parcel source) {
                    return new TichengEntity(source);
                }

                public TichengEntity[] newArray(int size) {
                    return new TichengEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.amount);
            dest.writeTypedList(zhuli);
            dest.writeTypedList(ticheng);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.amount = in.readString();
            this.zhuli = in.createTypedArrayList(ZhuliEntity.CREATOR);
            this.ticheng = in.createTypedArrayList(TichengEntity.CREATOR);
        }

        public static final Parcelable.Creator<ResultEntity> CREATOR = new Parcelable.Creator<ResultEntity>() {
            public ResultEntity createFromParcel(Parcel source) {
                return new ResultEntity(source);
            }

            public ResultEntity[] newArray(int size) {
                return new ResultEntity[size];
            }
        };
    }
}
