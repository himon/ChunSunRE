package com.chunsun.redenvelope.model.entity.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.model.entity.BaseEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/8 12:11
 * @des 转发类套餐实体
 */
public class RepeatMealEntity extends BaseEntity{


    /**
     * result : [{"id":2,"price":0.03,"number_of_copies":3000},{"id":3,"price":0.05,"number_of_copies":3000},{"id":4,"price":0.1,"number_of_copies":3000},{"id":9,"price":0.2,"number_of_copies":3000}]
     */
    private List<ResultEntity> result;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity implements Parcelable {
        /**
         * id : 2
         * price : 0.03
         * number_of_copies : 3000
         */
        private int id;
        private double price;
        private int number_of_copies;
        private double total;

        public void setId(int id) {
            this.id = id;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setNumber_of_copies(int number_of_copies) {
            this.number_of_copies = number_of_copies;
        }

        public int getId() {
            return id;
        }

        public double getPrice() {
            return price;
        }

        public int getNumber_of_copies() {
            return number_of_copies;
        }

        public double getTotal() {
            return price * number_of_copies;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeDouble(this.price);
            dest.writeInt(this.number_of_copies);
            dest.writeDouble(this.total);
        }

        public ResultEntity() {
        }

        protected ResultEntity(Parcel in) {
            this.id = in.readInt();
            this.price = in.readDouble();
            this.number_of_copies = in.readInt();
            this.total = in.readDouble();
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
