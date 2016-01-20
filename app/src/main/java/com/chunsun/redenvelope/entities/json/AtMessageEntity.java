package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/20 14:37
 * @des @消息
 */
public class AtMessageEntity extends BaseEntity {

    private List<ResultEntity> result;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * AddUser : 0
         * NoReadCount : 0
         * RowNum : 1
         * NickName : 青青
         * Type : 2
         * InteractionId : 0
         * HbId : 14570
         * IsSystem : False
         * TypeName : 圈子
         * UserId : 3110
         * City :
         * Title : 测试压缩图片
         * CommentId : 0
         * HbType : 7
         * ImgUrl : /upload/201508/24/201508241119222366.jpeg
         * IsValid : True
         * CoverImgThumb : /upload/201601/13/thumb_201601131723068520.jpeg
         * IsRead : False
         * Id : 896
         * Content : 啥事情啊？
         * AddTime : 2016/1/19 17:55:36
         */
        private String AddUser;
        private String NoReadCount;
        private String RowNum;
        private String NickName;
        private int Type;
        private String InteractionId;
        private String HbId;
        private boolean IsSystem;
        private String TypeName;
        private String UserId;
        private String City;
        private String Title;
        private String CommentId;
        private int HbType;
        //头像
        private String ImgUrl;
        private boolean IsValid;
        private String CoverImgThumb;
        private boolean IsRead;
        private String Id;
        private String Content;
        private String AddTime;

        public String getAddUser() {
            return AddUser;
        }

        public void setAddUser(String addUser) {
            AddUser = addUser;
        }

        public String getNoReadCount() {
            return NoReadCount;
        }

        public void setNoReadCount(String noReadCount) {
            NoReadCount = noReadCount;
        }

        public String getRowNum() {
            return RowNum;
        }

        public void setRowNum(String rowNum) {
            RowNum = rowNum;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String nickName) {
            NickName = nickName;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public String getInteractionId() {
            return InteractionId;
        }

        public void setInteractionId(String interactionId) {
            InteractionId = interactionId;
        }

        public String getHbId() {
            return HbId;
        }

        public void setHbId(String hbId) {
            HbId = hbId;
        }

        public boolean isSystem() {
            return IsSystem;
        }

        public void setIsSystem(boolean isSystem) {
            IsSystem = isSystem;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String typeName) {
            TypeName = typeName;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getCommentId() {
            return CommentId;
        }

        public void setCommentId(String commentId) {
            CommentId = commentId;
        }

        public int getHbType() {
            return HbType;
        }

        public void setHbType(int hbType) {
            HbType = hbType;
        }

        public String getImgUrl() {
            return Constants.IMG_HOST_URL + ImgUrl;
        }

        public void setImgUrl(String imgUrl) {
            ImgUrl = imgUrl;
        }

        public boolean isValid() {
            return IsValid;
        }

        public void setIsValid(boolean isValid) {
            IsValid = isValid;
        }

        public String getCoverImgThumb() {
            return Constants.IMG_HOST_URL + CoverImgThumb;
        }

        public void setCoverImgThumb(String coverImgThumb) {
            CoverImgThumb = coverImgThumb;
        }

        public boolean isRead() {
            return IsRead;
        }

        public void setIsRead(boolean isRead) {
            IsRead = isRead;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String addTime) {
            AddTime = addTime;
        }
    }
}
