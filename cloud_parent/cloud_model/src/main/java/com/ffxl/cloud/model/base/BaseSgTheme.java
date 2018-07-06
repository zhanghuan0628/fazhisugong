package com.ffxl.cloud.model.base;

import java.io.Serializable;
import java.util.Date;

public class BaseSgTheme implements Serializable {
    private String id;

    private String title;

    private String content;

    private Integer num;

    private Date startTime;

    private Date endTime;

    private Integer subjectCounts;

    private Date createTime;

    private Integer sore;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSubjectCounts() {
        return subjectCounts;
    }

    public void setSubjectCounts(Integer subjectCounts) {
        this.subjectCounts = subjectCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSore() {
        return sore;
    }

    public void setSore(Integer sore) {
        this.sore = sore;
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
        BaseSgTheme other = (BaseSgTheme) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getSubjectCounts() == null ? other.getSubjectCounts() == null : this.getSubjectCounts().equals(other.getSubjectCounts()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSore() == null ? other.getSore() == null : this.getSore().equals(other.getSore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getSubjectCounts() == null) ? 0 : getSubjectCounts().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSore() == null) ? 0 : getSore().hashCode());
        return result;
    }
}