package com.ffxl.cloud.model.base;

import java.io.Serializable;
import java.util.Date;

public class BaseSgLawComment implements Serializable {
    private String id;

    private String topicId;

    private String topicType;

    private String content;

    private String fromUserType;

    private String fromUserId;

    private String replyContent;

    private String replyUserType;

    private String replyUserId;

    private Date createDate;

    private String state;

    private Boolean read;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFromUserType() {
        return fromUserType;
    }

    public void setFromUserType(String fromUserType) {
        this.fromUserType = fromUserType == null ? null : fromUserType.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public String getReplyUserType() {
        return replyUserType;
    }

    public void setReplyUserType(String replyUserType) {
        this.replyUserType = replyUserType == null ? null : replyUserType.trim();
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId == null ? null : replyUserId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseSgLawComment other = (BaseSgLawComment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicType() == null ? other.getTopicType() == null : this.getTopicType().equals(other.getTopicType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getFromUserType() == null ? other.getFromUserType() == null : this.getFromUserType().equals(other.getFromUserType()))
            && (this.getFromUserId() == null ? other.getFromUserId() == null : this.getFromUserId().equals(other.getFromUserId()))
            && (this.getReplyContent() == null ? other.getReplyContent() == null : this.getReplyContent().equals(other.getReplyContent()))
            && (this.getReplyUserType() == null ? other.getReplyUserType() == null : this.getReplyUserType().equals(other.getReplyUserType()))
            && (this.getReplyUserId() == null ? other.getReplyUserId() == null : this.getReplyUserId().equals(other.getReplyUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRead() == null ? other.getRead() == null : this.getRead().equals(other.getRead()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicType() == null) ? 0 : getTopicType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getFromUserType() == null) ? 0 : getFromUserType().hashCode());
        result = prime * result + ((getFromUserId() == null) ? 0 : getFromUserId().hashCode());
        result = prime * result + ((getReplyContent() == null) ? 0 : getReplyContent().hashCode());
        result = prime * result + ((getReplyUserType() == null) ? 0 : getReplyUserType().hashCode());
        result = prime * result + ((getReplyUserId() == null) ? 0 : getReplyUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRead() == null) ? 0 : getRead().hashCode());
        return result;
    }
}