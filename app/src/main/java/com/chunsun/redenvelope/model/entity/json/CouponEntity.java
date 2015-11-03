package com.chunsun.redenvelope.model.entity.json;

import com.chunsun.redenvelope.model.entity.BaseEntity;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/10/23 9:51
 * @des 券
 */
public class CouponEntity extends BaseEntity {


    /**
     * result : {"SellerName":"恭喜发财111","HbId":"9601","UserName":"恭喜发财111","UserId":"0","Code":"93189230","Title":"dfsd","AddUserId":"3110","HbAddTime":"2015/10/22 11:59:40","Status":"1","ImgUrl":"/upload/201510/22/201510221159402835.jpeg,/upload/201510/22/201510221159402955.jpeg","IsValid":"True","CoverImgUrl":"/upload/201510/22/201510221159402505.jpeg","Id":"4","EndTime":"2015/11/22 0:00:00","StartTime":"2015/9/22 0:00:00","Content":"dfsdfs","AddTime":"2015/10/22 16:15:04"}
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
         * SellerName : 恭喜发财111
         * HbId : 9601
         * UserName : 恭喜发财111
         * UserId : 0
         * Code : 93189230
         * Title : dfsd
         * AddUserId : 3110
         * HbAddTime : 2015/10/22 11:59:40
         * Status : 1
         * ImgUrl : /upload/201510/22/201510221159402835.jpeg,/upload/201510/22/201510221159402955.jpeg
         * IsValid : True
         * CoverImgUrl : /upload/201510/22/201510221159402505.jpeg
         * Id : 4
         * EndTime : 2015/11/22 0:00:00
         * StartTime : 2015/9/22 0:00:00
         * Content : dfsdfs
         * AddTime : 2015/10/22 16:15:04
         */
        private String SellerName;
        private String HbId;
        private String UserName;
        private String UserId;
        private String Code;
        private String Title;
        private String AddUserId;
        private String HbAddTime;
        private String Status;
        private String ImgUrl;
        private String IsValid;
        private String CoverImgUrl;
        private String Id;
        private String EndTime;
        private String StartTime;
        private String Content;
        private String AddTime;

        public void setSellerName(String SellerName) {
            this.SellerName = SellerName;
        }

        public void setHbId(String HbId) {
            this.HbId = HbId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }

        public void setHbAddTime(String HbAddTime) {
            this.HbAddTime = HbAddTime;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public void setIsValid(String IsValid) {
            this.IsValid = IsValid;
        }

        public void setCoverImgUrl(String CoverImgUrl) {
            this.CoverImgUrl = CoverImgUrl;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getSellerName() {
            return SellerName;
        }

        public String getHbId() {
            return HbId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getUserId() {
            return UserId;
        }

        public String getCode() {
            return Code;
        }

        public String getTitle() {
            return Title;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public String getHbAddTime() {
            return HbAddTime;
        }

        public String getStatus() {
            return Status;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public String getIsValid() {
            return IsValid;
        }

        public String getCoverImgUrl() {
            return CoverImgUrl;
        }

        public String getId() {
            return Id;
        }

        public String getEndTime() {
            return EndTime;
        }

        public String getStartTime() {
            return StartTime;
        }

        public String getContent() {
            return Content;
        }

        public String getAddTime() {
            return AddTime;
        }
    }
}
