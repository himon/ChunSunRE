package com.chunsun.redenvelope.entities.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.chunsun.redenvelope.entities.BaseEntity;
import com.chunsun.redenvelope.ui.base.SelectListBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class DistrictEntity extends BaseEntity{

    private List<AreaEntity> area;

    public void setArea(List<AreaEntity> area) {
        this.area = area;
    }

    public List<AreaEntity> getArea() {
        return area;
    }

    public static class AreaEntity extends SelectListBase {
        /**
         * p : 北京市
         * cc : [{"c":"市辖区","ct":[{"n":"东城区"},{"n":"西城区"},{"n":"朝阳区"},{"n":"丰台区"},{"n":"石景山区"},{"n":"海淀区"},{"n":"门头沟区"},{"n":"房山区"},{"n":"通州区"},{"n":"顺义区"},{"n":"昌平区"},{"n":"大兴区"},{"n":"怀柔区"},{"n":"平谷区"}]},{"c":"市辖县","ct":[{"n":"密云县"},{"n":"延庆县"}]}]
         */
        private String p;
        private ArrayList<CcEntity> cc;

        public void setP(String p) {
            this.p = p;
        }

        public void setCc(ArrayList<CcEntity> cc) {
            this.cc = cc;
        }

        public String getP() {
            return p;
        }

        public ArrayList<CcEntity> getCc() {
            return cc;
        }

        public static class CcEntity extends SelectListBase {
            /**
             * c : 市辖区
             * ct : [{"n":"东城区"},{"n":"西城区"},{"n":"朝阳区"},{"n":"丰台区"},{"n":"石景山区"},{"n":"海淀区"},{"n":"门头沟区"},{"n":"房山区"},{"n":"通州区"},{"n":"顺义区"},{"n":"昌平区"},{"n":"大兴区"},{"n":"怀柔区"},{"n":"平谷区"}]
             */
            private String c;
            private List<CtEntity> ct;

            public void setC(String c) {
                this.c = c;
            }

            public void setCt(List<CtEntity> ct) {
                this.ct = ct;
            }

            public String getC() {
                return c;
            }

            public List<CtEntity> getCt() {
                return ct;
            }

            public static class CtEntity implements Parcelable {
                /**
                 * n : 东城区
                 */
                private String n;

                public void setN(String n) {
                    this.n = n;
                }

                public String getN() {
                    return n;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.n);
                }

                public CtEntity() {
                }

                protected CtEntity(Parcel in) {
                    this.n = in.readString();
                }

                public static final Creator<CtEntity> CREATOR = new Creator<CtEntity>() {
                    public CtEntity createFromParcel(Parcel source) {
                        return new CtEntity(source);
                    }

                    public CtEntity[] newArray(int size) {
                        return new CtEntity[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeString(this.c);
                dest.writeTypedList(ct);
            }

            public CcEntity() {
            }

            protected CcEntity(Parcel in) {
                super(in);
                this.c = in.readString();
                this.ct = in.createTypedArrayList(CtEntity.CREATOR);
            }

            public static final Creator<CcEntity> CREATOR = new Creator<CcEntity>() {
                public CcEntity createFromParcel(Parcel source) {
                    return new CcEntity(source);
                }

                public CcEntity[] newArray(int size) {
                    return new CcEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(this.p);
            dest.writeTypedList(cc);
        }

        public AreaEntity() {
        }

        protected AreaEntity(Parcel in) {
            super(in);
            this.p = in.readString();
            this.cc = in.createTypedArrayList(CcEntity.CREATOR);
        }

        public static final Creator<AreaEntity> CREATOR = new Creator<AreaEntity>() {
            public AreaEntity createFromParcel(Parcel source) {
                return new AreaEntity(source);
            }

            public AreaEntity[] newArray(int size) {
                return new AreaEntity[size];
            }
        };
    }
}
