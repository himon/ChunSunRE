package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/21 15:52
 * @des 用户未读消息数量
 */
public class UserNoReadCountEntity extends BaseEntity{

    /**
     * result : [{"NoReadCount":"3","Type":"-1","TypeName":"综合"},{"NoReadCount":"3","Type":"2","TypeName":"圈子"}]
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
         * NoReadCount : 3
         * Type : -1
         * TypeName : 综合
         */
        private int NoReadCount;
        private String Type;
        private String TypeName;

        public void setNoReadCount(int NoReadCount) {
            this.NoReadCount = NoReadCount;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public int getNoReadCount() {
            return NoReadCount;
        }

        public String getType() {
            return Type;
        }

        public String getTypeName() {
            return TypeName;
        }
    }
}
