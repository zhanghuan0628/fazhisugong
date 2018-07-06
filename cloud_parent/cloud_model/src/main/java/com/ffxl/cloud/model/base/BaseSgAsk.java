package com.ffxl.cloud.model.base;

import java.io.Serializable;
import java.util.Date;

public class BaseSgAsk implements Serializable {
    private String id;

    private String title;

    private String content;

    private String userId;

    private Date createDate;

    private Boolean see;

    private Boolean dummy;

    private String urgent;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getSee() {
        return see;
    }

    public void setSee(Boolean see) {
        this.see = see;
    }

    public Boolean getDummy() {
        return dummy;
    }

    public void setDummy(Boolean dummy) {
        this.dummy = dummy;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent == null ? null : urgent.trim();
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
        BaseSgAsk other = (BaseSgAsk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getSee() == null ? other.getSee() == null : this.getSee().equals(other.getSee()))
            && (this.getDummy() == null ? other.getDummy() == null : this.getDummy().equals(other.getDummy()))
            && (this.getUrgent() == null ? other.getUrgent() == null : this.getUrgent().equals(other.getUrgent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getSee() == null) ? 0 : getSee().hashCode());
        result = prime * result + ((getDummy() == null) ? 0 : getDummy().hashCode());
        result = prime * result + ((getUrgent() == null) ? 0 : getUrgent().hashCode());
        return result;
    }
}