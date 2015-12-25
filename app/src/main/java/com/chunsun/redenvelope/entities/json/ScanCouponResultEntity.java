package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.ArrayList;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2015/11/6 9:37
 * @des 扫春笋券返回结果集
 */
public class ScanCouponResultEntity extends BaseEntity {

    /**
     * result : {"RowNum":"0","HbId":"9744","StartTimeStr":"2015年11月04日","UserName":"lingery","Code":"12508592","HbAddTime":"2015/11/4 17:25:55","ImgUrl":"/upload/201511/04/201511041725551274.jpeg","UpdateCode":"","IsValid":"True","CoverImgUrl":"/upload/201511/04/201511041725550734.jpeg","StartTime":"2015/11/4 0:00:00","IsV":"False","Content":"了了了了了","HeadImg":"/upload/201509/24/201509241749208741.jpeg","EndTimeStr":"2016年02月04日","SellerName":"恭喜发财111","AddTimeStr":"2015年11月05日","UserId":"0","HbAddTimeStr":"2015-11-04 17:25:55","Title":"啦啦啦","AddUserId":"3110","Status":"1","EndTime":"2016/2/4 23:59:59","Id":"152","AddTime":"2015/11/5 18:02:47"}
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
         * RowNum : 0
         * HbId : 9744
         * StartTimeStr : 2015年11月04日
         * UserName : lingery
         * Code : 12508592
         * HbAddTime : 2015/11/4 17:25:55
         * ImgUrl : /upload/201511/04/201511041725551274.jpeg
         * UpdateCode :
         * IsValid : True
         * CoverImgUrl : /upload/201511/04/201511041725550734.jpeg
         * StartTime : 2015/11/4 0:00:00
         * IsV : False
         * Content : 了了了了了
         * HeadImg : /upload/201509/24/201509241749208741.jpeg
         * EndTimeStr : 2016年02月04日
         * SellerName : 恭喜发财111
         * AddTimeStr : 2015年11月05日
         * UserId : 0
         * HbAddTimeStr : 2015-11-04 17:25:55
         * Title : 啦啦啦
         * AddUserId : 3110
         * Status : 1
         * EndTime : 2016/2/4 23:59:59
         * Id : 152
         * AddTime : 2015/11/5 18:02:47
         */
        private String RowNum;
        private String HbId;
        private String StartTimeStr;
        private String UserName;
        private String Code;
        private String HbAddTime;
        private String ImgUrl;
        private String UpdateCode;
        private String IsValid;
        private String CoverImgUrl;
        private String StartTime;
        private boolean IsV;
        private String Content;
        private String HeadImg;
        private String EndTimeStr;
        private String SellerName;
        private String AddTimeStr;
        private String UserId;
        private String HbAddTimeStr;
        private String Title;
        private String AddUserId;
        private String Status;
        private String EndTime;
        private String Id;
        private String AddTime;
        private ArrayList<String> urls = new ArrayList<>();

        public void setRowNum(String RowNum) {
            this.RowNum = RowNum;
        }

        public void setHbId(String HbId) {
            this.HbId = HbId;
        }

        public void setStartTimeStr(String StartTimeStr) {
            this.StartTimeStr = StartTimeStr;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public void setHbAddTime(String HbAddTime) {
            this.HbAddTime = HbAddTime;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public void setUpdateCode(String UpdateCode) {
            this.UpdateCode = UpdateCode;
        }

        public void setIsValid(String IsValid) {
            this.IsValid = IsValid;
        }

        public void setCoverImgUrl(String CoverImgUrl) {
            this.CoverImgUrl = CoverImgUrl;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }


        public void setContent(String Content) {
            this.Content = Content;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }

        public void setEndTimeStr(String EndTimeStr) {
            this.EndTimeStr = EndTimeStr;
        }

        public void setSellerName(String SellerName) {
            this.SellerName = SellerName;
        }

        public void setAddTimeStr(String AddTimeStr) {
            this.AddTimeStr = AddTimeStr;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setHbAddTimeStr(String HbAddTimeStr) {
            this.HbAddTimeStr = HbAddTimeStr;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getRowNum() {
            return RowNum;
        }

        public String getHbId() {
            return HbId;
        }

        public String getStartTimeStr() {
            return StartTimeStr;
        }

        public String getUserName() {
            return UserName;
        }

        public String getCode() {
            return Code;
        }

        public String getHbAddTime() {
            return HbAddTime;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public String getUpdateCode() {
            return UpdateCode;
        }

        public String getIsValid() {
            return IsValid;
        }

        public String getCoverImgUrl() {
            return CoverImgUrl;
        }

        public String getStartTime() {
            return StartTime;
        }


        public String getContent() {
            return Content;
        }

        public String getHeadImg() {
            return Constants.IMG_HOST_URL + HeadImg;
        }

        public String getEndTimeStr() {
            return EndTimeStr;
        }

        public String getSellerName() {
            return SellerName;
        }

        public String getAddTimeStr() {
            return AddTimeStr;
        }

        public String getUserId() {
            return UserId;
        }

        public String getHbAddTimeStr() {
            return HbAddTimeStr;
        }

        public String getTitle() {
            return Title;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public String getStatus() {
            return Status;
        }

        public String getEndTime() {
            return EndTime;
        }

        public String getId() {
            return Id;
        }

        public String getAddTime() {
            return AddTime;
        }

        public boolean isV() {
            return IsV;
        }

        public void setIsV(boolean isV) {
            IsV = isV;
        }

        public ArrayList<String> getUrls() {
            return urls;
        }

        public void setUrls(ArrayList<String> urls) {
            this.urls = urls;
        }
    }
}
